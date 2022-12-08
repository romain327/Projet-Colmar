package Whole.daoPackage;

import Whole.ccmsPackage.Auteur;

import java.sql.Connection;

import java.util.ArrayList;
/**
 * Classe servant à lié à la base de donnée les méthodes d'Auteurs
 * @see Auteur
 */
public class AuteurDAO extends AbstractDAO {

    private  Connection cn;
    /**
     * Constructeur d'AdminDAO
     * @param cn la connectionObtenue par SingleConnection
     * @see Whole.SingleConnection
     */
    public AuteurDAO(Connection cn) {
        super(cn);
    }




    /**
     * Met à jour la BD
     * @param objet Auteur à changer
     * @param changement Auteur de changement (les paramètres null ne sont pas à changer)
     */


    public void modifier(Auteur objet , Auteur changement) {

    }
    /**
     * Supprime de la db un Auteur
     * @param objet un Auteur d'un type à déterminer dans chaque implémentation
     * @see Auteur
     */

    public void supprimer(Auteur objet) {

    }
    /**
     * Ajoute à la base de donnée un Auteur
     * @param donne l'Auteur à ajouter
     * @see Auteur
     */

    public void creer(Auteur donne) {

    }
    /**
     *Cherche un Auteur dans la base
     * @param donne Auteur avec tous les paramètres nuls sauf ceux à chercher
     * @see Auteur
     */

    public ArrayList<Auteur> chercher(Auteur donne) {
        return null;
    }
}