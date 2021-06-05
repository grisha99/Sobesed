package ru.grishchenko.lessonFive.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "mark", length = 512, nullable = true)
    private String mark;

    public Student() {
    }

    public Student(Long id, String name, String mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nStudent: ")
                .append("[")
                .append("id=").append(id).append(", ")
                .append("name=").append(name).append(", ")
                .append("mark=").append(mark)
                .append("]");
        return sb.toString();
    }
}
