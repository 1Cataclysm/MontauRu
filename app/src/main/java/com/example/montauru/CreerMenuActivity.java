package com.example.montauru;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

public class CreerMenuActivity extends AppCompatActivity {

    private Spinner s_entree, s_plat, s_dessert;
    private EditText et_creerMenu;
    private Button b_annulerMenu, b_validerMenu, b_seConnecter, b_supprimer;
    private Intent monIntent;
    private entreeDAO bddEntree;
    private platDAO bddPlat;
    private dessertDAO bddDessert;
    private menuDAO bddMenu;
    private Menu monMenu;
    private String t_entree, t_plat, t_dessert;
    private Toast msgErreur;
    private static Utilisateur instanceUtilisateur;
    private utilisateurDAO bddUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cette directive enlève la barre de titre
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Cette directive permet d'enlever la barre de notifications pour afficher l'application en plein écran
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_creer_menu);

        /** INSTANCE UTILISATEUR **/
        Bundle extras = getIntent().getExtras(); // recupere l'instance de l'activité ayant appelée this
        if (extras != null) { // vérifie si une instance a été envoyé
            Log.d("TAG", "onCreate: Intent extra");
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
            Log.d("TAG", "onCreate: lutilisateur recup menus" + instanceUtilisateur.toString());
        }

        /*/*/


        onRestart();

    }
    public void onRestart() {
        super.onRestart();
        if (instanceUtilisateur.getEstConnecte() == 1 && instanceUtilisateur.getEstAdmin() == 1) {
            bddUtilisateur = new utilisateurDAO(this);
            s_entree = findViewById(R.id.s_entree);
            s_plat = findViewById(R.id.s_plat);
            s_dessert = findViewById(R.id.s_dessert);
            bddEntree = new entreeDAO(this);
            bddPlat = new platDAO(this);
            bddDessert = new dessertDAO(this);
            bddMenu = new menuDAO(this);
            et_creerMenu = findViewById(R.id.et_creerNomMenu);
            b_annulerMenu = findViewById(R.id.b_annulerMenu);
            b_validerMenu = findViewById(R.id.b_validerMenu);
            b_seConnecter = findViewById(R.id.b_seConnecter);
            b_seConnecter.setText("Déconnexion");
            b_supprimer = findViewById(R.id.b_supprimerMenu);



            b_seConnecter.setOnClickListener(new View.OnClickListener() { // se déconnecter
                @Override
                public void onClick(View view) {
                    bddUtilisateur.passeEnModeDeconnecte(CreerMenuActivity.this, instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                    instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                    Intent monIntent = new Intent (CreerMenuActivity.this, SplashScreenActivity.class);
                    startActivity(monIntent);
                }
            });

            //Maj de la liste Entree
            bddEntree.ajouterElementsBddSpinner(this, s_entree);
            //Evenement sur le spinner Entree
            s_entree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == adapterView.getCount() - 1) { // si dernier item selectionner ("Nouvelle entrée")
                        //pourSnak = 1;
                        monIntent = new Intent(CreerMenuActivity.this, CreerEntreeActivity.class);
                        monIntent.putExtra("utilisateur", instanceUtilisateur);
                        startActivity(monIntent);

                    }
                    t_entree = ((TextView) view).getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //Maj de la liste Plat
            bddPlat.ajouterElementsBddSpinner(this, s_plat);
            //Evenement sur le spinner Plat
            s_plat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == adapterView.getCount() - 1) { // si dernier item selectionner ("Nouvelle entrée")
                        monIntent = new Intent(CreerMenuActivity.this, CreerPlatActivity.class);
                        monIntent.putExtra("utilisateur", instanceUtilisateur);
                        startActivity(monIntent);
                    }
                    t_plat = ((TextView) view).getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //Maj de la liste Dessert
            bddDessert.ajouterElementsBddSpinner(this, s_dessert);
            //evenement sur le spinner dessert
            s_dessert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == adapterView.getCount() - 1) { // si dernier item selectionner("Nouvelle entrée")
                        monIntent = new Intent(CreerMenuActivity.this, CreerDessertActivity.class);
                        monIntent.putExtra("utilisateur", instanceUtilisateur);
                        startActivity(monIntent);
                    }
                    t_dessert = ((TextView) view).getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //entree
            List<Entree> listeEntree = bddEntree.getEntrees();
            for (int i = 0; i < listeEntree.size(); i++) {
                Log.d("Liste entrée", "Entree bdd " + i + " : " + listeEntree.get(i).toString());
            }

            //Création du MENU
            b_validerMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (t_entree != "Selectionnez une entrée" && t_plat != "Selectionnez un plat" && t_dessert != "Selectionnez un dessert") {
                        if (et_creerMenu.length() >= 0) {
                            Entree monEentree = bddEntree.getEntree(t_entree);
                            Plat monPlat = bddPlat.getPlat(t_plat);
                            Dessert monDessert = bddDessert.getDessert(t_dessert);
                            monMenu = new Menu(""+monEentree.getNom()+"-"+monPlat.getNom()+"-"+monDessert.getNom(), monEentree.getId(), monPlat.getId(), monDessert.getId());
                            bddMenu.insertMenu(monMenu, CreerMenuActivity.this);
                            monIntent = new Intent(CreerMenuActivity.this, MenusActivity.class);
                            monIntent.putExtra("utilisateur", instanceUtilisateur);
                            startActivity(monIntent);
                        } else {
                            msgErreur = Toast.makeText(CreerMenuActivity.this, "Saisir un nom pour le menu d'au moins 4 caractères", Toast.LENGTH_SHORT);
                            msgErreur.show();
                        }
                    } else {
                        //afficher pop up disant selectionner une entree/plat/dessert
                        msgErreur = Toast.makeText(CreerMenuActivity.this, "Selectionnez un(e) entrée/plat/dessert", Toast.LENGTH_LONG);
                        msgErreur.show();
                    }
                }
            });

            b_annulerMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monIntent = new Intent(CreerMenuActivity.this, MenusActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });

            b_supprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(getApplicationContext(), SupprimerMenuActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
        }

        else {
            monIntent = new Intent(CreerMenuActivity.this, MainActivity.class);
            monIntent.putExtra("utilisateur", instanceUtilisateur);
            startActivity(monIntent);
        }
    }
}
