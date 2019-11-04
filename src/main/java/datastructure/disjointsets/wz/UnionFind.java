package datastructure.disjointsets.wz;

public class UnionFind {
    // 结点的父亲
    private int[] parent;
    // 集合的秩
    private int[] rank;
    // 不相交集的个数
    private int count;

    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        // 忘了更新count
        count = n;
    }

    /**
     * 路径压缩，两趟将路径上全部指向根节点
     * @param p
     * @return
     */
    public int find(int p) {
        checkIndex(p);
        // 这里递归应该写成if而不是while，搞成了死循环
        if (p != parent[p]){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public int findHalve(int p) {
        checkIndex(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        checkIndex(p);
        checkIndex(q);
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (rank[i] < rank[j]) {
            parent[i] = j;
        } else if (rank[j] < rank[i]){
            parent[j] = i;
        } else {
            // 只有当大小相等时，高才增加1，也即秩增加1，其他情况下，秩是不变的
            // 刚开始写错了，应该是rank[j]增加
            parent[i] = j;
            rank[j]++;
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    private void checkIndex(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        int[] edge = {
                4,3,
                3,8,
                6,5,
                9,4,
                2,1,
                8,9,
                5,0,
                7,2,
                6,1,
                1,0,
                6,7
        };
        for (int i = 0; i < edge.length / 2; i++) {
            int index = i << 1;
            int p = edge[index];
            int q = edge[index + 1];
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
