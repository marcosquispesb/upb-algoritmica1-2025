package com.bo.upb.algoritmica.tree.binary;

import com.bo.upb.algoritmica.tree.print.TBPrint;
import com.bo.upb.algoritmica.tree.print.TBPrintUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

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

    public BinaryTree(int valueRoot) {
        root = new Node(valueRoot);
        size = 1;
    }

    public void putRoot(int value) {
        root = new Node(value);
        size = 1;
    }

    public void putChildren(int valueParent, Integer valueLeft, Integer valueRight) {
        if (valueLeft != null)
            putLeft(valueParent, valueLeft);
        if (valueRight != null)
            putRight(valueParent, valueRight);
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

    public int getSizeRecursivo(Node node) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return 1;

        int izq = getSizeRecursivo(node.getLeft());
        int der = getSizeRecursivo(node.getRight());
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

    // debe retornar la cantidad de nodos hojas que hay en el arbol
    public int countLeaves(Node node) {
        return 0;
    }

    // retorna true si value1 y value2 son hijos del mismo padre
    public boolean areSiblings(int value1, int value2, Node node) {
        if (node == null || node.isLeaf())
            return false;

        if (node.hasTwoSon()) {
            if (node.areHisChildren(value1, value2))
                return true;
        }

        return areSiblings(value1, value2, node.getLeft()) || areSiblings(value1, value2, node.getRight());
    }

    public void print(Node node) {
        if (node == null)
            return;

        System.out.println("node: " + node.getValue());
        print(node.getLeft());
        print(node.getRight());
    }

    public int depth(Node node) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return 1;

        int izq = depth(node.getLeft()); // 1
        int der = depth(node.getRight()); // 0
        return Math.max(izq, der) + 1;
    }

    public boolean isFull(Node node) {
        if (node == null)
            return false;
        if (node.isLeaf())
            return true;
        return isFull(node.getLeft()) && isFull(node.getRight());
    }

    public void bfs() {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.getValue() + " ");
            if (!node.isLeaf())
                queue.addAll(node.getChildren());
        }
    }

    public boolean isPerfect() {
        return (Math.pow(2, depth(root)) - 1) == size;
    }

    public void addByLevel(Integer value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }

        int level = depth(root) + (isPerfect() ? 0 : - 1);
        addByLevel(root, null, value, level);
    }

    public boolean addByLevel(Node node, Node parent, Integer value, int level) {
        if (level == 0) {
            if (node == null) {
                if (parent.getLeft() == null) {
                    parent.setLeft(new Node(value));
                } else { // right es null
                    parent.setRight(new Node(value));
                }
                size++;
                return true;
            }
            return false;
        }

        boolean izq = addByLevel(node.getLeft(), node, value, level - 1);
        if (izq)
            return izq;
        return addByLevel(node.getRight(), node, value, level - 1);
    }

    public static void main(String[] args) {
        BinaryTree t = new BinaryTree(10);
//        t.putChildren(10, 20, 30);
//        t.putChildren(20, null, 15);
//        t.putChildren(30, 25, null);
//        t.putChildren(25, null, 28);

//        t.putChildren(10, 20, 30);
//        t.putChildren(20, 40, 50);

//        t.putChildren(10, 20, 30);
//        t.putChildren(20, 40, 50);
//        t.putChildren(30, 60, 70);
        //t.putChildren(60, 80, 90);
//
//        //t.print(t.root);
//        TBPrintUtil.print(t);
//        System.out.println();
//        //System.out.println("size2: " + t.getSizeRecursivo(t.root));
////        System.out.println("sum: " + t.sum(t.root));
////        System.out.println("max: " + t.max(t.root));
//        System.out.println("depth: " + t.depth(t.root));
        //System.out.println("fin");

//        t.bfs();
        t = new BinaryTree();
        for (int i = 1; i <= 10; i++) {
            t.addByLevel(i * 10);
        }
        TBPrintUtil.print(t);

//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(10);
//        queue.add(20);
//        queue.add(30);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue);
    }
}
