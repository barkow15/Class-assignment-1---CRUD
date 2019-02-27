package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseModel{



    private final String dbName     = "windata";
    private final String dbHost     = "den1.mysql4.gear.host";
    private final String dbPort     = "3306";
    private final String dbPass     = "Bc250-rbRn!7";
    private final String dbUser     = "windata";
    private Connection conn;
    private boolean debug           = false;

    Scanner sc = new Scanner(System.in);

    Menu menu = new Menu();

    public DatabaseModel() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Lav conUrl tekststrengen
        String conUrl =
            "jdbc:mysql://"+
            this.dbHost    +
            ":"            +
            this.dbPort    +
            "/"            +
            this.dbName;

        // Forbind til databasen og set conn attributten
        this.conn = DriverManager.getConnection(conUrl, this.dbUser, this.dbPass);


    }
    
    
    public boolean opretMoebel(/*String moebelNavn, int moebelPris, int lIDproduktLokation*/) {

        //System.out.println("Skriv din tilføjelse: Moebelnavn, Moebelpris, lIDProduktlokation.");
        System.out.println("Skriv først navnet på ny varer: ");

        String s = sc.next();

        System.out.println("Skriv pris på ny varer: ");

        String a = sc.next();

        System.out.println("Skriv lokation: ");

        String b = sc.next();

        String sql = "INSERT INTO moebler (Moebelnavn, Moebelpris, lIDProduktlokation ) VALUES " + "('" + s + "', '" + a + "', '" + b + "'" + "); ";
        int rs = this.executeSql(sql);

        return true;

    }

    public boolean sletMoebel(int pID) {

        System.out.println("Du ønsker at slette en kolonne");
        System.out.println("Skriv først navnet på ønskede tabel, hvorfra rækken skal slettes:");

        String s = sc.next();


        System.out.println("Skriv navnet på katagori som ønskes slettet ");

        String a = sc.next();

        System.out.println("Skriv ud fra ID hvilket produkt der skal slettes: ");

        String b = sc.next();
        String sql = "DELETE FROM " + s + " WHERE " + a + " = " + b + ";";
        int rs = this.executeSql(sql);
        return true;
    }

    public boolean redigerMoebel(int pID){

        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv hvilken tabel der skal ændres i: ");

        String s = sc.next();

        System.out.println("Skriv hvad der skal ændres (moebelNavn, moebelPris, lIDproduktLokation); ");

        String a = sc.next();

        System.out.println("Skriv hvad den nye værdi");

        String b = sc.next();

        System.out.println("Vælg ud fra ID hvilken række der skal ændres");

        String c = sc.next();


        String sql = "UPDATE " + s + " SET " + a + " = '" + b + "' WHERE pID = " + c + ";";
        int rs = this.executeSql(sql);
        return true;


    }

    public Moebel hentMoebel(int id){
        ResultSet   rs  = null;
        String      sql = null;
        sql = "SELECT * FROM moebler WHERE pID = " + id;

        rs = this.querySql(sql);
        Moebel m = null;
        try
        {
            if(rs.next())
            {
                m = new Moebel(
                    rs.getInt("pID"),
                    rs.getInt("moebelPris"),
                    rs.getString("moebelNavn"),
                    new Lokation(rs.getInt("lIDproduktLokation"),"bla", "")
                );
            }
        }
        catch(Exception e)
        {
            System.err.println("hentMoebel" +  e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        finally { this.closeConnection(rs);  }
        if(this.debug == true){ System.out.println("Returning data"); }
        return(m);
    }

    public ArrayList<Lokation> hentLokationer() {
        ResultSet   rs  = null;
        String      sql = null;
        sql = "SELECT * FROM lokationer";

        rs = this.querySql(sql);
        Moebel m = null;
        ArrayList<Lokation> lokationer = new ArrayList();

        try
        {
            while (rs.next())
            {
                lokationer.add(
                    new Lokation(
                        rs.getInt("lID"),
                        rs.getString("lokationsNavn"),
                        rs.getString("lokationsAdresse")
                    )
                );
            }
        }
        catch(Exception e)
        {
            System.err.println("hentLokationer" +  e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        finally { this.closeConnection(rs);  }
        if(this.debug == true){ System.out.println("Returning data"); }
        return(lokationer);
    }

    public String udskrivLokationer() {
        String lokationerString = "--- Lokationer ----\n";
        ArrayList<Lokation> lokationerList = this.hentLokationer();

        for(int i = 0; i < lokationerList.size(); i++){
            Lokation l = lokationerList.get(i);

            lokationerString += "ID: " +      l.getId() +       "\n";
            lokationerString += "Navn: " +    l.getNavn() +     "\n";
            lokationerString += "Adresse: " + l.getAdresse() +  "\n";
            lokationerString += "\n";
        }

        return lokationerString;
    }

    private int executeSql(String sql)
    {
        int returnvalue = -1;
        PreparedStatement pstmt = null;

        try
        {
            if(this.debug) System.out.println("DB CONNECTION OPENED");

            pstmt = conn.prepareStatement(sql);

            if(this.debug) System.out.println("executeSql: EXECUTING SQL ... " + sql);
            returnvalue = pstmt.executeUpdate();

            if(this.debug) System.out.println("SQL SUCCESS: " + returnvalue);
            //System.out.println(returnvalue);
            if(this.debug) System.out.println("CLOSING DB CONNECTION ...");

            if(conn != null)
            {
                conn.close();
                if(this.debug) System.out.println("DB CONNECTION CLOSED");

            }

        }
        catch (Exception e)
        {
            System.err.println("executeSql: " + e.getClass().getName() + ": " + e.getMessage() + " Sql: " + sql);

            returnvalue = -1;
            //System.exit(0);
        }
        return(returnvalue);
    }

    private ResultSet querySql(String sql)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            //conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            if(this.debug) System.out.println("DB CONNECTION OPENED");

            pstmt = conn.prepareStatement(sql);

            if(this.debug) System.out.println("querySql EXECUTING SQL QUERY ... " + sql);
            rs = pstmt.executeQuery();

            return rs;
        }
        catch (Exception e)
        {
            System.err.println("querySql: " + e.getClass().getName() + ": " + e.getMessage() + " Sql: " + sql);
            System.exit(0);
        }
        return rs;
    }

    private void closeConnection(ResultSet rs)
    {
        try
        {
            if(conn != null)
            {
                if(this.debug) System.out.println("CLOSING DB CONNECTION");
                rs.close();
                conn.close();
                if(this.debug) System.out.println("DB CONNECTION CLOSED");
            }
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
