package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.grishchenko.model.Student;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    private final Map<Long, Student> students = new HashMap<>();
    private final AtomicLong studentIdCounter = new AtomicLong(0);
    private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    @PostConstruct
    private void initRepository() {

        long id;
        for (int i = 0; i < 10; i++) {
            id = studentIdCounter.incrementAndGet();
            students.put(id, new Student(id, "User-" + (i + 1), (short) ((i + 1) * 2)));
        }
    }

    public List<Student> findAll() {
        return List.copyOf(students.values()); // копия листа пользователей. для запрета изменений в оригинале
    }

    public Student findById(Long userId) {
        if (students.containsKey(userId)) {
            return students.get(userId);
        }
        throw new IllegalArgumentException("User not found");
    }

    public void insertOrUpdate(Student newStudent) {
        if (newStudent.getId() == null) {
            long id = studentIdCounter.incrementAndGet();
            newStudent.setId(id);
        }

        students.put(newStudent.getId(), newStudent);
    }

    public void deleteById(Long userId) {
        students.remove(userId);
        logger.info("Delete student ID: " + userId);
    }
}
