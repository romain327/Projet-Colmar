/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Whole;

import Whole.daoPackage.UtilisateurDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
* Coeur de l'application, doit avant tout lancer ConnectionUniqueBD
*/
public  class Controleur {
    static ArrayList<String> configList;
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

        login = utilisateurDAO.connexion(name, pwd);
        return login!=null;

    }
    /**
     * Demande a utilisateurDAO de creer un utilisateur
    * @param  name nom d'utilisateur
    * @param  pwd mot de passe
    * @param  confirm confirmation du mot de passe
     * @param statut le statut d'utilisateur
     * @return true si l'utilisateur a pu être aouté, false sino,
    */
    public Boolean AddUser(String name, String pwd, String confirm,String statut) {
        if(!pwd.equals(confirm)){
            return false;
        }
        return utilisateurDAO.creerUtilisateur(name,pwd,statut); //en attendant de coder la fonction
    }

    public static Boolean init(){
        return false;
    }

    public Controleur() {
        try {
            configList = new ArrayList<>();
            lireConfigFile();
            System.out.println(configList);
            utilisateurDAO = new UtilisateurDAO(configList.get(0),"connection","");
            this.pressePapier=new Object();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void lireConfigFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/configfile.txt"));
        String line;
        int i = 0;
        while ((line=br.readLine())!=null){
            line=line.split(";")[1];
            configList.add(line);

            i++;
        }
    }
}