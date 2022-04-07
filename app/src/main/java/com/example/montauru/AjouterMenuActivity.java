package com.example.montauru;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AjouterMenuActivity extends AppCompatActivity {
    private static int instanceJourSemaine;
    private static Utilisateur instanceUtilisateur;
    private Button b_seConnecter, b_precedent, b_suivant, b_valider, b_annuler;
    private Spinner s_menu1, s_menu2;
    private utilisateurDAO bddUtilisateur;
    private menuDAO bddMenu;
    private TextView tv_jour;
    private static String menu1, menu2;
    private static MenuSemaine menuSemaine;
    private menusemaineDAO bddMenuSemaine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_menu);

        Bundle extras = getIntent().getExtras(); // recupere l'instance de l'activité ayant appelée this
        if(extras != null) { // vérifie si une instance a été envoyé
            Log.d("TAG", "onCreate: Intent extra");
            instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
            Log.d("TAG", "onCreate: lutilisateur recup menus" + instanceUtilisateur.toString());
        }
        instanceJourSemaine = 0; // Par défaut la page affiché sera Lundi ( et ce à chaque retour de la page )
        onRestart();
    }

    protected void onRestart(){
        super.onRestart();
        bddUtilisateur = new utilisateurDAO(this);
        bddMenuSemaine = new menusemaineDAO(this);
        bddMenu = new menuDAO(this);
        b_seConnecter = findViewById(R.id.b_seConnecter);
        b_precedent = findViewById(R.id.b_menuPrecedent);
        b_suivant = findViewById(R.id.b_menuSuivant);
        b_valider = findViewById(R.id.b_valider);
        b_annuler = findViewById(R.id.b_annuler);
        s_menu1 = findViewById(R.id.s_menu1);
        s_menu2 = findViewById(R.id.s_menu2);
        tv_jour = findViewById(R.id.tv_joursSemaine);


        /** ******************* UTILISATEUR ADMIN *************** **/
        if (instanceUtilisateur.getEstConnecte() == 1 && instanceUtilisateur.getEstAdmin() == 1){ // si l'utilisateur est admin

            b_seConnecter.setText("Déconnexion");
            b_seConnecter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bddUtilisateur.passeEnModeDeconnecte(AjouterMenuActivity.this, instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                    instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                    Intent monIntent = new Intent (AjouterMenuActivity.this, SplashScreenActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
            b_annuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(AjouterMenuActivity.this, MenusActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    startActivity(monIntent);
                }
            });
            b_valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(AjouterMenuActivity.this, SplashScreenAjouterMenusActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    Toast.makeText(AjouterMenuActivity.this, "Menu(s) modifié(s)", Toast.LENGTH_SHORT).show();
                    startActivity(monIntent);
                }
            });
            //Initialisation des menus en utilisant la BDD
            bddMenu.ajouterMenuBddSpinner(AjouterMenuActivity.this, s_menu1);
            bddMenu.ajouterMenuBddSpinner(AjouterMenuActivity.this, s_menu2);

            b_precedent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (instanceJourSemaine > 0) {
                        instanceJourSemaine -= 1;
                    }
                    else {
                        instanceJourSemaine = 4;
                    }
                    onRestart();
                }
            });

            b_suivant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (instanceJourSemaine < 4 ) {
                        instanceJourSemaine += 1;
                    }
                    else {
                        instanceJourSemaine = 0;
                    }
                    onRestart();
                }
            });

            Log.d("TAG", "INSTANCE JOUR SEMAINE " + instanceJourSemaine);
            /** INSTANCE DE CHAQUE JOUR **/
                /**Lundi**/
            if (instanceJourSemaine == 0) {
                // Jour LUNDI
                tv_jour.setText(DateOutil.dateSemaineProchaine().get(instanceJourSemaine));
                /*/*/
                // Remet dans le spinner le menu approprié pour le jour
                ArrayList<MenuSemaine> listeMenuSemaine = bddMenuSemaine.getMenuSemaines();
                for (int i = 0; i < bddMenu.getMenus().size(); i++){
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu1().equals(bddMenu.getMenus().get(i).getNom())){
                        s_menu1.setSelection(i);
                    }
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu2().equals(bddMenu.getMenus().get(i).getNom())){
                        s_menu2.setSelection(i);
                    }
                }
                /*/*/
                s_menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 0) {
                            menu1 = s_menu1.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, menu1, s_menu2.getSelectedItem().toString());
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                s_menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 0) {
                            menu2 = s_menu2.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, s_menu1.getSelectedItem().toString(), menu2);
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

                /**Mardi**/
            if (instanceJourSemaine == 1) {
                // JOUR MARDI
                tv_jour.setText(DateOutil.dateSemaineProchaine().get(instanceJourSemaine));
                // Remet dans le spinner le menu approprié pour le jour
                ArrayList<MenuSemaine> listeMenuSemaine = bddMenuSemaine.getMenuSemaines();

                    for (int i = 0; i < bddMenu.getMenus().size(); i++) {
                        if (listeMenuSemaine.get(instanceJourSemaine).getMenu1().equals(bddMenu.getMenus().get(i).getNom())) {
                            s_menu1.setSelection(i);
                        }
                        if (listeMenuSemaine.get(instanceJourSemaine).getMenu2().equals(bddMenu.getMenus().get(i).getNom())) {
                            s_menu2.setSelection(i);
                        }
                    }

                /*/*/
                s_menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 1) {
                            menu1 = s_menu1.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, menu1, s_menu2.getSelectedItem().toString());
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                s_menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 1) {
                            menu2 = s_menu2.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, s_menu1.getSelectedItem().toString(), menu2);
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

                /**Mercredi**/
            if (instanceJourSemaine == 2) {
                // JOUR MERCREDI
                tv_jour.setText(DateOutil.dateSemaineProchaine().get(instanceJourSemaine));
                /*/*/
                // Remet dans le spinner le menu approprié pour le jour
                ArrayList<MenuSemaine> listeMenuSemaine = bddMenuSemaine.getMenuSemaines();
                for (int i = 0; i < bddMenu.getMenus().size(); i++) {
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu1().equals(bddMenu.getMenus().get(i).getNom())) {
                        s_menu1.setSelection(i);
                    }
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu2().equals(bddMenu.getMenus().get(i).getNom())) {
                        s_menu2.setSelection(i);
                    }
                }
                s_menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 2) {
                            menu1 = s_menu1.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, menu1, s_menu2.getSelectedItem().toString());
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                s_menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 2) {
                            menu2 = s_menu2.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, s_menu1.getSelectedItem().toString(), menu2);
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
                /**Jeudi**/
            if (instanceJourSemaine == 3) {
                // JOUR JEUDI
                tv_jour.setText(DateOutil.dateSemaineProchaine().get(instanceJourSemaine));
                /*/*/
                // Remet dans le spinner le menu approprié pour le jour
                ArrayList<MenuSemaine> listeMenuSemaine = bddMenuSemaine.getMenuSemaines();
                for (int i = 0; i < bddMenu.getMenus().size(); i++) {
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu1().equals(bddMenu.getMenus().get(i).getNom())) {
                        s_menu1.setSelection(i);
                    }
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu2().equals(bddMenu.getMenus().get(i).getNom())) {
                        s_menu2.setSelection(i);
                    }
                }
                s_menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 3) {
                            menu1 = s_menu1.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, menu1, s_menu2.getSelectedItem().toString());
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                s_menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 3) {
                            menu2 = s_menu2.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, s_menu1.getSelectedItem().toString(), menu2);
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

                /**Vendredi**/
            if (instanceJourSemaine == 4) {
                // JOUR VENDREDI
                tv_jour.setText(DateOutil.dateSemaineProchaine().get(instanceJourSemaine));
                /*/*/
                // Remet dans le spinner le menu approprié pour le jour
                ArrayList<MenuSemaine> listeMenuSemaine = bddMenuSemaine.getMenuSemaines();
                for (int i = 0; i < bddMenu.getMenus().size(); i++) {
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu1().equals(bddMenu.getMenus().get(i).getNom())) {
                        s_menu1.setSelection(i);
                    }
                    if (listeMenuSemaine.get(instanceJourSemaine).getMenu2().equals(bddMenu.getMenus().get(i).getNom())) {
                        s_menu2.setSelection(i);
                    }
                }
                s_menu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 4) {
                            menu1 = s_menu1.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, menu1, s_menu2.getSelectedItem().toString());
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                s_menu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (instanceJourSemaine == 4) {
                            menu2 = s_menu2.getSelectedItem().toString();
                            menuSemaine = new MenuSemaine(instanceJourSemaine, s_menu1.getSelectedItem().toString(), menu2);
                            bddMenuSemaine.insertMenuSemaine(menuSemaine);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }



        /** PAR PRECOTION -- AU CAS OU L'UTILISATEUR N'EST PAS CONNECTE **/
        if (instanceUtilisateur.getEstConnecte() != 1){
            Intent monIntent = new Intent(AjouterMenuActivity.this, MainActivity.class);
            monIntent.putExtra("utilisateur", instanceUtilisateur);
            startActivity(monIntent);
        }

    }
}
