package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConsoleColors cc = new ConsoleColors();
        cc.printTxtGreen("text").printTxtYellow("Ok yay").printTxtUnderline("---- OMG ----").print(true);

        DatabaseController d = new DatabaseController();

        //System.out.println(d.hentMoebel(2).getNavn());
        //System.out.println(d.sletMoebel(2));
        //System.out.println(d.redigerMoebel(2));
        System.out.println(d.opretMoebel());
    }
}
