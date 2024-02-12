package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import model.Address;
import model.Cleaner;

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
		this.dbName = "acces";
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
			System.err.println(e.getMessage());
		}
	}

	public void test() {
		try {
			String query = "SELECT * FROM cleaner";
			ResultSet resSet = this.stRead.executeQuery(query);
			while (resSet.next()) {
				System.out.println(resSet);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
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
				    rSet.getDate("birth_date"),
				    rSet.getDate("account_date"),
				    rSet.getBoolean("suspended")
				);

				out.add(cleaner);
			}
		} catch (Exception e) {
			System.out.println("Could not query cleaners in range " + addr + "due to: " + addr);
		}

		return out;
	}

	public void read() {
		try {
			String strQuery = "SELECT *FROM human;";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				System.out.print(
				    "Id[" + rsReader.getInt(1) + "]\n"
				    + "Password[" + rsReader.getString(2) + "]\n"
				    + "user[" + rsReader.getString(3) + "]\n"
				    + "pw[" + rsReader.getString(4) + "]\n"
				    + "statut[" + rsReader.getString(5) + "]\n"
				    + "age[" + rsReader.getString(6) + "]\n");
			}
			rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public ArrayList<Acces> DAOLister() {
		int i = 0;
		ArrayList<Acces> listAcces = new ArrayList<Acces>();
		try {
			String strQuery = "SELECT * FROM  access;";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				Acces a = new Acces(rsReader.getInt(1),
				                    rsReader.getString(2),
				                    rsReader.getString(3),
				                    rsReader.getString(4),
				                    rsReader.getString(5),
				                    rsReader.getInt(6));
				listAcces.add(i, a);
				i++;
			}
			rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return listAcces;
	}

	public void add(int id, String surName, String user, String pw, String status, int age) {
		try {
			String strQuery = "INSERT INTO `access`"
			                  + "(`id`, `prenom`, `user`, `pw`, `statut`, `age`) "
			                  + "VALUES ('" + id + "','" + surName + "','" + user + "','" + pw + "','" + status + "','" + age
			                  + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void DAOAdd(Acces a) {
		try {
			String strQuery = "INSERT INTO `access`"
			                  + "(`id`, `prenom`, `user`, `pw`, `statut`, `age`) "
			                  + "VALUES ('" + a.getId() + "','" + a.getSurName() + "','" + a.getUser() + "','" + a.getPw() + "','"
			                  + a.getStatus() + "','" + a.getAge() + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void DAODelete(Acces a) {
		try {
			String strQuery = "DELETE FROM `access` WHERE `id` = " + a.getId() + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void delete(int id) {
		try {
			String strQuery = "DELETE FROM `access` WHERE `id` = " + id + ";";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

}