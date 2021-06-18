package ru.grishchenko.model;

import java.util.Objects;

public class Student {

    private Long id;
    private String name;
    private short age;

    public Student() {
    }

    public Student(Long id, String name, short age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Student s = (Student) o;

        return Objects.equals(id, s.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
