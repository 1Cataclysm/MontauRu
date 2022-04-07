package com.example.montauru;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Dessert
{
    private static long count = 0;
    private long id;
    private String nom;

    public Dessert(long id, String n)
    {
        this.id = id;
        this.nom = n;
    }

    public Dessert(String n)
    {
        this.setId(++count);
        this.nom = n;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String d) { this.nom = d; }

    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(id);
        laListe.add(nom);
        return new JSONArray(laListe);
    }

    @Override
    public String toString()
    {
        return "Dessert[id="+id+", nom="+nom+"]";
    }
}
