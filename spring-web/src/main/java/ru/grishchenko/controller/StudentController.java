package ru.grishchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.grishchenko.model.Student;
import ru.grishchenko.repositories.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", this.studentRepository.findAll());
        return "students";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "editstudent";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id);
        model.addAttribute("student", student);
        return "editstudent";
    }

    @PostMapping
    public String indertOrUpdate(@ModelAttribute(name = "student") Student student) {
        studentRepository.insertOrUpdate(student);
        return "redirect:students";
    }

}
