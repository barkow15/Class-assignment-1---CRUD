package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConsoleColors cc = new ConsoleColors();

        cc.printTxtGreen("text").printTxtYellow("Ok yay").printTxtUnderline("---- OMG ----").print(true);

        Menu menu = new Menu();

        menu.welcome();

        

    }
}
