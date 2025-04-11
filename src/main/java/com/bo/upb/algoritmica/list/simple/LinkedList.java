package com.bo.upb.algoritmica.list.simple;

import lombok.Getter;

/**
 * LinkedList
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class LinkedList {

    private Node first;

    @Getter
    private int size;

    public LinkedList() {
        first = null;
        size = 0;
    }

    public void add(int ...values) {
        for (int value : values) {
            add(value);
        }
    }
    public void add(int value) {
        if (first == null) {
            first = new Node(value);
            size = 1;
            return;
        }

        Node node = first;
        while (node.hasNext()) {
            node = node.getNext();
        }
        node.setNext(new Node(value));
        size++;
    }

    public int get(int index) throws Exception {
        if (index < 0 || index >= size)
            throw new Exception("posicion no valida");

        int i = 0;
        Node node = first;
        while (node != null) {
            if (i == index)
                return node.getValue();
            i++;
            node = node.getNext();
        }
        return -1;
    }

    private int sum() {
        int suma = 0;
        Node node = first;
        while (node != null) {
            suma = suma + node.getValue();
            node = node.getNext();
        }
        return suma;
    }

    private int getSize2() {
        int count = 0;
        Node node = first;
        while (node != null) {
            count++;
            node = node.getNext();
        }
        return count;
    }

    private Integer max() {
        if (first == null) // Por si la lista esta vacia
            return null;

        int max = Integer.MIN_VALUE;
        Node node = first;
        while (node != null) {
            if (node.getValue() > max)
                max = node.getValue();
            node = node.getNext();
        }

        return max;
    }

    public void remove(int value) {
        if (first == null)
            return;

        Node node = first;
        Node prev = null;
        while (node != null) {
            if (node.getValue() == value) {
                if (prev != null) {
                    prev.setNext(node.getNext());
                    node.setNext(null);
                    node = prev.getNext();
                } else {
                    first = node.getNext();
                    node.setNext(null);
                    node = first;
                }
                size--;
                continue;
            }
            prev = node;
            node = node.getNext();
        }
    }

    @Override
    public String toString() {
        String s = "";
        Node node = first;
        while (node != null) {
            s += node.getValue() + (node.hasNext() ? " -> " : "");
            node = node.getNext();
        }

        return s;
    }

    public static void main(String[] args) throws Exception {
        LinkedList l = new LinkedList();
//        l.add(10);
//        l.add(20);
//        l.add(30);
//        l.add(40);
        l.add(4, 4, 5, 4, 6);
        System.out.println(l);
        l.remove(4);
        System.out.println(l);

//        System.out.println(l.get(2));
//        System.out.println(l.sum());
//        System.out.println(l.getSize2());
//        System.out.println(l.max());
    }
}
