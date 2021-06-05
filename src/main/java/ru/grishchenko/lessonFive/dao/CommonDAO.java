package ru.grishchenko.lessonFive.dao;

import org.hibernate.Session;
import ru.grishchenko.lessonFive.config.DBFactory;
import ru.grishchenko.lessonFive.entity.Student;

import java.util.List;

public class CommonDAO {

    private Session session;

    public <T> Long getCount(final Class<T> clazz) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        Long result = session.createQuery("select count(*) from clazz", Long.class).getSingleResult();
        session.getTransaction().commit();
        return result;
    }

    public <T> List<T> findAll(final Class<T> clazz) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        List<T> elements = session.createQuery("from T", clazz).getResultList();
        session.getTransaction().commit();
        return elements;
    }

    public <T> T getById(final Class<T> clazz, Long id) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        T result = session.find(clazz, id);
        session.getTransaction().commit();
        return result;
    }

    public <T> void insert(T object) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(object);
        session.getTransaction().commit();
    }

    public <T> T update(T object) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        T result = (T) session.merge(object);
        session.getTransaction().commit();
        return result;
    }

    public <T> void delete(T object) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(object);
        session.getTransaction().commit();
    }
}
