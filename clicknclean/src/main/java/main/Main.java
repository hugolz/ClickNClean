package main;

import model.Planning;
import tools.Db;
import view.Window;


import model.Address;

public class Main {
    public static void main(String[] args) {
        System.out.println("hi");

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