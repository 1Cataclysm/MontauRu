package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashPlaceActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 2000;
    private static Utilisateur instanceUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
        }

        placeDAO bddPlace = new placeDAO(null);
        bddPlace.majPlace();

        //rediriger vers MainActivity apres 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //demarer une page
                Log.d("Splash zz", ""+instanceUtilisateur.toString());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(intent);

            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}