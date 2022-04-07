package com.example.montauru;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccesDistant implements AsyncResponse {

    // constante
    /**
     * modifier l'adresse ipv4 pour passer au serveur distant de l'hebergeur
     */
    private static final String SERVEURADDR = "http://192.168.1.160/montauru/serveurmontauru.php";

    public AccesDistant() {
        super();
    }
    /**
     * retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        //Log.d("Serveur", "********************"+output);
        // découpage du message ressu
        String[] message = output.split("%");
        // dans message[0] : "enreg", "dernier", "erreur"
        // dans message[1] : reste du message
        Log.d("PROCESS " , "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"); //ll
        Log.d("PROCESS OUTPUT", output);
        Log.d("INPUT ", message[0]);
        //s'il y a 2 cases
        if(message.length > 1){
            if (message[0].equals("enregUtilisateur")){
                Log.d("****************", "************************************************************************************************: ");
                Log.d("enregUtilisateur", "*******************"+message[1]);
            }
            else {
                /** GESTION DE LA REQUETE PHP **/
                /**Entree**/
                if(message[0].equals("entrees")){
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Entree> listeEntree = new ArrayList<Entree>();

                        for (int i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            int id = obj.getInt("id");
                            String nom = obj.getString("nom");
                            listeEntree.add(new Entree(id, nom));
                        }
                        entreeDAO.setListeEntree(listeEntree);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /**Plat**/
                if(message[0].equals("plats")){
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Plat> listePlat = new ArrayList<Plat>();
                        for (int i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            int id = obj.getInt("id");
                            String nom = obj.getString("nom");
                            listePlat.add(new Plat(id, nom));
                        }
                        platDAO.setListePlat(listePlat);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /**Dessert**/
                if(message[0].equals("desserts")){
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Dessert> listeDessert = new ArrayList<Dessert>();
                        for (int i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            int id = obj.getInt("id");
                            String nom = obj.getString("nom");
                            listeDessert.add(new Dessert(id, nom));
                        }
                        dessertDAO.setListeDessert(listeDessert);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /**Menu**/
                if(message[0].equals("menus")){
                    try { Log.d("INPUTzz ", ""+message[1]);
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Menu> listeMenu = new ArrayList<Menu>();
                        for (int i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            int id = obj.getInt("id");
                            String nom = obj.getString("nom");
                            int id_entree = obj.getInt("id_entree");
                            int id_plat = obj.getInt("id_plat");
                            int id_dessert = obj.getInt("id_dessert");
                            listeMenu.add(new Menu(id, nom, id_entree, id_plat, id_dessert));
                        }
                        menuDAO.setListeMenu(listeMenu);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /**MENU SEMAINE**/
                if(message[0].equals("menusemaines")){
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <MenuSemaine> listeMenuSemaine = new ArrayList<MenuSemaine>();
                        for (int i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            int id = obj.getInt("id");
                            int jour = obj.getInt("jour");
                            String menu1 = obj.getString("menu1");
                            String menu2 = obj.getString("menu2");
                            listeMenuSemaine.add(new MenuSemaine(id, jour, menu1, menu2));
                        }
                        menusemaineDAO.setListeMenuSemaine(listeMenuSemaine);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /** UTILISATEUR **/
                if(message[0].equals("utilisateurs")){
                    Log.d("", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ");
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
                        int i;
                        for (i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            Log.d("FDP MARHE", "processFinish: 99999999999999999999999999999999999999999999999999999" + curseur);
                            String email = obj.getString("email");
                            String mdp = obj.getString("mdp");
                            String nom = obj.getString("nom");
                            String prenom = obj.getString("prenom");
                            int estAdmin = obj.getInt("estAdmin");
                            int estConnecte = obj.getInt("estConnecte");
                            int aVoter = obj.getInt("aVoter");
                            listeUtilisateur.add(new Utilisateur(email, mdp, nom, prenom, estAdmin, estConnecte, aVoter));
                        }
                        Log.d("TAG", "processFinish: zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" + " "+i);
                        utilisateurDAO.setListeUtilisateur(listeUtilisateur);
                        Log.d("zzzzzzz", "processFinish: ---------------------------------------------------------------" + utilisateurDAO.listeUtilisateur);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /** PLACE **/
                if(message[0].equals("place")){
                    Log.d("PLACE", "PLACE PLACE PLACE PLACE PLACE ");
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Place> listePlace = new ArrayList<Place>();
                        int i;
                        for (i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            Log.d("FDP MARHE", "processFinish: 99999999999999999999999999999999999999999999999999999" + curseur);
                            long id = obj.getLong("id");
                            int placeMax = obj.getInt("placeMax");
                            int placeDispo = obj.getInt("placeDispo");
                            listePlace.add(new Place(id, placeMax, placeDispo));
                        }
                        placeDAO.setListePlace(listePlace);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /** MENUVOTER **/
                if(message[0].equals("menuvoter")){
                    Log.d("menuvoter", "MENU VOTER MENU VOTER ");
                    try {
                        JSONArray curseur = new JSONArray(message[1]);
                        ArrayList <Menuvoter> listeMenuVoter = new ArrayList<Menuvoter>();
                        int i;
                        for (i = 0; i < curseur.length(); i++){
                            JSONObject obj = new JSONObject(curseur.getString(i));
                            long id = obj.getLong("id");
                            int mois = obj.getInt("mois");
                            int id_menu1 = obj.getInt("id_menu1");
                            int id_menu2 = obj.getInt("id_menu2");
                            int id_menu3 = obj.getInt("id_menu3");
                            int id_menu4 = obj.getInt("id_menu4");
                            int nb_voteMenu1 = obj.getInt("nb_voteMenu1");
                            int nb_voteMenu2 = obj.getInt("nb_voteMenu2");
                            int nb_voteMenu3 = obj.getInt("nb_voteMenu3");
                            int nb_voteMenu4 = obj.getInt("nb_voteMenu4");
                            listeMenuVoter.add(new Menuvoter(id, mois, id_menu1, id_menu2, id_menu3, id_menu4, nb_voteMenu1, nb_voteMenu2, nb_voteMenu3, nb_voteMenu4));
                        }
                        Log.d("tkt", listeMenuVoter.toString());
                        menuvoterDAO.setListeMenuVoter(listeMenuVoter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                /** CAS D'ERREUR DU SERVEUR PHP **/
                else if (message[0].equals("recuperation")){
                    Log.d("recuperation", "*****************"+message[1]);
                }
                else if (message[0].equals("recuperation")){
                    Log.d("erreurrecuperation", "*****************"+message[1]);
                }
                else {
                    if (message[0].equals("erreur")){
                        Log.d("erreur", "*******************"+message[1]);
                    }
                }
            }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP acceesDonnees = new AccesHTTP();
        // lien de délégation
        acceesDonnees.delegate = this;
        // ajout parametre
        acceesDonnees.addParam("operation", operation);
        Log.d("*************" + operation, "envoi ******* envoi ******* envoi : " + lesDonneesJSON.toString());
        acceesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        // appel au serveur
        acceesDonnees.execute(SERVEURADDR);
    }
}
