<?php

    function connexionPDO(){
        $login = "*****";
        $mdp = "*****";
        $bd = "montauru_bd";
        $serveur = "mysql******.net"; // adresse ip
        /*
        $login = "****"
        $mdp = "****"
        $bd = "montauru"
        $serveur = "localhost"; // adresse ip
        */
        try {
            $conn = new PDO("mysql:host=$serveur;dbname=$bd", $login, $mdp);
            return $conn;
        }
        catch(PDOException $e){
            print "Erreur";
            die();
        }

    }


?>
