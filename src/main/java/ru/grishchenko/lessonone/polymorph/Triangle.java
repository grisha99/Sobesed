package ru.grishchenko.lessonone.polymorph;

public class Triangle extends Shape{

    private int hypotenuseLength;
    private int legLength;
    private int angle;

    public Triangle(int hyp, int leg, int ang) {
        this.hypotenuseLength = hyp;
        this.legLength = leg;
        this.angle = ang;
    }

    @Override
    public double getArea() {
        return 0.5 * legLength * hypotenuseLength * Math.sin(angle);
    }
}
