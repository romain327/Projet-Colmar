package Whole.ccmsPackage;


/**
 * Interface permettant la généralisation dans la classe AbstractDAO.
 *
 * @param <CCMS> objet CCMS
 * @see Whole.daoPackage.AbstractDAO
 */
public interface CCMS <CCMS>{
    /**
     * Permet de verifier si 2 objets sont exactement similaires.
     *
     * @author Andreas
     * @param objet l'objet à comparer
     * @return renvoie true si les deux objets sont similaires, false sinon
     */
    Boolean estClone(CCMS objet);

}