package com.example.montauru;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags;

public class utilisateurDAO
{
    /** Acces distant **/
    private static AccesDistant accesDistant;
    public static ArrayList<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();

    public utilisateurDAO(Context ct)
    {

    }

    public Utilisateur getUtilisateur(String email, String mdp)
    {
        majUtilisateur();
        Utilisateur unUtilisateur = null;
        for (int i = 0; i < listeUtilisateur.size(); i++){
            if (listeUtilisateur.get(i).getEmail().equals(email) && listeUtilisateur.get(i).getMdp().equals(mdp)){
                unUtilisateur = new Utilisateur(email, mdp, listeUtilisateur.get(i).getNom(), listeUtilisateur.get(i).getPrenom(),
                        listeUtilisateur.get(i).getEstAdmin(), listeUtilisateur.get(i).getEstConnecte(), listeUtilisateur.get(i).getaVoter());
            }
        }
        return unUtilisateur;
    }

    public void majUtilisateur(){
        accesDistant = new AccesDistant();
        accesDistant.envoi("utilisateurs", new JSONArray());
    }
    public static void setListeUtilisateur(ArrayList<Utilisateur> l){
        utilisateurDAO.listeUtilisateur = l;
    }

    public ArrayList<Utilisateur> getUtilisateurs()
    {
        majUtilisateur();
        return utilisateurDAO.listeUtilisateur;
    }

    public Boolean exitsEmailUtilisateur(Utilisateur utilisateur){
        for (int i = 0; i < listeUtilisateur.size() ; i++){
            if (listeUtilisateur.get(i).getEmail().equals(utilisateur.getEmail())){
                return true;
            }
        }
        return false;
    }

    public Boolean insertUtilisateur(Utilisateur utilisateur, Context context){
        if (!exitsEmailUtilisateur(utilisateur))
            {
                accesDistant = new AccesDistant();
                accesDistant.envoi("enregUtilisateur", utilisateur.converToJSONArray());
                Log.d(TAG, "insertUtilisateur: " + listeUtilisateur.toString());
                Toast.makeText(context, "Compte crée", Toast.LENGTH_SHORT).show();
                Log.d("Insert Utilisateur", "insertUtilisateur: Insertion FAITE pour l'utilisateur : " + utilisateur.getEmail() + ", "+ utilisateur.getMdp());
                return true;
            }
        return false;
    }

    public void passeEnModeConnecte(Context context, String email, String mdp) {
        accesDistant = new AccesDistant();
        Utilisateur user = getUtilisateur(email, mdp);
        boolean code = false;
        Intent monIntent;
        try {
            accesDistant.envoi("enregConnecte", user.converToJSONArray());
            majUtilisateur();
            Toast.makeText(context, "Connexion résussie", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "passeEnModeConnecte: de l'utilisateur : " + this.getUtilisateur(email, mdp).toString());
            code = true;
        } catch (java.lang.NullPointerException e){
            Toast.makeText(context, "Votre compte est crée tentez de vous connecter", Toast.LENGTH_LONG).show();
            code = false;
        }
        if (!code)
        {
            monIntent = new Intent (context, SplashScreenActivity.class);
            monIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(monIntent);
        }
    }
    public void passeEnModeDeconnecte(Context context, String email, String mdp){
        accesDistant = new AccesDistant();
        Utilisateur user = getUtilisateur(email ,mdp);
        accesDistant.envoi("enregDeconnecte", user.converToJSONArray());
        majUtilisateur();
        Toast.makeText(context, "Déconnexion résussie", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "passeEnModeDeconnecte: de l'utilisateur : " + this.getUtilisateur(email,mdp).toString());
    }

    public Boolean estInscrit(String email, String mdp){
        Utilisateur user = getUtilisateur(email, mdp);
        if (user == null) {
            Log.d(TAG, "estInscrit: NON");
            return false;
        }
        Log.d(TAG, "estInscrit: OUI");
        return true;
    }

    public void jeVote(Utilisateur user){
        Log.d(TAG, "jeVote: luser " + user.toString() + " va voté");
        accesDistant = new AccesDistant();
        accesDistant.envoi("enregAVoter", user.converToJSONArray());
    }
}
