package com.example.montauru;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreerDessertActivity extends AppCompatActivity {

    private TextView tv_nom;
    private EditText et_dessert;
    private Button b_valier, b_annuler;
    private dessertDAO bddDessert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_entree);

        bddDessert = new dessertDAO(this);
        tv_nom = findViewById(R.id.tv_nom);
        tv_nom.setText("Dessert");
        et_dessert = findViewById(R.id.et_creeEntree);
        b_valier = findViewById(R.id.b_creeEntree);
        b_annuler = findViewById(R.id.b_annulerEntree);

        b_valier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_dessert.length() > 0) {
                    Dessert monDessert = new Dessert(et_dessert.getText().toString());
                    bddDessert.insertDessert(monDessert);
                    Intent monIntent = new Intent(CreerDessertActivity.this, SplashScreenMenusActivity.class);
                    startActivity(monIntent);
                }
                else {
                    Toast.makeText(CreerDessertActivity.this, "Saisir au moins 1 caractère", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monIntent = new Intent(CreerDessertActivity.this, CreerMenuActivity.class);
                startActivity(monIntent);
            }
        });

    }
}
