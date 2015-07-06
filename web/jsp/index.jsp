<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="oracle.acsi.Article"%>
<%@page import="oracle.acsi.ArticleManager"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<%
    ArticleManager am = ArticleManager.getInstance();
    List<Article> lstArt = am.getAll();
%>
<table id="tableProduits">
    <tr style="background: #aaa;">
        <td>Image</td>
        <td>Désignation</td>
        <td>Référence</td>
        <td>Prix</td>
    </tr>
    <%
        for (Article art : lstArt) {
    %>
    <tr>
    <td class="miniature"><img src="img/<% out.print(art.getReference() + ".png"); %>"></td>
    <td><% out.print(art.getLibelle()); %></td>
    <td><% out.print(art.getReference()); %></td>
    <td><% out.print(String.format("%.2f", art.getPrix()) + " €"); %></td>
    </tr>
    <%
        }
    %>
</table>
<%@include file="footer.jsp" %>
