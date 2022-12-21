package Whole.daoPackage;

import Whole.SingleConnection;
import Whole.ccmsPackage.Tag;

import java.util.ArrayList;
/**
 * Classe servant à lié à la base de donnée les méthodes de Tag
 * @see Tag
 */
public class TagDAO extends AbstractDAO<Tag>{

    /**
     * Constructeur de la classe OuvrageDAO.
     * @see SingleConnection
     */
    public TagDAO(String url, String login, String password) {
        super(url, login, password);
    }


    /**
     * Met à jour la BD
     *
     * @param objet      Tag à changer
     * @param changement Tag de changement (les paramètres null ne sont pas à changer)
     * @return
     * @see SingleConnection
     */


    @Override
    public boolean modifier(Tag objet , Tag changement) {

        return false;
    }
    /**
     * Supprime de la db un Tag
     *
     * @param objet un Tag d'un type à déterminer dans chaque implémentation
     * @return
     * @see SingleConnection
     * @see Tag
     */
    @Override

    public boolean supprimer(Tag objet ) {

        return false;
    }
    /**
     * Ajoute à la base de donnée un Tag
     *
     * @param objet le Tag à ajouter
     * @return
     * @see SingleConnection
     * @see Tag
     */
    @Override

    public boolean creer(Tag objet) {

        return false;
    }

    /**
     *Cherche un Tag dans la base
     * @param objet Tag avec tous les paramètres nuls sauf ceux à chercher
     * @return La liste des tags qui correspond auc paramètres donnés
     * @see SingleConnection
     * @see Tag
     *
     */
    @Override
    public ArrayList<Tag> chercher(Tag objet) {
        return null;
    }
}