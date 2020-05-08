<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <H1> Modification d'un adhérent </H1>
</div>
<form method="post" action="/mediatheque/updateAdherent" onsubmit="return test()">
    <div class="col-md-12 well well-md">
        <input type="hidden" name="idAdh" value="${adh.idAdherent}">
        <h1>Modification Adherent n° ${adh.idAdherent}</h1>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Nom de l'adherent : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtnom" value="${adh.nomAdherent}" id="nom" class="form-control" min="0">
            </div>

        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Prénom de l'adherent : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtprenom" value="${adh.prenomAdherent}" id="prenom" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Ville de l'adherent : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtville" value="${adh.villeAdherent}" id="ville" class="form-control" min="0">
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
                            window.location = './listerOeuvrepret.jsp';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>