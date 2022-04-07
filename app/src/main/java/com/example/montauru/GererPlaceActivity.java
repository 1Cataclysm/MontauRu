package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GererPlaceActivity extends AppCompatActivity {
    private Button b_annuler, b_valider, b_moins;
    private EditText et_placeMax, et_placeDispo;
    private placeDAO bddPlace;
    private static Utilisateur instanceUtilisateur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_place);

        b_annuler = findViewById(R.id.b_annulerPlace);
        b_valider = findViewById(R.id.b_validerPlace);
        et_placeDispo = findViewById(R.id.et_placeDispo);
        et_placeMax = findViewById(R.id.et_placeMax);
        bddPlace = new placeDAO(this);
        Place laPlace = bddPlace.getPlace(1);
        et_placeDispo.setText(""+laPlace.getPlaceDispo());
        et_placeMax.setText(""+laPlace.getPlaceMax());
        b_moins = findViewById(R.id.b_plusPlace);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
        }

        b_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(et_placeDispo.getText().toString()) < 0 || Integer.valueOf(et_placeDispo.getText().toString()) > Integer.valueOf(et_placeMax.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Saisir une valeur inférieur au places max", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("Splash zz", ""+instanceUtilisateur.toString());
                    Place laPlace = new Place (1, Integer.valueOf(et_placeMax.getText().toString()),Integer.valueOf(et_placeDispo.getText().toString()));
                    Log.d("llu", "demande isnertion la place : " + laPlace.getPlaceMax() + " " + laPlace.getPlaceDispo());
                    bddPlace.insertPlace(laPlace);
                    Toast.makeText(getApplicationContext(), "Modification faite avec succès", Toast.LENGTH_LONG);
                    Intent monIntent = new Intent (getApplicationContext(), SplashPlaceActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            }
        });

        b_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(getApplicationContext(),MainActivity.class);
                monIntent.putExtra("utilisateur", instanceUtilisateur);
                startActivity(monIntent);
            }
        });

        b_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = Integer.valueOf(et_placeDispo.getText().toString())-1;
                et_placeDispo.setText(String.valueOf(t));
            }
        });
    }
}