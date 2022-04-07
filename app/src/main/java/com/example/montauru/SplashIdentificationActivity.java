package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SplashIdentificationActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 1000;
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

        //rediriger vers MainActivity apres 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //demarer une page
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                instanceUtilisateur = bddUtilisateur.getUtilisateur(email, mdp);
                if (instanceUtilisateur != null){
                    Log.d("mimoune", "run: " + instanceUtilisateur.toString());
                    intent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(intent);
                }
                else {
                    startActivity(new Intent (getApplicationContext(), SplashScreenActivity.class));
                }

            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}