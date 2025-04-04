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

    public void add(int value) {
        if (first == null) {
            first = new Node(value);
            size = 1;
            return;
        }

        Node aux = first;
        while (aux.hasNext()) {
            aux = aux.getNext();
        }
        aux.setNext(new Node(value));
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
        l.add(10);
        l.add(20);
        l.add(30);
        l.add(40);
        System.out.println(l);

        System.out.println(l.get(5));
    }
}
