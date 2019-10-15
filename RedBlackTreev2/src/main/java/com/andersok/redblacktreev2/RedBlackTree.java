package com.andersok.redblacktreev2;

import java.util.ArrayList;

public class RedBlackTree implements MapInterface {

    private ArrayList<Node> nodes;
    private Node head;

    public RedBlackTree() {
        nodes = new ArrayList<>();
        head = null;
    }

    private void leftRotation(Node x) {

    }

    private void rightRotation(Node x) {

    }

    private void rebuildTree(Node x, Node grandfather) {
        if (grandfather != null) {
            if (grandfather.getLeft() == x.getUp()) { //ojciec jest lewym synem swojego ojca
                if (grandfather.getRight().getColor() == 'r') {
                    grandfather.getLeft().setColor('b');
                    grandfather.getRight().setColor('b');
                    grandfather.setColor('r');
                } else if (grandfather.getRight().getColor() == 'b' 
                        && grandfather.getLeft().getRight() == x) {
                    leftRotation(x.getUp());
                } else {
                    x.getUp().setColor('r');
                    grandfather.setColor('r');
                    rightRotation(grandfather);
                }
            }
        }
    }

    @Override
    public void setValue(Comparable key, Object value) {
        if (head == null) {
            head = new Node(key, value);
            head.setUp(null);
            head.setLeft(null);
            head.setRight(null);
            head.setColor('b');
        } else {
            Node newFather = head;
            Node grandfather = null;
            boolean flag = true;
            while (flag) {
                if (key.compareTo(newFather.getKey()) < 0) {
                    if (newFather.getLeft() != null) {
                        grandfather = newFather;
                        newFather = newFather.getLeft();
                    } else {
                        Node n = new Node(key, value);
                        n.setUp(newFather);
                        newFather.setLeft(n);
                        n.setLeft(null);
                        n.setRight(null);
                        n.setColor('r');
                        flag = false;
                        rebuildTree(n, grandfather);
                    }
                } else {
                    if (newFather.getRight() != null) {
                        newFather = newFather.getRight();
                    } else {
                        Node n = new Node(key, value);
                        n.setUp(newFather);
                        newFather.setRight(n);
                        n.setLeft(null);
                        n.setRight(null);
                        n.setColor('r');
                        flag = false;
                    }
                }
            }

        }
    }

    @Override
    public Object getValue(Comparable key) {
        Object value = null;
        return value;
    }

}
