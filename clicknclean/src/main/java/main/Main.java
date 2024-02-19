package main;

import model.planning.Planning;
import model.planning.TimeSlot;
import tools.*;
import view.Window;
import view.Connection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import model.Address;
import model.Cleaner;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ArrayList<TimeSlot> slots = new ArrayList<>();

        slots.add(new TimeSlot(LocalDateTime.now(), 1.5));
        slots.add(new TimeSlot(LocalDateTime.now(), 5.5));
        slots.add(new TimeSlot(LocalDateTime.now(), 0.5));
        slots.add(new TimeSlot(LocalDateTime.now(), 2.5));

        Planning planning = new Planning(slots);

        Db db = new Db();
        db.writePlanning(planning, 0);

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


        new Connection().main(args);


    }
}