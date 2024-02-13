package main;

import model.Planning;
import tools.*;
import view.Window;
import view.ConnectionView;
import view.Owner_registration;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import model.Address;
import model.Cleaner;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Db db = new Db();
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

        // new Owner_registration().main(args);
        new ConnectionView().main(args);


    }
}