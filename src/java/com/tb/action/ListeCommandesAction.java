/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tb.JPA.Commande;
import com.tb.DAO.DAOCommande;
import com.tb.DAO.DAOFournisseur;
import com.tb.JPA.Ligne_commande;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Classe Struts2 d'Action concernant la liste des commandes joue le rôle de
 * contrôleur : Appelle la couche DAO pour maintenir la session en lien avec
 * listeCommandes.jsp
 *
 * @author cflagollet
 */
public class ListeCommandesAction extends ActionSupport implements SessionAware {

    private DAOCommande DAOc = new DAOCommande();
    private DAOFournisseur DAOf = new DAOFournisseur();
    private Commande commande;
    private List<Commande> lCommandes;
    // session
    private Map<String, Object> session;
    private int id_com;

    public String getlistecommandes() {
        lCommandes = DAOc.getCommandes();
        session.put("lCommandes", lCommandes);
        session.put("mStatuts", DAOc.getStatuts());
        System.out.println("********************************************************nombre de commandes : " + lCommandes.size());
        return "success";
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<Commande> getLCommandes() {
        return lCommandes;
    }

    public void setLCommandes(List<Commande> lCommandes) {
        this.lCommandes = lCommandes;
    }

    public String updateLignesCommande() {
        session.put("commande", DAOc.getCommande(id_com));
        session.put("produits", DAOf.getProduits(DAOc.getCommande(id_com).getFournisseur().getCode_fou(), id_com));
        return "success";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

}
