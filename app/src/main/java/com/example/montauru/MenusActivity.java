package com.example.montauru;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MenusActivity extends AppCompatActivity {
    private Button b_accueil, b_seConnecter, b_modifierMenu, b_creerMenu;
    private static Utilisateur instanceUtilisateur;
    private utilisateurDAO bddUtilisateur;
    private menusemaineDAO bddMenuSemaine;
    private static int instanceJourSemaine = 0;
    private Button b_precedent, b_suivant, b_menu1, b_menu2, b_voter;
    private TextView tv_entree1, tv_plat1, tv_dessert1, tv_jour;
    private menuDAO bddMenu;
    private entreeDAO bddEntree;
    private platDAO bddPlat;
    private dessertDAO bddDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        bddUtilisateur = new utilisateurDAO(this);
        instanceUtilisateur = new Utilisateur(null, null, null, null, 0, 0);

        /** Chose promis, chose dû ! Recupere l'instance ici **/
        Bundle extras = getIntent().getExtras(); // recupere l'instance de l'activité ayant appelée this
        if(extras != null) { // vérifie si une instance a été envoyé
            Log.d("TAG", "onCreate: Intent extra");
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
            Log.d("TAG", "onCreate: lutilisateur recup menus" + instanceUtilisateur.toString());
        }
        bddMenu = new menuDAO(null);
        bddMenu.majMenu();
        bddUtilisateur = new utilisateurDAO(null);
        onRestart();
    }

    public void onRestart(){
        super.onRestart();
        b_modifierMenu = findViewById(R.id.b_modifierMenu);
        b_modifierMenu.setVisibility(View.GONE);
        b_creerMenu = findViewById(R.id.b_creerMenu);
        b_creerMenu.setVisibility(View.GONE);
        b_seConnecter = findViewById(R.id.b_seConnecter);
        b_seConnecter.setText("Connexion");
        bddMenuSemaine = new menusemaineDAO(this);
        b_precedent = findViewById(R.id.b_menuPrecedent);
        b_suivant = findViewById(R.id.b_menuSuivant);
        tv_entree1 = findViewById(R.id.tv_entree1);
        tv_plat1 = findViewById(R.id.tv_plat1);
        tv_dessert1 = findViewById(R.id.tv_dessert1);
        bddMenu = new menuDAO(this);
        bddEntree = new entreeDAO(this);
        bddPlat = new platDAO(this);
        bddDessert = new dessertDAO(this);
        tv_jour = findViewById(R.id.tv_menuJour);
        b_menu1 = findViewById(R.id.b_menu1);
        b_menu2 = findViewById(R.id.b_menu2);
        b_voter = findViewById(R.id.b_voter);

        b_seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenusActivity.this, IdentificationConnexionActivity.class));
            }
        });
        b_accueil = findViewById(R.id.b_accueil);
        b_accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(MenusActivity.this, MainActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        });
        /** ------------------------ GESTION DES MENUS SELON LES JOURS -------------------------- **/

        b_precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (instanceJourSemaine > 0) {
                    instanceJourSemaine -= 1;
                }
                else {
                    instanceJourSemaine = 4;
                }
                onRestart();
            }
        });

        b_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (instanceJourSemaine  < 4) {
                    instanceJourSemaine += 1;
                }
                else {
                    instanceJourSemaine = 0;
                }
                onRestart();
            }
        });
        Log.d("instance", "instance jour semaine : " + instanceJourSemaine);

        // MENU
        if (instanceJourSemaine > (-1) && instanceJourSemaine < 5 ) {
            tv_jour.setText(DateOutil.dateSemaineProchaine().get(instanceJourSemaine));
            tv_entree1.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdEntree()).getNom());
            tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdPlat()).getNom());
            tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdDessert()).getNom());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                b_menu1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.vert_clair));
                b_menu2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
            }
            b_menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        b_menu1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.vert_clair));
                        b_menu2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
                    }
                    if(!(tv_entree1.equals(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdEntree()).getNom()))) {
                        tv_entree1.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdEntree()).getNom());
                        tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdPlat()).getNom());
                        tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu1()).getIdDessert()).getNom());
                        //  b_menu1.setBackgroundColor( R.drawable.arrondir_click);
                        //  b_menu2.setBackgroundColor( R.drawable.arrondir);
                    }
                }
            });

            b_menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        b_menu1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
                        b_menu2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.vert_clair));
                    }
                    if(!(tv_entree1.equals(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu2()).getIdEntree()).getNom()))) {
                        tv_entree1.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu2()).getIdEntree()).getNom());
                        tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu2()).getIdPlat()).getNom());
                        tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(instanceJourSemaine).getMenu2()).getIdDessert()).getNom());
                        //   b_menu1.setBackgroundColor( R.drawable.arrondir);
                        //   b_menu2.setBackgroundColor( R.drawable.arrondir_click);
                    }
                }
            });
        }

        b_voter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Vous n'êtes pas connecté", Toast.LENGTH_LONG).show();
            }
        });

        /** ---------- UTILISATEUR CONNECTE -------------- **/
        if (instanceUtilisateur.getEstConnecte() == 1){ // si l'utilisateur est connecté
            b_seConnecter.setText("Déconnexion");
            b_seConnecter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bddUtilisateur.passeEnModeDeconnecte(MenusActivity.this, instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                    instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                    Intent monIntent = new Intent (MenusActivity.this, SplashScreenActivity.class);
                    startActivity(monIntent);
                }
            });
            b_voter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent (getApplicationContext(), VoterActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
        }
        /** --------------------- UTILISATEUR ADMIN --------------------- **/
        if (instanceUtilisateur.getEstAdmin() == 1 && instanceUtilisateur.getEstConnecte() == 1){
            /** Bouton gerer les menus **/
            //tv_admin.setVisibility(View.VISIBLE);
            b_modifierMenu.setVisibility(View.VISIBLE);
            b_creerMenu.setVisibility(View.VISIBLE);

            b_modifierMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent =(new Intent(MenusActivity.this, SplashModifierMenuActivity.class));
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
            b_creerMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(MenusActivity.this, CreerMenuActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
        }
    }

}
