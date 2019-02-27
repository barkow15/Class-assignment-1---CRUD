package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void welcome() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Velkommen til.");

        System.out.println("Vælg handling: ");
        System.out.println("1. opret møbel");
        System.out.println("2. rediger møbel");
        System.out.println("3. slet møbel");
        System.out.println("4. hent møbel");
        System.out.println("5. Exit");

        DatabaseController d;
        d = new DatabaseController();

        int in = scanner.nextInt();
        switch (in){

            case 1: d.opretMoebel();
                break;
            case 2: d.redigerMoebel(2);
                break;
            case 3: d.sletMoebel(2);
                break;
            case 4: System.out.println(d.hentMoebel(2).getNavn());
                break;
            case 5:
                return;

        }

    }
}
