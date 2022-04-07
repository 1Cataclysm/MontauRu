package com.example.montauru;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class entreeDAO
{
    /** distant **/
    private static AccesDistant accesDistant;
    public static ArrayList<Entree> listeEntree = new ArrayList<Entree>();

    public entreeDAO(Context ct)
    {

    }



    public Entree getEntree(long id)
    {
        Entree uneEntree = null;
        for (int i = 0; i < listeEntree.size(); i++){
            if (listeEntree.get(i).getId() == id){
                uneEntree = new Entree (id, listeEntree.get(i).getNom());
            }
            else {
                Log.d(TAG, "getEntree: Aucune entrée récupéré dans le getEntree(String nom)");
            }
        }
        return uneEntree;
    }

    public Entree getEntree(String nom)
    {
        Entree uneEntree = null;
        for (int i = 0; i < listeEntree.size(); i++){
            if (listeEntree.get(i).getNom().equals(nom)){
                uneEntree = new Entree (listeEntree.get(i).getId(), nom);
            }
            else {
                Log.d(TAG, "getEntree: Aucune entrée récupéré dans le getEntree(String nom)");
            }
        }
        return uneEntree;
    }

    public void majEntree(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("entrees", new JSONArray());
    }
    public static void setListeEntree(ArrayList<Entree> l){
        entreeDAO.listeEntree = l;
    }

    public ArrayList<Entree> getEntrees()
    {
        majEntree();
        return entreeDAO.listeEntree;
    }


    public void insertEntree(Entree uneEntree)
    {
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregEntree", uneEntree.converToJSONArray());
        majEntree();
    }

    public void ajouterElementsBddSpinner(Context context, Spinner s_entree){
        List<String> liste = new ArrayList<>();
        ArrayList<Entree> listeEntree = this.getEntrees();
        liste.add("Selectionnez une entrée");
        for (int i = 0 ; i < listeEntree.size() ; i++){
            liste.add(listeEntree.get(i).getNom());
        }
        liste.add("Nouvelle entrée");
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, liste);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_entree.setAdapter(adp1);
    }
}
