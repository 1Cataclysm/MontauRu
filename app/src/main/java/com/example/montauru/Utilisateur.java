package com.example.montauru;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Parcelable
{
    private String email;
    private String mdp;
    private String nom;
    private String prenom;
    private int estAdmin;
    private int estConnecte;
    private int aVoter;

    public Utilisateur(String e, String m, String n, String p, int a, int ec)
    {
        this.email = e;
        this.mdp = m;
        this.nom = n;
        this.prenom = p;
        this.estAdmin = a;
        this.estConnecte = ec;
        this.aVoter = 0;
    }

    public Utilisateur(String e, String m, String n, String p, int a, int ec, int av){
        this.email = e;
        this.mdp = m;
        this.nom = n;
        this.prenom = p;
        this.estAdmin = a;
        this.estConnecte = ec;
        this.aVoter = av;
    }

    public Utilisateur(String e, String m, String n, String p)
    {
        this.email = e;
        this.mdp = m;
        this.nom = n;
        this.prenom = p;
        this.estAdmin = 0;
        this.estConnecte = 0;
        this.aVoter = 0;
    }

    protected Utilisateur(Parcel in) {
        email = in.readString();
        mdp = in.readString();
        nom = in.readString();
        prenom = in.readString();
        estAdmin = in.readInt();
        estConnecte = in.readInt();
        aVoter = in.readInt();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getMdp() { return mdp; }
    public void setMdp(String m) { this.mdp = m; }
    public String getNom() { return nom; }
    public void setNom(String n) { this.nom = n; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String p) { this.prenom = p; }
    public int getEstAdmin() { return estAdmin; }
    public void setEstAdmin(int a) { this.estAdmin = a; }
    public void setEstConnecte(int e) { this.estConnecte = e; }
    public int getEstConnecte() { return this.estConnecte; }

    public int getaVoter() {
        return aVoter;
    }

    public void setaVoter(int aVoter) {
        this.aVoter = aVoter;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", estAdmin=" + estAdmin +
                ", estConnecte=" + estConnecte +
                ", aVoter=" + aVoter +
                '}';
    }

    public JSONArray converToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(email);
        laListe.add(mdp);
        laListe.add(nom);
        laListe.add(prenom);
        laListe.add(estAdmin);
        laListe.add(estConnecte);
        laListe.add(aVoter);
        return new JSONArray(laListe);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(mdp);
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeInt(estAdmin);
        parcel.writeInt(estConnecte);
        parcel.writeInt(aVoter);
    }
}
