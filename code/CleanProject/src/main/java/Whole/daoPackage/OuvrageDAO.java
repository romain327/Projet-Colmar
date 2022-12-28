package Whole.daoPackage;

import Whole.SingleConnection;
import Whole.ccmsPackage.Ouvrage;
import Whole.ccmsPackage.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Cette classe est appelee pour creer un lien entre l'application et la base de donnees
 * pour tout ce qui concerne les interactions et les modifications d'un ouvrage
 * @see AbstractDAO
 */
public class OuvrageDAO extends AbstractDAO<Ouvrage> {
    /**
     * Constructeur de la classe OuvrageDAO.
     *
     * @see Connection
     * @see SingleConnection
     */

    public OuvrageDAO(String url, String login, String password) {
        super(url, login, password);
    }

    /**
     * Ajoute à la base de données un ouvrage.
     *
     * @param donne l'ouvrage à ajouter
     * @see Ouvrage
     * @see SingleConnection
     */
    @Override
    public boolean creer(Ouvrage donne) {
        if(donne.getId() < 0) {
            return false;
        }
        try {
        	PreparedStatement stmt = cn.prepareStatement("INSERT INTO ouvrages(libraire, "
            		+ "imprimeur, lieuImpression, dateEdition, lien, nbPage, copyright, "
            		+ "creditPhoto, resolution, format, titre) "
            		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, donne.getLibraire().getId());
            stmt.setInt(2, donne.getImprimeur().getId());
            stmt.setString(3, donne.getLieuImpression());
            stmt.setInt(4, donne.getDateEdition());
            stmt.setString(5, donne.getLien());
            stmt.setInt(6, donne.getNbPage());
            stmt.setString(7, donne.getCopyright());
            stmt.setString(8, donne.getCreditPhoto());
            stmt.setString(9, donne.getResolution());
            stmt.setString(10, donne.getFormat());
            stmt.setString(11, donne.getTitre());
            return stmt.execute();
        }
        catch (SQLException e) {
            return false;
        }
    }


    /**
    * Permet de faire une insertion dans la table "ecrit". On vérifie que
    * ni l'auteur, ni l'ouvrage est NULL, puis on fait une requête d'insertion.
    *
    * @param p Personne l'auteur de l'ouvrage
    * @param objet Ouvrage ouvrage qu'on souhaite inserer
     * @see SingleConnection
    */
    public Boolean ecrit(Personne p, Ouvrage objet) {
    	if(p==null){
            return false;
        }
        if(objet==null){
            return false;
        }
        try {
            PreparedStatement stmt = cn.prepareStatement("insert into ecrit values(?,?)");
            stmt.setInt(1,p.getId());
            stmt.setInt(2,objet.getId());
            return stmt.execute();
        } catch (SQLException e) {
            System.out.println("something went wrong "+e);
        }
        return false;

    }
    
    /**
     * Permet de modifier un ouvrage dans la base de donnees. On s'assure que
     * l'ouvrage qu'on souhaite modifier est bien dans la base de donnees, puis
     * on le remplace par le nouvel ouvrage passe en 2e parametre.
     * 
     * @param objet l'ouvrage cible qu'on souhaite modifier
     * @param changement l'ouvrage par lequel on souhaite remplacer l'ancien
     * @see SingleConnection
     */
    public boolean modifier(Ouvrage objet, Ouvrage changement) {
    	boolean fonctionne=false;
    	String sql="SELECT * FROM ouvrages WHERE idOuvrage="+objet.getId();
    	try {
			Statement stmt=cn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if (rs.next()) { //Si l'ouvrage existe bien
				sql="UPDATE ouvrages SET libraire=?, imprimeur=?, lieuImpression=?, "
						+ "dateEdition=?, lien=?, nbPage=?, copyright=?, creditPhoto=?, "
						+ "resolution=?, format=?, titre=? "
					+"'WHERE idOuvrage=?";
				PreparedStatement pstmt=cn.prepareStatement(sql);
				pstmt.setInt(1, changement.getLibraire().getId());
				pstmt.setInt(2, changement.getImprimeur().getId());
				pstmt.setString(3, changement.getLieuImpression());
				pstmt.setInt(4, changement.getDateEdition());
				pstmt.setString(5, changement.getLien());
				pstmt.setInt(6, changement.getNbPage());
				pstmt.setString(7, changement.getCopyright());
				pstmt.setString(8, changement.getCreditPhoto());
				pstmt.setString(9, changement.getResolution());
				pstmt.setString(10, changement.getFormat());
				pstmt.setString(11, changement.getTitre());
				pstmt.setInt(12, objet.getId());
				fonctionne=stmt.execute(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return fonctionne;
    }
    
    /**
     * Permet de supprimer un ouvrage de la base de donnees. On s'assure que
     * l'ouvrage qu'on souhaite supprimer est bien dans la base de donnees, puis
     * si trouve on le supprime.
     * 
     * @param objet l'ouvrage cible qu'on souhaite supprimer
     * @see SingleConnection
     */
    public boolean supprimer(Ouvrage objet) {
        try {
            PreparedStatement stmt = cn.prepareStatement("DELETE FROM `ecrit` WHERE `idOuvrage`=?");
            stmt.setInt(1,objet.getId());
            stmt.execute();
            stmt=cn.prepareStatement("DELETE FROM `ouvrages` WHERE `idOuvrage`=?");
            stmt.setInt(1,objet.getId());
            return stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Permet de rechercher un ou plusieurs ouvrages dans la base de donnees
     * selon un ou plusieurs criteres.
     * 
     * @param objet ouvrage avec tous les parametres nuls sauf ceux a chercher
     * @return renvoie une liste des ouvrages qui correspondent aux critères
     * de recherche
     * @see SingleConnection
     */
    public ArrayList<Ouvrage> chercher(Ouvrage objet) {
        PreparedStatement stmt= null;
        ArrayList list = new ArrayList();
        boolean premier=true; //Permet d'ajouter les AND dans la requête si ce n'est pas la première condition
        String sql="SELECT * FROM ouvrages WHERE";
    	if (objet.getLibraire() != null) {
    		sql+=" libraire='"+objet.getLibraire()+"'";
    		premier=false;
    	}	
    	if (objet.getImprimeur() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" imprimeur='"+objet.getImprimeur()+"'";
    		premier=false;
    	}    		
    	if (objet.getLieuImpression() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" lieuImpression='"+objet.getLieuImpression()+"'";
    		premier=false;
    	}
    	if (objet.getDateEdition() != -1) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" dateEdition="+objet.getDateEdition();
    		premier=false;
    	}
    	if (objet.getLien() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" lien='"+objet.getLien()+"'";
    		premier=false;
    	}
    	if (objet.getNbPage() != -1) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" nbPage="+objet.getNbPage();
    		premier=false;
    	}
    	if (objet.getCopyright() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" copyright='"+objet.getCopyright()+"'";
    		premier=false;
    	}
    	if (objet.getCreditPhoto() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" creditPhoto='"+objet.getCopyright()+"'";
    		premier=false;
    	}
    	if (objet.getResolution() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" resolution='"+objet.getResolution()+"'";
    		premier=false;
    	}
    	if (objet.getFormat() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" format='"+objet.getFormat()+"'";
    		premier=false;
    	}
    	if (objet.getTitre() != null) {
    		if (!premier)
    			sql+=" AND";
    		sql+=" titre='"+objet.getTitre()+"'";
    		premier=false;
    	}
    	
    	if (premier) //Si aucune condition de recherche n'a été donnée
    		sql="SELECT * FROM ouvrages";
    	
        try {
            stmt = cn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Ouvrage o2=new Ouvrage();
                o2.setId(rs.getInt("idOuvrage"));
                o2.setLibraire(getPersonne(rs.getInt("libraire")));
                o2.setImprimeur(getPersonne(rs.getInt("imprimeur")));
                o2.setLieuImpression(rs.getString("lieuImpression"));
                o2.setDateEdition(rs.getInt("dateEdition"));
                o2.setLien(rs.getString("lien"));
                o2.setNbPage(rs.getInt("nbPage"));
                o2.setCopyright(rs.getString("copyright"));
                o2.setCreditPhoto(rs.getString("creditPhoto"));
                o2.setResolution(rs.getString("resolution"));
                o2.setFormat(rs.getString("format"));
                o2.setTitre(rs.getString("titre"));
                o2.setAuteurs(listeAuteur(o2.getId()));
                list.add(o2);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permet d'obtenir la liste des auteurs ayant écrit un ouvrage
     * @param id l'identifiant de l'ouvrage
     * @return la liste des auteurs ayant écrie l'ouvrage
     */
    private ArrayList listeAuteur(int id){
        ArrayList l = new ArrayList();
        try {
            PreparedStatement stmt = cn.prepareStatement("SELECT * FROM `personnes` WHERE `idOuvrage`=?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                l.add(getPersonne(rs.getInt(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    /**
     * Permet d'obtenir une personne grace à son id
     * @param id l'identifiant de la personne
     * @return la personne
     */
    private Personne getPersonne(int id){
        PreparedStatement stmt= null;
        try {
            stmt = cn.prepareStatement("SELECT * FROM `personnes` WHERE `idOuvrage`=?");
            stmt.setInt(1,id);
            ResultSet rs =stmt.executeQuery();
            Personne p = new Personne();
            if(rs.next()){
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setNote(rs.getString(4));
                p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}