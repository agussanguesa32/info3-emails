package main.java.structures;

import java.util.Iterator;
import java.util.Optional;

public class LinkedList<AnyType> {
    private LinkedNode<AnyType> begin;
    private int size;

    public LinkedList() {
        begin = null;
        size = 0;
    }

    /**
     * Adds a node at the pos position
     *
     * @param d   the data
     * @param pos the position
     */

    public void add(AnyType d, int pos) throws Exception {
        LinkedNode<AnyType> aux;
        if (pos == 0) {
            begin = new LinkedNode<>(begin, d);
        } else {
            aux = getNode(pos - 1);
            aux.next = new LinkedNode<>(aux.next, d);
        }
        size++;
    }


    /**
     * Add data at the begining
     *
     * @param d
     */
    public void add(AnyType d) throws Exception {
        this.add(d, this.size);
    }

    /**
     * Get data from positon idx
     *
     * @param pos
     * @return the data
     */
    public AnyType get(int pos) {
        LinkedNode<AnyType> aux = getNode(pos);
        return aux.data;
    }

    /**
     * @param d
     * @param pos
     */
    public void update(AnyType d, int pos) throws Exception {
        LinkedNode<AnyType> aux = getNode(pos);
        aux.data = d;
    }

    /**
     * @param pos
     */
    public void delete(int pos) throws Exception {
        if (pos == 0) {
            if (begin == null)
                throw new Exception("pos not found");
            begin = begin.next;
        } else {
            LinkedNode<AnyType> aux = getNode(pos - 1);
            if (aux.next == null)
                throw new Exception("pos not found");

            aux.next = aux.next.next;
        }
        size--;
    }


    /**
     * @return
     */
    public int getSize() {
        return size;
    }

    private LinkedNode<AnyType> getNode(int pos) {
        LinkedNode<AnyType> aux = begin;
        int p = 0;
        while (p < pos && aux != null) {
            p++;
            aux = aux.next;
        }
        if (aux == null) {
            return null;
        }
        return aux;
    }


    public void moverSiguiente(AnyType d) {
        LinkedNode<AnyType> aux = begin;


        while (aux.next != null && !aux.next.data.equals(d)) {
            aux = aux.next;
        }

        LinkedNode<AnyType> ant = aux;
        LinkedNode<AnyType> dato = aux.next;
        LinkedNode<AnyType> sig = aux.next.next;

        ant.next = sig;
        if (sig != null) {
            dato.next = sig.next;
            sig.next = dato;
        }
    }


    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (LinkedNode<AnyType> x = begin; x != null; x = x.next)
            result[i++] = x.data;
        return result;
    }

    public boolean contains(AnyType value) {
        LinkedNode<AnyType> current = begin;

        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }
}