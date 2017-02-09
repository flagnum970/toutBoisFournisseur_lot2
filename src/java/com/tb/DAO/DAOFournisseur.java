/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.DAO;

import com.tb.utils.HibernateUtils;
import com.tb.JPA.Commande;
import com.tb.JPA.Fournisseur;
import com.tb.JPA.Ligne_commande;
import com.tb.JPA.Produit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * Classe couche DAO de gestion des fournisseurs et de leurs produits associés
 *
 * Utilisation de Hibernate
 *
 * @author cflagollet
 */
public class DAOFournisseur {

    /**
     * méthode d'obtention d'un fournisseur
     *
     * @param id_fou index du fournisseur
     * @return le fournisseur
     */
    public Fournisseur getFournisseur(int id_fou) {
        Session s = null;
        Fournisseur f = null;
        try {
            s = HibernateUtils.getSession();

            Query req = s.createQuery("from Fournisseur where id = :monId");
            req.setString("monId", String.valueOf(id_fou));
            f = (Fournisseur) req.uniqueResult();
            System.out.println("fournisseur : " + f.getEnseigne());
            //fermeture session 
            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException nel) {
            System.out.println("erreur requete");
        }
        return f;
    }

    /**
     *
     * Méthode de récupération de la liste des enseignes des fournisseurs
     *
     * @return une map(id_fournisseur,enseigne)
     */
    public Map<Integer, String> getEnseigneFournisseurs() {
        Session s = null;
        Map<Integer, String> mFours = new HashMap<Integer, String>();
        try {
            s = HibernateUtils.getSession();

            Query req = s.createQuery("from Fournisseur");

            List<Fournisseur> lF = (ArrayList) req.list();
            for (Fournisseur f : lF) {
                mFours.put(f.getCode_fou(), f.getEnseigne());
            }
            //fermeture session 
            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException npex) {
            System.out.println("erreur requete");
        }
        return mFours;
    }

    /**
     * récupération d'un fournisseur par son enseigne
     *
     * @param monEnseigne enseigne
     * @return le fournisseur associé
     * @deprecated
     */
    public Fournisseur getFournisseurFromEnseigne(String monEnseigne) {
        Session s = null;
        Fournisseur f = null;

        try {
            s = HibernateUtils.getSession();

            Query req = s.createQuery("from Fournisseur where enseigne = :monEnseigne");
            req.setString("monEnseigne", monEnseigne);
            f = (Fournisseur) req.uniqueResult();

            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException npex) {
            System.out.println("erreur requete");
        }
        return f;
    }

    /**
     * méthode de récupération la liste des produits disponibles selon un n° de
     * commande et un code_fournisseur (de cette commande) renvoie tous les
     * produits du fournisseur, sauf ceux qui sont déjà dans la commande passée
     * en paramètre
     *
     * @param code_fou code fournisseur
     * @param id_com id de la commande
     * @return liste de produits
     */
    public List<Produit> getProduits(int code_fou, int id_com) {
        List<Produit> lP = null;
        Session s = null;

        try {
            s = HibernateUtils.getSession();

            // ne fonctionne pas, pb de mapping, 
/*            Query req = s.createQuery("from Produit as produit where code_fou = :monId "
                    + "produit.code_prod not in (select code_prod from Ligne_commande.commande where id_com= :idCom");
            req.setString("monId", String.valueOf(code_fou));
            req.setString("idCom", String.valueOf(id_com)); */
            Query req = s.createQuery("from Produit as produit where code_fou = :monId ");
            req.setString("monId", String.valueOf(code_fou));
            lP = (ArrayList) req.list();

            Query req2 = s.createQuery("from Ligne_commande where id_com= :idCom");
            req2.setString("idCom", String.valueOf(id_com));
            List<Ligne_commande> lLc = (ArrayList) req2.list();
            List<Produit> lPAsup = new ArrayList<Produit>();
            lLc.forEach((lc) -> {
                lPAsup.add(lc.getProduit());
            });
            lP.removeAll(lPAsup);

            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException npex) {
            System.out.println("erreur requete");
        }
        return lP;
    }

    /**
     * Méthode de récupération d'un produit
     *
     * @param code_prod code produit
     * @return le produit
     */
    public Produit getProduit(int code_prod) {
        Session s = null;
        Produit p = null;
        try {
            s = HibernateUtils.getSession();

            Query req = s.createQuery("from Produit where id = :monId");
            req.setString("monId", String.valueOf(code_prod));
            p = (Produit) req.uniqueResult();
            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException nel) {
            System.out.println("erreur requete");
        }
        return p;
    }

}
