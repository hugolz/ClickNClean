package main;

import model.planning.Planning;
import model.planning.TimeSlot;
import tools.*;
import view.Window;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import model.Address;
import model.OwnerMotivation;
import model.UserStatus;

public class Main {


    int ownerId = 0;

    public static void testProperty() throws SQLException, InterruptedException, ExecutionException {
        Db connection = new Db();
        connection.DAOCreateNewProperty(
            new Address("1", "Pl. Louis Armand", "29000", "quimper"), 
            35, 
            null, 
            null, 
            null, 
            6);
        connection.disconnect();
        connection = null;
    }


    public static void testOwner() throws SQLException, ExecutionException, InterruptedException {
        Db connection = new Db();
        connection.DAOAddOwner("Lezoualch", "noobie", "gogo", "email", "null", LocalDate.now(), false, OwnerMotivation.GUEST_ROOM);
        connection.disconnect();
        connection =null;
    }

    public static void testUser()  throws SQLException, ExecutionException, InterruptedException {

        Db connection = new Db();
        connection.DAOaddUser("John", "null", "null", "null", "null", LocalDate.now(), false, UserStatus.ADMIN);
        connection.disconnect();
        connection = null;
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

    public static void testPlanning() throws Exception, SQLException, InterruptedException, ExecutionException {


    };


    public static void main(String[] args) throws SQLException, InterruptedException, ExecutionException {

      
        try {
            testProperty();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            Address.main(args);
            // a.main(args);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        // new Window().run();

    }
}