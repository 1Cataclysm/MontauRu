package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        entreeDAO bddEntree = new entreeDAO(null);
        bddEntree.majEntree();
        platDAO bddPlat = new platDAO(null);
        bddPlat.majPlat();
        dessertDAO bddDessert = new dessertDAO(null);
        bddDessert.majDessert();
        menuDAO bddMenu = new menuDAO(null);
        bddMenu.majMenu();
        menusemaineDAO bddMenusemaine = new menusemaineDAO(null);
        bddMenusemaine.majMenuSemaine();
        utilisateurDAO bddUtilisateur = new utilisateurDAO(null);
        bddUtilisateur.majUtilisateur();
        placeDAO bddPlace = new placeDAO(null);
        bddPlace.majPlace();
        menuvoterDAO bddMenuvoter = new menuvoterDAO(null);
        bddMenuvoter.majMenuvoter();


        //rediriger vers MainActivity apres 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //demarer une page
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}