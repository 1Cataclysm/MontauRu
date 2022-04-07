package com.example.montauru;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Entree
{
    private static long count = 0;
    private long id;
    private String nom;

    public Entree(long id, String n)
    {
        this.id = id;
        this.nom = n;
    }

    public Entree(String n)
    {
        this.setId(++count);
        this.nom = n;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String n) { this.nom = n; }

    public void creerUneEntree() //Evenement onClick du bouton valider dans la vue de création d'un menu
    {
        //Entree entree = null;     //Création d'une entrée vide qu'on utilisera plus tard
        //String nomEntree = ...;       //Récupère la valeur du EditText liée à l'entrée "et_…" (nom au choix)
        //Entree uneEntree = new Entree(nomEntree);
        //insertEntree(uneEntree);
    }

    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(id);
        laListe.add(nom);
        return new JSONArray(laListe);
    }

    @Override
    public String toString()
    {
        return "Entree[id="+id+", nom="+nom+"]";
    }
}
