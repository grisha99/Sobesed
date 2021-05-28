package ru.grishchenko.lessontwo;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

    private int size;

    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public MyLinkedList(E element) {
        this.size = 1;
        this.first = new Node<E>(element);
        this.first.next = null;
        this.first.prev = null;
        this.last = this.first;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (first == null) {
            last = newNode;
        } else {
            first.prev = newNode;
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node(element);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = this.last;
            this.last.next = newNode;
            this.last = newNode;
        }

        this.size++;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E value = this.first.value;
        this.first = this.first.next;
        if (this.first != null) {
            this.first.prev = null;
        }
        size--;
        return value;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E value = this.last.value;
        this.last = this.last.prev;
        if (this.last != null) {
            this.last.next = null;
        }
        size--;
        return value;
    }

    public boolean remove(E value) {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node<E> currentNode = first;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                size--;
                if (currentNode == first) {
                    removeFirst();
                    return true;
                }
                if (currentNode == last) {
                    removeLast();
                    return true;
                }
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                return true;
            }

            currentNode = currentNode.next;

        }
        return false;
    }

    public E getFirst() {
        if (first == null) {
            return null;
        } else {
            return first.value;
        }
    }

    public E getLast() {
        if (last == null) {
            return null;
        } else {
            return last.value;
        }
    }


    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = this.first;
        while (node != null) {
            sb.append(node.value).append(" - ");
            node = node.next;
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        public Node(E value) {
            this.value = value;
            next = null;
            prev = null;
        }

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
            this.prev = null;
        }
    }

    private class Iter implements Iterator<E> {

        private Node<E> currentNode = new Node<>(null, first);

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public E next() {
            currentNode = currentNode.next;
            return currentNode.value;
        }

        @Override
        public void remove() {
            if (currentNode == first) {
                removeFirst();
//                currentNode = new Node<>(null, first);
                return;
            }
            if (currentNode == last) {
                removeLast();
//                currentNode = new Node<>(null, last);
                return;
            }

            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            size--;

        }
    }

}
