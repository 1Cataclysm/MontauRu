<?php

    function connexionPDO(){
        $login = "montauru";
        $mdp = "QQDjhkjq4d54";
        $bd = "montauru_bd";
        $serveur = "mysql-montauru.alwaysdata.net"; // adresse ip
        /*
        $login = "root"//"montauru";
        $mdp = ""//"QQDjhkjq4d54"; //QQDjhkjq4d54
        $bd = "montauru"//"montauru";
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