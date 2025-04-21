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
public class BST implements TBPrint {

    @Getter
    @Setter
    private Node root;

    @Getter
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public void addAll(int ...values) {
        for (int value : values) {
            add(value);
        }
    }

    // insertar BST (version iterativa)
    public void add(int value) {
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

    // insertar BST (version recursiva)
//    public void add(int value) {
//        root = addRec(root, value);
//    }
//
//    public Node addRec(Node node, int value) {
//        if (node == null) {
//            size++;
//            return new Node(value);
//        }
//        if (value < node.getValue()) {
//            node.setLeft(addRec(node.getLeft(), value));
//        } else if (value > node.getValue()) {
//            node.setRight(addRec(node.getRight(), value));
//        }
//        return node;
//    }

    public static void main(String[] args) {
        BST t;

        //t = new BST();
        //t.addAll(19, 7, 10, 16, 20, 8, 9, 13, 40, 4, 9);
//        TBPrintUtil.print(t);

        t = new BST();
        t.addAll(19, 6, 13, 16, 30, 8, 10, 15, 40, 4);//, 7, 18, 9, 12);
        TBPrintUtil.print(t);

//        t = new BST();
//        for (int i = 0; i < 20; i++) {
//            t.add(i + 1);
//        }
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
