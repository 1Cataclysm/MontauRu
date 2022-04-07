package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashModifierMenuActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 1000;
    private static Utilisateur instanceUtilisateur;
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
                Bundle extras = getIntent().getExtras(); // recupere l'instance de l'activité ayant appelée this
                if(extras != null) { // vérifie si une instance a été envoyé
                    Log.d("TAG", "onCreate: Intent extra");
                    instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
                    Log.d("TAG", "onCreate: lutilisateur recup menus" + instanceUtilisateur.toString());
                }
                //demarer une page
                Intent intent = new Intent(getApplicationContext(),AjouterMenuActivity.class);
                intent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}