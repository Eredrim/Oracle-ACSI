/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

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
    
    //PAS FINI (gérer USER_ID)
    public void enregistrer(Article art){
        if(art != null)
        {
            MySQLCon connexion = MySQLCon.getInstance();

            int nbArticles = 0;
            
            //Vérification que l'article n'existe pas
            ResultSet resultat;
            resultat = connexion.getResult("SELECT COUNT(*) FROM ARTICLE "
                    + "WHERE ART_REF LIKE '" + art.getReference() +"';");
            try {
                resultat.first();
                nbArticles = resultat.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(!(nbArticles > 0))
            {
                //INSERTION
                connexion.getResult("INSERT INTO ARTICLE (ART_REF, ART_LIBELLE, ART_DESCRIPTION, ART_PRIX) VALUES"
                        + "'" + art.getReference() + "',"
                        + "'" + art.getLibelle() + "',"
                        + "'" + art.getDescription() + "',"
                        + "'" + art.getPrix() + "'"
                        +";");

                //INSERTION DE L'OPERATION
                connexion.getResult("INSERT INTO GESTION (GEST_DATE, GEST_OP, USR_ID, ART_REF)"
                        + " VALUES("
                        + System.currentTimeMillis() + ","
                        + "SUPPR,"
                        + ""//USR_ID,
                        + art.getReference() + ","
                        + ");");
            }
            connexion.close();
        }
    }
    
    //PAS FINI (gérer USER_ID)
    public void supprimer(String refArticle){
        if(refArticle != null)
        {
            MySQLCon connexion = MySQLCon.getInstance();

            //SUPPRESSION
            connexion.getResult("DELETE FROM ARTICLE WHERE ART_REF LIKE '" + refArticle + "';");

            //INSERTION DE L'OPERATION
            connexion.getResult("INSERT INTO GESTION (GEST_DATE, GEST_OP, USR_ID, ART_REF)"
                    + " VALUES("
                    + System.currentTimeMillis() + ","
                    + "SUPPR,"
                    + ""//USR_ID,
                    + refArticle + ","
                    + ");");

            connexion.close();
        }
    }

    //PAS FINI (gérer USER_ID)
    public void modifier(String refArt1, Article art2)
    {
        MySQLCon connexion = MySQLCon.getInstance();

        int nbArticles = 0;

        //Vérification que l'article existe
        ResultSet resultat;
        resultat = connexion.getResult("SELECT COUNT(*) FROM ARTICLE "
                + "WHERE ART_REF LIKE '" + refArt1 +"';");
        try {
            resultat.first();
            nbArticles = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(nbArticles == 1)
        {
            //MODIFICATION
            connexion.getResult("UPDATE ARTICLE SET "
                    + " ART_LIBELLE = '" + art2.getLibelle() + "'"
                    + ", ART_PRIX = '" + art2.getPrix() + "'"
                    + ", ART_DESCRIPTION = '" + art2.getDescription() + "'"
                    +";");

            //INSERTION DE L'OPERATION
            connexion.getResult("INSERT INTO GESTION (GEST_DATE, GEST_OP, USR_ID, ART_REF)"
                    + " VALUES("
                    + System.currentTimeMillis() + ","
                    + "MODIF,"
                    + ""//USR_ID,
                    + refArt1 + ","
                    + ");");
        }
        connexion.close();
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
    
    public List<Object[]> getAll()
    {
        List<Object[]> articles = new ArrayList<>();
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet cursor;

        try {
            cursor = connexion.getResult("SELECT * FROM ARTICLE;");

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
    
    public int getNbVisiteurParArticle(String refArticle){
        int nbVis = 0;
        MySQLCon connexion = MySQLCon.getInstance();
        
        ResultSet resultat;
        resultat = connexion.getResult("SELECT SUM(C.CPTR_COUNT) FROM ARTICLE A, CONSULTER C"
                + "WHERE A.ART_REF = C.ART_REF"
                + "AND A.ART_REF LIKE '" + refArticle + "';");
        
        try {
            resultat.first();
            nbVis = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connexion.close();
        return nbVis;
    }
}