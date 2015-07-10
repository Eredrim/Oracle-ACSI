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
<a href=".">Retour à la liste</a> - <a href="statistiques">Accès statistiques générales</a>
<h2>Pannel administrateur</h2>
<div>Nombre d'articles créés: <% out.print(am.getNbCrees()); %> &emsp;
    Nombre d'articles modifiés: <% out.print(am.getNbModifies());%> &emsp;
    Nombre d'articles supprimés: <% out.print(am.getNbSupprimes()); %></div>
<br>
<div>
    <form action="admin" method="post">Ajouter un article : 
        <input name="ref" placeholder="référence">&emsp;
        <input name="libelle" placeholder="nom du produit">&emsp;
        <input name="descr" placeholder="description">&emsp;
        <input name="prix" placeholder="prix">&emsp;
        <button name="add" type="submit">+</button>
    </form>
</div>
<br>
<table id="tableProduits">
    <tr style="background: #aaa;">
        <td>Image</td>
        <td>Désignation</td>
        <td>Référence</td>
        <td>Prix</td>
        <td>Description</td>
        <td>Nombre de vues</td>
        <td>Suppr</td>
    </tr>
    <%
        for (Article art : lstArt) {
    %>
    <tr class="trProduit">
    <td class="miniature"><img src="img/<% out.print(art.getReference() + ".png"); %>"></td>
    <td><input value="<% out.print(art.getLibelle()); %>"></td>
    <td><% out.print(art.getReference()); %></td>
    <td><input value="<% out.print(String.format("%.2f", art.getPrix())); %>"></td>
    <td><input value="<% out.print(art.getDescription()); %>"></td>
    <td><% out.print(vm.getNbVues(art.getReference())); %></td>
    <td onclick="supprArticle('<% out.print(art.getReference());%>', this)">X</td>
    </tr>
    <%
        }
    %>
</table>
    <script>
        function supprArticle(ref, e){
            $.post("admin", {reference : ref, suppr : 1});
            $(e).parent().hide();
        }
    </script>
<%@include file="footer.jsp" %>
