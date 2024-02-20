package main;

import model.Planning;
import model.User;
import model.UserStatus;
import model.Cleaner;
import tools.*;
import view.Window;
<<<<<<< HEAD

=======
import view.Connection;
>>>>>>> fed0f4bfa4ec26993e2619a04320aa51304a3dea

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
            null, 
            new Address("28","av yves thepot" , "29000", "quimper"),
            0, 0,
            null, 
            null, 
            null, 
            null, 
            false, 
            null, 
            null);
        connection.disconnect();
        connection = null;


        

    }
    public static void main(String[] args) throws SQLException, InterruptedException, ExecutionException {
        Planning plan = new Planning(1);
        plan.getAvailableSlots();
<<<<<<< HEAD
        
     

        Db db = new Db();
<<<<<<< HEAD
        Window w = new Window();
        w.run();
       
        
=======

>>>>>>> fed0f4bfa4ec26993e2619a04320aa51304a3dea
        // db.DAOAdd(
        //     new Cleaner(
        //         5,
        //         new Address("3", "av Yves thepot",
        //                     "29000", "quimper"),
        //         13,
        //         0,
        //         "null",
        //         "null",
        //         "null",
        //         "null",
        //         false,
        //         "Martin",
        //         "null",
        //         "Durand",
        //         "test@test.com",
        //         "88967886",
        //         LocalDate.now(),
        //         LocalDate.now(),
        //         false
        //     )
        // );
=======

        try {
            testCleaner();
        } catch (SQLException e) {
			System.err.println(e.getMessage());
		}catch (Exception e){
			System.err.println(e.getMessage());
        }


>>>>>>> 81423c019c0eeee0f40ecde25f31a5ff9d1acc86


        try {
            Address.main(args);
            // a.main(args);

        } catch (Exception e) {
            System.out.println("Error" + e);
        }


        // new Window().run();

<<<<<<< HEAD
       // new Owner_registration().main(args);
        // new Connection().main(args);
=======
       
        new Connection().main(args);
>>>>>>> fed0f4bfa4ec26993e2619a04320aa51304a3dea


    }
}