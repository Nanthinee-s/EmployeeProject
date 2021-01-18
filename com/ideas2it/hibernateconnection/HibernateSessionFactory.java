package com.ideas2it.hibernateconnection;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class HibernateSessionFactory {    
    private static SessionFactory sessionFactory = null;
    public static SessionFactory buildSessionFactory() {
    try {
        if ( sessionFactory == null ) {
            sessionFactory = new Configuration().configure("resource/properties/Hibernate.cfg.xml").buildSessionFactory();
            System.out.println("SESSION FACTORY NEWLY CREATED");
        }
    } catch( HibernateException e ) {
        System.out.println(e);
        e.printStackTrace();
    }
    return sessionFactory;
    }
    public static void closeSessionFactory() {
        sessionFactory.close();
        System.out.println("SESSION FACTORY CLOSED SUCCESSFULLY");
    }
}


