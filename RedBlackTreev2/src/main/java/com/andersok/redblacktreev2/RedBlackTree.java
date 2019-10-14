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
    
    private void rebuildTree() {
        
    }

    @Override
    public void setValue(Comparable key, Object value) {
        if(head == null) {
            head = new Node(key, value);
            head.setUp(null);
            head.setLeft(null);
            head.setRight(null);
            head.setColor('b');
        }
        else {
            Node newFather = head;
            
        }
    }

    @Override
    public Object getValue(Comparable key) {
        Object value = null;
        return value;
    }

}
