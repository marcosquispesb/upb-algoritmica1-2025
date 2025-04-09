package com.bo.upb.algoritmica.tree.binary;

import com.bo.upb.algoritmica.tree.print.TBPrint;
import com.bo.upb.algoritmica.tree.print.TBPrintUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * BinaryTree
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class BinaryTree implements TBPrint {

    @Getter
    @Setter
    private Node root;

    @Getter
    private int size;

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public void putRoot(int value) {
        root = new Node(value);
        size = 1;
    }

    public void putLeft(int valueParent, int value) {
        Node parent = getNode(valueParent, root);
        if (parent != null) {
            parent.setLeft(new Node(value));
            size++;
        }
    }

    public void putRight(int valueParent, int value) {
        Node parent = getNode(valueParent, root);
        if (parent != null) {
            parent.setRight(new Node(value));
            size++;
        }
    }

    public Node getNode(int valueToSearch, Node node) {
        if (node == null)
            return null;
        if (node.getValue() == valueToSearch)
            return node;

        Node izq = getNode(valueToSearch, node.getLeft());
        if (izq != null)
            return izq;

        return getNode(valueToSearch, node.getRight());
    }

    public int getSize2(Node node) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return 1;

        int izq = getSize2(node.getLeft());
        int der = getSize2(node.getRight());
        return izq + der + 1;
    }

    public int sum(Node node) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return node.getValue();

        return sum(node.getLeft()) + sum(node.getRight()) + node.getValue();
    }

    public int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        if (node.isLeaf())
            return node.getValue();

        int maximo = Math.max(max(node.getLeft()), max(node.getRight()));
        return Math.max(maximo, node.getValue());
    }

    public void print(Node node) {
        if (node == null)
            return;

        System.out.println("node: " + node.getValue());
        print(node.getLeft());
        print(node.getRight());
    }

    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();
        t.putRoot(100);
        t.putLeft(100, 20);
        t.putRight(100, 30);
        t.putRight(20, 15);
        t.putLeft(30, 25);

        //t.print(t.root);
        TBPrintUtil.print(t);
        System.out.println();
        //System.out.println("size2: " + t.getSize2(t.root));
        System.out.println("sum: " + t.sum(t.root));
        System.out.println("max: " + t.max(t.root));
        //System.out.println("fin");
    }
}
