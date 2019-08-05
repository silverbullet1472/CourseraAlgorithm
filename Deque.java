import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Node before;
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            Node newNode = new Node();
            newNode.before = null;
            newNode.next = null;
            newNode.item = item;
            first = newNode;
            last = newNode;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.before = null;
            first.item = item;
            first.next = oldfirst;
            oldfirst.before = first;
            size++;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            Node newNode = new Node();
            newNode.before = null;
            newNode.next = null;
            newNode.item = item;
            first = newNode;
            last = newNode;
        } else {
            Node oldlast = last;
            last = new Node();
            last.before = oldlast;
            last.item = item;
            last.next = null;
            oldlast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Item removed = first.item;
        Node newfirst = first.next;
        first.before = null;
        first = newfirst;
        size--;
        return removed;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Item removed = last.item;
        Node newlast = last.before;
        newlast.next = null;
        last = newlast;
        size--;
        return removed;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> test = new Deque<String>();
        StdOut.println("Start Testing");
        StdOut.println(test.size());
        StdOut.println(test.isEmpty());
        test.addFirst("at");
        test.addFirst("look");
        test.addFirst("fuck this song");
        test.removeFirst();
        test.addLast("the");
        test.addLast("stars");
        test.addLast("fuck this song");
        test.removeLast();
        for (String s : test) StdOut.println(s);
    }

}