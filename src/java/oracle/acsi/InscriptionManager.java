/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clem-62
 */
public class InscriptionManager {

    private static InscriptionManager instance = null;

    private InscriptionManager() {
    }   //Pour empêcher l'instanciation autrement qu'avec getInstance()

    public static InscriptionManager getInstance() {
        if (instance == null) {
            instance = new InscriptionManager();
        }
        return instance;
    }

    public Utilisateur inscrireUtilisateur(String email, String password, String cp) {
        MySQLCon connexion = MySQLCon.getInstance();

        int nbRow = 0;
        Utilisateur usr = null;
        try {
            //Vérification que l'utilisateur n'existe pas
            ResultSet resultat = connexion.getResult("SELECT COUNT(*) FROM UTILISATEUR "
                    + "WHERE USR_EMAIL = '" + email + "';");
            resultat.first();
            nbRow = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (nbRow <= 0) {
            try {
                //INSERTION
                int idusr = connexion.insertRequest("INSERT INTO UTILISATEUR "
                        + "(USR_ID, USR_EMAIL, USR_PASSWD, USR_CP, USR_ISADMIN) VALUES("
                        + "0,"
                        + "'" + email + "',"
                        + "'" + password + "',"
                        + "'" + cp + "',"
                        + "false);");
                //INSERTION DE L'OPERATION
                connexion.insertRequest("INSERT INTO GESTION (GEST_DATE, GEST_OP, USR_ID, ART_REF)"
                        + " VALUES("
                        + "DATE(NOW()), "
                        + "'CREA', "
                        + idusr + ", "
                        + "NULL"
                        + ");");
                usr = new Utilisateur(email, cp, password);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            return null;
        }
        connexion.close();
        return usr;
    }

    public int getInscritsDepuis(Date date) {
        int nbRow = 0;
        MySQLCon connexion = MySQLCon.getInstance();

        ResultSet resultat;

        try {
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String fDate = sdf.format(date);
            resultat = connexion.getResult("SELECT count(*) FROM GESTION "
                    + "WHERE GEST_DATE > '" + fDate + "' and gest_op = 'CREA';");
            resultat.first();
            nbRow = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        connexion.close();
        return nbRow;
    }

    public int getNbVisiteurParCP(String codePostal) {
        int nbRow = 0;
        MySQLCon connexion = MySQLCon.getInstance();

        ResultSet resultat;
        try {
            resultat = connexion.getResult("SELECT count(*) FROM UTILISATEUR WHERE USR_CP LIKE '" + codePostal + "';");
            resultat.first();
            nbRow = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        connexion.close();
        return nbRow;
    }
    
    public List<String> getTopCp(){
        List<String> codePostaux = new ArrayList<>();
        MySQLCon connexion = MySQLCon.getInstance();

        ResultSet resultat;
        try {
            resultat = connexion.getResult("SELECT usr_cp, count(usr_cp) as cpt FROM utilisateur group by usr_cp order by cpt desc limit 3");
            while(resultat.next()){
                codePostaux.add(resultat.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        connexion.close();
        return codePostaux;
    }
    
    public int getNbInscrits(){
        int nbRow = 0;
        MySQLCon connexion = MySQLCon.getInstance();

        ResultSet resultat;
        try {
            resultat = connexion.getResult("SELECT count(*) FROM UTILISATEUR;");
            resultat.first();
            nbRow = resultat.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        connexion.close();
        return nbRow;
    }
}
