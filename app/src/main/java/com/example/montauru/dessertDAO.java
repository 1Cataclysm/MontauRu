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

public class dessertDAO
{
    /** ACCES DISTANT **/
    private static AccesDistant accesDistant;
    private static ArrayList<Dessert> listeDessert = new ArrayList<Dessert>();
    public dessertDAO(Context ct)
    {

    }

    public void majDessert(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("desserts", new JSONArray());
    }

    public Dessert getDessert(long id)
    {
        Dessert unDessert = null;
        for (int i = 0; i < listeDessert.size(); i++){
            if (listeDessert.get(i).getId() == id){
                unDessert = new Dessert(id, listeDessert.get(i).getNom());
            }
        }
        return unDessert;
    }

    public Dessert getDessert(String nom)
    {
        Dessert unDessert = null;
        for (int i = 0; i < listeDessert.size(); i++){
            if (listeDessert.get(i).getNom().equals(nom)){
                unDessert = new Dessert(listeDessert.get(i).getId(), nom);
            }
        }
        return unDessert;
    }

    public ArrayList<Dessert> getDesserts()
    {
        majDessert();
        return dessertDAO.listeDessert;
    }

    public static void setListeDessert (ArrayList<Dessert> l){
        dessertDAO.listeDessert = l;
    }

    public void insertDessert(Dessert monDessert)
    {
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregDessert",monDessert.converToJSONArray());
        majDessert();
    }

    public void ajouterElementsBddSpinner(Context context, Spinner s_dessert){
        List<String> liste = new ArrayList<>();
        ArrayList<Dessert> listeDessert = this.getDesserts();
        liste.add("Selectionnez un dessert");
        for (int i = 0 ; i < listeDessert.size() ; i++){
            liste.add(listeDessert.get(i).getNom());
        }
        liste.add("Nouveau dessert");
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, liste);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_dessert.setAdapter(adp1);
    }
}
