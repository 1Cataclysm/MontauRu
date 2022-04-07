package com.example.montauru;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Menu
{
    private static long count = 0;
    private long id;
    private String nom;
    private long id_entree;
    private long id_plat;
    private long id_dessert;

    public Menu(long id, String n,long id_e, long id_p, long id_d)
    {
        this.id = id;
        this.nom = n;
        this.id_entree = id_e;
        this.id_plat = id_p;
        this.id_dessert = id_d;
    }

    public Menu(String n, long id_e, long id_p, long id_d)
    {
        this.setId(++count);
        this.nom = n;
        this.id_entree = id_e;
        this.id_plat = id_p;
        this.id_dessert = id_d;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String n) { this.nom = n;}
    public long getIdEntree() { return id_entree; }
    public void setIdEntree(long id_e) { this.id_entree = id_e; }
    public long getIdPlat() { return id_plat; }
    public void setIdPlat(long id_p) { this.id_plat = id_p; }
    public long getIdDessert() { return id_dessert; }
    public void setIdDessert(long id_d) { this.id_dessert = id_d; }

    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(id);
        laListe.add(nom);
        laListe.add(id_entree);
        laListe.add(id_plat);
        laListe.add(id_dessert);
        return new JSONArray(laListe);
    }

        @Override
    public String toString()
    {
        return "Menu[id="+id+", nom="+nom+", id_entree="+id_entree+", id_plat="+id_plat+", id_dessert="+id_dessert+"]";
    }
}
