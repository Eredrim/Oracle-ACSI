/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clem-62
 */
public class InscriptionManager {
    private static InscriptionManager instance = null;
    protected InscriptionManager() {}   //Pour empêcher l'instanciation autrement qu'avec getInstance()
    public InscriptionManager getInstance(){
        if(instance == null){
            instance = new InscriptionManager();
        }
        return instance;
    }
    
    public void inscrire(Utilisateur user){
        //TO DO
    }
    
    public int getInscritsDepuis(Date date){
        //TO DO
        return 0;
    }
    
    public int getNbVisiteurParCP(String codePostal){
        int nbRow = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT count(*) FROM UTILISATEUR WHERE USR_CP LIKE '" + codePostal + "';");
        
        try {
            resultat.first();
            nbRow = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbRow;
    }
    
    public int getNbVisiteurParArticle(String refArticle){
        //TO DO
        return 0;
    }
}
