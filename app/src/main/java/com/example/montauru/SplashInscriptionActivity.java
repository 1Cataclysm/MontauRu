package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashInscriptionActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;
    private static Utilisateur instanceUtilisateur;
    private String email, mdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        utilisateurDAO bddUtilisateur = new utilisateurDAO(null);
        Log.d("JEXECUTE ", "--------------------------------------------------------------------------------------------------------");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
            mdp = extras.getString("mdp");
        }
        bddUtilisateur.majUtilisateur();
        bddUtilisateur.majUtilisateur();

        //rediriger vers MainActivity apres 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //demarer une page
                Log.d("zbeub", email +" "+mdp);
                bddUtilisateur.passeEnModeConnecte(getApplicationContext(),email,mdp);
                Intent intent = new Intent(getApplicationContext(), SplashIdentificationActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("mdp", mdp);
                startActivity(intent);
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}