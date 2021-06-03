package com.adast_adamov.business_helper.repositories.implementations;

import com.adast_adamov.business_helper.models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collections;
import java.util.List;

public class HibernateRepository {

    private SessionFactory factory;
    private Session session;

    HibernateRepository(Class<?> clazz) {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(clazz)
                .buildSessionFactory();
    }

    protected <T> List<T> executeQuery(String query, Class<T> clazz) {
        session = factory.openSession();
        Transaction transaction = null;
        List<T> results;

        try {
            transaction = session.beginTransaction();
            results = session.createQuery(query, clazz).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //TODO Add processing
            results = Collections.emptyList();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    protected Object executeQuery(String query) {
        session = factory.openSession();
        Transaction transaction = null;
        Object results;

        try {
            transaction = session.beginTransaction();
            results = session.createQuery(query).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //TODO Add processing
            results = Collections.emptyList();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }
}
