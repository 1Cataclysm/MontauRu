package com.example.montauru;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Menuvoter {
    long id;
    int mois, id_menu1, id_menu2, id_menu3, id_menu4, nb_voteMenu1, nb_voteMenu2, nb_voteMenu3, nb_voteMenu4;

    @Override
    public String toString() {
        return "Menuvoter{" +
                "id=" + id +
                ", mois=" + mois +
                ", id_menu1=" + id_menu1 +
                ", id_menu2=" + id_menu2 +
                ", id_menu3=" + id_menu3 +
                ", id_menu4=" + id_menu4 +
                ", nb_voteMenu1=" + nb_voteMenu1 +
                ", nb_voteMenu2=" + nb_voteMenu2 +
                ", nb_voteMenu3=" + nb_voteMenu3 +
                ", nb_voteMenu4=" + nb_voteMenu4 +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getId_menu1() {
        return id_menu1;
    }

    public void setId_menu1(int id_menu1) {
        this.id_menu1 = id_menu1;
    }

    public int getId_menu2() {
        return id_menu2;
    }

    public void setId_menu2(int id_menu2) {
        this.id_menu2 = id_menu2;
    }

    public int getId_menu3() {
        return id_menu3;
    }

    public void setId_menu3(int id_menu3) {
        this.id_menu3 = id_menu3;
    }

    public int getId_menu4() {
        return id_menu4;
    }

    public void setId_menu4(int id_menu4) {
        this.id_menu4 = id_menu4;
    }

    public int getNb_voteMenu1() {
        return nb_voteMenu1;
    }

    public void setNb_voteMenu1(int nb_voteMenu1) {
        this.nb_voteMenu1 = nb_voteMenu1;
    }

    public int getNb_voteMenu2() {
        return nb_voteMenu2;
    }

    public void setNb_voteMenu2(int nb_voteMenu2) {
        this.nb_voteMenu2 = nb_voteMenu2;
    }

    public int getNb_voteMenu3() {
        return nb_voteMenu3;
    }

    public void setNb_voteMenu3(int nb_voteMenu3) {
        this.nb_voteMenu3 = nb_voteMenu3;
    }

    public int getNb_voteMenu4() {
        return nb_voteMenu4;
    }

    public void setNb_voteMenu4(int nb_voteMenu4) {
        this.nb_voteMenu4 = nb_voteMenu4;
    }

    public Menuvoter(long i, int m, int i_1, int i_2, int i_3, int i_4, int nb_1, int nb_2, int nb_3, int nb_4){
        this.id = i;
        mois = m;
        id_menu1 = i_1;
        id_menu2 = i_2;
        id_menu3 = i_3;
        id_menu4 = i_4;
        nb_voteMenu1 = nb_1;
        nb_voteMenu2 = nb_2;
        nb_voteMenu3 = nb_3;
        nb_voteMenu4 = nb_4;
    }
    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(id);
        laListe.add(mois);
        laListe.add(id_menu1);
        laListe.add(id_menu2);
        laListe.add(id_menu3);
        laListe.add(id_menu4);
        laListe.add(nb_voteMenu1);
        laListe.add(nb_voteMenu2);
        laListe.add(nb_voteMenu3);
        laListe.add(nb_voteMenu4);
        return new JSONArray(laListe);
    }
}
