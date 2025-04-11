package com.bo.upb.algoritmica.tree.binary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Node
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class Node {
    private int value;

    private Node left;

    private Node right;

    private String id; // solo para el print del docente
    private boolean viewed; // solo para el print del docente

    public Node(int value) {
        this.value = value;
        this.id = UUID.randomUUID().toString(); // agregar esta linea
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasTwoSon() {
        return left != null && right != null;
    }

    public boolean areHisChildren(int value1, int value2) {
        return (left.value == value1 && right.value == value2)
                || (left.value == value2 && right.value == value1);
    }

    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>(2);
        if (left != null)
            children.add(left);
        if (right != null)
            children.add(right);

        return children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + (left != null ? left.getValue() : null) +
                ", right=" + (right != null ? right.getValue() : null) +
                '}';
    }

    public static void main(String[] args) {
        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);

        node10.setRight(node20);
        node20.setLeft(node30);

        System.out.println(node10);
        System.out.println(node20);
        System.out.println(node30);
    }
}
