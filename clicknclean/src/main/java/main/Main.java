package main;

import model.Planning;
import tools.*;
import view.Window;
<<<<<<< HEAD

=======
import view.Connection;
>>>>>>> fed0f4bfa4ec26993e2619a04320aa51304a3dea

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import model.Address;
import model.Cleaner;

import model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Planning plan = new Planning(1);
        plan.getAvailableSlots();
        
     

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