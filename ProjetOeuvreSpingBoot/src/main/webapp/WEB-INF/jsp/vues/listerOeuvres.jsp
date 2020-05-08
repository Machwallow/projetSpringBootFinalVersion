<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Listing des vos oeuvres</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="/authentification/accueil" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau de vos oeuvres</h2>
    <div class="container main">
        <h3>Liste de vos oeuvres en prêt</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-3">Proprietaire</th>
                <th class="col-md-3">Emprunté par</th>

            </tr>

            <c:forEach items="${mesOeuvrepret}" var="item">
                <tr>
                    <td>${item.idOeuvrepret}</td>
                    <td>${item.titreOeuvrepret}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>${item.adherentByIdAdherent.nomAdherent} ${item.adherentByIdAdherent.prenomAdherent}</td>
                </tr>
            </c:forEach>
        </table>
        <h3>Liste des vos oeuvres en vente</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-1">Etat</th>
                <th class="col-md-1">Prix</th>
            </tr>
            <c:forEach items="${mesOeuvrevente}" var="item">
                <tr>
                    <td>${item.idOeuvrevente}</td>
                    <td>${item.titreOeuvrevente}</td>
                    <td>${item.etatOeuvrevente}</td>
                    <td>${item.prixOeuvrevente} €</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <h2>Tableau de vos emprunts</h2>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-3">Proprietaire</th>
            </tr>

            <c:forEach items="${mesOeuvreempruntees}" var="item">
                <tr>
                    <td>${item.idOeuvrepret}</td>
                    <td>${item.titreOeuvrepret}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>
                        <form method="post" action="/oeuvrepret/rendreOeuvre?id=${item.idOeuvrepret}">
                            <button type="submit" class="btn btn-warning">
                                <span class="glyphicon glyphicon-minus-sign"></span>
                                Rendre
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>