/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.JPA;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Type;
import java.util.Date;

/**
 *
 * Classe JPA de Commande Mapping Hibernate
 *
 * @author cflagollet
 */
@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_com")
    private int id_com;
    @Type(type = "date")
    private Date date_com;
    @Type(type = "date")
    private Date date_livr;
    @OneToOne
    @JoinColumn(name = "code_statut", referencedColumnName = "code_statut")
    private Statut statut;
    @OneToOne
    @JoinColumn(name = "code_fou", referencedColumnName = "code_fou")
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "commande", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Ligne_commande> l_com;

    public Commande() {
    }

    public Commande(Date date_com, Date date_livr, Statut statut, Fournisseur fournisseur, List<Ligne_commande> l_com) {
        this.date_com = date_com;
        this.date_livr = date_livr;
        this.statut = statut;
        this.fournisseur = fournisseur;
        this.l_com = l_com;
    }

    public Commande(int id_com, Date date_com, Date date_livr, Statut statut, Fournisseur fournisseur, List<Ligne_commande> l_com) {
        this.id_com = id_com;
        this.date_com = date_com;
        this.date_livr = date_livr;
        this.statut = statut;
        this.fournisseur = fournisseur;
        this.l_com = l_com;
    }

    public Commande(Date date_com, Date date_livr, Statut statut, Fournisseur fournisseur) {
        this.date_com = date_com;
        this.date_livr = date_livr;
        this.statut = statut;
        this.fournisseur = fournisseur;
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

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }

    public Date getDate_livr() {
        return date_livr;
    }

    public void setDate_livr(Date date_livr) {
        this.date_livr = date_livr;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<Ligne_commande> getL_com() {
        return l_com;
    }

    public void setL_com(List<Ligne_commande> l_com) {
        this.l_com = l_com;
    }

    public void addL_com(Ligne_commande l_com) {
        this.l_com.add(l_com);
    }

}
