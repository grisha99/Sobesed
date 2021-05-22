package ru.grishchenko.lessonone;

import ru.grishchenko.lessonone.builder.Person;
import ru.grishchenko.lessonone.polymorph.Circle;
import ru.grishchenko.lessonone.polymorph.Shape;
import ru.grishchenko.lessonone.polymorph.Square;
import ru.grishchenko.lessonone.polymorph.Triangle;

import java.util.Arrays;
import java.util.List;

public class LessonOneApp {

    public static void main(String[] args) {

        Person person = Person.getBuilder()
                .setFirstName("Ilya")
                .setLastName("Grishchenko")
                .setMiddleName("Evegenievich")
                .setAddress("Moscow")
                .setCountry("Russia")
                .setPhone("89261234567")
                .setAge(40)
                .setGender("M")
                .build();

        System.out.println(person);

        Shape shape = new Shape();

        Shape square = new Square(7);
        Shape triangle = new Triangle(10, 5, 30);
        Shape circle = new Circle(6);

        List<Shape> shapeList = Arrays.asList(shape, square, triangle, circle);

        for (Shape s : shapeList) {
            System.out.println(s.getArea());    // полиморфный вызов метода getArea()
        }

    }
}
