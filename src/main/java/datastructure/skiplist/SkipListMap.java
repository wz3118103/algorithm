package datastructure.skiplist;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SkipListMap<K, V> {
    private HeadIndex<K, V> head;
    private final Comparator<? super K> comparator;

    private static final Object BASE_HEADER = new Object();

    public SkipListMap() {
        this(null);
    }

    public SkipListMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
        this.head = new HeadIndex<>(new Node<>(null, BASE_HEADER, null),
                null, null, 1);
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        // step1.插入到数据层Node的正确位置

        // b: 插入节点前驱节点
        // n：插入节点的后继结点
        Node<K, V> b = findPredecessor(key), n = b.next;
        for (; n != null ; b = n, n = n.next) {
            int c = cpr(comparator, key, n.key);
            if (c == 0) {
                return n.setValue(value);
            } else if (c < 0){
                break;
            }
        }
        Node<K,V> newNode = new Node<>(key, value, n);
        b.next = newNode;

        // step2.建立随机索引层Index
        // 1）获取随机层数
        // 随机概率：一层的概率1/2；二层的概率1/4；三层的概率1/8
        int rnd = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        if ((rnd & 0x1) == 0) {
            return null;
        }
        // 就像掷硬币，为1，继续查看下一个，否则中断
        int level = 1;
        // 这个地方经常出错，将>>>=写成>>>
        while (((rnd >>>= 1) & 1) != 0) {
            ++level;
        }
        // 2）如果level <= max
        int max = head.level;
        Index<K,V> idx = null;
        // 记录原来的head，并为后面在每层插入index服务
        Index<K,V> h = head;
        if (level <= max) {
            for (int i = 1; i <= level; i++) {
                // 从第一层建立到level层的down链表
                idx = new Index<>(newNode, idx, null);
            }
            // 当level小于max时，需要将h降低到与level层级
            // 后面程序的假设来自于此！非常重要，涉及到跳表链表层次结构的正确性
            while (++level <= max) {
                h = h.down;
            }
        } else {
            // 3）如果level > max，head要增加一层，并更新head
            level = max + 1;
            for (int i = 1; i <= level; i++) {
                // 从第一层建立到level层的down链表
                idx = new Index<>(newNode, idx, null);
            }
            head = new HeadIndex<>(head.node, head, idx, level);
            // 记录后面待插入索引的最高层级
            idx = idx.down;
        }
        // 4）将新增索引层插入到每一层索引的指定位置（前驱q和后继r之间）
        // 同理，外层循环遍历每一层，内层循环找到插入的位置
        for (Index<K,V> q = h; q != null && idx != null; idx = idx.down, q = q.down) {
            Index<K,V> r = q.right;
            for (; (r != null) && cpr(comparator, key, r.node.key) > 0; q = r, r = r.right) {
            }
            q.right = idx;
            idx.right = r;
        }

        return null;
    }

    public V remove(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }

        // step1.在数据层找到Node，删除
        for (Node<K,V> p = findPredecessor(key), r = p.next; r != null; p = r, r = r.next) {
            int c = cpr(comparator, key, r.key);
            if (c < 0) {
                break;
            } else if (c > 0) {
                continue;
            }
            p.next = r.next;
            r.next = null;

            // step2.索引层删除
            // 同理两层循环：外层遍历每一层，内层在某一层删除node对应的索引
            for (Index<K,V> prev = head; prev != null; prev = prev.down) {
                // 这一句不能放在for循环语句中，只能放在这里，因为prev可能为null，所以只有检查完之后才能赋值
                Index<K,V>  right = prev.right;
                for (; right != null; prev = right, right = prev.right) {
                    if (cpr(comparator, r.key, right.node.key) < 0) {
                        break;
                    } else if (right.node == r) {
                        prev.right = right.right;
                        right.right = null;
                        break;
                    }
                }
            }
            // step3.head层可能要压缩
            if (head.right == null) {
                tryReduceLevel();
            }
            return (V)r.value;
        }
        return null;
    }

    private void tryReduceLevel() {
        HeadIndex<K,V> h = head;
        HeadIndex<K,V> d;
        HeadIndex<K,V> e;
        if (h.level > 3 &&
                (d = (HeadIndex<K,V>)h.down) != null &&
                (e = (HeadIndex<K,V>)d.down) != null &&
                e.right == null &&
                d.right == null &&
                h.right == null) {
            head = d;
        }

    }

    private Node<K, V> findPredecessor(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }
        // 外层循环：按层处理；内层循环：在该层找到前驱和后继
        // 直到最后一层，最后一层的特征是q.down为null
        for (Index<K, V> q = head, r = q.right, d; ; q = d, r = q.right) {
            while (r != null && cpr(comparator, key, r.node.key) > 0) {
                q = r;
                r = r.right;
            }

            if ((d = q.down) == null) {
                return q.node;
            }
        }
    }

    private static int cpr(Comparator comparator, Object x, Object y) {
        return comparator != null ? comparator.compare(x, y) : ((Comparable) x).compareTo(y);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<K,V> node = head.node.next; node != null; node = node.next) {
            builder.append(node + " ");
        }
        builder.append("\n");

        return builder.toString();
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        final K key;
        Object value;
        Node<K, V> next;

        public Node(K key, Object value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            if (value == BASE_HEADER) {
                return null;
            }
            return (V) value;
        }

        @Override
        public V setValue(V value) {
            V old = (V) this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    static class Index<K, V> {
        final Node<K, V> node;
        final Index<K, V> down;
        Index<K, V> right;

        public Index(Node<K, V> node, Index<K, V> down, Index<K, V> right) {
            this.node = node;
            this.down = down;
            this.right = right;
        }
    }

    static class HeadIndex<K, V> extends Index<K, V> {
        final int level;

        public HeadIndex(Node<K, V> node, Index<K, V> down, Index<K, V> right, int level) {
            super(node, down, right);
            this.level = level;
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, Integer> map = new SkipListMap<>();
        String[] strings = {"a", "bc", "de", "fe",
                "hi", "dea", "cd", "hello",
                "why", "ok", "change", "the", "world"};
        for (String str : strings) {
            map.put(str, str.length());
        }
        System.out.println(map);

        System.out.println(map.remove("bc"));
        System.out.println(map);

        System.out.println(map.remove("a"));
        System.out.println(map);

        System.out.println(map.remove("world"));
        System.out.println(map);

        System.out.println(map.remove("fe"));
        System.out.println(map);
    }
}
