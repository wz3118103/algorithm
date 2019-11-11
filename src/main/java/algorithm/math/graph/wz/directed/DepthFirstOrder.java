package algorithm.math.graph.wz.directed;

import java.util.ArrayDeque;

/**
 * 前序：在递归调用之前将顶点加入队列
 * 后序：在递归调用之后将顶点加入队列
 * 逆后序：在递归调用之后将顶点压入栈
 */
public class DepthFirstOrder {
    private Digraph G;
    private boolean[] marked;
    private ArrayDeque<Integer> pre;
    private ArrayDeque<Integer> post;
    private ArrayDeque<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        G = g;
        marked = new boolean[g.V()];
        pre = new ArrayDeque<>();
        post = new ArrayDeque<>();
        reversePost = new ArrayDeque<>();
        for (int v = 0; v < g.V(); v++) {
            // 不要少了这个检测
            if (!marked[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        pre.offer(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
        post.offer(v);
        reversePost.push(v);
    }

    /**
     * 完全是根据dfs调用顺序，如果对于(v, w)，先调用w,后调用v，则无法确保拓扑排序
     * @return
     */
    public Iterable<Integer> pre() {
        return pre;
    }

    /**
     * 与拓扑排序相反
     * @return
     */
    public Iterable<Integer> post() {
        return post;
    }

    /**
     * 拓扑排序：针对任意的边(v, w)，w先入栈
     * 证明，调用dfs(v)时
     * 1）先调用w，dfs(w)先调用完，w先入栈
     * 2）先调用v，dfs(w)还未被调用，根据递归调用方式，也是w先入栈
     * 3）先调用dfs(w)并且未完成，意味着w->v->w有路径，存在环，则有问题
     * @return
     */
    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
