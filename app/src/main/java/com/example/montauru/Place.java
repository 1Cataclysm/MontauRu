package com.example.montauru;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Place {
    private long id;
    private int placeMax, placeDispo;

    public Place (long id, int pm, int pd){
        this.id = id;
        this.placeDispo = pd;
        this.placeMax = pm;
    }

    public Place (int pm, int pd){
        this.placeDispo = pd;
        this.placeMax = pm;
        this.id = 1;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", placeMax=" + placeMax +
                ", placeDispo=" + placeDispo +
                '}';
    }
    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(id);
        laListe.add(placeMax);
        laListe.add(placeDispo);
        return new JSONArray(laListe);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlaceMax(int placeMax) {
        this.placeMax = placeMax;
    }

    public void setPlaceDispo(int placeDispo) {
        this.placeDispo = placeDispo;
    }

    public long getId() {
        return id;
    }

    public int getPlaceMax() {
        return placeMax;
    }

    public int getPlaceDispo() {
        return placeDispo;
    }
}
