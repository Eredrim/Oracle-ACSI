/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.acsi.*;

/**
 *
 * @author clem-62
 */
public class ConnexionServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String codePostal = request.getParameter("code postal");
        
        if(Integer.parseInt(codePostal) < 01000 || Integer.parseInt(codePostal) > 97999)
        {
            //Mauvais code postal, envoyer chier.
        }
        else
        {
            Utilisateur user = new Utilisateur();
            user.setPassword(codePostal);
            user.setEmail(login);
            InscriptionManager.getInstance().inscrire(user);    //Si l'utilisateur n'existe pas il est crée.
            
            //Définition de l'utilisateur courant dans une variable de session
            request.getSession().setAttribute();
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
    
