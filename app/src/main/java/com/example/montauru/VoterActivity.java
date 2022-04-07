package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class VoterActivity extends AppCompatActivity {
    private TextView mois, tv_entree1, tv_entree2, tv_entree3, tv_plat1, tv_plat2, tv_plat3, tv_dessert1, tv_dessert2, tv_dessert3, tv_entree4, tv_plat4, tv_dessert4;
    private Button b_jeVote , b_modifier, b_accueil, b_menus, b_seConnecter;
    private RadioButton rb_choix1, rb_choix2, rb_choix3, rb_choix4;
    private static Utilisateur instanceUtilisateur;
    private utilisateurDAO bddUtilisateur;
    private menuDAO bddMenu;
    private menuvoterDAO bddMenuvoter;
    private platDAO bddPlat;
    private entreeDAO bddEntre;
    private dessertDAO bddDessert;
    private int choix=0;
    private static String[] moisAnnee = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août","Septembre", "Octobre", "Novembre", "Décembre"};
    private static String[] moisAnneeAnglais = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter);

        mois = findViewById(R.id.tv_mois);
        tv_entree1 = findViewById(R.id.tv_entreeVoter1);
        tv_entree2 = findViewById(R.id.tv_entreeVoter2);
        tv_entree3 = findViewById(R.id.tv_entreeVoter3);
        tv_entree4 = findViewById(R.id.tv_entreeVoter4);
        tv_plat1 = findViewById(R.id.tv_platVoter1);
        tv_plat2 = findViewById(R.id.tv_platVoter2);
        tv_plat3 = findViewById(R.id.tv_platVoter3);
        tv_plat4 = findViewById(R.id.tv_platVoter4);
        tv_dessert1 = findViewById(R.id.tv_dessertVoter1);
        tv_dessert2 = findViewById(R.id.tv_dessertVoter2);
        tv_dessert3 = findViewById(R.id.tv_dessertVoter3);
        tv_dessert4 = findViewById(R.id.tv_dessertVoter4);
        b_jeVote = findViewById(R.id.b_precedentModifierVote);
        b_modifier = findViewById(R.id.b_suivantModifierVote);
        b_accueil = findViewById(R.id.b_accueil);
        b_menus = findViewById(R.id.b_menus);
        b_seConnecter = findViewById(R.id.b_seConnecter);
        rb_choix1 = findViewById(R.id.rb_choix1);
        rb_choix2 = findViewById(R.id.rb_choix2);
        rb_choix3 = findViewById(R.id.rb_choix3);
        rb_choix4 = findViewById(R.id.rb_choix4);
        bddUtilisateur = new utilisateurDAO(this);
        bddEntre = new entreeDAO(this);
        bddPlat = new platDAO(this);
        bddDessert = new dessertDAO(this);
        bddMenu = new menuDAO(this);
        bddMenuvoter = new menuvoterDAO(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
        }
        b_seConnecter.setText("Déconnexion");
        if (instanceUtilisateur.getEstConnecte() == 0){
            startActivity(new Intent(getApplicationContext(), SplashScreenActivity.class));
        }

        b_seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bddUtilisateur.passeEnModeDeconnecte(getApplicationContext(), instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                Intent monIntent = new Intent (getApplicationContext(), SplashScreenActivity.class);
                startActivity(monIntent);
            }
        });

        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH,31);
        java.util.Date jourSi = cal.getTime();
        String moisAnglais = jourSi.toString().substring(4,7);
        for (int i = 0; i < moisAnneeAnglais.length; i++){
            if (moisAnneeAnglais[i].equals(moisAnglais)){
                mois.setText(moisAnnee[i]);
                break;
            }
        }

        b_accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(getApplicationContext(), MainActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        });
        b_menus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(getApplicationContext(), MenusActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        });

        Menuvoter monMenuvoter = bddMenuvoter.getMenuVoter(2);
        tv_entree1.setText(bddEntre.getEntree(bddMenu.getMenu(monMenuvoter.getId_menu1()).getIdEntree()).getNom());
        tv_entree2.setText(bddEntre.getEntree(bddMenu.getMenu(monMenuvoter.getId_menu2()).getIdEntree()).getNom());
        tv_entree3.setText(bddEntre.getEntree(bddMenu.getMenu(monMenuvoter.getId_menu3()).getIdEntree()).getNom());
        tv_entree4.setText(bddEntre.getEntree(bddMenu.getMenu(monMenuvoter.getId_menu4()).getIdEntree()).getNom());

        tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(monMenuvoter.getId_menu1()).getIdPlat()).getNom());
        tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(monMenuvoter.getId_menu2()).getIdPlat()).getNom());
        tv_plat3.setText(bddPlat.getPlat(bddMenu.getMenu(monMenuvoter.getId_menu3()).getIdPlat()).getNom());
        tv_plat4.setText(bddPlat.getPlat(bddMenu.getMenu(monMenuvoter.getId_menu4()).getIdPlat()).getNom());

        tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(monMenuvoter.getId_menu1()).getIdDessert()).getNom());
        tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(monMenuvoter.getId_menu2()).getIdDessert()).getNom());
        tv_dessert3.setText(bddDessert.getDessert(bddMenu.getMenu(monMenuvoter.getId_menu3()).getIdDessert()).getNom());
        tv_dessert4.setText(bddDessert.getDessert(bddMenu.getMenu(monMenuvoter.getId_menu4()).getIdDessert()).getNom());

        rb_choix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_choix2.setChecked(false);
                rb_choix3.setChecked(false);
                rb_choix4.setChecked(false);
                choix = 1;
            }
        });
        rb_choix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_choix1.setChecked(false);
                rb_choix3.setChecked(false);
                rb_choix4.setChecked(false);
                choix = 2;
            }
        });
        rb_choix3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_choix2.setChecked(false);
                rb_choix1.setChecked(false);
                rb_choix4.setChecked(false);
                choix = 3;
            }
        });
        rb_choix4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_choix2.setChecked(false);
                rb_choix3.setChecked(false);
                rb_choix1.setChecked(false);
                choix = 4;
            }
        });
        b_modifier.setVisibility(View.GONE);

        Log.d("avoter", ""+instanceUtilisateur.getaVoter());
        if(instanceUtilisateur.getaVoter() > 0){
            Toast.makeText(getApplicationContext(), "Vous avez déjà voté ce mois pour le menu "+instanceUtilisateur.getaVoter(), Toast.LENGTH_SHORT).show();
            b_jeVote.setEnabled(false);
            if(instanceUtilisateur.getaVoter() == 1) rb_choix1.setChecked(true);
            if(instanceUtilisateur.getaVoter() == 2) rb_choix2.setChecked(true);
            if(instanceUtilisateur.getaVoter() == 3) rb_choix3.setChecked(true);
            if(instanceUtilisateur.getaVoter() == 4) rb_choix4.setChecked(true);
        }
        else {
            b_jeVote.setEnabled(true);
        }

        b_jeVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (choix == 0) {
                    Toast.makeText(getApplicationContext(), "Choisissez un menu", Toast.LENGTH_SHORT).show();
                }
                else {
                    instanceUtilisateur.setaVoter(choix);
                    Log.d("choix", "le choix est mtn : " + instanceUtilisateur.getaVoter());
                    bddUtilisateur.jeVote(instanceUtilisateur);
                    Intent monIntent = new Intent(getApplicationContext(), SplashVoterActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            }
        });


        /**-----------ADMIN-------------------**/
        if (instanceUtilisateur.getEstAdmin() == 1){
            b_modifier.setVisibility(View.VISIBLE);
            b_jeVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Vous ne pouvez pas voter", Toast.LENGTH_SHORT).show();
                }
            });
            b_modifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(getApplicationContext(), VoterModifierActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
        }


    }
}