<%@page import="oracle.acsi.Utilisateur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <title>Mon Commerce</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <a href="."><img id="logo" alt="logo" src="img/logo-e-commerce.jpg"/></a>
                <div id="tHeaderBloc">
                    <div id="tHeader1">Mon commerce</div>
                    <div id="tHeader2">Ma boutique en ligne pour acheter plein de choses</div>
                </div>
                <%
                Utilisateur usr = (Utilisateur) session.getAttribute("user");
                if(usr == null){%>
                    <a id="connectionLink" href="connexion">Se connecter</a>
                <%} else {%>
                <a id="connectionLink" href="connexion">Bienvenue <%out.print(usr.getEmail());%></a>
                <%}%>
            </div>
            <div id="redHR"></div>
            <div id="content">
