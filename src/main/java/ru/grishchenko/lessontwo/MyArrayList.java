package ru.grishchenko.lessontwo;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList<T> {

    private final static int INIT_CAPACITY = 15;
    private final static float LOAD_FACTOR = 0.75f;

    private int capacity;
    private int size;

    private Object[] items;

    public MyArrayList() {
        items = new Object[INIT_CAPACITY];
        this.capacity = INIT_CAPACITY;
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        items = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) items[index];
    }

    public T set(int index, T item) {
        checkIndex(index);
        T oldItem = (T)items[index];
        items[index] = item;
        return oldItem;
    }

    public void add(T item) {
        recapacity();
        items[size] = item;
        size++;
    }

    public T remove(int index) {
        checkIndex(index);
        T item = (T)items[index];
        System.arraycopy(items, index+1, items, index, size-index);
        size--;
        return item;
    }

    public boolean remove(T item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Size: " + size + " but Index: " + index);
        }
    }

    private void recapacity() {
        if (size / (float)capacity >= LOAD_FACTOR ) {
            capacity = capacity * 2;
            Object[] oldItems = Arrays.copyOf(items, size);;
            items = new Object[capacity];
            System.arraycopy(oldItems, 0, items, 0, size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ").append(size).append("\n");
        sb.append("Capacity: ").append(capacity).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append("[").append(items[i]). append("]");
        }
        return sb.toString();
    }
}
