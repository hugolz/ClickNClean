package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		this.dbName = "access";
		this.login = "root";
		this.password = "";
		this.strUrl = "jdbc:mysql://localhost:3306/" + dbName
		              + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Paris";

		try {
			Class.forName(strClassName);
			this.conn = DriverManager.getConnection(strUrl, login, password);
			this.stRead = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.err.println("Driver unloaded !");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public void read() {
		try {
			String strQuery = "SELECT *FROM  access;";
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