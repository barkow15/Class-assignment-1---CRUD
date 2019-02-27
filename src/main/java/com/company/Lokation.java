package com.company;

public class Lokation {
    private int     id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    private String  navn;
    private String  adresse;

    public Lokation(int id, String navn, String adresse){
        this.id = id;
        this.navn = navn;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "id:" + this.id + "\nNavn: " + this.navn + "\nAdresse:" + this.adresse + "\n\n";
    }
}


