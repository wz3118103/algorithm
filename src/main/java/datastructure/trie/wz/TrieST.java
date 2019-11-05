package datastructure.trie.wz;

import java.util.ArrayList;
import java.util.List;

public class TrieST<V> {
    private static final int R = 26;
    private Node<V> root;
    // 可以增加一个size表示trie树大小
//    private int size;

    public TrieST() {
        root = new Node<>();
    }

    public void put(String key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        put(key, value, root, 0);
    }

    private void put(String key, V value, Node<V> node, int d) {
        if (d == key.length()) {
            node.value = value;
            return;
        }
        int i = key.charAt(d) - 'a';
        if (node.next[i] == null) {
            node.next[i] = new Node<>();
        }
        put(key, value, node.next[i], d + 1);
    }

    public V get(String key) {
        Node<V> node = root;
        int i = 0;
        while (node != null && i < key.length()) {
            // 这里少减了'a'
            node = node.next[key.charAt(i) - 'a'];
            // 这里少了i++，node是trie树，i是字符串，这两个要同步更新
            i++;
        }
        if (node == null) {
            return null;
        }
        return node.value;
    }

    /**
     * 删除比较麻烦，需要考虑删除节点
     * 因为没有父指针，所以删除需要使用递归，递归从下至上检查是否需要删除沿路结点
     * 保证这种较为复杂递归方法写法正确性的秘诀：用一个极其简单的示例进行验证，例如"hi"
     * @param key
     */
    public V delete(String key) {
        return delete(key, 0, root);
    }

    private V delete(String key, int d, Node<V> node) {
        // key在Trie树种不存在
        if (node == null) {
            return null;
        }

        // 到了最后一个结点上，此时需要将value置为null
        if (d == key.length()) {
            V old = node.value;
            node.value = null;
            return old;
        }

        Node<V> child = node.next[key.charAt(d) - 'a'];
        V old = null;
        if (child != null) {
            old = delete(key, d + 1, child);
        }

        // 这里少了value监测，因为child置为null的充要条件是：value为null且所有孩子结点为null
        if (child.value != null) {
            return old;
        }
        for (int i = 0; i < R; i++) {
            if (child.next[i] != null) {
                return old;
            }
        }
        node.next[key.charAt(d) - 'a'] = null;
        return old;
    }

    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        int length = longestPrefixOf(query, 0, root, -1);
        if (length == -1) {
            return null;
        } else {
            return query.substring(0, length);
        }
    }

    private int longestPrefixOf(String query, int d, Node<V> node, int length) {
        if (node == null) {
            return length;
        }
        // 如果需要包含query本身，那么就将d==query.length()放到node.value!=null后面
        if (node.value != null) {
            length = d;
        }
        if (d == query.length()) {
            return length;
        }
        return longestPrefixOf(query, d + 1, node.next[query.charAt(d) - 'a'], length);
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        Node<V> x = get(prefix,  root);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private Node<V> get(String prefix, Node<V> node) {
        int d = 0;
        while (node != null && d < prefix.length()) {
            node = node.next[prefix.charAt(d) - 'a'];
            d++;
        }
        return node;
    }

    private void collect(Node<V> node, StringBuilder prefix, List<String> results) {
        if (node == null) {
            return;
        }
        if (node.value != null) {
            results.add(prefix.toString());
        }
        // 深度优先遍历
        for (int i = 0; i < R; i++) {
            // 这里需要强制转换为char
            prefix.append((char)('a' + i));
            collect(node.next[i], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        List<String> results = new ArrayList<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node<V> node, StringBuilder prefix, String pattern, List<String> resuslts) {
        if (node == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length()) {
            if (node.value != null) {
                resuslts.add(prefix.toString());
            }
            return;
        }
        // 这里由于限制了字符只能为小写的26个字母，所以无法完成.正则表达式的测试
        // 这里d从0开始
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char i = 0; i < R; i++) {
                prefix.append(i);
                collect(node.next[i], prefix, pattern, resuslts);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(node.next[c], prefix, pattern, resuslts);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    private static class Node<V> {
        private V value;
        private Node<V>[] next = new Node[R];
    }

    public static void main(String[] args) {
        String[] array = {"by", "sea", "sells", "she", "shells", "shore", "the"};
        TrieST<Integer> trie = new TrieST<>();
        for (String str : array) {
            trie.put(str, str.length());
        }
        for (String str : array) {
            System.out.println(trie.get(str));
        }
        System.out.println();
//        for (String str : array) {
//            System.out.println(trie.delete(str));
//        }
        System.out.println(trie.longestPrefixOf("shells"));
        System.out.println(trie.longestPrefixOf("shell"));

        System.out.println();
        Iterable<String> results = trie.keysWithPrefix("sh");
        for (String str : results) {
            System.out.printf(str + " ");
        }
        System.out.println();
    }

}
