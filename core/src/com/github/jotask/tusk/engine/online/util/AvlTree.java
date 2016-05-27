package com.github.jotask.tusk.engine.online.util;

import java.util.LinkedList;

public class AvlTree<DATA> {

    public Node root;

    public int size;

    public class Node {

        public int id;
        public int balance;

        public DATA data;

        public Node left, right, parent;

        Node(int k, DATA c, Node p) {
            size++;
            id = k;
            data = c;
            parent = p;
        }

    }

    public DATA exist(int delKey) {

        if (root == null) return null;

        Node n = root;
        Node delNode = null;
        Node child = root;

        while (child != null) {
            n = child;
            child = delKey >= n.id ? n.right : n.left;
            if (delKey == n.id)
                delNode = n;
        }

        if(delNode == null) {
            System.out.println("notfinded " + delKey);
            return null;
        }

        return delNode.data;

    }

    public boolean insert(int id, DATA data) {

        if (root == null) {
            root = new Node(id, data, null);
        }else{
            Node n = root;
            Node parent;
            while (true) {
                if (n.id == id) {
                    n.data = data;
                    return false;
                }

                parent = n;

                boolean goLeft = n.id > id;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new Node(id, data, parent);
                    } else {
                        parent.right = new Node(id, data, parent);
                    }
                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }

    public LinkedList<DATA> getAllTree(){

        LinkedList<DATA> characters = new LinkedList<DATA>();

        iterator(root, characters);

        return characters;

    }

    public void iterator(Node node, LinkedList<DATA> characters){
        if(node == null)return;
        if(node.left != null) iterator(node.left, characters);
        characters.add(node.data);
        if(node.right != null) iterator(node.right, characters);
    }


    public void delete(int delKey) {

        if (root == null) return;

        Node n = root;
        Node parent = root;
        Node delNode = null;
        Node child = root;

        while (child != null) {
            parent = n;
            n = child;
            child = delKey >= n.id ? n.right : n.left;
            if (delKey == n.id)
                delNode = n;
        }

        if (delNode != null) {
            delNode.id = n.id;

            child = n.left != null ? n.left : n.right;

            if (root.id == delKey) {
                root = child;
            } else {
                if (parent.left == n) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
                rebalance(parent);
            }
            size--;
        }
    }

    private void rebalance(Node n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private Node rotateLeft(Node a) {

        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null)
            a.right.parent = a;

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateRight(Node a) {

        Node b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null)
            a.left.parent = a;

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    public int height(Node n) {
        if (n == null)
            return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    private void setBalance(Node... nodes) {
        for (Node n : nodes)
            n.balance = height(n.right) - height(n.left);
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

}