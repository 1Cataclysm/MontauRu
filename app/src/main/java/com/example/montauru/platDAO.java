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

public class platDAO
{
    /**Acces distant**/
    private static AccesDistant accesDistant;
    public static ArrayList<Plat> listePlat = new ArrayList<Plat>();

    public platDAO(Context ct)
    {

    }

    public Plat getPlat(long id)
    {
        Plat unPlat = null;
        for (int i = 0; i < listePlat.size(); i++){
            if (listePlat.get(i).getId() == id){
                unPlat = new Plat (id, listePlat.get(i).getNom());
            }
            else {
                Log.d("Recup plat platDAO", "getPlat: Aucun plat trouvé dans getPlat(int id)");
            }
        }
        return unPlat;
    }

    public Plat getPlat(String nom)
    {
        Plat unPlat = null;
        for (int i = 0; i < listePlat.size(); i++){
            if (listePlat.get(i).getNom().equals(nom)){
                unPlat = new Plat (listePlat.get(i).getId(), nom);
            }
            else {
                Log.d("Recup plat platDAO", "getPlat: Aucun plat trouvé dans getPlat(String nom)");
            }
        }
        return unPlat;
    }

    public void majPlat(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("plats", new JSONArray());
    }
    public static void setListePlat(ArrayList<Plat> l){
        platDAO.listePlat = l;
    }

    public ArrayList<Plat> getPlats()
    {
        majPlat();
        return platDAO.listePlat;
    }

    public void insertPlat(Plat monPlat)
    {
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregPlat", monPlat.converToJSONArray());
        majPlat();
    }

    public void ajouterElementsBddSpinner(Context context, Spinner s_plat){
        List<String> liste = new ArrayList<>();
        ArrayList<Plat> listePlat = this.getPlats();
        liste.add("Selectionnez un plat");
        for (int i = 0 ; i < listePlat.size() ; i++){
            liste.add(listePlat.get(i).getNom());
        }
        liste.add("Nouveau plat");
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, liste);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_plat.setAdapter(adp1);
    }
}
