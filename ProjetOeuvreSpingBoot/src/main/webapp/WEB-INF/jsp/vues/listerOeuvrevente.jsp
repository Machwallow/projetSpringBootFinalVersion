<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Listing des oeuvres en vente</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="/authentification/accueil" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des oeuvres en vente</h2>
    <div class="container main">
        <h3>Liste des oeuvres libres</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-1">Etat</th>
                <th class="col-md-3">Proprietaire</th>
                <th class="col-md-1">Prix</th>
            </tr>
            <c:forEach items="${mesOeuvresLibres}" var="item">
                <tr>
                    <td>${item.idOeuvrevente}</td>
                    <td>${item.titreOeuvrevente}</td>
                    <td>${item.etatOeuvrevente}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>${item.prixOeuvrevente} €</td>
                    <c:if test="${item.proprietaireByIdProprietaire.idProprietaire != sessionScope.idAdh && sessionScope.isAdmin != 1}">
                    <td>
                        <form method="post" action="/oeuvrevente/reserverOeuvre?id=${item.idOeuvrevente}">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-heart"></span>
                                Reserver
                            </button>
                        </form>
                    </td>
                    </c:if>
                    <c:if test="${sessionScope.isAdmin == 1}">
                    <td><a class="btn btn-warning" href="/oeuvrevente/updateViewOeuvrevente?id=${item.idOeuvrevente}" role="button"><span
                            class="glyphicon glyphicon-pencil"></span> Modifier</a></td>
                        <td>
                            <form method="post" action="/oeuvrevente/deleteOeuvrevente?id=${item.idOeuvrevente}">
                                <button type="submit" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove-circle"></span>
                                    Supprimer
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <h3>Liste des oeuvres réservées</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-1">Etat</th>
                <th class="col-md-3">Proprietaire</th>
                <th class="col-md-1">Prix</th>
            </tr>
            <c:forEach items="${mesOeuvresReservees}" var="item">
                <tr>
                    <td>${item.idOeuvrevente}</td>
                    <td>${item.titreOeuvrevente}</td>
                    <td>${item.etatOeuvrevente}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>${item.prixOeuvrevente} €</td>
                    <c:if test="${item.proprietaireByIdProprietaire.idProprietaire == sessionScope.idAdh}">
                    <td><a class="btn btn-info" href="/reservation/getReservations" role="button"><span
                            class="glyphicon glyphicon-pencil"></span> Gérer</a>
                    </c:if>
                    <c:if test="${sessionScope.isAdmin == 1}">
                    <td><a class="btn btn-warning" href="/oeuvrevente/updateViewOeuvrevente?id=${item.idOeuvrevente}" role="button"><span
                            class="glyphicon glyphicon-pencil"></span> Modifier</a></td>
                    <td>
                        <form method="post" action="/oeuvrevente/deleteOeuvrevente?id=${item.idOeuvrevente}">
                            <button type="submit" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                                Supprimer
                            </button>
                        </form>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <h3>Liste des oeuvres vendues</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-1">Etat</th>
                <th class="col-md-3">Proprietaire</th>
                <th class="col-md-1">Prix</th>
            </tr>
            <c:forEach items="${mesOeuvresVendues}" var="item">
                <tr>
                    <td>${item.idOeuvrevente}</td>
                    <td>${item.titreOeuvrevente}</td>
                    <td>${item.etatOeuvrevente}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>${item.prixOeuvrevente} €</td>
                    <c:if test="${sessionScope.isAdmin == 1}">
                        <td><a class="btn btn-warning" href="/oeuvrevente/updateViewOeuvrevente?id=${item.idOeuvrevente}" role="button"><span
                                class="glyphicon glyphicon-pencil"></span> Modifier</a></td>
                        <td>
                            <form method="post" action="/oeuvrevente/deleteOeuvrevente?id=${item.idOeuvrevente}">
                                <button type="submit" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove-circle"></span>
                                    Supprimer
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>