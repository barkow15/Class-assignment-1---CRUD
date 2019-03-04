package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private MoebelController m;
    private TableController tabCon;
    private Scanner          scanner;
    private ConsoleColors    cc;

    public Menu() throws SQLException, ClassNotFoundException{
        this.m       = new MoebelController();
        this.tabCon  = new TableController();
        this.scanner = new Scanner(System.in);
        this.cc      = new ConsoleColors();
        System.out.println("Velkommen til");
        System.out.println("Skriv /quit for at afslutte applikationen");
    }

    public void welcome(){

        System.out.println("Vælg handling: ");
        cc.printTxtPurple("1. opret møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtYellow("2. rediger møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtRed("3. slet møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtBlue("4. hent møbel").print(true);
        cc.clearTxtBuffer();
        cc.printTxtPurple("5. lav tabel med eget tabelnavn og kolonner").print(true);
        cc.clearTxtBuffer();

        String in = scanner.nextLine();
        switch (in){
            case "1":
                // Metode som initialiserer de forskellige trin for at oprette et nyt møbel
                this.m.opretMoebel();

                // Vis hovedmenuen igen
                this.welcome();

                break;
            case "2":
                this.m.redigerMoebel();

                this.welcome();
                break;
            case "3":
                // Metode som initialiserer de forskellige trin for at oprette et nyt møbel
                this.m.sletMoebel();

                // Vis hovedmenuen igen
                this.welcome();
                break;
            case "4": //System.out.println(d.hentMoebel(2).getNavn());
                this.m.hentMoebel();
                this.welcome();
                break;
            case "5":

                this.tabCon.createTable();

                // Vis hovedmenuen igen
                this.welcome();
                break;
            case "/quit":
                System.out.println("Afslutter nu applikation");

                break;
            default:
                System.out.println("Kommando ikke fundet. Prøv igen");
                this.welcome();
                break;


        }

    }
}
