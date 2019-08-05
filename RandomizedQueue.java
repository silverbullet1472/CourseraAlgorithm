import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int N) {
        Item[] temp = (Item[]) new Object[N];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // add the item
    public void enqueue(Item item) {
        // add the item
        if (item == null) {
            throw new NullPointerException();
        }
        items[size++] = item;
        if (size == items.length) {
            resize(2 * size);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        int i = StdRandom.uniform(size);
        Item removed = items[i];
        items[i] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size <= items.length / 4) {
            resize(items.length / 2);
        }
        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int i = StdRandom.uniform(size);
        Item sampled = items[i];
        return sampled;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQInterator();
    }


    private class RQInterator implements Iterator<Item> {
        private int itTimes = size;
        private Item[] backItems;

        private RQInterator() {
            backItems = (Item[]) new Object[size];
            for (int j = 0; j < size; j++) {
                backItems[j] = items[j];
            }
        }

        public boolean hasNext() {
            return itTimes != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (size == 0) throw new NoSuchElementException();
            int choice = StdRandom.uniform(itTimes);
            Item item = backItems[choice];
            backItems[choice] = backItems[itTimes - 1];
            backItems[itTimes - 1] = null;
            itTimes--;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        StdOut.println("Start Testing");
        StdOut.println(test.size());
        StdOut.println(test.isEmpty());
        test.enqueue("at");
        test.enqueue("look");
        test.dequeue();
        test.enqueue("the");
        test.enqueue("stars");
        test.enqueue("fuck this song");
        StdOut.println(test.sample());
        for (String s : test) StdOut.println(s);
    }

}