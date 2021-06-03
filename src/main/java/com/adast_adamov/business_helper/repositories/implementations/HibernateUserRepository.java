package com.adast_adamov.business_helper.repositories.implementations;

import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.repositories.interfaces.UserRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class HibernateUserRepository implements UserRepository {

    private static SessionFactory factory;

    HibernateUserRepository() {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> users = Collections.emptyList();

        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM User WHERE LOGIN = '" + login + "'", User.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users.isEmpty() ? null : users.get(0);
    }

}
