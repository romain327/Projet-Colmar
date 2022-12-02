package Whole.ccmsPackage;

import Whole.daoPackage.AbstractDAO;

public interface CCMS {
    /**
     * Modifie un CCMS, appel une méthode d'un objet de type AbstractDAO
     * @see AbstractDAO
     */
    void modifier();
    /**
     * Cherche un CCMS, appel une méthode d'un objet de type AbstractDAO
     * @see AbstractDAO
     */
    void chercher();
    /**
     * Créer un CCMS, appel une méthode d'un objet de type AbstractDAO
     * @see AbstractDAO
     */
    void creer();
    /**
     * Supprime un CCMS, appel une méthode d'un objet de type AbstractDAO
     * @see AbstractDAO
     */
    void supprimer();
}