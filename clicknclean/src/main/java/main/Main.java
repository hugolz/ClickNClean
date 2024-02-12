package main;

import model.Planning;
import tools.*;
import view.Window;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import model.Address;
import model.Cleaner;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LocalDate birth = LocalDate.now();
        Db db = new Db();
        db.DAOAdd(new Cleaner("test@test.com", 
                                    "null", 
                                    "Durand", 
                                    "Martin", 
                                    88967886, 
                                    birth, 
                                    false, 
                                    LocalDate.now().toString(), 0, 
                                    new Address("3", "av Yves thepot", 
                                        "29000", "quimper"), 
                                    5, 
                                    13, 
                                    null, 
                                    null, 
                                    null, 
                                    null, 
                                    null, 
                                    false, 
                                    null, 
                                    null));

        try {
            Address.main(args);
            // a.main(args);

        } catch (Exception e) {
            System.out.println("Error" + e);
        }


        Window win = new Window();

        win.run();

    }
}