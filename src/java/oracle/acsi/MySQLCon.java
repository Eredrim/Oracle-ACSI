/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.acsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gilles
 */
public class MySQLCon {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    final String url = "jdbc:mysql://localhost:3306/reynier";
    final String user = "root";
    final String password = "12345";

    private static MySQLCon instance;

    public static MySQLCon getInstance() {
        if (null == instance) { // Premier appel
            instance = new MySQLCon();
        }
        return instance;
    }

    private MySQLCon() {

    }

    public ResultSet getResult(String request) {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery(request);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    public void close() {
        try {
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
