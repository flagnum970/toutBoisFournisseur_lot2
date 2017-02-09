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
import com.tb.JPA.Statut;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Classe Struts2 d'Action concernant les commandes joue le rôle de contrôleur :
 * Appelle la couche DAO pour maintenir la session en lien avec
 * formCommandeC.jsp, formCommandeU.jsp
 *
 * @author cflagollet
 */
public class CommandeAction extends ActionSupport implements SessionAware {

    private DAOCommande DAOc = new DAOCommande();
    private DAOFournisseur DAOf = new DAOFournisseur();
    private int id_com;
    private Date date_com;
    private Date date_livr;
    private Statut statut;
    private int code_fou;
    private String ens_fou;
    private String code_statut;
    private String libelle_statut;
    private Map<String, Object> session;

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public CommandeAction() {
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public Date getDate_com() {
        return date_com;
    }

    public String getEns_fou() {
        return ens_fou;
    }

    public void setEns_fou(String ens_fou) {
        this.ens_fou = ens_fou;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }

    public Date getDate_livr() {
        return date_livr;
    }

    public void setDate_livr(Date date_livr) {
        this.date_livr = date_livr;
    }

    public String getLibelle_statut() {
        return libelle_statut;
    }

    public void setLibelle_statut(String libelle_statut) {
        this.libelle_statut = libelle_statut;
    }

    public String getCode_statut() {
        return code_statut;
    }

    public void setCode_statut(String code_statut) {
        this.code_statut = code_statut;
    }

    public int getCode_fou() {
        return code_fou;
    }

    public void setCode_fou(int code_fou) {
        this.code_fou = code_fou;
    }

    public String UpdateCommandePrepare() {
        session.put("mFours", DAOf.getEnseigneFournisseurs());
        session.put("commande", DAOc.getCommande(id_com));
        session.put("mStatut", DAOc.getStatuts());
        return "success";
    }

    public String CreateCommandePrepare() {
        //recupération de la liste des enseignes des fournisseurs        
        session.put("mFours", DAOf.getEnseigneFournisseurs());
        session.put("mStatut", DAOc.getStatuts());
        return "success";
    }

    public String updateCommande() {
        Fournisseur f = DAOf.getFournisseur(code_fou);
        Statut s = DAOc.getStatut(code_statut);
        boolean ret = DAOc.upCommande(new Commande(id_com, date_com, date_livr, s, f, null));

        //Maj session------------------------------------------------
        session.put("lCommandes", DAOc.getCommandes());
        session.put("commande", DAOc.getCommande(id_com));
        session.put("produits", DAOf.getProduits(code_fou, id_com));

        return (ret) ? "success" : "input";
    }

    public String createCommande() {

        id_com = DAOc.crCommande(new Commande(date_com, date_livr,
                DAOc.getStatut(code_statut),
                DAOf.getFournisseur(code_fou)));

        //Maj Session---------------------------------------------------
        session.put("lCommandes", DAOc.getCommandes());
        session.put("commande", DAOc.getCommande(id_com));
        session.put("produits", DAOf.getProduits(DAOc.getCommande(id_com).getFournisseur().getCode_fou(), id_com));

        return (id_com != -1) ? "success" : "input";
    }

    @SkipValidation
    public String DeleteCommande() {
        if (DAOc.delCommande(id_com)) {
            session.put("lCommandes", DAOc.getCommandes());
            return "success";
        } else {
            return "input";
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
