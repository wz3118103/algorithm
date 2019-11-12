package datastructure.string.wz.compression;

import java.util.PriorityQueue;

public class Huffman {
    private static final int R = 256;

    /**
     * 构建Huffman编码树
     * @param freq
     * @return
     */
    private static Node buildTrie(int[] freq) {
        // step1.将所有字符对应的频率组成Node放入优先队列
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char c = 0; c < R; c++) {
            if (freq[c] != 0) {
                pq.offer(new Node(c, freq[c], null, null));
            }
        }

        // step2.总是从队列中取出两个，然后组成一个，再重新插入到pq
        while (pq.size() > 1) {
            Node x = pq.poll();
            Node y = pq.poll();
            Node z = new Node('\0', x.freq + y.freq, x, y);
            pq.offer(z);
        }
        return pq.poll();
    }

    static class Node implements Comparable<Node> {
        private char c;
        private int freq;
        private Node left;
        private Node right;

        public Node(char c, int freq, Node left, Node right) {
            this.c = c;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }
}
