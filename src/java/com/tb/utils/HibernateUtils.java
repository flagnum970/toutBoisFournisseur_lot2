/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author cflagollet
 *
 * Classe utilitaire de Hibernate
 */
public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(ssrb.build());

        } catch (HibernateException he) {
            System.err.println("Erreur création de la session : " + he);
            throw new ExceptionInInitializerError(he);
        }
    }

    /**
     * méthode d'obtention d'une session Hibernate
     *
     * @return une une session Hibernate
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
