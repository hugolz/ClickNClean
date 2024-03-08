package tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javafx.util.Pair;
import model.Activity;
import model.ActivityType;
import model.Address;
import model.Admin;
import model.Cleaner;
import model.CleanerExperience;
import model.Mission;
import model.MissionStatus;
import model.Owner;
import model.OwnerMotivation;
import model.Property;
import model.Review;
import model.User;
import model.UserStatus;
import model.planning.Planning;
import model.planning.TimeSlot;

public class Db {
	String strClassName;
	String dbName;
	String login;
	String password;
	String strUrl;
	Statement stRead;
	Connection conn;

	public Db() {
		this.strClassName = "com.mysql.cj.jdbc.Driver";
		this.dbName = "click_n_clean";

		this.login = "root";
		this.password = "";

		this.strUrl = "jdbc:mysql://localhost:3306/" + dbName
		              + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Paris";

		try {
			Class.forName(strClassName);
			this.conn = DriverManager.getConnection(strUrl, login, password);
			this.stRead = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not loaded !");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("[ERROR] Db could not connect to the database due to: " + e.getMessage());
		}
	}

	public Statement getStRead() {
		return stRead;
	}

	public void setStRead(Statement stRead) {
		this.stRead = stRead;
	}

	public ArrayList<Cleaner> getCleanersInRange(Address addr) {
		ArrayList<Cleaner> out = new ArrayList<>();
		String query = "SELECT * FROM cleaner JOIN ON user USING cleaner.id_cleaner = user.id_user;";

		try {
			ResultSet rSet = this.stRead.executeQuery(query);
			while (rSet.next()) {
				int cleaner_id = rSet.getInt("id_cleaner");

				int cleaner_range = rSet.getInt("km_range");

				Address cleaner_addr = new Address(
				    rSet.getString("address_display"),
				    rSet.getInt("latitude"),
				    rSet.getInt("longitude"));

				double distance = addr.calculateDistance(cleaner_addr);

				if (distance * 1000 /* m to km */ > cleaner_range) {
					System.out.println("Skipped cleaner with id: '" + cleaner_id + "', not in range (" + distance + "> "
					                   + cleaner_range + ").");
					continue;
				}
				Planning planning = this.DAOReadPlanning(cleaner_id);

				Cleaner cleaner = new Cleaner(

				    rSet.getInt("id_cleaner"),
				    new Address(
				        rSet.getString("address_display"),
				        rSet.getDouble("latitude"),
				        rSet.getDouble("longitude")),
				    rSet.getInt("km_range"),
				    rSet.getInt("hourly_rate"),
				    rSet.getString("biography"),
				    rSet.getString("photo_identity"),
				    rSet.getString("photo_profile"),
				    rSet.getString("photo_live"),
				    rSet.getString("motivation"),
				    CleanerExperience.fromInt(rSet.getInt("experience")),
				    rSet.getBoolean("confirmed"),
				    rSet.getString("name"),
				    rSet.getString("password"),
				    rSet.getString("surname"),
				    rSet.getString("email"),
				    rSet.getString("phone_number"),
				    rSet.getDate("birth_date").toLocalDate(),
				    rSet.getBoolean("suspended"),
				    new ArrayList<Integer>(), // reviews,
				    planning);

				out.add(cleaner);
			}
			rSet.close();
		} catch (Exception e) {
			System.out.println("Could not query cleaners in range " + addr + "due to: " + addr);
		}
		return out;
	}

	public Pair<Integer, UserStatus> DAOReadUser(String login, String pwd)
	throws InterruptedException, ExecutionException, Exception {
		String pwd_hash = User.sha3256Hashing(pwd);
		String query = "SELECT * FROM user where email  = '" + login + "' AND password = '" + pwd_hash + "';";

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			return new Pair<Integer, UserStatus>(
			           rSet.getInt("id_user"),
			           UserStatus.fromInt(rSet.getInt("status")));
		}
		rSet.close();

		throw new Exception("Could not find a user with the given address / pasword");
	}

	public Cleaner DAOReadCleaner(int id_user) throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM cleaner JOIN user ON (cleaner.id_cleaner = user.id_user) WHERE id_cleaner = "
		               + id_user;

		// Ik that this can throw, but there is no point to catch it as
		// we can't continue this execution anyway.
		// "We could use an empty planning if nothing is read"
		// - There is no point, DAOReadPlanning already does that.
		Planning planning = this.DAOReadPlanning(id_user);

		// TODO: this
		// ArrayList<Integer> reviews_ids = // we can't read cleaner review yet !

		ResultSet rSet = this.stRead.executeQuery(query);
		int cleanerIdTest = 0;
		while (rSet.next()) {
			if (UserStatus.fromInt(rSet.getInt("status")) != UserStatus.CLEANER) {
				throw new Exception("Found a user with given id, but it's not a cleaner;");
			}
			cleanerIdTest = rSet.getInt("id_cleaner");

			Cleaner cleaner = new Cleaner(
			    rSet.getInt("id_cleaner"),
			    new Address(
			        rSet.getString("address_display"),
			        rSet.getDouble("latitude"),
			        rSet.getDouble("longitude")),
			    rSet.getInt("km_range"),
			    rSet.getInt("hourly_rate"),
			    rSet.getString("biography"),
			    rSet.getString("photo_identity"),
			    rSet.getString("photo_profile"),
			    rSet.getString("photo_live"),
			    rSet.getString("motivation"),
			    CleanerExperience.fromInt(rSet.getInt("experience")),
			    rSet.getBoolean("confirmed"),
			    rSet.getString("name"),
			    rSet.getString("password"),
			    rSet.getString("surname"),
			    rSet.getString("email"),
			    rSet.getString("phone_number"),
			    rSet.getDate("birth_date").toLocalDate(),
			    rSet.getBoolean("suspended"),
			    new ArrayList<Integer>(), // reviews,
			    planning);

			rSet.close();
			return cleaner;
		}
		rSet.close();

		throw new Exception("Could not find any cleaner with the given id " + cleanerIdTest);
	}

	public Owner DAOReadOwner(int id_user) throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM owner JOIN user ON (owner.id_owner = user.id_user) WHERE owner.id_owner = "
		               + id_user;

		// String query = "SELECT * FROM owner JOIN user ON(owner.id_owner =
		// user.id_user) JOIN property ON(owner.id_owner = property.id_owner) WHERE
		// owner.id_owner = " + id_user;

		ArrayList<Integer> ownerReviews = this.DAOReadOwnerReviewsIds(id_user);
		ArrayList<Integer> listproperty = this.DAOReadOwnerPropertiesIds(id_user);

		ResultSet rSet = this.stRead.executeQuery(query);
		if (rSet.next()) {
			if (UserStatus.fromInt(rSet.getInt("status")) != UserStatus.OWNER) {
				throw new Exception("Found a user with given id, but it's not an owner;");
			}

			int id_owner = rSet.getInt("id_owner");

			Owner owner = new Owner(
			    id_owner,
			    OwnerMotivation.fromInt(rSet.getInt("type_service")),
			    ownerReviews,
			    listproperty,
			    rSet.getString("name"),
			    rSet.getString("password"),
			    rSet.getString("surname"),
			    rSet.getString("email"),
			    rSet.getString("phone_number"),
			    rSet.getDate("birth_date").toLocalDate(),
			    rSet.getBoolean("suspended"));
			rSet.close();
			return owner;
		}
		rSet.close();

		throw new Exception("Could not find any owner with the given id");
	}

	public ArrayList<Review> DAOReadOwnerReviews(int id_owner)
	throws InterruptedException, ExecutionException, Exception {
		ArrayList<Review> reviews = new ArrayList<Review>();
		String query = "SELECT * FROM review JOIN owner ON (review.id_user_receiving = owner.id_owner) WHERE owner.id_owner = "
		               + id_owner;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			Review review = new Review(
			    rSet.getInt("id_review"),
			    rSet.getString("content"),
			    rSet.getInt("grade"),
			    rSet.getInt("id_user"),
			    rSet.getInt("id_mission"));
			reviews.add(review);
		}

		return reviews;
	}

	public ArrayList<Integer> DAOReadOwnerReviewsIds(int id_owner)
	throws InterruptedException, ExecutionException, Exception {
		ArrayList<Integer> reviews = new ArrayList<Integer>();
		String query = "SELECT * FROM review JOIN owner ON (review.id_user = owner.id_owner) WHERE owner.id_owner = "
		               + id_owner;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			reviews.add(rSet.getInt("id_review"));
		}
		rSet.close();

		return reviews;
	}

	public ArrayList<Property> DAOReadOwnerProperties(int id_owner)
	throws InterruptedException, ExecutionException, Exception {
		ArrayList<Property> properties = new ArrayList<Property>();
		String query = "SELECT * FROM property JOIN owner ON (property.id_owner = owner.id_owner) WHERE owner.id_owner = "
		               + id_owner;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			Property property = new Property(
			    rSet.getInt("id_property"),
			    new Address(
			        rSet.getString("address_display"),
			        rSet.getDouble("latitude"),
			        rSet.getDouble("longitude")),
			    rSet.getInt("surface"),
			    rSet.getInt("id_owner"),
			    rSet.getString("acces_code"),
			    rSet.getString("keybox_code"),
			    rSet.getString("special_instruction"));

			properties.add(property);
		}
		rSet.close();
		return properties;
	}

	public ArrayList<Integer> DAOReadOwnerPropertiesIds(int id_owner)
	throws InterruptedException, ExecutionException, Exception {
		ArrayList<Integer> properties = new ArrayList<Integer>();
		String query = "SELECT * FROM property JOIN owner ON (property.id_owner = owner.id_owner) WHERE owner.id_owner = "
		               + id_owner;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {

			properties.add(rSet.getInt("id_property"));
		}
		rSet.close();

		return properties;
	}

	public ArrayList<Activity> DAOReadActivities(int targetId) throws SQLException, Exception {
		ArrayList<Activity> out = new ArrayList<Activity>();

		String query = "SELECT * FROM activity where id_target  = '" + targetId + "';";

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			out.add(
			    new Activity(
			        rSet.getInt("id_activity"),
			        ActivityType.fromInt(rSet.getInt("type")),
			        rSet.getBoolean("read"),
			        rSet.getInt("id_owner"),
			        rSet.getInt("id_cleaner"),
			        rSet.getInt("id_mission"),
			        rSet.getInt("id_dispute"),
			        rSet.getInt("id_admin"),
			        rSet.getInt("id_target")
			    )
			);
		}
		rSet.close();

		return out;

	}

	public Admin DAOReadAdmin(int id_user) throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM admin JOIN user ON (admin.id_admin = user.id_user) WHERE id_admin = " + id_user;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			if (UserStatus.fromInt(rSet.getInt("status")) != UserStatus.ADMIN) {
				throw new Exception("Found a user with given email & password, but it's not a admin;");
			}
			Admin admin = new Admin(
			    rSet.getInt("id_admin"),
			    rSet.getString("name"),
			    rSet.getString("password"),
			    rSet.getString("surname"),
			    rSet.getString("email"),
			    rSet.getString("phone_number"),
			    rSet.getDate("birth_date").toLocalDate(),
			    rSet.getDate("account_date").toLocalDate(),
			    rSet.getBoolean("suspended"),
			    UserStatus.fromInt(rSet.getInt("status")));

			return admin;
		}

		throw new Exception("Could not find a user with the given address / pasword");
	}

	public void read() {
		try {
			String strQuery = "SELECT * FROM user;";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				System.out.print(
				    "Id[" + rsReader.getInt(1) + "]\n"
				    + "Name[" + rsReader.getString(2) + "]\n"
				    + "Pwd[" + rsReader.getString(3) + "]\n"
				    + "Surname[" + rsReader.getString(4) + "]\n"
				    + "Email[" + rsReader.getString(5) + "]\n"
				    + "Phone[" + rsReader.getString(6) + "]\n"
				    + "Birth date[" + rsReader.getString(6) + "]\n"
				    + "Account date[" + rsReader.getString(6) + "]\n"
				    + "Suspend status[" + rsReader.getString(6) + "]\n");
			}
			rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public Planning DAOReadPlanning(int id_user) throws Exception {
		String query = "SELECT * FROM planning JOIN user ON (planning.id_cleaner = user.id_user) WHERE id_cleaner = "
		               + id_user;

		ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>();

		// Load timeslots from db
		try {
			ResultSet rSet = stRead.executeQuery(query);
			while (rSet.next()) {
				TimeSlot ts = new TimeSlot(
				    rSet.getTimestamp("datetime").toLocalDateTime(),
				    rSet.getDouble("durationH"),
				    rSet.getInt("id_mission"));

				slots.add(ts);
			}
			rSet.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return new Planning(slots);
	}

	public void DAOWritePlanning(Planning planning, int id_user) {
		try {
			for (TimeSlot ts : planning.getTimeSlots()) {
				String strQuery = "INSERT INTO `planning`"
				                  + "(`id_cleaner`, `datetime`, `durationH`, `id_mission`)"
				                  + "VALUES ('" + id_user + "','" + ts.getLocalDateTime() + "','" + ts.getDurationH() + "','"
				                  + ts.getIdMission() + "');";
				stRead.executeUpdate(strQuery);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/*--------------------------------------ADD A CLEANER (and User)---------------------------------------------------------- */

	public int DAOAddCleaner(String name,
	                         String pwd,
	                         String surname,
	                         String email,
	                         String phoneN,
	                         LocalDate birthDate,
	                         boolean isSuspended,
	                         Address departureAddress,
	                         int kmRange,
	                         int hourlyRate,
	                         String bio,
	                         String photoIdentity,
	                         String motivation,
	                         CleanerExperience experience,
	                         boolean isConfirmed,
	                         String photoProfile,
	                         String photoLive) {
		int cleanerID = DAOAddUser(name, pwd, surname, email, phoneN, birthDate, isSuspended, UserStatus.CLEANER);
		try {
			String strQuery = "INSERT INTO `cleaner`"
			                  + "(`id_cleaner`, `address_display`, `latitude`, `longitude`, `km_range`, `hourly_rate`, `biography`, `photo_identity`, `motivation`, `experience`, `confirmed`, `photo_profile`, `photo_live`) "
			                  + "VALUES ('" + cleanerID + "','" + departureAddress.asString() + "','"
			                  + departureAddress.getLatitude() + "','" + departureAddress.getLongitude() + "',' " + kmRange + "','"
			                  + hourlyRate + "','" + bio + "','"
			                  + photoIdentity + "','" + motivation + "','" + experience.asInt() + "','" + (isConfirmed ? 1 : 0) + "','"
			                  + photoProfile + "','" + photoLive + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return cleanerID;
	}

	public int DAOAddOwner(String name, String pwd, String surname, String email, String phoneN, LocalDate birthDate,
	                       boolean isSuspended, OwnerMotivation serviceType) {

		int ownerId = DAOAddUser(name, pwd, surname, email, phoneN, birthDate, isSuspended, UserStatus.OWNER);

		try {
			String strQuery = "INSERT INTO `owner`"
			                  + "(`id_owner`, `type_service`) "
			                  + "VALUES ('" + ownerId + "','" + serviceType.asInt() + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return ownerId;
	}

	/*--------------------------------------MANAGE RIGHTS ON USER / CLEANER--------------------------------------------------- */

	public void DAOSuspendUser(int userID, boolean suspend) {
		try {
			String strQuery = "UPDATE user SET suspended = " + (suspend ? 1 : 0) + "WHERE id_user = " + userID + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void DAOConfirmCleaner(int cleanerId) {
		try {
			String strQuery = "UPDATE cleaner SET confirmed = 1 WHERE id_cleaner = " + cleanerId + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("[ERROR] Could not close the db's connection due to: " + e);
		}
	}

	/*--------------------------------------CREATE / MANAGE MISSIONS--------------------------------------------------- */

	public void DAOCreateNewMission(
	    Property property,
	    LocalDateTime localDateTime,
	    double duration) {

		duration = Mission.setDuration(property.getPropertySurface());

		try {
			String strQuery = "INSERT INTO `mission`"
			                  + "(`date_start`, `cost`, `duration`, `commision`, `state`,`id_owner`,`id_property`) "
			                  + "VALUES ('" + localDateTime + "','" + 0.0 + "','" + duration + "','" + 0.0 + "','"
			                  + MissionStatus.PUBLISHED.asInt() + "','"
			                  + property.getOwnerId() + "','" + property.getPropertyId() + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void DAOResolveDispute(int missionID, int state) {
		try {
			String strQuery = "UPDATE mission SET state = " + state + "WHERE id_mission = " + missionID + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/*--------------------------------------TOOLS METHODS--------------------------------------------------------------------- */

	public <T extends User> int DAOAddUser(String name, String pwd, String surname, String email, String phoneN,
	                                       LocalDate birthDate, boolean isSuspended, UserStatus status) {

		int id = 0;
		LocalDate accountDate = LocalDate.now();
		Date sqlBirthDate = Date.valueOf(birthDate);
		Date sqlAccountdate = Date.valueOf(accountDate);

		String pwd_hash = User.sha3256Hashing(pwd);

		try {
			String strQuery = "INSERT INTO `user`"
			                  + "(`name`, `password`, `surname`, `email`, `phone_number`, `birth_date`, `account_date`, `suspended`, `status`) "
			                  + "VALUES ('" + name + "','" + pwd_hash + "','" + surname + "','" + email + "','" + phoneN + "','"
			                  + sqlBirthDate + "','" + sqlAccountdate + "','" + (isSuspended ? 1 : 0) + "','" + status.asInt()
			                  + "');";

			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println("Error in DAOAddUser: " + e.getMessage());
		}

		try {
			String strQuery = "SELECT * FROM user;";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				id = rsReader.getInt("id_user");
			}
			rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return id;
	}

	/*--------------------------------------MANAGE ACTIVITY--------------------------------------------------------------------- */

	public void DAOaddActivity(ActivityType type, int targetUser, Integer ownerId, Integer cleanerId,
	                           Integer missionId, Integer disputeId, Integer adminId) {

		try {
			String sql = "INSERT INTO `activity`"
			             + "(`type`, `read`, `id_owner`, `id_cleaner`, `id_mission`, `id_dispute`, `id_admin`, `id_target`)"
			             + " VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type.asInt());
			pstmt.setInt(2, 0);

			if (ownerId == null)
				pstmt.setNull(3, java.sql.Types.INTEGER);
			else
				pstmt.setInt(3, ownerId);
			if (cleanerId == null)
				pstmt.setNull(4, java.sql.Types.INTEGER);
			else
				pstmt.setInt(4, cleanerId);
			if (missionId == null)
				pstmt.setNull(5, java.sql.Types.INTEGER);
			else
				pstmt.setInt(5, missionId);
			if (disputeId == null)
				pstmt.setNull(6, java.sql.Types.INTEGER);
			else
				pstmt.setInt(6, disputeId);
			if (adminId == null)
				pstmt.setNull(7, java.sql.Types.INTEGER);
			else
				pstmt.setInt(7, adminId);
			pstmt.setInt(8, targetUser);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/*--------------------------------------MANAGE PLANNING--------------------------------------------------------------------- */
	public void DAOCreateNewPlanning(LocalDate date, LocalTime hour, int availability, int cleanerID) {
		Date sqlDate = Date.valueOf(date);
		Time sqlTime = Time.valueOf(hour);
		try {
			String strQuery = "INSERT INTO `planning`"

			                  + "(`date`, `time`, `availability`, `id_cleaner`) "
			                  + "VALUES ('" + sqlDate + "','" + sqlTime + "','" + availability + "','" + cleanerID + "');";

			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/*--------------------------------------CREATE PROPERTY-------------------------------------------------------------- */
	public void DAOCreateNewProperty(
	    Address propertyAddress,
	    int propertySurface,
	    String accesCode,
	    String keyBoxCode,
	    String specialInstruction,
	    int ownerId) {

		try {
			String strQuery = "INSERT INTO `property`"
			                  + "(`address_display`, `latitude`, `longitude`, `surface`, `id_owner`, `acces_code`, `key_box_code`, `special_instruction`) "
			                  + "VALUES ('" + propertyAddress.asString() + "','" + propertyAddress.getLatitude() + "','"
			                  + propertyAddress.getLongitude() + "','" + propertySurface + "','" + ownerId + "','"
			                  + accesCode + "','" + keyBoxCode + "','" + specialInstruction + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/*--------------------------------------CREATE REVIEW-------------------------------------------------------------- */
	public void DAOCreateNewReview(String content, double/* menton */ grade, int userReceivingId, int missionId) {

		try {
			String strQuery = "INSERT INTO `review`"
			                  + "(`content`, `grade`, `id_user`, `id_mission`, `id_owner`) "
			                  + "VALUES ('" + content + "','" + userReceivingId + "','" + missionId + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
