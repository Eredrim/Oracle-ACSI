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
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirect(request, response, "jsp/connexion.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String login = request.getParameter("login");
            String codePostal = request.getParameter("cp");
            String password = request.getParameter("password");

            Utilisateur user = new Utilisateur(login, codePostal, password);
            if (user.authentifier()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("index");
            } else {
                redirect(request, response, "jsp/connexion.jsp?erreur=2");
            }
        } catch (Exception e) {
            redirect(request, response, "jsp/connexion.jsp?erreur=1");
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
