<%@page import="oracle.acsi.VisiteManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="oracle.acsi.Article"%>
<%@page import="oracle.acsi.ArticleManager"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<%
    ArticleManager am = ArticleManager.getInstance();
    VisiteManager vm = VisiteManager.getInstance();
    List<Article> lstArt = am.getAll();
%>
<h2>Statistiques administrateur</h2>
<table id="tableProduits">
    <tr style="background: #aaa;">
        <td>Image</td>
        <td>Désignation</td>
        <td>Référence</td>
        <td>Prix</td>
        <td>Nombre de vues</td>
    </tr>
    <%
        for (Article art : lstArt) {
    %>
    <tr class="trProduit">
    <td class="miniature"><img src="img/<% out.print(art.getReference() + ".png"); %>"></td>
    <td><% out.print(art.getLibelle()); %></td>
    <td><% out.print(art.getReference()); %></td>
    <td><% out.print(String.format("%.2f", art.getPrix()) + " €"); %></td>
    <td><% out.print(vm.getNbVues(art.getReference())); %></td>
    </tr>
    <%
        }
    %>
</table>
<%@include file="footer.jsp" %>
