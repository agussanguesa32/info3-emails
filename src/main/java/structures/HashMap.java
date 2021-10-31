package main.java.structures;

public class HashMap<K, T> {
    private HashEntry<K, T>[] table;
    private int size;

    public HashMap() {
        table = new HashEntry[7];
        size = 7;
    }

    public HashMap(int size) {
        table = new HashEntry[size];
        this.size = size;
    }

    public void put(K key, T value) throws Exception {
        int pos = inRange(key);
        if (table[pos] != null)
            throw new Exception("colition");

        table[pos] = new HashEntry<>(key, value);
    }

    public T get(K key) throws Exception {
        int pos = inRange(key);
        if (table[pos] == null || table[pos].getKey() != key)
            throw new Exception("not found");

        return table[pos].getData();
    }

    public void remove(K key) throws Exception {
        int pos = inRange(key);
        if (table[pos] == null || table[pos].getKey() != key)
            throw new Exception("not found");

        table[pos] = null;
    }

    private int inRange(K key) {
        return hashFunc(key) % size;
    }

    private int hashFunc(K key) {
        Integer tmp = Integer.parseInt("" + key);
        return (int) Math.pow(tmp, 3);
    }

    public static void main(String[] args) {
        HashMap<Integer, String> miTabla = new HashMap<>(13);


        try {
            miTabla.put(3, "Hola");
            miTabla.put(5, "Chau");
            miTabla.put(10, "Coli");

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(miTabla.get(3));
            System.out.println(miTabla.get(5));
            System.out.println(miTabla.get(10));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}