package ru.grishchenko.lessonthree;

public class LessonThreeApp {


    public static void main(String[] args) {

        PingPongGame ppg = new PingPongGame();
        ppg.startGame(6);

        ConcurrentCounter cc = new ConcurrentCounter();

        System.out.println(cc.getCounter());
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                cc.increment();
            }).start();
            System.out.println("Поток " + Thread.currentThread().getId() + " чтение значение (ПИ) - " + cc.getCounter());
            new Thread(() -> {
                cc.decrement();
            }).start();
            System.out.println("Поток " + Thread.currentThread().getId() + " чтение значение (ПД) - " + cc.getCounter());
        }
    }
}
