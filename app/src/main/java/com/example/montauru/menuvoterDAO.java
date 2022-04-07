package com.example.montauru;

import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;

public class menuvoterDAO {
    private static AccesDistant accesDistant;
    private static ArrayList<Menuvoter> listeMenuVoter = new ArrayList<>();

    public menuvoterDAO (Context ct){}

    public Menuvoter getMenuVoter(long id){
        Menuvoter unMenuvoter = null;
        for (int i = 0; i < listeMenuVoter.size(); i++){
            if (listeMenuVoter.get(i).getId() == id){
                unMenuvoter = new Menuvoter(id, listeMenuVoter.get(i).getMois(), listeMenuVoter.get(i).getId_menu1(),
                        listeMenuVoter.get(i).getId_menu2(), listeMenuVoter.get(i).getId_menu3(), listeMenuVoter.get(i).getId_menu4(),
                        listeMenuVoter.get(i).getNb_voteMenu1(), listeMenuVoter.get(i).getNb_voteMenu2(), listeMenuVoter.get(i).getNb_voteMenu3(),
                        listeMenuVoter.get(i).getNb_voteMenu4());
            }
        }
        return unMenuvoter;
    }
    public static void setListeMenuVoter (ArrayList<Menuvoter> l){
        menuvoterDAO.listeMenuVoter = l;
    }
    public void majMenuvoter(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("menuvoter", new JSONArray());
    }
    public void insertMenuvoter(Menuvoter monMenuvoter){
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregMenuvoter", monMenuvoter.converToJSONArray());
        majMenuvoter();
    }

}
