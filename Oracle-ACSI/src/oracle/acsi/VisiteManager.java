/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //TO DO
    }
    
    public Time getTempsMoyen(){
        //TO DO
        return null;
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
    
    public void getHitParade(int place1, int place2){
        //TO DO
        //Changer le type de retour
    }
}
