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

    public Utilisateur(String email, String codePostal, String password) {
        this.email = email;
        this.codePostal = codePostal;
        this.password = password;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }
    
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
    
    public boolean authentifier(){
        try {
            MySQLCon connexion = MySQLCon.getInstance();
            ResultSet resultat;
            resultat = connexion.getResult("Select * from utilisateur where usr_email = '"+this.email+"' and usr_passwd='"+this.password+"';");
            resultat.first();
            this.id = resultat.getInt("usr_id");
            this.admin = resultat.getBoolean("usr_isadmin");
            this.codePostal = resultat.getString("usr_cp");
            connexion.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
