package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SupprimerMenuActivity extends AppCompatActivity {
    private Button b_valider, b_annuler, b_seConnecter;
    private Spinner s_menus;
    private menuDAO bddMenu;
    private static Utilisateur instanceUtilisateur;
    private utilisateurDAO bddUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_menu);

        b_valider = findViewById(R.id.b_validerSupprimer);
        b_annuler = findViewById(R.id.b_annulerSupprimer);
        b_seConnecter = findViewById(R.id.b_seConnecter);
        s_menus = findViewById(R.id.s_menuSupprimer);
        bddMenu = new menuDAO(this);
        bddUtilisateur = new utilisateurDAO(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            instanceUtilisateur = extras.getParcelable("utilisateur");
        }

        bddMenu.ajouterMenuBddSpinner(getApplicationContext(), s_menus);
        b_seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bddUtilisateur.passeEnModeDeconnecte(getApplicationContext(), instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                Intent monIntent = new Intent (getApplicationContext(), SplashScreenActivity.class);
                startActivity(monIntent);
            }
        });

        b_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(getApplicationContext(), CreerMenuActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        });

        b_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomMenu = ((TextView)s_menus.getSelectedView()).getText().toString();
                if (!nomMenu.equals("Aucun")){
                    bddMenu.deleteMenu(bddMenu.getMenu(nomMenu));
                    Intent monIntent = new Intent (getApplicationContext(), SplashSupprimerMenuActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    Toast.makeText(getApplicationContext(), "Menu supprimé", Toast.LENGTH_SHORT).show();
                    startActivity(monIntent);
                }
                else Toast.makeText(getApplicationContext(), "Selectionner un menu", Toast.LENGTH_SHORT).show();
            }
        });

    }
}