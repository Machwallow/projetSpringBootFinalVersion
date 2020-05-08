<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Profile</h1>
</div>
 <div class="container">

     <h1>Vos Informations</h1>
     <br>
     <div class="row">
        <label class="col-md-2 control-label">Nom : </label>
        <div class="col-md-2">
            ${adherent.prenomAdherent} ${adherent.nomAdherent}
        </div>
     </div>
     <br>
     <div class="row">
        <label class="col-md-2 control-label">Ville : </label>
        <div class="col-md-2">
            ${adherent.villeAdherent}
        </div>
     </div>
     <br>
     <div class="row">
         <label class="col-md-2 control-label">Solde : </label>
         <div class="col-md-2">
             ${sessionScope.solde}
         </div>
     </div>
     <br>
     <form method="post" action="/profile/addSolde" onsubmit="return test()">
         <input type="number" name="addSolde" min="0" step="0.01" value="500">
         <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
             Ajouter des fonds
         </button>
     </form>
     <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
     </div>
 </div>
<%@include file="footer.jsp"%>
</body>

</html>