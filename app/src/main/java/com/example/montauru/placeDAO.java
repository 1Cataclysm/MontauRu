package com.example.montauru;

import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;

public class placeDAO {
    private static ArrayList<Place> listePlace = new ArrayList<Place>();
    private static AccesDistant accesDistant;

    public placeDAO (Context ct){

    }

    public Place getPlace (long id){
        Place laPlace = null;
        for (int i = 0; i < listePlace.size(); i++){
            if (listePlace.get(i).getId() == id){
                laPlace = new Place (id, listePlace.get(i).getPlaceMax(), listePlace.get(i).getPlaceDispo());
            }
        }
        return laPlace;
    }
    public ArrayList<Place> getPlaces (){
        majPlace();
        return placeDAO.listePlace;
    }
    public void majPlace(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("place", new JSONArray());
    }
    public void insertPlace(Place laPlace){
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregPlace", laPlace.converToJSONArray());
        majPlace();
    }
    public static void setListePlace(ArrayList<Place> l){
        placeDAO.listePlace = l;
    }

}
