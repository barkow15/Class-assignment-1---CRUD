package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void welcome() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        ConsoleColors cc = new ConsoleColors();

        System.out.println("Velkommen til");

        System.out.println("Vælg handling: ");
        cc.printTxtPurple("1. opret møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtYellow("2. rediger møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtRed("3. slet møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtBlue("4. hent møbel").print(true);
        cc.clearTxtBuffer();
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
