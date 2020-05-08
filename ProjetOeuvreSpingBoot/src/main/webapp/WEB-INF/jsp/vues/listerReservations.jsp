<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Listing de vos réservations</h1>
</div>

<div class="container main">
    <a class="btn btn-secondary" href="/authentification/accueil" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <div class="container">
        <h3>Vos réservations :</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-2">Proprietaire</th>
                <th class="col-md-2">Date</th>
                <th class="col-md-2">Statut</th>

            </tr>

            <c:forEach items="${mesReservations}" var="item">
                <tr>
                    <td>${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}</td>
                    <td>${item.oeuvreventeByIdOeuvrevente.titreOeuvrevente}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>${item.dateReservation}</td>
                    <td>${item.statut}</td>
                    <td>
                        <c:if test="${item.statut == 'confirmee' }">
                        <form method="post" action="/oeuvrevente/buyOeuvre?id=${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}">
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-shopping-cart"></span>
                                Acheter
                            </button>
                        </form>
                        </c:if>
                    </td>
                    <td>
                        <form method="post" action="/reservation/deleteReservation?idOeuvre=${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}&idAdh=${item.adherentByIdAdherent.idAdherent}">
                            <button type="submit" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                                Annuler
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <h3>Liste des réservations sur vos oeuvres :</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-2">Réservé par</th>
                <th class="col-md-2">Date</th>
                <th class="col-md-2">Statut</th>

            </tr>

            <c:forEach items="${mesOeuvresReservees}" var="item">
                <tr>
                    <td>${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}</td>
                    <td>${item.oeuvreventeByIdOeuvrevente.titreOeuvrevente}</td>
                    <td>${item.adherentByIdAdherent.nomAdherent} ${item.adherentByIdAdherent.prenomAdherent}</td>
                    <td>${item.dateReservation}</td>
                    <td>${item.statut}</td>
                    <c:if test="${item.statut == 'en attente'}">
                    <td>
                        <form method="post" action="/reservation/validateReservation?idOeuvre=${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}&idAdh=${item.adherentByIdAdherent.idAdherent}">
                            <button type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-ok-sign"></span>
                                Valider
                            </button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="/reservation/deleteReservation?idOeuvre=${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}&idAdh=${item.adherentByIdAdherent.idAdherent}">
                            <button type="submit" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                                Refuser
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