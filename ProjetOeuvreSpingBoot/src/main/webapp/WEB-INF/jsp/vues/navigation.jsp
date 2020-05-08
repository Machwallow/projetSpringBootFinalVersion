<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Médiathèque de POLYTECH</a>
        </div>
        <p class="navbar-text">Gestion de l'exposition 2019</p>
        <ul class="nav navbar-nav">
            <li><a href="/authentification/accueil"> <span class="glyphicon glyphicon-home"></span> Accueil</a></li>
            <c:if test="${sessionScope.id == null }">
            <li class="dropdown">
                <a class="nav navbar-nav navbar-right"  href="/authentification/login">
                    <span class="glyphicon glyphicon-user"></span>
                    Se Connecter
                    <span class="caret"></span>
                </a>
            </li>
            </c:if>
            <c:if test="${sessionScope.isAdmin == 1}">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="glyphicon glyphicon-list"></span>
                    Adhérents
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/mediatheque/ajout"> <span class="glyphicon glyphicon-plus"></span> Ajout Adhérent</a></li>
                    <li><a href="/mediatheque/getAdherents"><span class="glyphicon glyphicon-th-list"></span> Lister les adhérents</a></li>
                </ul>
            </li>
            </c:if>
            <c:if test="${sessionScope.id > 0 }">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="glyphicon glyphicon-file"></span>
                    Oeuvres prêtées
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <c:if test="${sessionScope.isAdmin == 1}">
                        <li><a href="/oeuvrepret/addViewOeuvrepret"> <span class="glyphicon glyphicon-plus"></span> Ajouter une oeuvre</a></li>
                    </c:if>
                    <li><a href="/oeuvrepret/getOeuvres"><span class="glyphicon glyphicon-th-list"></span> Lister les oeuvres</a></li>
                </ul>
            </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-file"></span>
                        Oeuvres en vente
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:if test="${sessionScope.isAdmin == 1}">
                            <li><a href="/oeuvrevente/addViewOeuvrevente"> <span class="glyphicon glyphicon-plus"></span> Ajouter une oeuvre</a></li>
                        </c:if>
                        <li><a href="/oeuvrevente/getOeuvres"><span class="glyphicon glyphicon-th-list"></span> Lister les oeuvres</a></li>
                    </ul>
                </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="glyphicon glyphicon-user"></span>
                    Votre profile
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <c:if test="${sessionScope.isAdmin != 1}">
                    <li style="background-color : lightgray"><a> Solde ${sessionScope.solde} <span class="glyphicon glyphicon-euro"></span></a></li>
                    <li class="drop"><a href="/profile/gererProfile"><span class="glyphicon glyphicon-cog"></span> Gérer</a></li>
                    <li class="drop"><a href="/profile/getOeuvres"><span class="glyphicon glyphicon-list"></span> Vos oeuvres</a></li>
                    <li class="drop"><a href="/reservation/getReservations"><span class="glyphicon glyphicon-list"></span> Vos reservations</a></li>
                    </c:if>
                    <li><a href="/authentification/disconnect"><span class="glyphicon glyphicon-log-out"></span> Quitter</a></li>
                </ul>
            </li>
            </c:if>
        </ul>
    </div>
</nav>
