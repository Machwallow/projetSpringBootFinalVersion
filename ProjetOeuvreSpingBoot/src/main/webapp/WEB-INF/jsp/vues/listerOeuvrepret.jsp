<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Listing des oeuvres empruntées</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="/authentification/accueil" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des oeuvres empruntées</h2>
    <div class="container main">
        <h3>Liste des oeuvres</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero</th>
                <th class="col-md-2">Titre</th>
                <th class="col-md-3">Proprietaire</th>
                <th class="col-md-3">Emprunté par</th>

            </tr>

            <c:forEach items="${mesOeuvres}" var="item">
                <tr>
                    <td>${item.idOeuvrepret}</td>
                    <td>${item.titreOeuvrepret}</td>
                    <td>${item.proprietaireByIdProprietaire.nomProprietaire} ${item.proprietaireByIdProprietaire.prenomProprietaire}</td>
                    <td>${item.adherentByIdAdherent.nomAdherent} ${item.adherentByIdAdherent.prenomAdherent}</td>

                    <c:if test="${item.adherentByIdAdherent.idAdherent == null && sessionScope.isAdmin != 1 && item.proprietaireByIdProprietaire.idProprietaire != sessionScope.idAdh}">
                        <td>
                            <form method="post" action="/oeuvrepret/emprunterOeuvre?id=${item.idOeuvrepret}">
                                <button type="submit" class="btn btn-success">
                                    <span class="glyphicon glyphicon-plus-sign"></span>
                                    Emprunter
                                </button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${item.adherentByIdAdherent.idAdherent == sessionScope.idAdh && sessionScope.isAdmin != 1}">
                        <td>
                            <form method="post" action="/oeuvrepret/rendreOeuvre?id=${item.idOeuvrepret}">
                                <button type="submit" class="btn btn-warning">
                                    <span class="glyphicon glyphicon-minus-sign"></span>
                                    Rendre
                                </button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${sessionScope.isAdmin == 1}">
                    <td>
                        <a class="btn btn-warning" href="/oeuvrepret/updateViewOeuvrepret?id=${item.idOeuvrepret}" role="button"><span
                                class="glyphicon glyphicon-pencil"></span> Modifier</a>
                    </td>
                    <td>
                        <form method="post" action="/oeuvrepret/deleteOeuvre?id=${item.idOeuvrepret}">
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