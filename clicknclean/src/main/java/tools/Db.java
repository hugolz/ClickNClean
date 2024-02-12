package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			+ "JOIN user  ON cleaner.id_cleaner = user.user_id;"
			+ ";";
			ResultSet rsReader = stRead.executeQuery(strQuery);
			while (rsReader.next()) {
				Cleaner b = new Cleaner(strQuery, strQuery, strQuery, strQuery, i, strQuery, false, strQuery, i, null, i, i, null, strQuery, strQuery, strQuery, strQuery, false, strQuery, null)
				Cleaner a = new Cleaner(rsReader.getInt("id_cleaner"),
									rsReader.getString("name"),
									rsReader.getString("surname")
				                    rsReader.getString("address"),
				                    rsReader.getInt("km_range"),
				                    rsReader.getInt("hourly_rate"),
				                    rsReader.getString("biography"),
				                    rsReader.getString("photo"), 
									rsReader.getString("motivation"),
									rsReader.getString("experience"),
									rsReader.getBoolean("confirmed"));
				cleanerList.add(i, a);
				i++;
			}
			rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return cleanerList;
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

	public void DAOAdd(Cleaner a) {
		int id = 0;
		try {
			String strQuery = "INSERT INTO `user`"
			                  + "(`name`, `password`, `surname`, `email`, `phone_number`, `birth_date`, `accunt_date`, `suspended`) "
			                  + "VALUES ('" + a.getName() + "','" + a.getPwd() + "','" + a.getSurName() + "','" + a.getEmail() + "','" + a.getPhoneN() + "','"
			                  + a.getBirthDate() + "','" + a.getAccountDate() + "','" + (a.isSuspended()? 1 : 0) + "');";
			stRead.executeUpdate(strQuery);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

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
			String toQuery = (a.getDepartureAddress().getHouseNumber() + " " + a.getDepartureAddress().getLabel() + " " + a.getDepartureAddress().getPostCode() + " " + a.getDepartureAddress().getCity());
			String strQuery = "INSERT INTO `cleaner`"
			                  + "(`id_cleaner`, `address`, `km_range`, `hourly_rate`, `biography`, `photo`, `motivation`, `experience`, `confirmed`) "
			                  + "VALUES ('" + id + "','" + toQuery + "','" + a.getKmRange() + "','" + a.getHourlyRate() + "','" + a.getBiography() + "','"
			                  + a.getProfilePhoto() + "','" + a.getMotivation() + "','" + a.getExperience() + "','" + (a.isConfirmed()? 1 : 0)  + "');";
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

	public void main (String[] args) { 

	}

}