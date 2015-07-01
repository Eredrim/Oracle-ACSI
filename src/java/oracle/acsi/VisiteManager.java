/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author clem-62
 */
public class VisiteManager {
    private static VisiteManager instance = null;
    protected VisiteManager() {}    //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public VisiteManager getInstance(){
        if(instance == null){
            instance = new VisiteManager();
        }
        return instance;
    }
    
    public void enregistrerVisite(Visite myVisite){
        MySQLCon connexion = MySQLCon.getInstance();
        
        connexion.getResult("UPDATE CONSULTER SET "
                + " ART_REF = '" + myVisite.getRefArticle() + "' "
                + ", USR_ID = '" + myVisite.getIdUtilisateur() + "' "
                + " , CPTR_COUNT = (CPTR_COUNT + 1)"
                + ";");
        
        connexion.close();
    }
    
    public int getNbVuesTotal(){
        int nbVues = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT SUM(CPTR_COUNT) FROM CONSULTER;");
        
        try {
            resultat.first();
            nbVues = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbVues;
    }
    
    public int getNbVues(String refArticle){
        int nbVues = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT SUM(CPTR_COUNT) FROM CONSULTER"
                + " WHERE ART_REF LIKE '" + refArticle + "';");
        
        try {
            resultat.first();
            nbVues = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbVues;
    }
    
    public List<Object[]> getHitParade(int place1, int place2){
        List<Object[]> articles = new ArrayList<>();
        MySQLCon connexion = MySQLCon.getInstance();
        ResultSet cursor;

        try {
            cursor = connexion.getResult("SELECT * FROM ARTICLE A, ("
                        + "SELECT A.ART_REF, SUM(C.CPTR_COUNT) as SOMME FROM ARTICLE A, CONSULTER C "
                        + " WHERE A.ART_REF = C.ART_REF "
                        + " GROUP BY A.ART_REF"
                    + ") VUES"
                    + "WHERE A.ART_REF = VUES.ART_REF"
                    + "ORDER BY SOMME;");

            if (cursor.first()){
                while(!cursor.isAfterLast()){
                    String reference = cursor.getString("ART_REF");
                    String libelle = cursor.getString("ART_LIBELLE");
                    float prix = cursor.getInt("ART_PRIX");
                    String description = cursor.getString("ART_DESCRIPTION");

                    //Création de l'image
                    ImageIcon image = new ImageIcon(reference + ".png");
                    
                    Object[] article = {reference, libelle, prix, description, image};
                    articles.add(article);
                    cursor.next();
                }
             }
            cursor.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        connexion.close();
        
        return articles;
    }
}
