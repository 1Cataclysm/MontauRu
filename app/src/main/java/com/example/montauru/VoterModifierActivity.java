package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class VoterModifierActivity extends AppCompatActivity {
    private Button b_annuler, b_valider, b_accueil, b_menus, b_seConnecter, b_precedent, b_suivant;
    private TextView tv_mois;
    private Spinner s_menu1, s_menu2, s_menu3, s_menu4;
    private utilisateurDAO bddUtilisateur;
    private menuvoterDAO bddMenuvoter;
    private menuDAO bddMenu;
    private static Utilisateur instanceUtilisateur;
    private static int mois=1;
    private static String[] moisAnnee = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août","Septembre", "Octobre", "Novembre", "Décembre"};
    private static String[] moisAnneeAnglais = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"};
    private static Menuvoter leMenuAInserer1, leMenuAInserer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_modifier);

        bddMenuvoter = new menuvoterDAO(this);
        leMenuAInserer1 = bddMenuvoter.getMenuVoter(1);
        leMenuAInserer2 = bddMenuvoter.getMenuVoter(2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
        }

        b_annuler = findViewById(R.id.b_annulerModifierVote);
        b_valider = findViewById(R.id.b_validerModifierVote);
        b_accueil = findViewById(R.id.b_accueil);
        b_menus = findViewById(R.id.b_menus);
        b_seConnecter = findViewById(R.id.b_seConnecter);
        b_precedent = findViewById(R.id.b_precedentModifierVote);
        b_suivant = findViewById(R.id.b_suivantModifierVote);
        tv_mois = findViewById(R.id.tv_moisModifierVote);
        s_menu1 = findViewById(R.id.s_modifierVoteMenu1);
        s_menu2 = findViewById(R.id.s_modifierVoteMenu2);
        s_menu3 = findViewById(R.id.s_modifierVoteMenu3);
        s_menu4 = findViewById(R.id.s_modifierVoteMenu4);

        bddMenu = new menuDAO(this);
        bddMenuvoter = new menuvoterDAO(this);
        bddUtilisateur = new utilisateurDAO(this);
        onResume();
    }
    public void onResume() {
        super.onResume();

        if (mois == 1) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, 0);
            Date jourSi = cal.getTime();
            String moisAnglais = jourSi.toString().substring(4, 7);
            for (int i = 0; i < moisAnneeAnglais.length; i++) {
                Log.d("i", "je verif oui " + i);
                if (moisAnneeAnglais[i].equals(moisAnglais)) {
                    tv_mois.setText(moisAnnee[i]);
                    break;
                }
            }
        }
        else if (mois == 2) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, 31);
            Date jourSi = cal.getTime();
            String moisAnglais = jourSi.toString().substring(4, 7);
            for (int i = 0; i < moisAnneeAnglais.length; i++) {
                Log.d("i", "je verif oui " + i);
                if (moisAnneeAnglais[i].equals(moisAnglais)) {
                    tv_mois.setText(moisAnnee[i]);
                    break;
                }
            }
        }
        bddMenu.ajouterMenuBddSpinner(getApplicationContext(), s_menu1);
        bddMenu.ajouterMenuBddSpinner(getApplicationContext(), s_menu2);
        bddMenu.ajouterMenuBddSpinner(getApplicationContext(), s_menu3);
        bddMenu.ajouterMenuBddSpinner(getApplicationContext(), s_menu4);

        if (mois == 1) {
            s_menu1.setSelection((int) (bddMenu.getMenu(leMenuAInserer1.getId_menu1()).getId())-1);
            s_menu2.setSelection((int) (bddMenu.getMenu(leMenuAInserer1.getId_menu2()).getId())-1);
            s_menu3.setSelection((int) (bddMenu.getMenu(leMenuAInserer1.getId_menu3()).getId())-1);
            s_menu4.setSelection((int) (bddMenu.getMenu(leMenuAInserer1.getId_menu4()).getId())-1);
        } else if (mois == 2) {
            s_menu1.setSelection((int) (bddMenu.getMenu(leMenuAInserer2.getId_menu1()).getId())-1);
            s_menu2.setSelection((int) (bddMenu.getMenu(leMenuAInserer2.getId_menu2()).getId())-1);
            s_menu3.setSelection((int) (bddMenu.getMenu(leMenuAInserer2.getId_menu3()).getId())-1);
            s_menu4.setSelection((int) (bddMenu.getMenu(leMenuAInserer2.getId_menu4()).getId())-1);
        }

        s_menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mois == 1){
                    leMenuAInserer1.setId_menu1(i+1);
                }
                else if (mois == 2){
                    leMenuAInserer2.setId_menu1(i+1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        s_menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mois == 1){
                    leMenuAInserer1.setId_menu2(i+1);
                }
                else if (mois == 2){
                    leMenuAInserer2.setId_menu2(i+1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        s_menu3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mois == 1){
                    leMenuAInserer1.setId_menu3(i+1);
                }
                else if (mois == 2){
                    leMenuAInserer2.setId_menu3(i+1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        s_menu4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mois == 1){
                    leMenuAInserer1.setId_menu4(i+1);
                }
                else if (mois == 2){
                    leMenuAInserer2.setId_menu4(i+1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bddMenuvoter.insertMenuvoter(leMenuAInserer1);
                bddMenuvoter.insertMenuvoter(leMenuAInserer2);
                Intent monIntent = new Intent(getApplicationContext(), SplashModifierMenuVoterActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                Toast.makeText(getApplicationContext(), "Menus à voter modifiés", Toast.LENGTH_SHORT).show();
                startActivity(monIntent);
            }
        });

        b_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mois < 2) {
                    mois += 1;
                    Log.d("mois : ", "" + mois);
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.add(Calendar.DAY_OF_MONTH, 31);
                    Date jourSi = cal.getTime();
                    String moisAnglais = jourSi.toString().substring(4, 7);
                    for (int i = 0; i < moisAnneeAnglais.length; i++) {
                        Log.d("i", "je verif oui " + i);
                        if (moisAnneeAnglais[i].equals(moisAnglais)) {
                            tv_mois.setText(moisAnnee[i]);
                            break;
                        }
                    }
                    onResume();
                }
            }
        });
        b_precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mois > 1) {
                    mois -= 1;
                    Log.d("mois : ", "" + mois);
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.add(Calendar.DAY_OF_MONTH, 0);
                    Date jourSi = cal.getTime();
                    String moisAnglais = jourSi.toString().substring(4, 7);
                    for (int i = 0; i < moisAnneeAnglais.length; i++) {
                        if (moisAnneeAnglais[i].equals(moisAnglais)) {
                            tv_mois.setText(moisAnnee[i]);
                            break;
                        }
                    }
                    onResume();
                }
            }
        });

        b_seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bddUtilisateur.passeEnModeDeconnecte(getApplicationContext(), instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                Intent monIntent = new Intent (getApplicationContext(), SplashScreenActivity.class);
                startActivity(monIntent);
            }
        });
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
        b_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(getApplicationContext(), VoterActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        });

    }
}