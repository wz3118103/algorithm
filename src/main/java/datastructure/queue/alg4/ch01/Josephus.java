package datastructure.queue.alg4.ch01;

import util.alg4.library.StdOut;

/******************************************************************************
 *  Compilation:  javac datastructure.queue.alg4.ch01.Josephus.java
 *  Execution:    java datastructure.queue.alg4.ch01.Josephus m n
 *  Dependencies: Queue.java
 *
 *  Solves the datastructure.queue.alg4.ch01.Josephus problem.
 *
 *  % java datastructure.queue.alg4.ch01.Josephus 2 7
 *  1 3 5 0 4 2 6
 *
 ******************************************************************************/

public class Josephus {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        // initialize the queue
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        while (!queue.isEmpty()) {
            for (int i = 0; i < m-1; i++)
                queue.enqueue(queue.dequeue());
            StdOut.print(queue.dequeue() + " ");
        } 
        StdOut.println();
    }
}

