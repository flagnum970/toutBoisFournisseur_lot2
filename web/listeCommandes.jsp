<%-- 
    Document   : listeCommandes
    Created on : 18 janv. 2017, 12:47:08
    Author     : cflagollet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToutBois - Liste commandes</title>           
        <link type="text/css" rel="stylesheet" href="css/style.css" />
     </head>
    <body>
        <c:import url="/inc/header.jsp" />

<div id="corps">
<h2 style="color: #0568CD;"><b>Liste des commandes</b></h2>

<div class="container">
            <div class='row'>
                <div class='col-md-8 col-sm-offset-2'>
                    <div class="panel-group" id="accordion">

 <s:iterator value="#session.lCommandes" var="co">
 <div class="panel panel-default">
    <!-- Entête du panel --> 
    <div class="panel-heading">
        <div class='row'>
            <div class='col-md-8'>
                <h3 class="panel-title">
                <a data-toggle="collapse" href="#<s:property value="#co.id_com"/>">
                Commande <s:property value="#co.id_com"/></a></h3>
            </div>
            <div class='col-md-4 text-right'>
                <s:url id="editURL" action="UCommande">
                <s:param name="id_com" value="#co.id_com"></s:param>
                </s:url> <s:a href="%{editURL}"><i class="fa fa-edit" style="font-size:24px;color:blue;"></i></s:a>
                
                <s:url id="deleteURL" action="DCommande">
                <s:param name="id_com" value="#co.id_com"></s:param>
                </s:url> 
                <s:a href="%{deleteURL}" onClick="return confirm('Etes vous sur de vouloir supprimer la commande ${co.id_com} ?')">
                <i class="fa fa-times-circle" style="font-size:24px;color:red;"></i></s:a>
           
            </div>
        </div>
    </div>
                
    <!-- corps du panel -->                        
    <div id="<s:property value="#co.id_com"/>" class="panel-collapse collapse">
        <div class="panel-body">
            <div class="row">
                <div class='col-md-6'>
                <i class="fa fa-calendar" style="font-size:24px;margin:10px;"></i> <b>Date de commande</b> : <s:property value="#co.date_com"/> 
                </div>
                <div class='col-md-6'>
                <i class="fa fa-truck" style="font-size:24px;margin:10px;"></i> <b>Date de livraison</b> : <s:property value="#co.date_livr"/> 
                </div>
                </div>
                <div class="row">
                <div class='col-md-6'>
                <i class="fa fa-user" style="font-size:24px;margin:10px;"></i> <b>Fournisseur</b> : <s:property value="#co.fournisseur.enseigne"/> 
                </div>
                <div class='col-md-6'>
               <i class="fa fa-flag" style="font-size:24px;margin:10px;"></i> <b>Statut</b> : <s:property value="#co.statut.libelle_statut"/> 
                </div>
                </div>
                <hr>
                <i class="fa fa-shopping-cart" style="font-size:24px;margin:10px;"></i> <b>Produits</b> 
                <s:url id="mngligComURL" action="MngLignesCommandes">
                <s:param name="id_com" value="#co.id_com"></s:param>
                </s:url> <s:a href="%{mngligComURL}"><button type="button" class="btn btn-primary btn-xs" >Modifier</button></s:a><hr/>
            <ul>
            <s:iterator value="#co.l_com" var="lo">
                <li><s:property value="#lo.qte"/> <s:property value="#lo.produit.designation"/></li>                              
            </s:iterator>
            </ul>       
        </div>
    </div>
 </div>
 </s:iterator>                   
 
                    </div>
                </div>
             </div>
        </div>

        <s:url  id="CreateURL" action="CCommande"></s:url> <s:a href="%{CreateURL}" role="button" class="btn btn-primary btn-lg">Nouvelle Commande</s:a>
 </div>  
  <c:import url="/inc/footer.jsp" />
</body>
</html>
