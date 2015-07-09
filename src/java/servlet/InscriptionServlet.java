/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.acsi.*;

/**
 *
 * @author clem-62
 */
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirect(request, response, "jsp/inscription.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String login = request.getParameter("login");
            String codePostal = request.getParameter("cp");
            String password = request.getParameter("password");

            if (Integer.parseInt(codePostal) < 01000 || Integer.parseInt(codePostal) > 97699) {
                redirect(request, response, "jsp/inscription.jsp?erreur=2");
            } else {
                Utilisateur user = InscriptionManager.getInstance().inscrireUtilisateur(login, password, codePostal);
                //Si l'utilisateur n'existe pas il est crée.

                if(user == null){
                    redirect(request, response, "jsp/inscription.jsp?erreur=3");
                }
                else{
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    redirect(request, response, "jsp/index.jsp"); 
                }
            }
        } catch (Exception e) {
            redirect(request, response, "jsp/inscription.jsp?erreur=1");
        }
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response, String destination) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
            dispatcher.forward(request, response);
        }
    }
}
