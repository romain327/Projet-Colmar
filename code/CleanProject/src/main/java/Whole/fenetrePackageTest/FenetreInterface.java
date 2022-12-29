/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Whole.fenetrePackageTest;

import javafx.scene.Scene;

/**
 * Interface permettant de changer simplement d'Interface utilisateur
 */
public interface FenetreInterface {

    /**
     * Permet de changer la scène
     * @param s une scène
     * @see Scene
    */
    void afficher(Scene s);

    /**
     * @return l'emplacement du fichier où sera sauvegarder un contenu.
     */
    String savePopUp();

    /**
     * Permet d'afficher à l'utilisateur un message
     *
     * @param contentAlert tableau de deux elements, le premier étant le titre et le second le message à afficher à l'utilisateur
     */
    void afficherMessage(String[] contentAlert);
}