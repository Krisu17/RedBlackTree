
package com.andersok.redblacktreev2;


public class Node <K extends Comparable<K>, V> {
    
    private Node up;
    private Node left;
    private Node right;
    private char color;
    private K key;
    private V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    
    
    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    
    
    
}
