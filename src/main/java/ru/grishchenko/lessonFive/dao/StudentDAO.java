package ru.grishchenko.lessonFive.dao;

import org.hibernate.Session;
import ru.grishchenko.lessonFive.config.DBFactory;
import ru.grishchenko.lessonFive.entity.Student;

import java.util.List;

public class StudentDAO {

    private Session session;

    public Long getCount() {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        Long result = session.createQuery("select count(*) from Student", Long.class).getSingleResult();
        session.getTransaction().commit();
        return result;
    }

    public List<Student> findAll() {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        List<Student> result = session.createQuery("from Student", Student.class).getResultList();
        session.getTransaction().commit();
        return result;

    }

    public Student getById(Long studentId) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        Student result = session.find(Student.class, studentId);
        session.getTransaction().commit();
        return result;
    }

    public void insertOrUpdate(Student student) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        if (student.getId() == null) {
            session.persist(student);
        } else {
            session.merge(student);
        }
        session.getTransaction().commit();
    }

    public void insertOrUpdate(List<Student> students) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        for (Student s : students) {
            if (s.getId() == null) {
                session.persist(s);
            } else {
                session.merge(s);
            }
        }
        session.getTransaction().commit();
    }

    public void delete(Student student) {
        session = DBFactory.getInstance().getFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(student);
        session.getTransaction().commit();
    }

    public void delete(Long id) {
        Student student = this.getById(id);
        if (student != null) {
            this.delete(student);
        }
    }
}
