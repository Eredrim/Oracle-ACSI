<%@page import="oracle.acsi.Article"%>
<%@page import="oracle.acsi.ArticleManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%
ArticleManager am = ArticleManager.getInstance();
Article art =  am.getArticle(request.getParameter("ref"));
%>
<a href=".">Retour à la liste</a>
<h1><% out.print(art.getLibelle());%></h1>
<br>
<img class="imgPageArticle" src="img/<% out.print(art.getReference() + ".png");%>"/>
<br>
<h3><% out.print(String.format("%.2f", art.getPrix()) + " €"); %></h3>
<div><% out.print(art.getDescription()); %></div>
<%@include file="footer.jsp" %>