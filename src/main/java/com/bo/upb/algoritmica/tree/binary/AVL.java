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
public class AVL implements TBPrint {

    @Getter
    @Setter
    private Node root;

    @Getter
    private int size;

    public AVL() {
        root = null;
        size = 0;
    }

    // insertar BST (version iterativa)
    private void addBST(int value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node node = root;
        while (node != null) {
            if (value < node.getValue()) { // value menor (ir por la izq)
                if (node.getLeft() == null) {
                    node.setLeft(new Node(value));
                    size++;
                    return;
                }
                node = node.getLeft();
            } else  if (value > node.getValue()) { // value mayor (ir por la der)
                if (node.getRight() == null) {
                    node.setRight(new Node(value));
                    size++;
                    return;
                }
                node = node.getRight();
            } else { // value igual (para que no inserte repetidos)
                return;
            }
        }
    }

    public void add(int value) {
        addBST(value);
        balance(root, null);
    }

    public void addAll(int ...values) {
        for (int value : values) {
            add(value);
        }
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

    private Node rotateLeft(Node node) { // der desbalanceado
        System.out.println("rotateLeft: " + node.getValue());
        Node child = node.getRight();

        int dChildLeft = depth(child.getLeft());
        int dChildRight = depth(child.getRight());
        if (dChildLeft > dChildRight) {
            node.setRight(rotateRight(child));
            child = node.getRight();
        }

        Node auxChildLeft = child.getLeft();
        child.setLeft(node);
        node.setRight(auxChildLeft);
        return child;
    }
    private Node rotateRight(Node node) { // izq desbalanceado
        System.out.println("rotateRight: " + node.getValue());
        Node child = node.getLeft();

        int dChildLeft = depth(child.getLeft());
        int dChildRight = depth(child.getRight());
        if (dChildRight > dChildLeft) {
            node.setLeft(rotateLeft(child));
            child = node.getLeft();
        }

        Node auxChildRight = child.getRight();
        child.setRight(node);
        node.setLeft(auxChildRight);
        return child;
    }

//    private int balance(Node parent, Node node) {
//        if (node == null)
//            return 0;
//        if (node.isLeaf())
//            return 1;
//
//        int izq = balance(node, node.getLeft());
//        int der = balance(node, node.getRight());
//        int fe = der - izq;
//        //System.out.println("node: " + node.getValue() + "  fe: " + fe);
//        if (fe == 2) {
//            // actualizar hijo que corresponda (izq o der)
//            // si se esta rotando el root, actualizar el puntero root
//            parent.setRight(rotateLeft(node));
//            System.out.println("desbalance der: " + node.getValue() + "  fe: " + fe);
//            // retornar la nueva altura max
//        } else if (fe == -2) {
//            // actualizar hijo que corresponda (izq o der)
//            // si se esta rotando el root, actualizar el puntero root
//            rotateRight(node);
//            System.out.println("desbalance izq: " + node.getValue() + "  fe: " + fe);
//            // retornar la nueva altura max
//        }
//
//        return Math.max(izq, der) + 1;
//    }

    private int balance(Node node, Node parent) {
        if (node == null)
            return 0;
        if (node.isLeaf())
            return 1;

        int izq = balance(node.getLeft(), node);
        int der = balance(node.getRight(), node);
        int fe = der - izq;
        if (fe == -2) { // desbalance por la izq
            System.out.println("desbalance por la izq node: " + node.getValue());
            if (parent == null) {
                root = rotateRight(node);
            } else if (parent.getLeft() != null && parent.getLeft().getValue() == node.getValue()) {
                parent.setLeft(rotateRight(node));
            } else {
                parent.setRight(rotateRight(node));
            }
            return Math.max(balance(node.getLeft(), node), balance(node.getRight(), node))  + 1;
        } else if (fe == 2) { // desbalance por la der
            System.out.println("desbalance por la der node: " + node.getValue());
            if (parent == null) {
                root = rotateLeft(node);
            } else if (parent.getLeft() != null && parent.getLeft().getValue() == node.getValue()) {
                parent.setLeft(rotateLeft(node));
            } else {
                parent.setRight(rotateLeft(node));
            }
            return Math.max(balance(node.getLeft(), node), balance(node.getRight(), node))  + 1;
        }
        return Math.max(izq, der) + 1;
    }

    public static void main(String[] args) {
        AVL t;
        //t.addAll(19, 7, 10, 16, 20, 8, 9, 13, 40, 4, 9);

        t = new AVL();
        // 24 elementos
        //17, 38, 90, 14, 89, 11, 93, 29, 98, 34, 79, 32, 41, 71, 87, 91, 21, 80, 31, 52, 97, 62, 13, 74
        //t.addAll(17, 38, 90, 14, 89, 11, 93, 29, 98, 34, 79, 32, 41, 71, 87, 91, 21, 80, 31, 52, 97, 62, 13, 74); // no imprime bien el 4

        // 10 elementos
        //33, 25, 28, 40, 66, 18, 15, 100, 75, 50
        t.addAll(33, 25, 28, 40, 66, 18, 15, 100, 75, 50); // ok
        //rdi root, ri, rd, rid, rid

        // 11 elementos
        //60, 50, 40, 70, 65, 55, 30, 35, 57, 58, 20    68, 25, 22
        //t.addAll(60, 50, 40, 70, 65, 55, 30, 35, 57, 58, 20, 68, 25, 22); // ok

//        for (int i = 0; i < 5; i++) {
//            t.add(i + 1);
//        }
//        t.addAll(33, 25, 28, 40, 66, 18, 15, 100, 75, 50); //33, 25, 28, 40, 66, 18, 15, 100, 75, 50);
//
//        t.addAll(16, 10, 4, 2);
//        t.rotateRight(t.root);

//        t.addAll(16, 10);
//        t.addAll(16, 10, 12);
//        t.addAll(16, 10, 4, 2, 12);
//        t.addAll(16, 10, 4, 12, 14);
//
//        t.addAll(16, 24, 30);
//        t.addAll(16, 24);
//        t.addAll(16, 24, 20);
//        t.addAll(16, 24, 20, 30, 40);
//        t.addAll(16, 24, 20, 30, 18);

        TBPrintUtil.print(t);
        System.out.println();
        //Node node = t.rotateRight(t.root);
        //Node node = t.rotateLeft(t.root);
        //System.out.println(node);

//        System.out.println();
//        t = new AVL();
//        for (int i = 0; i < 20; i++) {
//            t.add(i + 1);
//        }
        //t.addAll(10, 2, 1, 0, 6, 4, 3, 8, 20, 30);
        //TBPrintUtil.print(t);
        //t.analyzeFE(t.root, null);
//        TBPrintUtil.print(t);

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
//        //System.out.println("size2: " + t.getSize2(t.root));
////        System.out.println("sum: " + t.sum(t.root));
////        System.out.println("max: " + t.max(t.root));
//        System.out.println("depth: " + t.depth(t.root));
        //System.out.println("fin");

//        t.bfs();
//        t = new BST();
//        for (int i = 1; i <= 10; i++) {
//            t.addByLevel(i * 10);
//        }
//        TBPrintUtil.print(t);

//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(10);
//        queue.add(20);
//        queue.add(30);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue);
    }
}
