package org.jugru.treetraversal;

import lombok.Getter;

@Getter
class Node {
    private Node left;
    private Node right;
    private int value;
    private int count;

    public Node(int value) {
        this.value = value;
        this.count = 1;
    }

    public void incrementCount(){
        count++;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
