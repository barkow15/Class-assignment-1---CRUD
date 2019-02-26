package com.company;

public class Moebel {
    private int         id;
    private int         pris;
    private String      navn;
    private Lokation    lokation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Lokation getLokation() {
        return lokation;
    }

    public void setLokation(Lokation lokation) {
        this.lokation = lokation;
    }

    public Moebel(int id, int pris, String navn, Lokation lokation){
        this.id = id;
        this.pris = pris;
        this.navn = navn;
        this.lokation = lokation;
    }

}


