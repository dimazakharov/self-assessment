package org.jugru.treetraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
    private Node parent;

    public void add(int value) {
        if (parent != null) {
            add(value, parent);
        } else {
            parent = new Node(value);
        }
    }

    public List<Integer> getAll() {

        List<Integer> answer = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        addToQueueIfNotNull(queue, parent);

        while (queueHasElements(queue)) {
            Node currentNode = queue.remove();
            addValueToAnswer(answer, currentNode);
            addToQueueIfNotNull(queue, currentNode.getLeft());
            addToQueueIfNotNull(queue, currentNode.getRight());
        }
        return answer;
    }

    private void addToQueueIfNotNull(Queue<Node> queue, Node node) {
        if (node != null) {
            queue.add(node);
        }
    }

    private boolean queueHasElements(Queue<Node> queue) {
        return queue.size() > 0;
    }

    private void addValueToAnswer(List<Integer> answer, Node currentNode) {
        for (int i = 0; i < currentNode.getCount(); i++) {
            answer.add(currentNode.getValue());
        }
    }

    private void add(int value, Node node) {
        if (node.getValue() == value) {
            node.incrementCount();
        } else if (value < node.getValue()) {
            addToLeft(value, node);
        } else {
            addToRight(value, node);
        }
    }

    private void addToRight(int value, Node node) {
        if (node.getRight() == null)
            node.setRight(new Node(value));
        else
            add(value, node.getRight());
    }

    private void addToLeft(int value, Node node) {
        if (node.getLeft() == null)
            node.setLeft(new Node(value));
        else
            add(value, node.getLeft());
    }
}
