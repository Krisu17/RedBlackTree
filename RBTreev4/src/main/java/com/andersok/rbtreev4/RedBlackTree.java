package com.andersok.rbtreev4;

public class RedBlackTree<K extends Comparable<K>, V> implements MapInterface<K, V> {

    private Node head;
    private Node nil;

    public RedBlackTree() {
        head = null;
        nil = new Node();
        nil.setColor('b');
    }

    private void leftRotation(Node x) {
        Node tmp = x.getRight().getLeft();
        if (x.getUp().getLeft() == x) {
            x.getRight().setUp(x.getUp());
            x.getUp().setLeft(x.getRight());
            x.getRight().setLeft(x);
            x.setUp(x.getRight());
            x.setRight(tmp);
            tmp.setUp(x);
        } else {
            x.getRight().setUp(x.getUp());
            x.getUp().setRight(x.getRight());
            x.getRight().setLeft(x);
            x.setUp(x.getRight());
            x.setRight(tmp);
            tmp.setUp(x);
        }
        if (x == head) {
            head = x.getUp();
        }
    }

    private void rightRotation(Node y) {
        Node tmp = y.getLeft().getRight();
        if (y.getUp().getLeft() == y) {
            y.getUp().setLeft(y.getLeft());
            y.getUp().getLeft().setUp(y.getUp());
            y.setLeft(tmp);
            tmp.setUp(y);
            y.getUp().getLeft().setRight(y);
            y.setUp(y.getUp().getLeft());
        } else {
            y.getUp().setRight(y.getLeft());
            y.getUp().getRight().setUp(y.getUp());
            y.setLeft(tmp);
            tmp.setUp(y);
            y.getUp().getRight().setRight(y);
            y.setUp(y.getUp().getRight());
        }
        if (y == head) {
            head = y.getUp();
        }
    }

    @Override
    public void setValue(Comparable key, Object value) {
        if (head == null) {
            head = new Node(key, value);
            head.setColor('b');
            head.setLeft(nil);
            head.setRight(nil);
            head.setUp(nil);
        } else {
            Node newFather = head;
            boolean flag = true;
            while (flag) {
                if (key.compareTo(newFather.getKey()) < 0) {
                    if (newFather.getLeft() != nil) {
                        newFather = newFather.getLeft();
                    } else {    //kiedy lewy to nil
                        Node n = new Node(key, value);
                        newFather.setLeft(n);
                        n.setLeft(nil);
                        n.setRight(nil);
                        n.setUp(newFather);
                        n.setColor('r');
                        flag = false;
                        if (newFather.getColor() == 'r') {
                            fixTree(n);
                        }
                    }
                } else if (key.compareTo(newFather.getKey()) == 0) {
                    newFather.setValue(value);
                } else if (key.compareTo(newFather.getKey()) > 0) {
                    if (newFather.getRight() != nil) {
                        newFather = newFather.getRight();
                    } else {    //kiedy prawy jest nil
                        Node n = new Node(key, value);
                        newFather.setRight(n);
                        n.setLeft(nil);
                        n.setRight(nil);
                        n.setUp(newFather);
                        n.setColor('r');
                        flag = false;
                        if (newFather.getColor() == 'r') {
                            fixTree(n);
                        }
                    }
                }
            }
        }
    }

    private void fixTree(Node n) {
        if (n.getUp().getUp() != nil) {
            if (n.getUp().getUp().getLeft() == n.getUp()) {  //ojciec jest lewym dzieckiem
                if (n.getUp().getUp().getRight().getColor() == 'r') {    //1 przypadek
                    n.getUp().setColor('b');
                    n.getUp().getUp().getRight().setColor('b');
                    n.getUp().getUp().setColor('r');
                    if (n.getUp().getUp() == head) {
                        n.getUp().getUp().setColor('b');
                    } else {
                        fixTree(n.getUp().getUp());
                    }
                } else if (n.getUp().getRight() == n) {        //2 przypadek
                    leftRotation(n.getUp());
                    fixTree(n.getLeft());
                } else if (n.getUp().getLeft() == n) {          //3 przypadek
                    rightRotation(n.getUp().getUp());
                    changeColor(n.getUp());
                    changeColor(n.getUp().getRight());
                }

            } else if (n.getUp().getUp().getRight() == n.getUp()) {      //ojciec jest prawym dzieckiem
                if (n.getUp().getUp().getLeft().getColor() == 'r') {      //1 przypadek
                    n.getUp().setColor('b');
                    n.getUp().getUp().getLeft().setColor('b');
                    n.getUp().getUp().setColor('r');
                    if (n.getUp().getUp() == head) {
                        n.getUp().getUp().setColor('b');
                    } else {
                        fixTree(n.getUp().getUp());
                    }
                } else if (n.getUp().getLeft() == n) {        //2 przypadek
                    rightRotation(n.getUp());
                    fixTree(n.getRight());
                } else if (n.getUp().getRight() == n) {      //3 przypadek
                    leftRotation(n.getUp().getUp());
                    changeColor(n.getUp());
                    changeColor(n.getUp().getLeft());
                }
            }
        }
    }

    @Override
    public V getValue(Comparable key) {
        Node value = head;
        if (head != null) {
            while (value.getKey().compareTo(key) != 0) {
                if (key.compareTo(value.getKey()) < 0) {
                    value = value.getLeft();
                } else {
                    value = value.getRight();
                }
                if (value == nil) {
                    return null;
                }
            }
        } else {
            return null;
        }
        return (V) value.getValue();
    }

    public Node getHead() {
        return head;
    }

    public void changeColor(Node x) {
        if (x.getColor() == 'r') {
            x.setColor('b');
        } else {
            x.setColor('r');
        }
    }

    public void wypiszDzieci(Node x, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println("Dla węzła " + x.getKey() + " dzieci to:");
        if (x.getLeft() != nil) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println("L: " + x.getLeft().getKey() + " " + x.getLeft().getColor());
            wypiszDzieci(x.getLeft(), level + 1);
        } else {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println("L - nill");
        }
        if (x.getRight() != nil) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println("R: " + x.getRight().getKey() + " " + x.getRight().getColor());
            wypiszDzieci(x.getRight(), level + 1);
        } else {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println("R - nill");
        }
    }

}
