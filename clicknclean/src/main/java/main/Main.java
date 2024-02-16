package main;

import model.Planning;
import tools.*;
import view.Window;
import view.Connection;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import model.Address;
import model.Cleaner;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Planning plan = new Planning(1);
        plan.getAvailableSlots();
        
     
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