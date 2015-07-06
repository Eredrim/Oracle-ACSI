<%@include file="../static/header.html" %>
<h1>Connexion</h1>
<form method="post" action="connexion">
    <label for="login">Identifiant</label>
    <input name="Login" type="email"/>
    <label for="code postal">Mot de passe</label>
    <input name="code postal" type="text"/>
    <button text="Valider" type="submit"/>
</form>
<%@include file="../static/footer.html" %>
