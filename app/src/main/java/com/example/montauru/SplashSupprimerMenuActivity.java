package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashSupprimerMenuActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 2000;
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
                menuDAO bddMenu = new menuDAO(null);
                bddMenu.majMenu();
                Intent monIntent = new Intent(getApplicationContext(), CreerMenuActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}