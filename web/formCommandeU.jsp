<%-- 
    Document   : formCommandes
    Created on : 20 janv. 2017, 09:15:31
    Author     : cflagollet
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <title>ToutBois - Modification commande</title>
    </head>
    <body>
        <c:import url="/inc/header.jsp" />
        
         <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-2">
                <fieldset>
        <s:form action="upCommande"  method="post" validate="true">
            <legend>Modification de la commande <s:property value="%{#session.commande.id_com}"/></legend>
            <s:textfield name="id_com" value="%{#session.commande.id_com}" hidden="true"/>
            <s:textfield name="ens_fou" value="%{#session.commande.fournisseur.enseigne}" hidden="true"/>
            <p><b><i>Fournisseur :  <s:property value="%{#session.commande.fournisseur.enseigne}"/></i></b></p>
            <s:textfield name="date_com" id="date_com" value="%{#session.commande.date_com}" label="date commande" />
            <s:textfield name="date_livr" id="date_livr" value="%{#session.commande.date_livr}" label="date livraison" />            
            <s:select label="Statut"                
                 list="#session.mStatuts" name="code_statut" value="%{#session.commande.statut.code_statut}"
            />   
            <s:textfield name="code_fou" value="%{#session.commande.fournisseur.code_fou}" hidden="true"/>
           
            <s:submit value="Continuer"></s:submit>
        </s:form></fieldset>
                
                
            </div></div></div>
    <c:import url="/inc/footer.jsp" />
    </body>
</html>
