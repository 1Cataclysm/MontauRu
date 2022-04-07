package com.example.montauru;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenMenusActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 1000;

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

        //rediriger vers MainActivity apres 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //demarer une page
                Intent intent = new Intent(getApplicationContext(),CreerMenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}

