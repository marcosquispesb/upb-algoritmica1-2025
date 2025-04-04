package com.bo.upb.algoritmica.list.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Node
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Getter
@Setter
//@ToString
public class Node {

    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public boolean hasNext() {
        return next != null;
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + (next != null ? next.value : null) +
                '}';
    }

    public static void main(String[] args) {
        Node p = new Node(10);
        Node q = new Node(20);
        Node r = new Node(30);

        p.setNext(q);
        q.setNext(r);
        r.setNext(new Node(40));

        System.out.println(p);
        System.out.println(q);
        System.out.println(r);
    }
}
