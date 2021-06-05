package ru.grishchenko.lessonFive;

import ru.grishchenko.lessonFive.dao.StudentDAO;
import ru.grishchenko.lessonFive.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentApp {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        if (studentDAO.getCount() <= 0) {
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                students.add(new Student(null, "StudentName_" + (i + 1), "Mark_" + (i + 1)));
            }
            studentDAO.insertOrUpdate(students);
        }

        Student student = studentDAO.getById(10L);
        System.out.println(student);
        student.setName("Antoon");
        studentDAO.insertOrUpdate(student);
        student = studentDAO.getById(10L);
        System.out.println(student);

    }
}
