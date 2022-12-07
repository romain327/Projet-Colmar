/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Whole.fenetrePackage;

import javafx.scene.Scene;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Interface permettant de changer simplement d'Interface utilisateur
 */
public interface FenetreInterface {

    /**
     * Permet de changer la scène
    * @param s une scène
     * @see javafx.scene.Scene
    */
    void afficher(Scene s);

    /**
     * Permet d'ouvrir une fenetre pour obtenir un fichier
     * @return le fichier ou sera sauvegardé un contenu
     */
    BufferedImage savePopUp();

}