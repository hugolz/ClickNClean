package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import model.*;

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
		this.password = "root";

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
		String query = "SELECT * FROM cleaner;";

		try {
			ResultSet rSet = this.stRead.executeQuery(query);
			while (rSet.next()) {
				int cleaner_id = rSet.getInt("id_cleaner");

				int cleaner_range = rSet.getInt("km_range");

				ArrayList<String> addr_split = new ArrayList<String>(
				    Arrays.asList(
				        rSet.getString("address").split("+")
				    )
				);

				if (addr_split.size() != 4) {
					System.out.println("[Error] Could not deserialize address for cleaner with id '" + cleaner_id + "': " + addr_split);
					continue;
				}

				Address cleaner_addr = new Address(
				    addr_split.get(0),
				    addr_split.get(1),
				    addr_split.get(2),
				    addr_split.get(3));

				double distance = addr.calculateDistance(cleaner_addr);

				if (distance > cleaner_range) {
					System.out.println("Skipped cleaner with id: '" + cleaner_id + "', not in range (" + distance + "> " + cleaner_range + ").");
					continue;
				}

				Cleaner cleaner = new Cleaner(
				    cleaner_id,
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
				    rSet.getDate("account_date").toLocalDate(),
				    rSet.getBoolean("suspended"),
				    rSet.getInt("status")
				);

				out.add(cleaner);
			}
		} catch (Exception e) {
			System.out.println("Could not query cleaners in range " + addr + "due to: " + addr);
		}
		return out;
	}

	public Cleaner loginCleaner(String login, String password) throws InterruptedException, ExecutionException, Exception {
		boolean found = false;
		Cleaner out;
		String query = "SELECT * FROM user where email  = " + login + " AND password = " + password + ";";

		ResultSet rSet = this.stRead.executeQuery(query);
		while (rSet.next()) {
			if (rSet.getInt("status") != 2 /* cleaner */) {
				throw new Exception("Found a user with given email & password, but it's not a cleaner;");
			}
			Cleaner cleaner = new Cleaner(
			    rSet.getInt("id_cleaner"),
			    new Address(
			        rSet.getString("address_display"),
			        rSet.getString("address_coords")
			    ),
			    rSet.getInt("km_range"),
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
			    rSet.getDate("account_date").toLocalDate(),
			    rSet.getBoolean("suspended"),
			    rSet.getInt("status")
			);
			return cleaner;
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

	public ArrayList<Cleaner> DAOLister() {
		int i = 0;
		ArrayList<Cleaner> cleanerList = new ArrayList<Cleaner>();
		try {
			String strQuery =
			    " SELECT * FROM  cleaner"
			    + "JOIN user  ON cleaner.id_cleaner = user.user_id;";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				// Cleaner b = new Cleaner(strQuery,strQuery,strQuery,strQuery,i,strQuery,false,strQuery,i,null,i,i,null,strQuery,strQuery,strQuery,strQuery,false,strQuery,null);

				int cleaner_id = rsReader.getInt("id_cleaner");

				ArrayList<String> addr_split = new ArrayList<String>(
				    Arrays.asList(
				        rsReader.getString("address").split("+")
				    )
				);

				if (addr_split.size() != 4) {
					System.out.println("[Error] Could not deserialize address for cleaner with id '" + cleaner_id + "': " + addr_split);
					continue;
				}

				Address cleaner_addr;
				try {
					cleaner_addr =  new Address(
					    addr_split.get(0),
					    addr_split.get(1),
					    addr_split.get(2),
					    addr_split.get(3)
					);
				} catch (Exception e) {
					System.out.println("[ERROR] Could not create Address from '" + addr_split + "' due to: " + e.toString());
					continue;
				}

				Cleaner a = new Cleaner(
				    cleaner_id,
				    cleaner_addr,
				    rsReader.getInt("km_range"),
				    rsReader.getInt("hourly_rate"),
				    rsReader.getString("biography"),
				    rsReader.getString("photo"),
				    rsReader.getString("motivation"),
				    rsReader.getString("experience"),
				    rsReader.getBoolean("confirmed"),
				    rsReader.getString("name"),
				    rsReader.getString("password"),
				    rsReader.getString("surname"),
				    rsReader.getString("email"),
				    rsReader.getString("phone_number"),
				    rsReader.getDate("birth_date").toLocalDate(),
				    rsReader.getDate("account_date").toLocalDate(),
				    rsReader.getBoolean("suspended"),
				    rsReader.getInt("status")
				);
				cleanerList.add(i, a);
				i++;
			}
			rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return cleanerList;
	}

	
	public void DAOAddCleaner(Cleaner a) {
		int id = 0;
		DAOaddUser(a);

		try {
			String strQuery = "SELECT * FROM user;";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				id = rsReader.getInt("id_user");
				rsReader.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		try {
			//String toQuery = (a.getDepartureAddress().getHouseNumber() + " " + a.getDepartureAddress().getLabel() + " " + a.getDepartureAddress().getPostCode() + " " + a.getDepartureAddress().getCity());
			String strQuery = "INSERT INTO `cleaner`"
			                  + "(`id_cleaner`, `address`, `km_range`, `hourly_rate`, `biography`, `photo`, `motivation`, `experience`, `confirmed`) "
			                  + "VALUES ('" + id + "','" + a.getDepartureAddress().toString() + "','" + a.getKmRange() + "','" + a.getHourlyRate() + "','" + a.getBiography() + "','"
			                  + a.getProfilePhoto() + "','" + a.getMotivation() + "','" + a.getExperience() + "','" + (a.isConfirmedId() ? 1 : 0)  + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}


/*--------------------------------------ADD AN OWNER (and User)---------------------------------------------------------- */

	public void DAOAddOwner(Owner a) {
		int id = 0;
		DAOaddUser(a);

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
			String strQuery = "INSERT INTO `owner`"
			                  + "(`id_owner`, `type_service`) "
			                  + "VALUES ('" + id + "','" + a.getServiceType() + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}


/*--------------------------------------ADD AN ADMIN (and User)---------------------------------------------------------- */

	public void DAOAddAdmin(Admin a) {
		int id = 0;
		DAOaddUser(a);

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
			String strQuery = "UPDATE user SET suspended = " + (suspend ? 1 : 0) + "WHERE id_user = "+ userID + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void DAOConfirmCleaner(int cleanerId) {
		try {
			String strQuery = "UPDATE cleaner SET confirmed = 1 WHERE id_cleaner = "+ cleanerId + ";";
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

	public void main (String[] args) {

	}

/*--------------------------------------MANAGE RIGHTS ON USER / CLEANER--------------------------------------------------- */

	public void DAOResolveDispute(int missionID, int state) {
		try {
			String strQuery = "UPDATE mission SET state = " + state + "WHERE id_mission = "+ missionID + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

/*--------------------------------------TOOLS METHODS--------------------------------------------------------------------- */

	public <T extends User> void DAOaddUser(T a) {
		try {
			String strQuery = "INSERT INTO `user`"
			                  + "(`name`, `password`, `surname`, `email`, `phone_number`, `birth_date`, `accunt_date`, `suspended`) "
			                  + "VALUES ('" + a.getName() + "','" + a.getPwd() + "','" + a.getSurname() + "','" + a.getEmail() + "','" + a.getPhoneNumber() + "','"
			                  + a.getBirthLocalDate() + "','" + a.getAccountLocalDate() + "','" + (a.isSuspended() ? 1 : 0) + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}