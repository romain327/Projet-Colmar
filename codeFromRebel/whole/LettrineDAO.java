/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package whole;


import java.io.File;

/**
 * Classe héritant d'AbstractDAO, permettant de lier une Lettrine à la base de donnée
 * @see AbstractDao
 */
public class LettrineDAO extends AbstractDao {

    /**
     *permet de lier dans la base de donnée une lettrine à ouvrage, en effet une lettrine n'est présente dans un seul et unique ouvrage.
    * @param l la lettrine à lier à l'ouvrage
    * @param O l'ouvrage d'origine
     * @see Ouvrage
     * @see Lettrine
    */

    // ----------- << method.annotations@AAAAAAGEIHt5eyFqOnk= >>
    // ----------- >>
    public void provient(Lettrine l , Ouvrage O)   {
    // ----------- << method.body@AAAAAAGEIHt5eyFqOnk= >>
    // ----------- >>
    }
    /**
     *Permet de lier dans la base de donnée une lettrine à un tag
    * @param l La lettrine dont on souhaite ajouter un tag
    * @param t Le tag à ajouter à la lettrine
     * @see Lettrine
     * @see Tag
    */

    // ----------- << method.annotations@AAAAAAGEIHvxLSTVufQ= >>
    // ----------- >>
    public void tager(Lettrine l , Tag t) {
    // ----------- << method.body@AAAAAAGEIHvxLSTVufQ= >>
    // ----------- >>
    }
    /**
     * permet de caracteriser une lettrine en ajoutant une métadonnée
    * @param meta Métadonnée à ajouter à la lettrine
     * @see Metadonnee
    */

    // ----------- << method.annotations@AAAAAAGEfcqAt1VgxHs= >>
    // ----------- >>
    public void ajouterMeta(Metadonnee meta) {
    // ----------- << method.body@AAAAAAGEfcqAt1VgxHs= >>
    // ----------- >>
    }
    /**
     * permet de décaracteriser une lettrine en supprimant une métadonnée
     * @param meta Métadonnée à supprimer à la lettrine
     * @see Metadonnee
     */

    // ----------- << method.annotations@AAAAAAGEfcyVPFpkdWk= >>
    // ----------- >>
    public void supprimerMeta(Metadonnee meta) {
    // ----------- << method.body@AAAAAAGEfcyVPFpkdWk= >>
    // ----------- >>
    }
    /**
     * Met à jour la base de donnée avec les nouvelles valeurs de la métadonnée
     * @param meta La métadonnée dont l'on souhaite que la partie code correspond avec la partie base de donnée
     * @see Metadonnee
    */

    public void modifierMeta(Metadonnee meta) {
    // ----------- << method.body@AAAAAAGEfc0dGGFnIFc= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGErxGufsFVogM= >>
    // ----------- >>

    /**
     * Met en ligne une image stockée sur disque et renvoie son URL
     * @param file le fichier où se trouve l'image dans le disque, obtenue grâce à la fênetre
     * @return String: le lien vers l'image en ligne
     * @see File
     * @see #Fenetre.savePopUp
     */
    private String upload(File file) {
        return null;
    }
// ----------- << class.extras@AAAAAAGEEKm5d/fG0Tk= >>
// ----------- >>
}
