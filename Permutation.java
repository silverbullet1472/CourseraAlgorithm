import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        // Reservoir sampling
        int k = Integer.parseInt(args[0]);
        for (int i = 1; !StdIn.isEmpty(); i++) {
            String s = StdIn.readString();
            if (i <= k) {
                rq.enqueue(s);
            } else if (Math.random() < (double) k/i) {
                rq.dequeue();
                rq.enqueue(s);
            }
        }

        while (!rq.isEmpty()) {
            System.out.println(rq.dequeue());
        }
    }
}