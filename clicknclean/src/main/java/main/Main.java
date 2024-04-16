package main;

import tools.*;
import view.Window;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import model.ActivityType;
import model.Address;
import model.Mission;
import model.OwnerMotivation;
import model.Property;
import model.User;
import model.UserStatus;

public class Main {





    public static void testReview() throws SQLException, InterruptedException, ExecutionException {
        Db connection = new Db();
        connection.DAOCreateNewReview("Super", 5., 5, 1 );
        connection.disconnect();
        connection = null;
    }

    public static void testActivity() throws SQLException, InterruptedException, ExecutionException {
        Db connection = new Db();
        connection.DAOaddActivity(ActivityType.MISSION_CANCELED, 4,
                                  null,
                                  4,
                                  null,
                                  null,
                                  null);
        connection.disconnect();
        connection = null;
    }
    public static void testMission() throws SQLException, InterruptedException, ExecutionException {
        Db connection = new Db();
        Property testProp = new Property(
            2,
            new Address("1", "Pl. Louis Armand", "29000", "quimper"),
            40,
            6,
            "null",
            "null",
            "null"
        );

        connection.DAOCreateNewMission(testProp, LocalDateTime.now());

    }

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
        connection = null;
    }

    public static void testUser()  throws SQLException, ExecutionException, InterruptedException {

        Db connection = new Db();
        connection.DAOAddUser("", "null", "null", "null", "null", LocalDate.now(), false, UserStatus.ADMIN);
        connection.disconnect();
        connection = null;
    }

    public static void testCleaner() throws SQLException, InterruptedException, ExecutionException {
        Db connection = new Db();
        connection.DAOAddCleaner(
            "Doe",
            "null",
            "John",
            null,
            null,
            LocalDate.now(),
            false,
            new Address("28", "av yves thepot" , "29000", "quimper"),
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

    public static void main(String[] args) throws Exception {
        new Window().run();
        Db con = new Db();
    }
}