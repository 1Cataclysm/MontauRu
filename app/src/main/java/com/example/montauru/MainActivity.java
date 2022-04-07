package com.example.montauru;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
        // instance de la page pour savoir qui est dessus :
                                                    // utilisateur / utilisateur co / admin
        private static int instanceConnexion;
        private ImageView iv_entree2;
        private ImageView iv_plat2;
        private ImageView iv_dessert2;
        private Dialog dialog;
        private Button b_seConnecter;
        private Button b_menus, b_accueil, b_voter, b_plus;
        private utilisateurDAO bddUtilisateur;
        private menusemaineDAO bddMenuSemaine;
        private entreeDAO bddEntree;
        private platDAO bddPlat;
        private dessertDAO bddDessert;
        private menuDAO bddMenu;
        private TextView tv_entree, tv_plat1, tv_dessert1, tv_jour;
        private static String[] moisAnnee;
        private static String mois;
        private TextView tv_entree2, tv_plat2, tv_dessert2;
        private CarouselView carouselView;
        private int[] sampleImages = {R.drawable.entrees, R.drawable.plats, R.drawable.desserts};
        private ImageView iv_setting;
        private TextView tv_placeDispo, tv_placeMax;
        private ProgressBar pb_places;
        private TextView tv_menu2;

        private static Utilisateur instanceUtilisateur = new Utilisateur(null, null, null, null, 0, 0, 0);
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            /***************************RECUP L'INSTANCE DE L'UTILISATEUR DES AUTRES ACTIVITES************************/
            /**********************************************************************************************************/
            instanceConnexion = 0; // déclarer ici car ne redeviendra jamais 0
            Log.d("eeeeeeeeeeeee", "Le on Create relancé: ");
            Log.d("AAAAAAAAAAAAAA", "XXXXXXXXXXXXXXXXXXXXXXXXX" + utilisateurDAO.listeUtilisateur);

            onRestart();
        }

        @SuppressLint("ResourceAsColor")
        public void onRestart(){
            super.onRestart();

            bddMenu = new menuDAO(this);
            bddEntree = new entreeDAO(this);
            bddPlat = new platDAO(this);
            bddDessert = new dessertDAO(this);
            bddMenuSemaine = new menusemaineDAO(this);
            b_menus = findViewById(R.id.b_menus);
            b_accueil = findViewById(R.id.b_accueil);
            b_accueil.setFocusable(true);
            b_accueil.setActivated(false);
            instanceUtilisateur = new Utilisateur(null, null, null, null, 0, 0);
            b_seConnecter = findViewById(R.id.b_seConnecter);
            bddUtilisateur = new utilisateurDAO(this);
            tv_jour = findViewById(R.id.tv_accueilJour);
            carouselView = findViewById(R.id.carouselView);
            tv_placeDispo = findViewById(R.id.tv_placesDispo);
            tv_placeMax = findViewById(R.id.tv_placeMax);
            iv_setting = findViewById(R.id.iv_settings);
            pb_places = findViewById(R.id.pb_places);
            Log.d("9898989898", bddEntree.getEntrees().toString());
            Log.d("9898989898", bddPlat.getPlats().toString());
            Log.d("9898989898", bddMenu.getMenus().toString());

            /***********************        Ajout iv clickable Benjamin        ***************************/
            iv_entree2 = findViewById(R.id.iv_entree2);
            iv_plat2 = findViewById(R.id.iv_plat2);
            iv_dessert2 = findViewById(R.id.iv_dessert2);
            dialog = new Dialog(MainActivity.this);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.color.cardview_light_background));
            }
            dialog.setCancelable(true);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

            /** ------- PLACES -- **/
            iv_setting.setVisibility(View.GONE);
            placeDAO bddPlace = new placeDAO(this);
            Place laPlace = bddPlace.getPlace(1);
            Log.d("laPlace", laPlace.toString());
            tv_placeMax.setText(""+laPlace.getPlaceMax());
            tv_placeDispo.setText(""+laPlace.getPlaceDispo());
            pb_places.setMax(laPlace.getPlaceMax());
            pb_places.setProgress(laPlace.getPlaceDispo());

            /** ---------------------------- INIT POUR IMAGE VIEW -------------------------------- **/

            bddEntree = new entreeDAO(this);
            bddPlat = new platDAO(this);
            bddMenu = new menuDAO(this);
            bddMenuSemaine = new menusemaineDAO(this);

            moisAnnee = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août","Septembre", "Octobre", "Novembre", "Décembre"};
            Log.d(TAG, "JE suis arrivé ici");
            Calendar instanceDate = Calendar.getInstance();
            mois = moisAnnee[instanceDate.get(Calendar.MONTH)];
            GregorianCalendar calendar =new GregorianCalendar();
            calendar.setTime(new Date());
            int today =calendar.get(calendar.DAY_OF_WEEK);
            Log.d(TAG, "LE JOUR ENFIN ? " + today);

            switch (today) {
                case GregorianCalendar.MONDAY:
                    //on est lundi
                    Log.d(TAG, "LUNDI");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(0));
                    break;
                case GregorianCalendar.TUESDAY:
                    //on est mardi
                    Log.d(TAG, "MARDI");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(1));
                    break;
                case GregorianCalendar.WEDNESDAY:
                    Log.d(TAG, "MERCREDI");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(2));
                    break;
                case GregorianCalendar.THURSDAY:
                    Log.d(TAG, "JEUDI");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(3));
                    break;
                case GregorianCalendar.FRIDAY:
                    Log.d(TAG, "VENDREDI");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(4));
                    break;
                case GregorianCalendar.SATURDAY:
                    Log.d(TAG, "samedi");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(0));
                    break;
                case GregorianCalendar.SUNDAY:
                    Log.d(TAG, "JE SUIS DIMANCHE ENTREE");
                    tv_jour.setText(DateOutil.dateSemaineProchaine().get(0));
                    break;
                default:
                    //ça devrait pas erreur
                    break;
            }

            /** ------------------------------ GESTION IMAGE VIEW SELON LA DATE DU JOUR --------------------------------- **/
            carouselView.setPageCount(sampleImages.length);
            carouselView.setImageListener(imageListener);

            carouselView.setImageClickListener(new ImageClickListener() {
                @Override
                public void onClick(int position) {
                    Log.d("click","+++++++++++++++++++++++++++++++++++++++++++");
                    Log.d("Recuperation item","+++++++++++++++++++++++++++++++++++++++++++"+carouselView.getCurrentItem());
                    switch (carouselView.getCurrentItem()){
                        case 0:
                            dialog.setContentView(R.layout.activity_popup_entree);
                            dialog.show();
                            tv_entree = dialog.findViewById(R.id.tv_entreepop1);
                            tv_entree2 = dialog.findViewById(R.id.tv_entreepop2);

                            switch (today) {
                                case GregorianCalendar.MONDAY:
                                    //on est lundi
                                    Log.d(TAG, "LUNDI");
                                    Log.d(TAG, "onClick: " + bddMenuSemaine.getMenuSemaine(0).getMenu1());
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdEntree()).getNom());
                                    break;
                                case GregorianCalendar.TUESDAY:
                                    //on est mardi
                                    Log.d(TAG, "MARDI");
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(1).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(1).getMenu2()).getIdEntree()).getNom());

                                    break;
                                case GregorianCalendar.WEDNESDAY:
                                    Log.d(TAG, "MERCREDI");
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(2).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(2).getMenu2()).getIdEntree()).getNom());

                                    break;
                                case GregorianCalendar.THURSDAY:
                                    Log.d(TAG, "JEUDI");
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(3).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(3).getMenu2()).getIdEntree()).getNom());

                                    break;
                                case GregorianCalendar.FRIDAY:
                                    Log.d(TAG, "VENDREDI");
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(4).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(4).getMenu2()).getIdEntree()).getNom());

                                    break;
                                case GregorianCalendar.SATURDAY:
                                    Log.d(TAG, "samedi");
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdEntree()).getNom());

                                    break;
                                case GregorianCalendar.SUNDAY:
                                    Log.d(TAG, "JE SUIS DIMANCHE ENTREE");
                                    tv_entree.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdEntree()).getNom());
                                    tv_entree2.setText(bddEntree.getEntree(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdEntree()).getNom());
                                    break;
                                default:
                                    //ça devrait pas erreur
                                    break;
                            }
                            break;
                        case 1:
                            dialog.setContentView(R.layout.activity_popup_plat);
                            dialog.show();
                            tv_plat1 = dialog.findViewById(R.id.tv_platpop1);
                            tv_plat2 = dialog.findViewById(R.id.tv_platpop2);
                            switch (today) {
                                case GregorianCalendar.MONDAY:
                                    //on est lundi
                                    Log.d(TAG, "LUNDI");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdPlat()).getNom());
                                    break;
                                case GregorianCalendar.TUESDAY:
                                    //on est mardi
                                    Log.d(TAG, "MARDI");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(1).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(1).getMenu2()).getIdPlat()).getNom());
                                    break;
                                case GregorianCalendar.WEDNESDAY:
                                    Log.d(TAG, "MERCREDI");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(2).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(2).getMenu2()).getIdPlat()).getNom());
                                    break;
                                case GregorianCalendar.THURSDAY:
                                    Log.d(TAG, "JEUDI");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(3).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(3).getMenu2()).getIdPlat()).getNom());
                                    break;
                                case GregorianCalendar.FRIDAY:
                                    Log.d(TAG, "VENDREDI");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(4).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(4).getMenu2()).getIdPlat()).getNom());
                                    break;
                                case GregorianCalendar.SATURDAY:
                                    Log.d(TAG, "samedi");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdPlat()).getNom());
                                    break;
                                case GregorianCalendar.SUNDAY:
                                    Log.d(TAG, "JE SUIS DIMANCHE PLAT");
                                    tv_plat1.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdPlat()).getNom());
                                    tv_plat2.setText(bddPlat.getPlat(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdPlat()).getNom());
                                    break;
                                default:
                                    //ça devrait pas erreur
                                    break;
                            }
                            break;
                        default:
                            dialog.setContentView(R.layout.activity_popup_dessert);
                            dialog.show();
                            tv_dessert1 = dialog.findViewById(R.id.tv_dessertpop1);
                            tv_dessert2 = dialog.findViewById(R.id.tv_dessertpop2);
                            switch (today) {
                                case GregorianCalendar.MONDAY:
                                    //on est lundi
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdDessert()).getNom());
                                    break;
                                case GregorianCalendar.TUESDAY:
                                    //on est mardi
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(1).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(1).getMenu2()).getIdDessert()).getNom());
                                    break;
                                case GregorianCalendar.WEDNESDAY:
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(2).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(2).getMenu2()).getIdDessert()).getNom());
                                    break;
                                case GregorianCalendar.THURSDAY:
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(3).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(3).getMenu2()).getIdDessert()).getNom());
                                    break;
                                case GregorianCalendar.FRIDAY:
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(4).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(4).getMenu2()).getIdDessert()).getNom());
                                    break;
                                case GregorianCalendar.SATURDAY:
                                    Log.d(TAG, "onClick: Samedi");
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdDessert()).getNom());
                                    break;
                                case GregorianCalendar.SUNDAY:
                                    Log.d(TAG, "onClick: Dimanche");
                                    tv_dessert1.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu1()).getIdDessert()).getNom());
                                    tv_dessert2.setText(bddDessert.getDessert(bddMenu.getMenu(bddMenuSemaine.getMenuSemaine(0).getMenu2()).getIdDessert()).getNom());
                                    break;
                                default:
                                    //ça devrait pas erreur
                                    break;
                            }
                            break;

                    }
                }
            });

            /** ------- Page **/

            b_seConnecter.setText("Connexion");

            if (instanceConnexion == 0) {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    instanceConnexion++;
                    instanceUtilisateur = extras.getParcelable("utilisateur"); // recupere l'instance de l'utilisateur
                }
            }
            b_voter = findViewById(R.id.b_voter);
            b_voter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Vous n'êtes pas connecté", Toast.LENGTH_LONG).show();
                }
            });

            b_menus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(MainActivity.this, MenusActivity.class);
                    monIntent.putExtra("utilisateur", instanceUtilisateur);
                    Log.d("LA JE RENVOI ACCUEIL", " : : : " + instanceUtilisateur.toString());
                    startActivity(monIntent);
                }
            });
            b_seConnecter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent monIntent = new Intent(MainActivity.this, IdentificationConnexionActivity.class);
                    startActivity(monIntent);
                }
            });
            /** -------------------------- UTILISATEUR CONNECTE -------------------------------- **/
            if (instanceUtilisateur.getEstConnecte() == 1){
                b_seConnecter.setText("Déconnexion");
                b_seConnecter.setOnClickListener(new View.OnClickListener() { // SE DECONNECTER
                    @Override
                    public void onClick(View view) {
                        bddUtilisateur.passeEnModeDeconnecte(MainActivity.this, instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                        instanceUtilisateur = bddUtilisateur.getUtilisateur(instanceUtilisateur.getEmail(), instanceUtilisateur.getMdp());
                        startActivity(new Intent(MainActivity.this, SplashScreenActivity.class));
                    }
                });
                b_voter = findViewById(R.id.b_voter);
                b_voter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent monIntent = new Intent (getApplicationContext(), VoterActivity.class);
                        monIntent.putExtra("utilisateur", instanceUtilisateur);
                        startActivity(monIntent);
                    }
                });
            }
            /** ------------------------- UTILISATEUR ADMIN ------------------------------------- **/
            if (instanceUtilisateur.getEstConnecte() == 1 && instanceUtilisateur.getEstAdmin() == 1) {
                /**
                 * Gerer les places disponibles : V2
                 */
                iv_setting.setVisibility(View.VISIBLE);
                iv_setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent monIntent = new Intent(getApplicationContext(), GererPlaceActivity.class);
                        monIntent.putExtra("utilisateur", instanceUtilisateur);
                        startActivity(monIntent);
                    }
                });
            }

            /**------------GAGNANT DU MOIS--------------**/
            iv_entree2 = findViewById(R.id.iv_entree2);
            iv_plat2 = findViewById(R.id.iv_plat2);
            iv_dessert2 = findViewById(R.id.iv_dessert2);

            menuvoterDAO bddMenuvoter = new menuvoterDAO(this);
            Menuvoter mv1 = bddMenuvoter.getMenuVoter(1);
            int nbVote1 = mv1.getNb_voteMenu1();
            int nbVote2 = mv1.getNb_voteMenu2();
            int nbVote3 = mv1.getNb_voteMenu3();
            int nbVote4 = mv1.getNb_voteMenu4();
            int[] nbVote = {nbVote1, nbVote2, nbVote3, nbVote4};
            int max = nbVote[0]; int l = 0;
            for (int i = 0; i < nbVote.length; i++) {
                if (nbVote[i] > max) {
                    max = nbVote[i];
                    l = i; // recuperer l'indice du plus haut
                }
            }
            l++; // incrémente pour que ce soit correct
            if (l == 1) l = mv1.getId_menu1(); // l contient mtn l'id du menu qui faut afficher
            else if (l == 2) l = mv1.getId_menu2(); // l contient mtn l'id du menu qui faut afficher
            else if (l == 3) l = mv1.getId_menu3(); // l contient mtn l'id du menu qui faut afficher
            else if (l == 4) l = mv1.getId_menu4(); // l contient mtn l'id du menu qui faut afficher

            Menu menuGagnant = bddMenu.getMenu(l);
            Log.d(TAG, "onRestart: " + menuGagnant.getIdEntree());
            Log.d(TAG, "onRestart: " + bddEntree.getEntrees());
            Log.d(TAG, "onRestart: " + bddPlat.getPlats());
            Log.d(TAG, "onRestart: " + bddDessert.getDesserts());

            iv_entree2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.setContentView(R.layout.activity_popup_entree);
                    dialog.show();
                    tv_entree = dialog.findViewById(R.id.tv_entreepop1);
                    tv_entree2 = dialog.findViewById(R.id.tv_entreepop2);
                    tv_entree2.setVisibility(View.GONE);
                    tv_menu2 = dialog.findViewById(R.id.tv_menu2);
                    tv_menu2.setVisibility(View.GONE);
                    tv_entree.setText(bddEntree.getEntree(menuGagnant.getIdEntree()).getNom());
                }
            });
            iv_plat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.setContentView(R.layout.activity_popup_plat);
                    dialog.show();
                    tv_plat1 = dialog.findViewById(R.id.tv_platpop1);
                    tv_plat2 = dialog.findViewById(R.id.tv_platpop2);
                    tv_plat2.setVisibility(View.GONE);
                    tv_menu2 = dialog.findViewById(R.id.tv_menu22);
                    tv_menu2.setVisibility(View.GONE);
                    tv_plat1.setText(bddPlat.getPlat(menuGagnant.getIdPlat()).getNom());
                }
            });
            iv_dessert2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.setContentView(R.layout.activity_popup_dessert);
                    dialog.show();
                    tv_dessert1 = dialog.findViewById(R.id.tv_dessertpop1);
                    tv_dessert2 = dialog.findViewById(R.id.tv_dessertpop2);
                    tv_dessert2.setVisibility(View.GONE);
                    tv_menu2 = dialog.findViewById(R.id.tv_menu222);
                    tv_menu2.setVisibility(View.GONE);
                    tv_dessert1.setText(bddDessert.getDessert(menuGagnant.getIdDessert()).getNom());
                }
            });

        }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}