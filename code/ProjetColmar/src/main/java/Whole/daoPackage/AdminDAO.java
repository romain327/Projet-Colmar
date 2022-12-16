package Whole.daoPackage;

import Whole.SingleConnection;
import Whole.exportPackage.ExportTypeInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.*;

import java.util.ArrayList;

import static Whole.daoPackage.AbstractDAO.cn;

/**
 * Classe permettant à l'administrateur de gérer la base de donnée
 */
public class AdminDAO {

    private String user;
    private String mdp;
    private String dbName;


    private ArrayList<ExportTypeInterface> listeMethode;
    private ArrayList<String> listeTable;
    /**
     * Constructeur de la classe
     */
    public AdminDAO() {
    }

    /**
     * Permet de stocker dans un fichier la bd
     * @author Andreas
     * @param methode le nom de la méthode d'export
     * @param path le fichier où sera exporter les données
     * @see SingleConnection
     * @see Whole.exportPackage.ExportTypeInterface
     *
     */


    public Boolean exportDonee(String methode, String path) {
        for (ExportTypeInterface e : listeMethode) {
            if (e.getName().equals(methode)) {
                if (e.getName().equals("SQL")) {
                    String os = System.getProperty("os.name");
                    String type = "sh";
                    if (os.contains("Windows")) {
                        type = "cmd.exe";
                    }
                    String[] cmd = {type, "exportSQL.sh", "src/main/shell/exportSQL.sh", user, mdp, dbName, path};
                    try {
                        Runtime.getRuntime().exec(cmd);
                    } catch (IOException ex) {
                        return false;
                    }
                } else {
                    Boolean b = true;
                    ArrayList<ArrayList<String>> list;
                    for (String st : listeTable) {
                        list = getTableList(st);
                        File f = new File(path + "/" + st);
                        b = b && e.export(f, list);
                    }
                    return b;

                }
            }
        }
        return false;
    }

    /**
     * Permet de transformer une table en une liste de liste, le premier niveau de liste représentant les lignes
     * et le second niveau les colones, la première ligne est le nom des colones.
     * @author Andreas
     * @param table la table que l'on souhaite exporter
     * @return true si tout c'est bien passé, false sinon
     */
    private ArrayList<ArrayList<String>> getTableList(String table){
        ArrayList<ArrayList<String>> list= new ArrayList<>();
        try {
            Statement stmt = cn.createStatement();

            ResultSet rs= stmt.executeQuery("SELECT * FROM "+table);
            ResultSetMetaData md =  rs.getMetaData();
            int size =md.getColumnCount();
            for(int i=0;i<size;i++){
                list.get(0).add(md.getColumnName(i));
            }
            int row=1;
            while(rs.next()){
                for(int j=0;j<row;j++){
                    for(int i=0;i<size;i++){
                        list.get(j).add(md.getColumnName(i));
                    }
                }
                row++;
            }
        } catch (SQLException e) {

        }
        return list;
    }


    /**
     * Permet de stocker dans un fichier les logs
     * @author Andreas
     * @param cn La connection à la base de donnée
     * @param file le fichier où sera exporter les log
     * @see SingleConnection
     */


    public Boolean exportLog(File file,Connection cn)  {
        try {
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `text`,`date`,`userLogin` FROM `log`");
            String str = "Hello";
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("Log exporté le "+new Date( System.currentTimeMillis()));

            while(rs.next()){
                bw.newLine();
                str=rs.getString(2)+" par "+rs.getString(3)+": "+rs.getString(1);
                bw.write(str);

            }
            bw.close();

        }catch(SQLException e){
            System.err.println("something went wrong with the database link");
        } catch (IOException e) {
            System.err.println("something went wrong with the writing of the file");

        }
        return false;
    }

    /**
     * Supprime les logs de la bd
     * @author Andreas
     * @param cn La connection à la base de donnée
     * @see SingleConnection
     *
     */
    public Boolean deleteLog(Connection cn) {
        Statement stm = null;
        try {
            stm = cn.createStatement();
            stm.execute("DELETE FROM `log`");
            return true;
        } catch (SQLException e) {
            System.err.println("something went wrong with the database link");
        }
        return false;
    }

    /**
     * Ajoute au log un text
     * @author Andreas
     * @param txt Le message à enregistrer
     * @return true si l'insertion peut se faire, false sinon
     * @see SingleConnection
     */
    public Boolean writeLog(String txt){
        try {
            Statement st = cn.createStatement();
            st.execute("INSERT INTO `log`( `text`, `date`, `userLogin`) VALUES('"+txt+"','"+new Date( System.currentTimeMillis())+"','"+user+"'");
            return true;
        } catch (SQLException e) {

        }
        return false;

    }

}