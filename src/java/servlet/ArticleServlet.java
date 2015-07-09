/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.acsi.Utilisateur;
import oracle.acsi.Visite;
import oracle.acsi.VisiteManager;

/**
 *
 * @author Gilles
 */
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("user");
        String refArticle = request.getParameter("ref");
        if (usr != null) { //si l'utilisateur est connecté on enregistre la visite
            Visite vst = new Visite(usr.getId(), refArticle);
            VisiteManager vm = VisiteManager.getInstance();
            vm.enregistrerVisite(vst);
        }

        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/article.jsp?ref=" + refArticle);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
