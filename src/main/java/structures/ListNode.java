package main.java.structures;


class ListNode<AnyType> {
    public AnyType element;
    public ListNode<AnyType> next;

    // Constructors
    public ListNode(AnyType theElement) {
        this(theElement, null);
    }

    public ListNode(AnyType theElement, ListNode<AnyType> n) {
        element = theElement;
        next = n;
    }
}