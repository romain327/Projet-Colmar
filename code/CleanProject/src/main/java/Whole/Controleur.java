/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Whole;

import Whole.daoPackage.UtilisateurDAO;


/**
* Coeur de l'application, doit avant tout lancer ConnectionUniqueBD
*/
public  class Controleur {
    static String lienDB;
    static UtilisateurDAO utilisateurDAO;
    private static String login;
    /**
    * Permet de sauvegarder des métadonnés (ou autre) dans le but d'un copier coller
    */
    private static Object pressePapier;

    /**
     *getter de PressePapier
     */
    public static Object getPressePapier() {
        return pressePapier;
    }

    /**
     * Permet de sauvegarder n'importe quel type dans le but d'un copier coller
     * @param objet, objet à copier
     */
    public static void setPressePapier(Object objet) {
        pressePapier = pressePapier;
    }

    /**
    * Permet de se connecter a la base de donnee en faisant un appel de SingleConnection avec les parametres choisis
    * @param name nom d'utilisateur
    * @param pwd mot de passe
     * @return True si l'utilisateur est connecté, false sinon
    * @see SingleConnection
    */
    public static Boolean Login( String name,  String pwd) {
        utilisateurDAO = new UtilisateurDAO(lienDB,"connection","");
        login = utilisateurDAO.connexion(name, pwd);
        return login!=null;

    }
    /**
     * Demande a utilisateurDAO de creer un utilisateur
    * @param  name nom d'utilisateur
    * @param  pwd mot de passe
    * @param  confirm confirmation du mot de passe
     * @return true si l'utilisateur a pu être aouté, false sino,
    */
    public Boolean AddUser(String name, String pwd, String confirm) {
        return false; //en attendant de coder la fonction
    }

    public static Boolean init(){
        return false;
    }

    public Controleur() {
        this.pressePapier=new Object();

    }
}