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
import oracle.acsi.Article;
import oracle.acsi.ArticleManager;
import oracle.acsi.Utilisateur;

/**
 *
 * @author Gilles
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur usr = (Utilisateur) session.getAttribute("user");

        if (usr.isAdmin()) {
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/admin.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArticleManager am = ArticleManager.getInstance();
        if ("1".equals(request.getParameter("suppr"))) {
            am.supprimer(request.getParameter("reference"));
        } else if (request.getParameter("add") != null) {
            Article art = new Article(request.getParameter("ref"), Float.parseFloat(request.getParameter("prix")), request.getParameter("libelle"), request.getParameter("descr"));
            am.enregistrer(art);
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/admin.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/static/404.html");
                dispatcher.forward(request, response);
            }
        }
    }

}
