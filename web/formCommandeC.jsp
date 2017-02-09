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
        <title>ToutBois - Creation commande</title>
    </head>
    <body>
       <c:import url="/inc/header.jsp" />
        
         <div class="container">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-2">
                <fieldset>
                <legend>Création de commande</legend>    
            <s:form action="crCommande" method="post" validate="true">
            <s:select label="Fournisseur"
                 headerKey="-1" headerValue="Sélectionnez le fournisseur"
                 list="#session.mFours" name="code_fou" value=""/>  
            <s:textfield name="date_com" id="date_com" label="date commande" />
            <s:textfield name="date_livr" id="date_livr" label="date livraison" />           
            <s:select label="Statut"                
                 list="#session.mStatuts" name="code_statut"/>  
            <s:submit value="Continuer" cssClass="btn btn-primary text-center"></s:submit>
            </s:form></fieldset>
                </div></div></div>
        <c:import url="/inc/footer.jsp" />
    </body>
</html>
