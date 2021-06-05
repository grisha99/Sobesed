package ru.grishchenko.lessonthree;

import java.util.ConcurrentModificationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounter {

    private volatile long counter;

    public final Lock lock;

    public ConcurrentCounter() {
        this.lock = new ReentrantLock();
        this.counter = 0;

    }

    public long getCounter() {
        this.lock.lock();
        try {
            return this.counter;
        }  finally {
            this.lock.unlock();
        }
    }

    public void increment() {
        this.lock.lock();
        try {
            System.out.println("Поток " + Thread.currentThread().getId() + " готов ИНкременту, значение " + getCounter());
            this.counter = this.counter + 1;
            System.out.println("Поток " + Thread.currentThread().getId() + " ИНКРЕМЕНТ, значение " + getCounter());
        }  finally {
            this.lock.unlock();
        }
    }

    public void decrement() {
        this.lock.lock();
        try {
            System.out.println("Поток " + Thread.currentThread().getId() + " готов ДЕкременту, значение " + getCounter());
            this.counter = this.counter - 1;;
            System.out.println("Поток " + Thread.currentThread().getId() + " ДЕКРИМЕНТ, значение " + getCounter());
        }  finally {
            this.lock.unlock();
        }
    }
}
