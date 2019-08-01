/******************************************************************************
 *  Compilation:  javac LPT.java
 *  Execution:    java LPT m < jobs.txt
 *  Dependencies: Processor.java Job.java
 *  
 *  Find an approximate solution to the load balancing
 *  problem using the LPT (longest processing time first) rule.
 *
 ******************************************************************************/

import algorithm.sort.alg4.ch02.MinPQ;
import util.alg4.library.StdIn;
import util.alg4.library.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class LPT {

    public static class Job{
        private final String name;
        private final double time;

        public Job(String name, double time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "name='" + name + '\'' +
                    ", time=" + time +
                    '}';
        }
    }

    public static class Processor implements Comparable<Processor> {
        private double load;
        private ArrayList<Job> jobs;

        public Processor() {
            this.load = Math.random();
            this.jobs = new ArrayList<>();
        }

        public void add (Job job) {
            jobs.add(job);
        }


        @Override
        public int compareTo(Processor o) {
            return (load - o.load) > 0 ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);   // number of machines

        int n = StdIn.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }

        // sort jobs in ascending order of processing time 
        Arrays.sort(jobs);

        // generate m empty processors and put on priority queue
        MinPQ<Processor> pq = new MinPQ<>(m);
        for (int i = 0; i < m; i++)
            pq.insert(new Processor());
        
        
        // assign each job (in decreasing order of time) to processor that is least busy
        for (int j = n-1; j >= 0; j--) {
            Processor min = pq.delMin();
            min.add(jobs[j]);
            pq.insert(min);
        }
        
        // print out contents of each processor
        while (!pq.isEmpty())
            StdOut.println(pq.delMin());
    }

}
