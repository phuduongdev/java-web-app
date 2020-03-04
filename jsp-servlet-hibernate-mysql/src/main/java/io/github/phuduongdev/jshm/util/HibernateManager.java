/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.jshm.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import io.github.phuduongdev.jshm.model.User;

/**
 *
 * @author phudev
 */
public class HibernateManager {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
//                Configuration configuration = new Configuration();
                Configuration configuration = new Configuration();

                Properties options = new Properties();
                options.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                options.put(Environment.URL, "jdbc:mysql://localhost:3306/java_hibernate?useSSL=false");
                options.put(Environment.USER, "root");
                options.put(Environment.PASS, "1");
                options.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                options.put(Environment.SHOW_SQL, "true");
                options.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                options.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(options);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                System.out.println("Hibernate Java Config serviceRegistry created");

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
