package algorithm.bigdataprocess.cracking.ch09_scalability_and_memory_limits.Q9_05_Cache;

public class Node {
	public Node prev;
	public Node next;
	public String[] results;
	public String query;

	public Node(String q, String[] res) {
		results = res;
		query = q;
	}
}
