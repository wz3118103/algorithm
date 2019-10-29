package datastructure.redblacktree.wz;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class TreeMap<K, V> {
    private Entry<K, V> root;
    private int size;
    private Comparator<? super K> comparator;


    private static final boolean BLACK = false;
    private static final boolean RED = true;

    public TreeMap() {
    }

    public TreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    /**
     * @param key
     * @param value
     * @return 如果key已经存在，则更新，并返回旧值
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }

        if (root == null) {
            root = new Entry<>(key, value, null);
            return null;
        }

        Entry<K, V> parent = null;
        Entry<K, V> t = root;
        int cmp = 0;
        if (comparator != null) {
            while (t != null) {
                parent = t;
                cmp = comparator.compare(key, t.key);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t.setValue(value);
                }
            }
        } else {
            Comparable<? super K> k = (Comparable<? super K>) key;
            while (t != null) {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t.setValue(value);
                }
            }
        }
        Entry<K, V> newNode = new Entry<>(key, value, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        fixupAfterInsert(newNode);
        // 不要忘了更新size
        size++;
        return null;
    }

    private void fixupAfterInsert(Entry<K, V> node) {
        node.color = RED;
        while (node != null && node != root && node.parent.color == RED) {
            // 按照父节点是祖父结点的左右子结点对称划分
            if (parentOf(node) == leftOf(parentOf(parentOf(node)))) {
                Entry<K, V> uncle = rightOf(parentOf(parentOf(node)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (node == rightOf(parentOf(node))) {
                        node = parentOf(node);
                        leftRotate(node, node.right);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rightRotate(parentOf(node), parentOf(parentOf(node)));
                }
            } else {
                Entry<K, V> uncle = leftOf(parentOf(parentOf(node)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (node == leftOf(parentOf(node))) {
                        node = parentOf(node);
                        rightRotate(node.left, node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    leftRotate(parentOf(parentOf(node)), parentOf(node));
                }
            }
        }
        root.color = BLACK;
    }

    public V remove(Object key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }

        V value = entry.value;
        deleteEntry(entry);
        return value;
    }

    private Entry<K, V> getEntry(Object key) {
        if (key == null) {
            return null;
        }
        Entry<K, V> t = root;
        if (comparator != null) {
            while (t != null) {
                int cmp = comparator.compare((K) key, t.key);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t;
                }
            }
            return null;
        }

        Comparable<? super K> k = (Comparable<? super K>) key;
        while (t != null) {
            int cmp = k.compareTo(t.key);
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                return t;
            }
        }
        return null;

    }

    private void deleteEntry(Entry<K,V> del) {
        size--;


        // 如果del有两个孩子结点，则转换为后继结点的删除（也即转换为叶子结点删除）
        if (del.left != null && del.right != null) {
            Entry<K,V> s = successor(del);
            del.key = s.key;
            del.value = s.value;
            del = s;
        }

        Entry<K,V> replacement = del.left != null ? del.left : del.right;
        // 至少有一个孩子
        if (replacement != null) {
            replacement.parent = del.parent;
            // 这里少了del.parent为null的判断！
            if (del.parent == null) {
                root = replacement;
            } else if(del == del.parent.left) {
                del.parent.left = replacement;
            } else {
                del.parent.right = replacement;
            }
            // 将删除的del的相关于置为null
            del.left = del.right = del.parent = null;
            if (del.color == BLACK) {
                fixupAfterRemove(replacement);
            }
        } else if(del.parent == null) {
            root = null;
        } else {
            if (del.color == BLACK) {
                fixupAfterRemove(del);
            }
            if (del.parent != null) {
                if (del == del.parent.left) {
                    del.parent.left = null;
                } else {
                    del.parent.right = null;
                }
                del.parent = null;
            }

        }
    }

    private void fixupAfterRemove(Entry<K,V> del) {
        while(del != root & colorOf(del) == BLACK) {
            // 根据del是父结点的左右孩子分为对称的两部分
            if (del == leftOf(parentOf(del))) {
                Entry<K,V> sib = rightOf(parentOf(del));
                // 为了找到真正的兄弟结点，左旋过后，兄弟结点sib一定为黑色
                if (colorOf(sib) == RED) {
                    leftRotate(parentOf(del), sib);
                    sib = rightOf(parentOf(del));
                }
                // 兄弟结点是2-结点
                if (colorOf(sib.left) == BLACK && colorOf(sib.right) == BLACK) {
                    setColor(sib, RED);
                    setColor(parentOf(del), BLACK);
                    del = parentOf(del);
                }  else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rightRotate(leftOf(sib), sib);
                        sib = rightOf(parentOf(del));
                    }
                    // sib的颜色设置为parentOf(del)的颜色，不要忘记
                    setColor(sib, colorOf(parentOf(del)));
                    setColor(parentOf(del), BLACK);
                    setColor(rightOf(sib), BLACK);
                    leftRotate(parentOf(del), sib);
                }
            } else {
                Entry<K,V> sib = leftOf(parentOf(del));
                // 为了找到真正的兄弟结点，左旋过后，兄弟结点sib一定为黑色
                if (colorOf(sib) == RED) {
                    rightRotate(sib, parentOf(del));
                    sib = leftOf(parentOf(del));
                }
                // 兄弟结点是2-结点
                // 要使用leftOf和rightOf，而不能使用sib.left和sib.right
                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    setColor(parentOf(del), BLACK);
                    del = parentOf(del);
                }  else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        leftRotate(sib, rightOf(sib));
                        sib = leftOf(parentOf(del));
                    }
                    // sib的颜色设置为parentOf(del)的颜色，不要忘记
                    setColor(sib, colorOf(parentOf(del)));
                    setColor(parentOf(del), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rightRotate(sib, parentOf(del));
                }
            }
        }
        setColor(del, BLACK);
    }

    public Entry<K,V> successor(Entry<K,V> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            Entry<K,V> succ = node.right;
            while (succ.left != null) {
                succ = succ.left;
            }
            return succ;
        }
        while (node == rightOf(parentOf(node))) {
            node = parentOf(node);
        }
        return parentOf(node);
    }


    private void leftRotate(Entry<K, V> x, Entry<K, V> y) {
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }


        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        // 少了x y这一对关系
        x.parent = y;
        y.left = x;
    }

    private void rightRotate(Entry<K, V> x, Entry<K, V> y) {
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }


        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        // 少了x y这一对关系
        x.right = y;
        y.parent = x;
    }

    private Entry<K, V> parentOf(Entry<K, V> node) {
        return node == null ? null : node.parent;
    }

    private Entry<K, V> leftOf(Entry<K, V> node) {
        return node == null ? null : node.left;
    }

    private Entry<K, V> rightOf(Entry<K, V> node) {
        return node == null ? null : node.right;
    }

    private boolean colorOf(Entry<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(Entry<K, V> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    static final class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;
        boolean color = BLACK;

        public Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<K, V> e = (Map.Entry<K, V>) obj;
            return Objects.equals(e.getKey(), key) && Objects.equals(e.getValue(), value);
        }

        @Override
        public int hashCode() {
            int keyHash = key == null ? 0 : key.hashCode();
            int valueHash = value == null ? 0 : value.hashCode();
            return keyHash ^ valueHash;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        String[] strings = {"It's", "my", "pleasure", "implementation", "guaranteed", "log(n)"};
        for(String str : strings) {
            map.put(str, str.length());
        }
        System.out.println(map.getEntry("my"));
        System.out.println(map.getEntry("It's"));
        System.out.println(map.getEntry("pleasure"));
        System.out.println();

        System.out.println(map.remove("pleasure"));
        System.out.println(map.getEntry("my"));
        System.out.println(map.getEntry("It's"));
        System.out.println(map.getEntry("pleasure"));
        System.out.println(map.getEntry("guaranteed"));
        System.out.println();

        System.out.println(map.remove("my"));
        System.out.println(map.getEntry("my"));
        System.out.println(map.getEntry("It's"));
        System.out.println(map.getEntry("pleasure"));
        System.out.println(map.getEntry("implementation"));
        System.out.println();

        System.out.println(map.remove("It's"));
        System.out.println(map.getEntry("my"));
        System.out.println(map.getEntry("It's"));
        System.out.println(map.getEntry("pleasure"));
        System.out.println(map.getEntry("log(n)"));
        System.out.println();


    }

}
