/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.JPA;

import java.io.Serializable;
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

/**
 *
 * Classe JPA de Statut - Mapping Hibernate
 *
 * @author cflagollet
 */
@Entity
@Table(name = "statut")
public class Statut implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_statut;
    private String code_statut;
    private String libelle_statut;

    public Statut() {
    }

    public int getId_statut() {
        return id_statut;
    }

    public void setId_statut(int id_statut) {
        this.id_statut = id_statut;
    }

    public String getCode_statut() {
        return code_statut;
    }

    public void setCode_statut(String code_statut) {
        this.code_statut = code_statut;
    }

    public String getLibelle_statut() {
        return libelle_statut;
    }

    public void setLibelle_statut(String libelle_statut) {
        this.libelle_statut = libelle_statut;
    }

}
