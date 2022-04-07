<?php
include "fonction.php";

// controle de reception de parametres
if (isset($_REQUEST["operation"])){
    //****************************************************************************** */
    //------------------ RECUPERATION DANS LA TABLE--------------------------------//
    /******************************************************************************* */

    // demande de recupération entree
    if ($_REQUEST["operation"] == "entrees"){
        try {
            print("entrees%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from entree order by id asc");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }

    // demande recuperation plat
    else if ($_REQUEST["operation"] == "plats"){
        try {
            print("plats%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from plat");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }

    // demande recuperation dessert
    else if ($_REQUEST["operation"] == "desserts"){
        try {
            print("desserts%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from dessert");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    // demande recuperation menu
    else if ($_REQUEST["operation"] == "menus"){
        try {
            print("menus%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from menu");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    // demande recuperation menusemaine
    else if ($_REQUEST["operation"] == "menusemaines"){
        try {
            print("menusemaines%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from menusemaine");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    // demande recuperation utilisateur
    else if ($_REQUEST["operation"] == "utilisateurs"){
        try {
            print("utilisateurs%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from utilisateur");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    // demande recuperation place
    else if ($_REQUEST["operation"] == "place"){
        try {
            print("place%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from place");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    //****************************************************************************** */
    //------------------ ENREGISTREMENT DANS LA TABLE--------------------------------//
    /******************************************************************************* */
    //enregistrer nouveau entree ..
    else if ($_REQUEST["operation"] == "enregEntree"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $nom = $donnee[1];
            //insertion dans la bdd
            print ("enregEntree%");
            $cnx = connexionPDO();
            $larequete = "insert into entree (nom) values (\"$nom\")";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur demande de recuperation%" . $e->getMessage();
            die();
        }
    }
    //enregistrer nouveau plat ..
    else if ($_REQUEST["operation"] == "enregPlat"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $nom = $donnee[1];
            //insertion dans la bdd
            print ("enregPlat%");
            $cnx = connexionPDO();
            $larequete = "insert into plat (nom) values (\"$nom\")";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur demande de recuperation%" . $e->getMessage();
            die();
        }
    }
    //enregistrer nouveau dessert
    else if ($_REQUEST["operation"] == "enregDessert"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $nom = $donnee[1];
            //insertion dans la bdd
            print ("enregDessert%");
            $cnx = connexionPDO();
            $larequete = "insert into dessert (nom) values (\"$nom\")";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur demande de recuperation%" . $e->getMessage();
            die();
        }
    }
    //enregistrer menu
    else if ($_REQUEST["operation"] == "enregMenu"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $nom = $donnee[1];
            $id_entree = $donnee[2];
            $id_plat = $donnee[3];
            $id_dessert = $donnee[4];
            //insertion dans la bdd
            print ("enregMenu%");
            $cnx = connexionPDO();
            $larequete = "insert into menu (nom, id_entree, id_plat, id_dessert) values (\"$nom\",$id_entree,$id_plat,$id_dessert)";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur demande de recuperation%" . $e->getMessage();
            die();
        }
    }
    //enregistrer menusemaine
    else if ($_REQUEST["operation"] == "enregMenusemaine"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $jour = $donnee[1];
            $menu1 = $donnee[2];
            $menu2 = $donnee[3];
            //premiere modif dans la bdd
            print ("enregMenusemaine%");
            $cnx = connexionPDO();
            $larequete = "update menusemaine set menu1= \"$menu1\" where jour=$jour";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            //2em modif
            print ("enregMenusemaine%");
            $cnx = connexionPDO();
            $larequete = "update menusemaine set menu2= \"$menu2\" where jour=$jour";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur%" . $e->getMessage();
            die();
        }
    }
    //enregistrer utilisateur
    else if ($_REQUEST["operation"] == "enregUtilisateur"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $email = $donnee[0];
            $mdp = $donnee[1];
            $nom = $donnee[2];
            $prenom = $donnee[3];
            $estAdmin = $donnee[4];
            $estConnecte = $donnee[5];
            $aVoter = $donnee[6];

            //insertion dans la bdd
            print ("enregUtilisateur%");
            $cnx = connexionPDO();
            $larequete = "insert into utilisateur (email,mdp,nom,prenom,estAdmin,estConnecte,aVoter) values (\"$email\", \"$mdp\", \"$nom\", \"$prenom\", $estAdmin, $estConnecte, $aVoter)";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreurrecuperation%" . $e->getMessage();
            die();
        }
    }
    //passe en mode connecte
    else if ($_REQUEST["operation"] == "enregConnecte"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $email = $donnee[0];
            $mdp = $donnee[1];
            $nom = $donnee[2];
            $prenom = $donnee[3];
            $estAdmin = $donnee[4];
            $estConnecte = $donnee[5];

            //insertion dans la bdd
            print ("enregConnecte%");
            $cnx = connexionPDO();
            $larequete = "update utilisateur set estConnecte=1 where email=\"$email\" and mdp=\"$mdp\"";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur demande de recuperation%" . $e->getMessage();
            die();
        }
    }
    //passe en mode deconnecte
    else if ($_REQUEST["operation"] == "enregDeconnecte"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $email = $donnee[0];
            $mdp = $donnee[1];
            $nom = $donnee[2];
            $prenom = $donnee[3];
            $estAdmin = $donnee[4];
            $estConnecte = $donnee[5];

            //insertion dans la bdd
            print ("enregDeconnecte%");
            $cnx = connexionPDO();
            $larequete = "update utilisateur set estConnecte=0 where email=\"$email\" and mdp=\"$mdp\"";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "erreur demande de recuperation%" . $e->getMessage();
            die();
        }
    }

    //augmenter place
    else if ($_REQUEST["operation"] == "enregPlace"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $placeMax = $donnee[1];
            $placeDispo = $donnee[2];

            //insertion dans la bdd
            print ("enregPlace%");
            $cnx = connexionPDO();
            $larequete = "update place set placeMax=$placeMax where id=1";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---2 */
            $cnx = connexionPDO();
            $larequete = "update place set placeDispo=$placeDispo where id=1";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }

    /********************************************************************************************** *
     * ***********************************************************************************************
     * --------------------- VOTER ------------------------/
     **/
    // demande recuperation menuvoter
    else if ($_REQUEST["operation"] == "menuvoter"){
        try {
            print("menuvoter%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from menuvoter");
            $req->execute();
            //s'il y a une entree
            if ($ligne = $req->fetchAll(PDO::FETCH_ASSOC)){
                print (json_encode($ligne));
            }
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    //modifier menuvoter
    else if ($_REQUEST["operation"] == "enregMenuvoter"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];
            $mois = $donnee[1];
            $id_menu1 = $donnee[2];
            $id_menu2 = $donnee[3];
            $id_menu3 = $donnee[4];
            $id_menu4 = $donnee[5];
            $nb_voteMenu1 = $donnee[6];
            $nb_voteMenu2 = $donnee[7];
            $nb_voteMenu3 = $donnee[8];
            $nb_voteMenu4 = $donnee[9];
            //insertion dans la bdd
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set id_menu1=$id_menu1 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---2 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set id_menu2=$id_menu2 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---3 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set id_menu3=$id_menu3 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---4 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set id_menu4=$id_menu4 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---5 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set nb_voteMenu1=0 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---5 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set nb_voteMenu2=0 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---5 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set nb_voteMenu3=0 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
            /**---5 */
            print ("enregMenuvoter%");
            $cnx = connexionPDO();
            $larequete = "update menuvoter set nb_voteMenu4=0 where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
    /**supprimer menu */
    else if ($_REQUEST["operation"] == "deleteMenu"){
        try {
            //récupération des données en post
            $lesdonnees = $_REQUEST["lesdonnees"];
            $donnee = json_decode($lesdonnees);
            $id = $donnee[0];

            //supprimer dans la bdd
            print ("deleteMenu%");
            $cnx = connexionPDO();
            $larequete = "delete from menu where id=$id";
            print ($larequete);
            $req = $cnx->prepare("$larequete");
            $req->execute();
        }
        catch (PDOException $e) {
            print "recuperation%" . $e->getMessage();
            die();
        }
    }
}

?>