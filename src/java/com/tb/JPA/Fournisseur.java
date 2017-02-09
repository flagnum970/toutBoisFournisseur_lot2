/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.JPA;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Classe JPA de Fournisseur - Mapping Hibernate
 *
 * @author cflagollet
 */
@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code_fou")
    private int code_fou;
    private String enseigne;
    private String siret;
    private String adresse;
    private String cp;
    private String ville;
    private String pays;
    private String email;
    private String telephone;
    @OneToMany(mappedBy = "fournisseur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Produit> lProduit;

    public Fournisseur() {
    }

    public Fournisseur(int code_fou, String enseigne, String adresse, String cp, String ville, String pays, String email, String telephone, String siret) {
        this.code_fou = code_fou;
        this.enseigne = enseigne;
        this.siret = siret;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.email = email;
        this.telephone = telephone;
    }

    public Fournisseur(String enseigne, String adresse, String cp, String ville, String pays, String email, String telephone, String siret) {
        this.enseigne = enseigne;
        this.siret = siret;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.email = email;
        this.telephone = telephone;
    }

    public List<Produit> getLProduit() {
        return lProduit;
    }

    public void setLProduit(List<Produit> lProduit) {
        this.lProduit = lProduit;
    }

    public int getCode_fou() {
        return code_fou;
    }

    public void setCode_fou(int code_fou) {
        this.code_fou = code_fou;
    }

    public String getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(String enseigne) {
        this.enseigne = enseigne;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
