/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clem-62
 */
public class ArticleManager {
    private static ArticleManager instance = null;
    protected ArticleManager() {}   //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public ArticleManager getInstance(){
        if(instance == null){
            instance = new ArticleManager();
        }
        return instance;
    }
    
    public void enregistrer(Article art){
        //TO DO
        //INSERT DANS ARTICLE + DANS GESTION
    }
    
    //PAS FINI
    public void supprimer(String refArticle){
        MySQLCon connexion = MySQLCon.getInstance();
        
        //SUPPRESSION
        connexion.getResult("DELETE FROM ARTICLE WHERE ART_REF LIKE '" + refArticle + "';");
        
        //INSERTION DE L'OPERATION
        connexion.getResult("INSERT INTO GESTION (GEST_DATE, GEST_OP, USR_ID, ART_REF)"
                + " VALUES("
                + ""//LA DATE,
                + "SUPPR,"
                + ""//USR_ID,
                + refArticle + ","
                + ");");
        
        connexion.close();
    }
    
    public void modifier(Article art1, Article art2){
        //TO DO
    }
    
    public int getNbCrees(){
        int nbVues = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT COUNT(*) FROM ARTICLE A, GESTION G"
                + "WHERE A.GEST_ID = G.GEST_ID"
                + "AND GEST_OP LIKE 'CREAT'"
                + "GROUP BY A.ART_REF;");
        
        try {
            resultat.first();
            nbVues = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbVues;
    }
    
    public int getNbModifies(){
        int nbVues = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT COUNT(*) FROM ARTICLE A, GESTION G"
                + "WHERE A.GEST_ID = G.GEST_ID"
                + "AND GEST_OP LIKE 'MODIF'"
                + "GROUP BY A.ART_REF;");
        
        try {
            resultat.first();
            nbVues = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbVues;
    }
    
    public int getNbSupprimes(){
        int nbVues = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT COUNT(*) FROM ARTICLE A, GESTION G"
                + "WHERE A.GEST_ID = G.GEST_ID"
                + "AND GEST_OP LIKE 'SUPPR'"
                + "GROUP BY A.ART_REF;");
        
        try {
            resultat.first();
            nbVues = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbVues;
    }
}
