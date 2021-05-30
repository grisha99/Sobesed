package ru.grishchenko.lessonthree;

public class PingPongGame {

    Object monitor;
    boolean isPing;
    int counter;

    public PingPongGame() {
        this.monitor = new Object();
        this.isPing = true;
    }

    public void startGame(int step) {
        this.counter = step;
        new Thread(() -> {
            doPing();
        }, "pingThread").start();
        new Thread(() -> {
            doPong();
        }, "pongThread").start();
    }

    private void doPing() {
        synchronized (monitor) {
            for (int i = 0; i < this.counter; i++) {
                while (!isPing) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": ping");
                isPing = !isPing;
                monitor.notifyAll();
            }
        }
    }

    private void doPong() {
        synchronized (monitor) {
            for (int i = 0; i < this.counter; i++) {
                while (isPing) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": pong");
                isPing = !isPing;
                monitor.notifyAll();
            }
        }
    }
}
