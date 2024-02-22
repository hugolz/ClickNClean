package tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import model.Property;
import javafx.util.Pair;

import javafx.util.Pair;
import model.planning.Planning;
import model.planning.TimeSlot;
import model.Address;
import model.Cleaner;
import model.Mission;
import model.MissionStatus;
import model.UserStatus;
import model.Owner;
import model.OwnerMotivation;
import model.Admin;
import model.Review;
import model.Activity;
import model.User;


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

		this.login = "rootx";
		this.password = "rootx";

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
				    rSet.getString("address_coords"),
				    rSet.getString("address_display"));

				double distance = addr.calculateDistance(cleaner_addr);

				if (distance * 1000 /* m to km */ > cleaner_range) {
					System.out.println("Skipped cleaner with id: '" + cleaner_id + "', not in range (" + distance + "> "
					                   + cleaner_range + ").");
					continue;
				}

				Cleaner cleaner = new Cleaner(
				    cleaner_addr,
				    cleaner_range,
				    rSet.getInt("hourly_rate"),
				    rSet.getString("biography"),
				    rSet.getString("photo"),
				    rSet.getString("motivation"),
				    rSet.getString("experience"),
				    rSet.getBoolean("confirmed"),
				    rSet.getString("name"),
				    rSet.getString("password"),
				    rSet.getString("surname"),
				    rSet.getString("email"),
				    rSet.getString("phone_number"),
				    rSet.getDate("birth_date").toLocalDate(),
				    rSet.getBoolean("suspended"));
				out.add(cleaner);
			}
			rSet.close();
		} catch (Exception e) {
			System.out.println("Could not query cleaners in range " + addr + "due to: " + addr);
		}
		return out;
	}

	public Pair<Integer, UserStatus> loginUser(String login, String password)
	throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM user where email  = " + login + " AND password = " + password + ";";

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			return new Pair<Integer, UserStatus>(
			           rSet.getInt("id_user"),
			           UserStatus.fromInt(rSet.getInt("status")));
		}

		throw new Exception("Could not find a user with the given address / pasword");
	}

	public Cleaner loginCleaner(int id_user) throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM cleaner JOIN user ON (cleaner.id_cleaner = user.id_user) WHERE id_cleaner = "
		               + id_user;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			if (UserStatus.fromInt(rSet.getInt("status")) != UserStatus.CLEANER) {
				throw new Exception("Found a user with given id, but it's not a cleaner;");
			}

			Cleaner cleaner = new Cleaner(
			    rSet.getInt("id_cleaner"),
			    new Address(
			        rSet.getString("address_coords"),
			        rSet.getString("address_display")),
			    rSet.getInt("km_range"),
			    rSet.getInt("hourly_rate"),
			    rSet.getString("biography"),
			    rSet.getString("photo_profile"),
			    rSet.getString("photo_id"),
			    rSet.getString("motivation"),
			    rSet.getString("experience"),
			    rSet.getBoolean("confirmed"),
			    rSet.getString("name"),
			    rSet.getString("password"),
			    rSet.getString("surname"),
			    rSet.getString("email"),
			    rSet.getString("phone_number"),
			    rSet.getDate("birth_date").toLocalDate(),
			    rSet.getBoolean("suspended"),
			    new ArrayList<Integer>(), // reviews,
			    new Planning(new ArrayList<TimeSlot>()));
			// TODO: Load planning and reviews
			return cleaner;
		}

		throw new Exception("Could not find any cleaner with the given id");
	}

	public Owner loginOwner(int id_user) throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM owner JOIN user ON (owner.id_owner = user.id_user) WHERE id_owner = " + id_user;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			if (UserStatus.fromInt(rSet.getInt("status")) != UserStatus.OWNER) {
				throw new Exception("Found a user with given id, but it's not an owner;");
			}

			Owner owner = new Owner(
			    new ArrayList<Integer>(), // ownerReviews
			    rSet.getString("serviceType"), //
			    rSet.getInt("id_owner"),
			    rSet.getString("name"),
			    rSet.getString("password"),
			    rSet.getString("surname"),
			    rSet.getString("email"),
			    rSet.getString("phone_number"),
			    rSet.getDate("birth_date").toLocalDate(),
			    rSet.getBoolean("suspended"));
			return owner;
		}

		throw new Exception("Could not find any owner with the given id");
	}

	public Admin loginAdmin(int id_user) throws InterruptedException, ExecutionException, Exception {
		String query = "SELECT * FROM admin JOIN user ON (admin.id_admin = user.id_user) WHERE id_admin = " + id_user;

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			if (UserStatus.fromInt(rSet.getInt("status")) != UserStatus.ADMIN) {
				throw new Exception("Found a user with given email & password, but it's not a cleaner;");
			}
			Admin admin = new Admin(
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



	public Planning readPlanning(int id_user) throws Exception {
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
				    rSet.getInt("id_mission")
				);

				slots.add(ts);
			}
			rSet.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return new Planning(slots);
	}

	public void writePlanning(Planning planning, int id_user) {
		try {
			for (TimeSlot ts : planning.getTimeSlots()) {
				String strQuery = "INSERT INTO `planning`"
				                  + "(`id_cleaner`, `datetime`, `durationH`, `id_mission`)"
				                  + "VALUES ('" + id_user + "','" + ts.getLocalDateTime() + "','" + ts.getDurationH() + "','" + ( ts.getIsAvailable() ? ts.getIdMission() : null) + "');";
				stRead.executeUpdate(strQuery);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}


	public int DAOAddCleaner(String name, String pwd, String surname, String email, String phoneN, LocalDate birthDate, boolean isSuspended, Address departureAddress, int kmRange, int hourlyRate, String bio, String photo, String motivation, String experience, boolean isConfirmed, String photoProfile, String photoLive) {
		int cleanerID = DAOaddUser(name, pwd, surname, email, phoneN, birthDate, isSuspended, UserStatus.CLEANER);
		try {
			String strQuery = "INSERT INTO `cleaner`"
			                  + "(`id_cleaner`, `address_display`, `latitude`, `longitude`, `km_range`, `hourly_rate`, `biography`, `photo`, `motivation`, `experience`, `confirmed`, `photo_profile`, `photo_live`) "
			                  + "VALUES ('" + cleanerID + "','" + departureAddress.asString() + "','" + departureAddress.getLatitude() +  "','" + departureAddress.getLongitude() + "','" + kmRange + "','" + hourlyRate + "','" + bio + "','"
			                  + photo + "','" + motivation + "','" + experience + "','" + (isConfirmed ? 1 : 0)  + "','" + photoProfile + "','" + photoLive + "');";			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return cleanerID;
	}

	/*--------------------------------------ADD AN OWNER (and User)---------------------------------------------------------- */



	public int DAOAddOwner(String name, String pwd, String surname, String email, String phoneN, LocalDate birthDate, boolean isSuspended, OwnerMotivation serviceType) {
		int ownerId = DAOaddUser(name, pwd, surname, email, phoneN, birthDate, isSuspended, UserStatus.OWNER);

		try {
			String strQuery = "INSERT INTO `owner`"
			                  + "(`id_owner`, `type_service`) "
			                  + "VALUES ('" + ownerId + "','" + serviceType + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return ownerId;
  }

	/*--------------------------------------ADD AN ADMIN (and User)---------------------------------------------------------- */


	public void DAOAddAdmin(Admin a) {
		int id = 0;
		// DAOaddUser(a);

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

		try {
			String strQuery = "INSERT INTO `admin`"
			                  + "(`id_admin`) "
			                  + "VALUES ('" + id + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
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
							+ "VALUES ('" + localDateTime + "','" + 0.0 + "','" + duration + "','" + 0.0 + "','" + MissionStatus.PUBLISHED.asInt() + "','"
							+ property.getOwnerId()  + "','" + property.getPropertyId() + "');";
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

	public <T extends User> int DAOaddUser(String name, String pwd, String surname, String email, String phoneN, LocalDate birthDate, boolean isSuspended, UserStatus status) {

		int id = 0;
		LocalDate accountDate = LocalDate.now();
		Date sqlBirthDate = Date.valueOf(birthDate);
		Date sqlAccountdate = Date.valueOf(accountDate);

		try {
			String strQuery = "INSERT INTO `user`"

			                  + "(`name`, `password`, `surname`, `email`, `phone_number`, `birth_date`, `account_date`, `suspended`, `status`) "
			                  + "VALUES ('" + name + "','" + pwd + "','" + surname + "','" + email + "','" + phoneN + "','"
			                  + sqlBirthDate + "','" + sqlAccountdate + "','" + (isSuspended ? 1 : 0) + "','" + status.asInt() + "');";

			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
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

	public void DAOaddActivity(Activity a) {
		try {
			String strQuery = "INSERT INTO `activity`"
			                  + "(`type`, `opened`, `id_owner`, `id_cleaner`, `id_mission`, `id_dispute`, `id_admin`) "
			                  + "VALUES ('" + a.getType() + "','" + (a.isOpened() ? 1 : 0) + "','" + a.getOwnerID() + "','"
			                  + a.getCleanerID() + "','" + a.getMissionID() + "','"
			                  + a.getDisputeID() + "','" + a.getAdminID() + "');";
			stRead.executeUpdate(strQuery);
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

	/*--------------------------------------MANAGE PROPERTIES-------------------------------------------------------------- */
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
			                  + "VALUES ('" + propertyAddress.asString() + "','" + propertyAddress.getLatitude() + "','" + propertyAddress.getLongitude() + "','" + propertySurface + "','" + ownerId + "','"
			                  + accesCode + "','" + keyBoxCode  + "','" + specialInstruction  + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}


	

}