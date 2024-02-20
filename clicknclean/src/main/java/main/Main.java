package main;

import model.Planning;
import model.User;
import model.UserStatus;
import model.Cleaner;
import tools.*;
import view.Window;
import view.Connection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import model.Address;


 

public class Main {

    public static void testUser()  throws SQLException, ExecutionException, InterruptedException {
        Db connection = new Db();
        connection.DAOaddUser("John", "null", "null", "null", "null", LocalDate.now(), false, UserStatus.ADMIN);
    }

    public static void testCleaner() throws SQLException, InterruptedException, ExecutionException {
        Db connection = new Db();
        int cleanerID = connection.DAOAddCleaner(
            "Doe", 
            "null", 
            "John", 
            null, 
            null, 
            LocalDate.now(), 
            false,           
            new Address("28","av yves thepot" , "29000", "quimper"),
            0, 0,
            null, 
            null, 
            null, 
            null, false, 
            null, 
            null);
        connection.disconnect();
        connection = null;


        

    }
    public static void main(String[] args) throws SQLException, InterruptedException, ExecutionException {
        Planning plan = new Planning(1);
        plan.getAvailableSlots();

        try {
            testCleaner();
        } catch (SQLException e) {
			System.err.println(e.getMessage());
		}catch (Exception e){
			System.err.println(e.getMessage());
        }




        try {
            Address.main(args);
            // a.main(args);

        } catch (Exception e) {
            System.out.println("Error" + e);
        }


        // new Window().run();

       
        new Connection().main(args);


    }
}