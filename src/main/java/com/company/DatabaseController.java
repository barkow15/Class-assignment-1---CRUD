package com.company;

import java.sql.*;
import java.util.Scanner;

public class DatabaseController implements DatabaseInterface{

    private final String dbName     = "windata";
    private final String dbHost     = "den1.mysql4.gear.host";
    private final String dbPort     = "3306";
    private final String dbPass     = "Bc250-rbRn!7";
    private final String dbUser     = "windata";
    private Connection conn;
    private boolean debug           = false;

    public DatabaseController() throws ClassNotFoundException, SQLException {
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
    
    
    public boolean opretMoebel(String moebelNavn, int moebelPris, int lIDproduktLokation) {

        return true;
    }

    public boolean sletMoebel(int pID) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Du ønsker at slette en kolonne");
        System.out.println("Skriv først navnet på ønskede tabel, hvorfra kolonnen skal slettes:");

        String s = sc.next();


        System.out.println("Skriv navnet på kolonnen som ønskes slettet ");

        String a = sc.next();
        String sql = "ALTER TABLE " + s + " DROP COLUMN " + a;
        int rs = this.executeSql(sql);
        return true;
    }

    public boolean redigerMoebel(int pID){

        Scanner sc = new Scanner(System.in);

        System.out.println("Indtast ny katagori til tabellen mødel: ");

        String s = sc.next();
        String sql = "ALTER TABLE moebler ADD " + s + " CHAR(20)";
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
        System.out.println("Returning data");
        return(m);
    }

    public Lokation hentlokation() {
        return null;
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
