package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConsoleColors cc = new ConsoleColors();


        Menu menu = new Menu();
        menu.welcome();

    }
}
