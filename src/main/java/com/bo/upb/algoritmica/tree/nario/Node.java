package com.bo.upb.algoritmica.tree.nario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private List<Node> children;

    public Node(int value) {
        this.value = value;
        children = new ArrayList<>(0);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {
        String childrenStr = "";
        for (Node child : children) {
            childrenStr += child.getValue() + " ";
        }
        return "Node{" +
                "value=" + value +
                ", children=[" + childrenStr + "]" +
                '}';
    }
}
