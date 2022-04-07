package com.example.montauru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CreerEntreeActivity extends AppCompatActivity {
    private static entreeDAO bddEntree;

    private Button b_creerEntree, b_annulerEntree;
    private EditText et_creerEntree;
    private Spinner s_entree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_entree);

        bddEntree = new entreeDAO(this);

        b_creerEntree = findViewById(R.id.b_creeEntree);
        et_creerEntree = findViewById(R.id.et_creeEntree);
        s_entree = findViewById(R.id.s_entree);
        b_annulerEntree = findViewById(R.id.b_annulerEntree);

        b_creerEntree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_creerEntree.length() > 0) {
                    Entree monEntree = new Entree(et_creerEntree.getText().toString());
                    bddEntree.insertEntree(monEntree);
                    Intent monIntent = new Intent(CreerEntreeActivity.this, SplashScreenMenusActivity.class);
                    startActivity(monIntent);
                }
                else
                    Toast.makeText(CreerEntreeActivity.this, "Saisir au moins 1 caractère", Toast.LENGTH_SHORT).show();
            }
        });

        b_annulerEntree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreerEntreeActivity.this, CreerMenuActivity.class));
            }
        });
    }
}