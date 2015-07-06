<%@page import="oracle.acsi.Article"%>
<%@page import="oracle.acsi.ArticleManager"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<%
    ArticleManager am = ArticleManager.getInstance();
    List<Article> lstArt = am.getAll();
%>
<table>
    <tr>
        <td>Image</td>
        <td>Désignation</td>
        <td>Référence</td>
        <td>Prix</td>
    </tr>
    <%
        for (Article art : lstArt) {
    %>
    <tr>
    <td></td>
    <td><% out.print(art.getLibelle()); %></td>
    <td><% out.print(art.getReference()); %></td>
    <td><% out.print(art.getDescription()); %></td>
    </tr>
    <%
        }
    %>
</table>
<%@include file="footer.jsp" %>
