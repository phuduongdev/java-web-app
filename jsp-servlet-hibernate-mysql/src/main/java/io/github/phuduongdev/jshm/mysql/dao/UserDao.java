/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.jshm.dao;

import io.github.phuduongdev.jshm.model.User;
import io.github.phuduongdev.jsp.servlet.hibernate.mysql.util.HibernateManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author phudev
 */
public class UserDao {

    public void createUser(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            doRollback(transaction);
            e.printStackTrace();
        }
    }

    public User readUser(int id) {
        Transaction transaction = null;
        User user = null;
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            user = session.get(User.class, id);

            transaction.commit();
        } catch (Exception e) {
            doRollback(transaction);
            e.printStackTrace();
        }
        return user;
    }

    public List<User> readAllUser() {
        Transaction transaction = null;
        List<User> userList = null;
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            userList = session.createQuery("from user").getResultList();

            transaction.commit();
        } catch (Exception e) {
            doRollback(transaction);
            e.printStackTrace();
        }
        return userList;
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            doRollback(transaction);
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        Transaction transaction = null;
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("User has been deleted.");
            }

            transaction.commit();
        } catch (Exception e) {
            doRollback(transaction);
            e.printStackTrace();
        }
    }

    private void doRollback(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }

}
