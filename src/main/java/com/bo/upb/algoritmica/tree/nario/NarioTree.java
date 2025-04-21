package com.bo.upb.algoritmica.tree.nario;

import lombok.Getter;
import lombok.Setter;

/**
 * BinaryTree
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class NarioTree {

    @Getter
    @Setter
    private Node root;

    @Getter
    private int size;

    public NarioTree() {
        root = null;
        size = 0;
    }
    public NarioTree(int value) {
        root = new Node(value);
        size = 1;
    }

    public void putChildren(int valueParent, Integer ...children) {
        Node parent = getNode(valueParent, root);
        if (parent == null)
            return;
        for (Integer valueChild : children) {
            parent.getChildren().add(new Node(valueChild));
            size++;
        }
    }

    public Node getNode(int valueToSearch, Node node) {
        if (node == null)
            return null;
        if (node.getValue() == valueToSearch)
            return node;

        for (Node child : node.getChildren()) {
            Node aux = getNode(valueToSearch, child);
            if (aux != null)
                return aux;
        }
        return null;
    }

    // 175
    public int sum(Node node) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return node.getValue();

        int result = node.getValue();
        for (Node child : node.getChildren()) {
            result += sum(child);
        }
        return result;
    }

    // 39
    public int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        if (node.isLeaf())
            return node.getValue();

        int maxResult = node.getValue();
        for (Node child : node.getChildren()) {
            maxResult = Math.max(maxResult, max(child));
        }
        return maxResult;
    }

    public void print(Node node) {
        if (node == null)
            return;

        System.out.println(node);
        for (Node child : node.getChildren()) {
            print(child);
        }
    }

    // (3 – 2 * 1) + (4 ^ 0)
    public int calculate(String formula) {
        // vaciar el arbol
        root = null;
        size = 0;
        // recorrer formula e insertar los nodos correspondientes
        // recorrer el arbol y retornar el resultado correspondiente
        return 0;
    }

    public static void main(String[] args) {
        NarioTree t;

        t = new NarioTree(1);
        t.putChildren(1, 10, 20, 30);
        t.putChildren(10, 11, 14, 17);
        t.putChildren(30, 33, 39);
        t.print(t.root);
        System.out.println();
        System.out.println("sum: " + t.sum(t.root));
        System.out.println("max: " + t.max(t.root));
    }
}
