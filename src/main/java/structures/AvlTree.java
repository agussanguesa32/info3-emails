package main.java.structures;


import main.java.Date;

import main.java.structures.*;


public class AvlTree<K extends Comparable<K>, T> {
    private Node<K, T> root;


    private int size;

    public AvlTree() {
        size = 0;
    }

    public AvlTree<K, T> insert(K key, T data) {
        root = insert(key, data, root);
        size++;
        return this;
    }

    private Node<K, T> insert(K key, T data, Node<K, T> node) {
        if (node == null) {
            return new Node<>(key, data);
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftChild(insert(key, data, node.getLeftChild()));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRightChild(insert(key, data, node.getRightChild()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public T get(K key) {
        return get(key, root);
    }

    private T get(K key, Node<K, T> node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.getKey()) < 0) {
            return get(key, node.getLeftChild());
        } else if (key.compareTo(node.getKey()) > 0) {
            return get(key, node.getRightChild());
        } else {
            return node.getData();
        }
    }

    public Node<K, T> getRoot() {
        return this.root;
    }

    public void delete(K key) {
        root = delete(key, root);
        size--;
    }

    private Node<K, T> delete(K key, Node<K, T> node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeftChild(delete(key, node.getLeftChild()));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRightChild(delete(key, node.getRightChild()));
        } else {
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            Node<K, T> tempNode = getMax(node.getLeftChild());
            node.setKey(tempNode.getKey());
            node.setData(tempNode.getData());
            node.setLeftChild(delete(node.getKey(), node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    private Node<K, T> applyRotation(Node<K, T> node) {
        int balance = balance(node);
        if (balance > 1) {
            // esta desbalanceado a la izquierda
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            // esta desbalanceado a la derecha
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<K, T> rotateRight(Node<K, T> node) {
        Node<K, T> leftNode = node.getLeftChild();
        Node<K, T> centerNode = leftNode.getRightChild();
        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<K, T> rotateLeft(Node<K, T> node) {
        Node<K, T> rightNode = node.getRightChild();
        Node<K, T> centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private int balance(Node<K, T> node) {
        return node != null ? height(node.getLeftChild()) - height(node.getRightChild()) : 0;
    }

    private void updateHeight(Node<K, T> node) {
        int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild()));
        node.setHeight(maxHeight + 1);
    }

    private int height(Node<K, T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<K, T> node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            traverseInOrder(node.getRightChild());
        }
    }

    public Node<K, T> getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private Node<K, T> getMax(Node<K, T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node;
    }

    public Node<K, T> getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    public Node<K, T> getMin(Node<K, T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node;
    }

    public boolean isEmpty() {
        return false;
    }



    public void print() {
        print(false, "", root);
    }

    private void print(boolean isRight, String identation, Node<K, T> r) {
        if (r.getRightChild() != null) {
            print(true, identation + (isRight ? "     " : "|    "), r.getRightChild());
        }
        System.out.print(identation);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("-- ");
        System.out.println(r.getKey());
        if (r.getLeftChild() != null) {
            print(false, identation + (isRight ? "|    " : "     "), r.getLeftChild());
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkedList<T> getIOList(K init, K end){

        LinkedList<T> list = new LinkedList<T>();

        getIO(this.root, list, init, end);

        return list;
    }
    public LinkedList<T> getIOList(){

        LinkedList<T> list = new LinkedList<T>();

        getIO(this.root, list);

        return list;
    }
    private void getIO(Node<K, T> node, LinkedList<T> list) {
        if (node == null)
            return;
        
        
            getIO(node.getLeftChild(), list);

            try {
                list.add(node.getData());
            } catch (Exception e) {
                System.out.println(e);
            }
            getIO(node.getRightChild(), list);
    }


    private void getIO(Node<K, T> node, LinkedList<T> list, K init, K end) {
        if (node == null)
            return;
        if (node.getKey().compareTo(init) > 0){
            getIO(node.getLeftChild(), list, init, end);
        }

        if (node.getKey().compareTo(init) > 0 && node.getKey().compareTo(end) < 0){
            try {
                list.add(node.getData());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (node.getKey().compareTo(end) < 0){
            getIO(node.getRightChild(), list, init, end);
        }
    }
}