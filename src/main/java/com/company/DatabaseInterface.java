package com.company;

public interface DatabaseInterface {

    public boolean      opretMoebel(String moebelNavn, int moebelPris, int lIDproduktLokation);
    public boolean      sletMoebel(int pID);
    public boolean      redigerMoebel(int pID);
    public Moebel       hentMoebel(int id);
    public Lokation     hentlokation();
}
