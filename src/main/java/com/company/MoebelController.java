package com.company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoebelController {
    DatabaseModel dbModel;
    Scanner       sc;

    public MoebelController() throws SQLException, ClassNotFoundException {
        this.dbModel = new DatabaseModel();
        this.sc      = new Scanner(System.in);
     }

    public void opretMoebel(){
        System.out.println("Skriv først navnet på ny varer: ");

        String s = sc.nextLine();

        System.out.println("Skriv pris på ny varer: (maks 1000)");

        String a = sc.next();

        System.out.println(this.udskrivLokationer());
        System.out.println("Skriv id på den lokation som varen skal have");

        String b = sc.next();

        if(dbModel.opretMoebel(s, a, b)){
            System.out.println("Møbel oprettet");
        }else{
            System.out.println("Noget gik galt. Møbel ikke oprettet. Prøv igen.");
        }
    }
    public void sletMoebel(){
        System.out.println("Du ønsker at slette en række");
        System.out.println("Skriv ud fra ID hvilket produkt der skal slettes: ");

        String b = sc.next();
        if(dbModel.sletRaekke("moebler", "pID", b)){
            System.out.println("Møbel Slettet");
        }else{
            System.out.println("Noget gik galt. Møbel ikke slettet. Prøv igen.");
        }
    }
    public void redigerMoebel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv hvad der skal ændres (moebelNavn, moebelPris, lIDproduktLokation); ");

        String a = sc.next();

        System.out.println("Skriv hvad den nye værdi");

        String b = sc.next();

        System.out.println("Vælg ud fra ID hvilken række der skal ændres");

        String c = sc.next();
        if(dbModel.redigerRaekke("moebler", a,  b, c)){
            System.out.println("Møbel opdateret");
        }else{
            System.out.println("Noget gik galt. Møbel ikke opdateret. Prøv igen.");
        }
    }
    public void hentMoebel(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Skriv hvilket pID du vil hente.");

        int c = sc.nextInt();



        Moebel m = dbModel.hentMoebel(c);
        System.out.println(
                "-- Møbel med id " + c + " hentet\n" +
                "Navn: " + m.getNavn() + "\n" +
                "Pris: " + m.getPris() + "\n"
        );



    }

    private String udskrivLokationer() {
        String lokationerString = "--- Lokationer ----\n";
        ArrayList<Lokation> lokationerList = dbModel.hentLokationer();

        for(int i = 0; i < lokationerList.size(); i++){
            Lokation l = lokationerList.get(i);

            lokationerString += "ID: " +      l.getId() +       "\n";
            lokationerString += "Navn: " +    l.getNavn() +     "\n";
            lokationerString += "Adresse: " + l.getAdresse() +  "\n";
            lokationerString += "\n";
        }

        return lokationerString;
    }

}
