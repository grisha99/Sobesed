package ru.grishchenko.lessonone.polymorph;

public class Square extends Shape{

    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * 2;
    }
}
