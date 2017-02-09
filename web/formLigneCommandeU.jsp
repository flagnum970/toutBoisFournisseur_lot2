<%-- 
    Document   : listeCommandes
    Created on : 18 janv. 2017, 12:47:08
    Author     : cflagollet
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToutBois - Commande Modification des produits</title>           
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <script type="text/javascript" src="js/verifFormat.js"></script>
     </head>
    <body>
        <c:import url="/inc/header.jsp" />
     <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-2">
                <fieldset> 
                    <legend>Gestion des produits de la commande <s:property value="#session.commande.id_com"/></legend>
                    <p><i>Date de la commande : <s:property value="#session.commande.date_com"/> | Date de livraison : <s:property value="#session.commande.date_livr"/></i></p>
                    <p><i>Statut : <s:property value="#session.commande.statut.libelle_statut"/> | Fournisseur : <s:property value="#session.commande.fournisseur.enseigne"/></i></p>
                    <hr/>
                    <legend>Produits commandés</legend>
       <div class="table-responsive">
        <table class="table table-striped">        
           
                <thead><tr>
                    <th>Quantité commandée</th>
                    <th>Produit</th>
                    <th></th>
                </tr> </thead> 
                <tbody>
             <s:iterator value="#session.commande.l_com" var="lo">      
                  <tr>
                    <td><s:property value="#lo.qte"/></td>
                    <td><s:property value="#lo.produit.designation"/></td>                                        
                    <td class="com"><s:url id="deleteURL" action="DLigneCommande">
                        <s:param name="id" value="#lo.id"></s:param>
                        <s:param name="id_com" value="#session.commande.id_com"></s:param>
                        <s:param name="code_fou" value="#session.commande.fournisseur.code_fou"></s:param>
                        </s:url> <s:a href="%{deleteURL}" onClick="return confirm('Etes vous sur de vouloir supprimer la ligne de commande ${lo.id} ?')">
                        <i class="fa fa-times-circle" style="font-size:24px;color:red;"></i></s:a>
                    </td>
                </tr>
            </s:iterator>
                </tbody></table>
           <legend>Produits pouvant être ajoutés</legend>
        <table class="table table-striped">        
           
                <thead><tr>
                    <th>Prix unitaire</th>
                    <th>Produit</th>
                    <th></th>
                </tr> </thead>    
           
                <tbody>
            <s:iterator value="#session.produits" var="pr">
                <tr>
                    <td><s:property value="#pr.pu"/></td>
                    <td><s:property value="#pr.designation"/></td>                                        
                    <td class="com">
                        <s:form action="CLigneCommande" onsubmit="return verifQte(this)" theme="simple">
                            <s:textfield name="code_prod" id="code_prod" value="%{#pr.code_prod}" hidden="true" />
                            <s:textfield name="id_com" id="id_com" value="%{#session.commande.id_com}" hidden="true" />
                            <s:textfield name="code_fou" id="code_fou" value="%{#session.commande.fournisseur.code_fou}" hidden="true" />
                            <s:textfield name="qte" id="qte" value="0" size="2"/>                            
                                <s:submit value="Ajouter" cssClass="btn btn-default btn-sm"></s:submit>
                        </s:form>
                    </td>
                </tr>
            </s:iterator>
                </tbody>
        </table> </div>        
        <div class="col-sm-12 text-center">
                       
                <a href="gestionCommandes" role="button" class="btn btn-primary btn-lg">Terminer</a>
           
        </div></fieldset>
     </div></div></div>
             <c:import url="/inc/footer.jsp" />
    </body>
</html>
