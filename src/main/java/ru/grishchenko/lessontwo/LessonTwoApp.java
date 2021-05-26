package ru.grishchenko.lessontwo;

import java.util.Iterator;

public class LessonTwoApp {

    public static void main(String[] args) {

        MyLinkedList<String> myList = new MyLinkedList<>();

        myList.addFirst("First");
        myList.addFirst("Second");
        myList.addFirst("Three");
        System.out.println("Size: " + myList.size());

        for (String e : myList) {
            System.out.println("ForEach: " + e);
        }

        Iterator<String> iter = myList.iterator();
        String value;
        while (iter.hasNext()) {
            value = iter.next();
            if (value.equals("Second")) {
                iter.remove();
            }
        }
        System.out.println("Size: " + myList.size());
        System.out.println(myList.toString());

        MyArrayList<Integer> mal = new MyArrayList<>(3);

        mal.add(10);
        System.out.println(mal);
        mal.add(20);
        System.out.println(mal);
        mal.add(30);
        System.out.println(mal);
        mal.add(40);
        System.out.println(mal);
        mal.remove((Integer)10);
        System.out.println(mal);
    }


}
