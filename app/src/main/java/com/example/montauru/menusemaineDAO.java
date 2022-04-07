package com.example.montauru;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class menusemaineDAO {

    /** Acces distant */
    private static ArrayList<MenuSemaine> listeMenuSemaine = new ArrayList<MenuSemaine>();
    private static AccesDistant accesDistant;

    public menusemaineDAO(Context ct)
    {

    }

    public MenuSemaine getMenuSemaine(int jour)
    {
        MenuSemaine unMenuSemaine = null;
        for (int i = 0; i < listeMenuSemaine.size(); i++){
            if (listeMenuSemaine.get(i).getJour() == jour){
                unMenuSemaine = new MenuSemaine(listeMenuSemaine.get(i).getId(), jour, listeMenuSemaine.get(i).getMenu1(), listeMenuSemaine.get(i).getMenu2());
            }
        }
        return unMenuSemaine;
    }

    public ArrayList<MenuSemaine> getMenuSemaines()
    {
        majMenuSemaine();
        return menusemaineDAO.listeMenuSemaine;
    }

    public void majMenuSemaine(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("menusemaines", new JSONArray());
    }

    public static void setListeMenuSemaine(ArrayList<MenuSemaine> l){
        menusemaineDAO.listeMenuSemaine = l;
    }

    public void insertMenuSemaine(MenuSemaine monMenuSemaine) // insert la première fois puis modifie juste
    {
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregMenusemaine", monMenuSemaine.converToJSONArray());
        majMenuSemaine();
    }

}
