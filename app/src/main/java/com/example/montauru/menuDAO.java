package com.example.montauru;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class menuDAO
{
    /**ACCES DISTANT**/
    private static AccesDistant accesDistant;
    private static ArrayList<Menu> listeMenu = new ArrayList<Menu>();

    public menuDAO(Context ct)
    {

    }

    public Menu getMenu(long id)
    {
        Menu unMenu = null;
        for (int i = 0; i < menuDAO.listeMenu.size(); i++){
            if (menuDAO.listeMenu.get(i).getId() == id){
                unMenu = new Menu (id, menuDAO.listeMenu.get(i).getNom(),menuDAO.listeMenu.get(i).getIdEntree(),menuDAO.listeMenu.get(i).getIdPlat(),menuDAO.listeMenu.get(i).getIdDessert());
            }
        }
        return unMenu;
    }

    public Menu getMenu(String nom)
    {
        Menu unMenu = null;
        for (int i = 0; i < menuDAO.listeMenu.size(); i++){
            if (menuDAO.listeMenu.get(i).getNom().equals(nom)){
                unMenu = new Menu (menuDAO.listeMenu.get(i).getId(), nom,menuDAO.listeMenu.get(i).getIdEntree(),menuDAO.listeMenu.get(i).getIdPlat(),menuDAO.listeMenu.get(i).getIdDessert());
            }
        }
        return unMenu;
    }

    public ArrayList<Menu> getMenus()
    {
        majMenu();
        return menuDAO.listeMenu;
    }

    public void deleteMenu(Menu monMenu){
       accesDistant = new AccesDistant();
       accesDistant.envoi("deleteMenu", monMenu.converToJSONArray());
       majMenu();
    }

    public Boolean existMenu(Menu leMenu)
    {
        ArrayList<Menu> listeMenu = this.getMenus();
        for (int i = 0; i < listeMenu.size(); i++)
        {
            if ( (listeMenu.get(i).getNom() == leMenu.getNom()) ||( (listeMenu.get(i).getIdEntree() == leMenu.getIdEntree()) &&
                    (listeMenu.get(i).getIdPlat() == leMenu.getIdPlat()) &&
                    (listeMenu.get(i).getIdDessert() == leMenu.getIdDessert())))
            {
                return true;
            }
        }
        return false;
    }

    public void insertMenu (Menu monMenu, Context context)
    {
        if (!existMenu(monMenu))
        {
            accesDistant = new AccesDistant();
            accesDistant.envoi("enregMenu", monMenu.converToJSONArray());
            Toast.makeText(context,"Menu crée", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Menu ou nom éxistant, veuillez le modifier", Toast.LENGTH_SHORT).show();
        }
    }

    public void majMenu(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("menus", new JSONArray());
    }
    public static void setListeMenu(ArrayList<Menu> l){
        menuDAO.listeMenu = l;
    }


    public void ajouterMenuBddSpinner(Context context, Spinner s_menu){
        List<String> liste = new ArrayList<>();
        ArrayList<Menu> listeMenu = this.getMenus();
        for (int i = 0 ; i < listeMenu.size() ; i++){
            liste.add(listeMenu.get(i).getNom());
        }
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, liste);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_menu.setAdapter(adp1);
    }
}
