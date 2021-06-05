package ru.grishchenko.lessontwo;

import java.util.Iterator;

public class LessonTwoApp {

    public static void main(String[] args) {

        MyLinkedList<Integer> myList = new MyLinkedList<>();

        for (int i = 0; i < 5; i++) {
            myList.addFirst((i + 1) * 10);
        }

        System.out.println(myList.toString());
        myList.reverse();
        System.out.println(myList.toString());

    }


}
