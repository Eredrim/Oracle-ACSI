<%@include file="header.jsp" %>
<h1>Connexion</h1>
<% 
    String erreur = "";
if("1".equals(request.getParameter("erreur"))){
    erreur = "Veuillez remplir tous les champs";
}
else if("2".equals(request.getParameter("erreur"))){
    erreur = "Erreur d'authentification";
}
%>
<form method="post" action="connexion">
    <label for="login">Identifiant</label>
    <input name="login" type="email"/>&emsp;
    <label for="password">Mot de passe</label>
    <input name="password" type="password"/>&emsp;
    <button type="submit">Valider</button>
    <br><br>
    <div style="color: red"><% out.print(erreur); %></div>
</form>
<%@include file="footer.jsp" %>
