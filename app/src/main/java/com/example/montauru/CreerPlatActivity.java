package com.example.montauru;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreerPlatActivity extends AppCompatActivity {

    TextView tv_nom;
    EditText et_plat;
    Button b_valier, b_annuler;
    platDAO bddPlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_entree);

        bddPlat = new platDAO(this);
        tv_nom = findViewById(R.id.tv_nom);
        tv_nom.setText("Plat");
        et_plat = findViewById(R.id.et_creeEntree);
        b_valier = findViewById(R.id.b_creeEntree);
        b_annuler = findViewById(R.id.b_annulerEntree);

        b_valier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_plat.length() > 0) {
                    Plat monPlat = new Plat(et_plat.getText().toString());
                    bddPlat.insertPlat(monPlat);
                    Intent monIntent = new Intent(CreerPlatActivity.this, SplashScreenMenusActivity.class);
                    startActivity(monIntent);
                }
                else {
                    Toast.makeText(CreerPlatActivity.this, "Saisir au moins 1 caractère", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(CreerPlatActivity.this, CreerMenuActivity.class);
            }
        });
    }

}
