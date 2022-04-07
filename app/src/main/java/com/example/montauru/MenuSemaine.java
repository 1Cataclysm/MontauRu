package com.example.montauru;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MenuSemaine {
    private static long count = 0;
    private long id;
    private int jour;
    private String menu1;
    private String menu2;

    public MenuSemaine (long i, int j, String m1, String m2){
        this.id = i;
        this.jour = j;
        this.menu1 = m1;
        this.menu2 = m2;
    }

    public MenuSemaine (int j, String m1, String m2){
        this.setId(++this.count);
        this.jour = j;
        this.menu1 = m1;
        this.menu2 = m2;
    }

    public void setId(long i) { this.id = i; }
    public void setMenu1(String m) { this.menu1 = m; }
    public void setMenu2(String m) { this.menu2 = m; }
    public long getId() { return this.id; }
    public String getMenu1() { return this.menu1; }
    public String getMenu2() { return this.menu2; }
    public void setJour(int j) { this.jour = j; }
    public int getJour() { return this.jour; }

    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(id);
        laListe.add(jour);
        laListe.add(menu1);
        laListe.add(menu2);
        return new JSONArray(laListe);
    }

    @Override
    public String toString()
    {
        return "MenuSemaine[id="+id+", jour="+jour+", menu1="+menu1+", menu2="+menu2+"]";
    }
}
