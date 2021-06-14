package ru.grishchenko.lessonFive.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBFactory {

    private SessionFactory factory;

    private static DBFactory dbFactory;

    private DBFactory() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public static DBFactory getInstance() {
        if (dbFactory == null) {
            dbFactory = new DBFactory();
        }

        return dbFactory;
    }
}
