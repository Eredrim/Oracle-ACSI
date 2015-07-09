<%@include file="header.jsp" %>
<h1>Inscription</h1>
<% 
    String erreur = "";
if("1".equals(request.getParameter("erreur"))){
    erreur = "Veuillez remplir tous les champs";
}
else if("2".equals(request.getParameter("erreur"))){
    erreur = "Le code postal est invalide";
}
else if("3".equals(request.getParameter("erreur"))){
    erreur = "Ce compte existe déjà";
}
%>
<form method="post" action="inscription">
    <label for="login">Identifiant</label>
    <input name="login" type="email"/>&emsp;
    <label for="password">Mot de passe</label>
    <input name="password" type="password"/>&emsp;
    <label for="cp">Code postal</label>
    <input name="cp" type="text" style="width: 35px;"/>&emsp;
    <button type="submit">Valider</button>
    <br><br>
    <div style="color: red"><% out.print(erreur); %></div>
</form>
<%@include file="footer.jsp" %>
