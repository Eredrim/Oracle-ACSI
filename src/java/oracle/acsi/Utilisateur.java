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
public class Utilisateur {
    private int id;
    private String email;
    private boolean admin;
    private String codePostal;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void inscrire()
    {
        MySQLCon connexion = MySQLCon.getInstance();

            int nbRow = 0;
            
            //Vérification que l'utilisateur n'existe pas
            ResultSet resultat;
            resultat = connexion.getResult("SELECT COUNT(*) FROM UTILISATEUR "
                    + "WHERE USR_ID = '" + this.id +"';");
            try {
                resultat.first();
                nbRow = resultat.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(InscriptionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(!(nbRow > 0))
            {
                //INSERTION
                connexion.getResult("INSERT INTO UTILISATEUR "
                        + "(USR_ID, USR_EMAIL, USR_PASSWD, USR_CP, USR_ISADMIN) VALUES"
                        + "'" + this.id + "',"
                        + "'" + this.email + "',"
                        + "'" + this.password + "',"
                        + "'" + this.codePostal + "',"
                        + "'" + this.admin + "'"
                        +";");

                //INSERTION DE L'OPERATION
                connexion.getResult("INSERT INTO GESTION (GEST_DATE, GEST_OP, USR_ID, ART_REF)"
                        + " VALUES("
                        + "GETDATE(), "
                        + "SUPPR, "
                        + this.id + ", "
                        + "NULL"
                        + ");");
            }
            connexion.close();
    }
}
