package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashVoterActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;
    private static Utilisateur instanceUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.d("JEXECUTE ", "--------------------------------------------------------------------------------------------------------");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            instanceUtilisateur = extras.getParcelable("utilisateur");
        }

        //rediriger vers MainActivity apres 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //demarer une page
                menuvoterDAO bddMenuvoter = new menuvoterDAO(null);
                bddMenuvoter.majMenuvoter();
                utilisateurDAO bddUtilisateur = new utilisateurDAO(null);
                bddUtilisateur.majUtilisateur();
                Intent monIntent = new Intent(getApplicationContext(), MainActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}