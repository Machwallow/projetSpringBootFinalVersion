<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <H1> Modification d'une Oeuvre pret </H1>
</div>
<form method="post" action="/oeuvrepret/updateOeuvrepret" onsubmit="return test()">
    <div class="col-md-12 well well-md">
        <input type="hidden" name="idOeuvre" value="${oeuvre.idOeuvrepret}">
        <h1>Modification Oeuvre n° ${oeuvre.idOeuvrepret}</h1>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
            <div class="col-md-3">
                <INPUT type="text" name="titre" value="${oeuvre.titreOeuvrepret}" id="titre" class="form-control" min="0">
            </div>

        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Proprietaire : </label>
            <div class="col-md-3">
                <select name="idProprietaire" required>
                    <c:forEach items="${allProp}" var="item">
                        <option value="${item.idProprietaire}"
                                <c:if test="${oeuvre.proprietaireByIdProprietaire.idProprietaire == item.idProprietaire}"> selected </c:if>
                        >${item.nomProprietaire} ${item.prenomProprietaire}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Loué par : </label>
            <div class="col-md-3">
                <select name="idAdherent">
                        <option value="null">-      -</option>
                    <c:forEach items="${allAdh}" var="item">
                        <option value="${item.idAdherent}"
                                <c:if test="${oeuvre.adherentByIdAdherent.idAdherent == item.idAdherent}"> selected </c:if>
                        >${item.nomAdherent} ${item.prenomAdherent}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Modifier
            </button>

            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                            window.location = './listerOeuvrepret';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>