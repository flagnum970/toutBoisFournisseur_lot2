/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tb.DAO.DAOCommande;
import com.tb.DAO.DAOFournisseur;
import com.tb.JPA.Commande;
import com.tb.JPA.Fournisseur;
import com.tb.JPA.Ligne_commande;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Classe Struts2 d'Action concernant les lignes de commande joue le rôle de
 * contrôleur : Appelle la couche DAO pour maintenir la session en lien avec
 * formLigneCommandeU.jsp
 *
 * @author cflagollet
 */
public class LigneCommandeAction extends ActionSupport implements SessionAware {

    private DAOCommande DAOc = new DAOCommande();
    private DAOFournisseur DAOf = new DAOFournisseur();
    private int id;
    private int code_fou;
    private int id_com;
    private int code_prod;
    private int qte;
    private Map<String, Object> session;

    public LigneCommandeAction() {
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode_prod() {
        return code_prod;
    }

    public void setCode_prod(int code_prod) {
        this.code_prod = code_prod;
    }

    public int getCode_fou() {
        return code_fou;
    }

    public void setCode_fou(int code_fou) {
        this.code_fou = code_fou;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    @SkipValidation
    public String DeleteLigneCommande() {
        if (DAOc.delLigneCommande(id)) {
            session.put("commande", DAOc.getCommande(id_com));
            session.put("produits", DAOf.getProduits(code_fou, id_com));
            return "success";
        } else {
            return "input";
        }
    }

    public String CreateLigneCommande() {

        System.out.println("creation ligne commande " + qte + " " + id_com + " " + code_prod + " " + code_fou + "-------------------------------");

        int id_lCom = DAOc.crLigneCommande(new Ligne_commande(qte, DAOc.getCommande(id_com),
                DAOf.getProduit(code_prod)));

        //Maj Session---------------------------------------------------
        session.put("lCommandes", DAOc.getCommandes());
        session.put("commande", DAOc.getCommande(id_com));
        session.put("produits", DAOf.getProduits(code_fou, id_com));

        return (id_lCom != -1) ? "success" : "input";
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
