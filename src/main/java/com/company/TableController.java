package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class TableController {
    DatabaseModel dbModel;
    Scanner       sc;

    public TableController() throws SQLException, ClassNotFoundException {
        this.dbModel = new DatabaseModel();
        this.sc      = new Scanner(System.in);
    }

    public boolean createTable(){
        System.out.println("Skriv navnet på den nye tabel: ");
        String name = sc.next();

        System.out.println("Skriv navnet på id rækken (primær nøgle)");

        String id = sc.next();

        System.out.println("Skriv navnet på række 1 (String)");
        String raekke1 = sc.next();

        System.out.println("Skriv navnet på række 2 (int)");
        String raekke2 = sc.next();

        System.out.println("Skriv navnet på række 3 (int)");
        String raekke3 = sc.next();


        CustomTable customTableObject = new CustomTable(name, id, raekke1, raekke2, raekke3);
        boolean completionStatus = false;

        if(dbModel.opretCustomTabel(customTableObject)){
            System.out.println("Tabellen '" + name + "' blev oprettet");
            completionStatus = true;
        }else{
            System.out.println("Noget gik galt. Tabellen blev ikke oprettet. Prøv igen");
        }

        return completionStatus;
    }
}
