package datastructure.trie.wz;

import java.util.ArrayList;
import java.util.List;

public class TST<V> {
    private Node<V> root;

    public TST() {
    }

    /**
     * 这里root不能作为哑结点
     *
     * @param key
     * @param value
     * @return
     */
    public void put(String key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value, 0);
    }

    private Node<V> put(Node<V> node, String key, V value, int d) {
        char c = key.charAt(d);
        if (node == null) {
            node = new Node<>(c);
        }

        if (c < node.c) {
            node.left = put(node.left, key, value, d);
        } else if (c > node.c) {
            node.right = put(node.right, key, value, d);
        } else if (d < key.length() - 1) {
            node.mid = put(node.mid, key, value, d + 1);
        } else {
            // 终止条件
            // 也即d 为 key.length() - 1时
            node.value = value;
        }
        return node;
    }

    public V get(String key) {
        Node<V> node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node<V> getNode(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node<V> node = root;
        int d = 0;
        // 这个地方不要写成&& d < key.length()，否则node总是为null
        // 终止条件是c == node.c 并且 d = key.length() - 1
        while (node != null) {
            char c = key.charAt(d);
            if (c < node.c) {
                node = node.left;
            } else if (c > node.c) {
                node = node.right;
            } else if (d < key.length() - 1) {
                node = node.mid;
                d++;
            } else {
                break;
            }
        }

        return node;
    }

    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = delete(root, key, 0);
    }

    private Node<V> delete(Node<V> node, String key, int d) {
        if (node == null) {
            return null;
        }
        char c = key.charAt(d);

        if (c < node.c) {
            node.left = delete(node.left, key, d);
        } else if (c > node.c) {
            node.right = delete(node.right, key, d);
        } else if (d < key.length() - 1) {
            node.mid = delete(node.mid, key, d + 1);
        } else {
            node.value = null;
        }
        //  因为没有父指针，所以删除需要使用递归，递归从下至上检查是否需要删除沿路结点
        if (node.left == null && node.mid == null && node.right == null) {
            node = null;
        }
        return node;
    }

    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        // 求出最长的length
        int length = 0;
        Node<V> node = root;
        int d = 0;
        while (node != null && d < query.length()) {
            char c = query.charAt(d);
            if (c < node.c) {
                node = node.left;
            } else if (c > node.c) {
                node = node.right;
            } else {
                d++;
                if (node.value != null) {
                    // 这里因为前面d已经加1了
                    length = d;
                }
                node = node.mid;
            }
        }

        return query.substring(0, length);
    }

    /**
     * 这个处理方法比较微妙
     * @param prefix
     * @return
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        List<String> results = new ArrayList<>();
        Node<V> node = getNode(prefix);
        if (node == null) {
            return results;
        }
        // prefix本身忘记处理
        if (node.value != null) {
            results.add(prefix);
        }
        // 应该是从node下一层开始，这是核心关键
        // prefix + node.mid/node.mid.left/node.mid.right
        collect(node.mid, new StringBuilder(prefix), results);
        return results;
    }

    /**
     * 注意递归的初始条件
     * 取值node时，prefix已经包含node.c，所以后面append时，需要append(node.mid.c)
     *
     * @param node
     * @param prefix
     * @param results
     */
    public void collect(Node<V> node, StringBuilder prefix, List<String> results) {
        if (node == null) {
            return;
        }
        if (node.value != null) {
            // 这里要注意，prefix是前面的prefix，因此这里需要加上node.c
            results.add(prefix.toString() + node.c);
        }
        collect(node.left, prefix, results);

        prefix.append(node.c);
        collect(node.mid, prefix, results);
        prefix.deleteCharAt(prefix.length() - 1);

        collect(node.right, prefix, results);

    }

    public Iterable<String> keysThatMatch(String pattern) {
        List<String> results = new ArrayList<>();
        collect(root, new StringBuilder(), pattern, 0, results);
        return results;
    }

    private void collect(Node<V> node, StringBuilder prefix, String pattern, int d, List<String> results) {
        // 终止情况
        if (node == null) {
            return;
        }
        char c = pattern.charAt(d);
        if (d == pattern.length() - 1) {
            if (c == node.c || c == '.') {
                // 注意这里需要添加上node.c，因为最后一次的node.c还未添加进来
                results.add(prefix.toString() + node.c);
            }
            return;
        }

        // 正常情况
        // 参考 算法4的合并写法，代码较少，但是方法执行效率不高
        if (c == '.') {
            collect(node.left, prefix, pattern, d, results);
            collect(node.right, prefix, pattern, d, results);
            // 这里添加的是node.c，而不是c，不要误将c加入到prefix
            prefix.append(node.c);
            collect(node.mid, prefix, pattern, d + 1, results);
            prefix.deleteCharAt(prefix.length() - 1);
        } else {
            if (c < node.c) {
                collect(node.left, prefix, pattern, d, results);
            } else if (c > node.c) {
                collect(node.right, prefix, pattern, d, results);
            } else {
                prefix.append(c);
                collect(node.mid, prefix, pattern, d + 1, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    private static class Node<V> {
        private char c;
        private V value;
        private Node<V> left, mid, right;

        public Node(char c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        String[] array = {"by", "sea", "sells", "she", "shells", "shore", "the"};
        TST<Integer> trie = new TST<>();
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

        Iterable<String> results2 = trie.keysThatMatch(".he");
        for (String str : results2) {
            System.out.printf(str + " ");
        }
        System.out.println();
    }
}
