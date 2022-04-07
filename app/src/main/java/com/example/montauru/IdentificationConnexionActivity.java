package com.example.montauru;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class IdentificationConnexionActivity extends AppCompatActivity {
    private final String TAG = "tag";
    private utilisateurDAO bddUtilisateur; //base de donnée
    private Button b_annulerConnexion , b_validerConnexion , b_inscription;
    private EditText et_mailConnexion , et_mdpConnexion , et_nomInscription , et_prenomInscription ,
            et_mailInscription , et_mdpInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification_connexion);

        bddUtilisateur = new utilisateurDAO(this);
        b_annulerConnexion = findViewById(R.id.b_annulerConnexion);
        b_validerConnexion = findViewById(R.id.b_validerConnexion);
        b_inscription = findViewById(R.id.b_inscription);
        et_mailConnexion = findViewById(R.id.et_mailConnexion);
        et_mailInscription = findViewById(R.id.et_mailInscription);
        et_mdpConnexion = findViewById(R.id.et_mdpConnexion);
        et_mdpInscription = findViewById(R.id.et_mdpInscription);
        et_nomInscription = findViewById(R.id.et_nomInscription);
        et_prenomInscription = findViewById(R.id.et_prenomInscription);

        Log.d("kuku", "onCreate: date " + DateOutil.dateSemaineProchaine());

        //annuler la connexion
        b_annulerConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //renvoie sur la main activity
                startActivity(new Intent(IdentificationConnexionActivity.this, MainActivity.class));
            }
        });//fin onClick bouton annuler

        //Valider la connexion
        b_validerConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: validerConnexion");
                if(bddUtilisateur.estInscrit(et_mailConnexion.getText().toString(), et_mdpConnexion.getText().toString())) {
                    Log.d("AG", "onClick: IF ON CLICK");
                    bddUtilisateur.passeEnModeConnecte(IdentificationConnexionActivity.this, et_mailConnexion.getText().toString(), et_mdpConnexion.getText().toString());
                    Intent monIntent = new Intent(IdentificationConnexionActivity.this, SplashIdentificationActivity.class);
                    // renvoi dans l'activité l'utilisateur ' user '
                    monIntent.putExtra("email", et_mailConnexion.getText().toString());
                    monIntent.putExtra("mdp", et_mdpConnexion.getText().toString());
                    startActivity(monIntent);
                }
                else {
                    Toast.makeText(IdentificationConnexionActivity.this, "EMail ou Mot de passe incorrect.",Toast.LENGTH_SHORT).show();
                }
            }
        });//fin onClick bouton Connexion

        //valider l'inscription
        b_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: Inscription");
                if (et_nomInscription.getText().toString().length() > 0 && et_prenomInscription.getText().toString().length() > 0){
                    //verif mdp et mail
                    if (et_mailInscription.length() > 0 && et_mdpInscription.getText().toString().length() > 0){
                        //verifier email correct
                        if (!bddUtilisateur.estInscrit(et_mailInscription.getText().toString(), et_mdpInscription.getText().toString())){
                            Utilisateur user = new Utilisateur(et_mailInscription.getText().toString(), et_mdpInscription.getText().toString(),
                                    et_nomInscription.getText().toString(), et_mdpInscription.getText().toString());
                            Intent monIntent = new Intent (IdentificationConnexionActivity.this, SplashInscriptionActivity.class);
                            monIntent.putExtra("email" , user.getEmail());
                            monIntent.putExtra("mdp", user.getMdp());
                            sendEmail();
                            Boolean insertion = bddUtilisateur.insertUtilisateur(user, IdentificationConnexionActivity.this);
                            if (insertion) startActivity(monIntent);
                        }
                        else {
                            Toast.makeText(IdentificationConnexionActivity.this, "EMail déjà utilisé",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(IdentificationConnexionActivity.this, "Saisir un email et un mdp", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(IdentificationConnexionActivity.this, "Saisir un nom et un prénom", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmail() {
        String mEmail = et_mailInscription.getText().toString();
        String mSubject = "Inscription MontauRu";
        String mMessage = "Bonjour nous confirmons votre inscription.\nVos informations:\nNom: "+et_nomInscription.getText()+"\nPrenom: "+et_prenomInscription.getText()+"\nmail: "+et_mailInscription.getText()+"\nMot de passe: "+et_mdpInscription.getText()+
                "\nCordialement,\nMontauRu.";
        JavaMailAPI javaMailAPI =new JavaMailAPI(this ,mEmail, mSubject, mMessage);
        javaMailAPI.execute();
    }
}