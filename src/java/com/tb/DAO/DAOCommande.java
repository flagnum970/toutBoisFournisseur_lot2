/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.DAO;

import com.tb.utils.HibernateUtils;
import com.tb.JPA.Commande;
import com.tb.JPA.Ligne_commande;
import com.tb.JPA.Statut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe couche DAO de gestion des commandes et des lignes de commande
 * Utilisation de Hibernate
 *
 * @author cflagollet
 *
 */
public class DAOCommande {

    /**
     * Methode de récupration d'une commande
     *
     * @param id_com Index de la commande demandée
     * @return la commande
     */
    public Commande getCommande(int id_com) {
        Session s = null;
        Commande c = null;
        List<Ligne_commande> llc = new ArrayList<Ligne_commande>();
        try {
            s = HibernateUtils.getSession();

            Query req = s.createQuery("from Commande where id = :monId");
            req.setString("monId", String.valueOf(id_com));
            c = (Commande) req.uniqueResult();

            req = s.createQuery("from Ligne_commande where id_com = :monId");
            req.setString("monId", String.valueOf(id_com));
            llc = (ArrayList) req.list();
            c.setL_com(llc);

            //fermeture session 
            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException nel) {
            System.out.println("erreur requete");
        }
        return c;
    }

    /**
     * Methode de récupration d'une liste de commandes
     *
     *
     * @return la liste des commandes
     */
    public List<Commande> getCommandes() {
        Session s = null;
        List<Commande> lc = new ArrayList<Commande>();
        List<Ligne_commande> llc = new ArrayList<Ligne_commande>();
        try {
            s = HibernateUtils.getSession();
            Query req = s.createQuery("from Commande order by date_com desc");

            lc = (ArrayList) req.list();

            for (Commande c : lc) {
                req = s.createQuery("from Ligne_commande where id_com = :monId");
                req.setString("monId", String.valueOf(c.getId_com()));
                llc = (ArrayList) req.list();
                c.setL_com(llc);
            }
            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException npex) {
            System.out.println("erreur requete");
        }
        return lc;
    }

    /**
     * Methode de suppression d'une commande
     *
     * @param id_com Index de la commande demandée
     * @return succès ou échec de méthode
     */
    public boolean delCommande(int id_com) {
        boolean bOk = false;
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtils.getSession();

            Query req = s.createQuery("from Commande where id = :monId");
            req.setString("monId", String.valueOf(id_com));
            Commande e = (Commande) req.uniqueResult();
            tx = s.beginTransaction();
            s.delete(e);
            tx.commit();
            s.close();
            bOk = true;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
        }
        return bOk;
    }

    /**
     * Methode de création d'une ligne de commande
     *
     * @param ligne_commande la ligne à créer (sans l'ID)
     * @return L'ID de la commande créée (ou -1 si erreur)
     */
    public Integer crLigneCommande(Ligne_commande ligne_commande) {
        Integer id = -1;
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtils.getSession();

            tx = s.beginTransaction();
            id = (Integer) s.save(ligne_commande);
            tx.commit();
            s.close();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
        }
        return id;
    }

    /**
     * Méthode de suppression d'une ligne de commande
     *
     * @param id ligne commande
     * @return succès ou échec
     */
    public boolean delLigneCommande(int id) {
        boolean bOk = false;
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtils.getSession();
            Query req = s.createQuery("from Ligne_commande where id = :monId");
            req.setString("monId", String.valueOf(id));
            Ligne_commande lc = (Ligne_commande) req.uniqueResult();
            tx = s.beginTransaction();
            s.delete(lc);
            tx.commit();
            s.close();
            bOk = true;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
        }
        return bOk;
    }

    /**
     * Méthode de création d'une commande
     *
     * @param com (sans l'ID)
     * @return l'ID (ou -1 si erreur)
     */
    public Integer crCommande(Commande com) {
        Integer id = -1;
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtils.getSession();
            tx = s.beginTransaction();
            id = (Integer) s.save(com);
            tx.commit();
            s.close();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
        }
        return id;
    }

    /**
     * Méthode de maj d'une commande
     *
     * @param com
     * @return succès ou échec
     */
    public boolean upCommande(Commande com) {
        boolean bOk = false;
        Session s = null;
        Transaction tx = null;

        try {
            s = HibernateUtils.getSession();
            tx = s.beginTransaction();
            s.update(com);
            tx.commit();
            s.close();
            bOk = true;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
        }
        return bOk;
    }

    /**
     * Méthode de récupération de la table statuts
     *
     * @return map(code_statut,libellé_statut)
     */
    public Map<String, String> getStatuts() {
        Map<String, String> lStatuts = new HashMap<String, String>();
        Session s = null;
        List<Statut> ls = new ArrayList<Statut>();
        try {
            s = HibernateUtils.getSession();
            Query req = s.createQuery("from Statut");

            ls = (ArrayList) req.list();
            for (Statut st : ls) {
                lStatuts.put(st.getCode_statut(), st.getLibelle_statut());
            }
            s.close();
        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException npex) {
            System.out.println("erreur requete");
        }
        return lStatuts;
    }

    /**
     * méthode de récupération d'un statut à partir de son code
     *
     * @param code_statut
     * @return le statut
     */
    public Statut getStatut(String code_statut) {
        Session s = null;
        Statut st = null;
        try {
            s = HibernateUtils.getSession();

            //En lecture, pas de transaction!!!!!!
            Query req = s.createQuery("from Statut where code_statut = :monId");
            req.setString("monId", code_statut);
            st = (Statut) req.uniqueResult();

            //fermeture session 
            s.close();

        } catch (HibernateException ex) {
            System.out.println("**************************************ERREUR HIBERNATE******************************************");
            System.out.println(ex.getMessage());
        } catch (NullPointerException nel) {
            System.out.println("erreur requete");
        }
        return st;
    }
}
