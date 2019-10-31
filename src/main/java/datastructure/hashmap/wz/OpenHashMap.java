package datastructure.hashmap.wz;

import java.util.Map;
import java.util.Objects;

public class OpenHashMap<K,V> {
    private Node<K,V>[] table;
    private int size;
    private int threshold;
    private float loadFactor = 0.5f;

    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int MIN_CAPACITY = 32;
    private static final int MAX_CAPACITY = 1 << 30;

    public OpenHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public OpenHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public OpenHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0 || loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException();
        }

        if (initialCapacity > MAX_CAPACITY) {
            initialCapacity = MAX_CAPACITY;
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    private int tableSizeFor(int initialCapacity) {
        int n = initialCapacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? MIN_CAPACITY : n > MAX_CAPACITY ? MAX_CAPACITY : n + 1;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value);
    }

    private V putVal(int hash, K key, V value) {
        // 数组table为空

        int i;
        Node<K,V> newNode = new Node<>(hash, key, value);
        if (table == null || table.length == 0) {
            table = resize();
            table[hash & (table.length - 1)] = newNode;
        } else if (table[i = (hash & (table.length - 1))] == null) {
            // slot为空
            table[i] = newNode;
        } else {
            // 冲突时处理，向后挨个检查
            for (Node<K,V> node; (node = table[i]) != null; i = nextKeyIndex(i, table.length)) {
                if (node.hash == hash && ((node.key == key) || Objects.equals(key, node.key))) {
                    return node.setValue(value);
                }
            }
            table[i] = newNode;
        }

        // 不要忘了更新size，并判断是否扩容
        size++;
        if (size > threshold) {
            resize();
        }
        return null;
    }

    public V get(K key) {
        Node<K,V> node;
        return (node = getEntry(hash(key), key)) == null ? null : node.value;
    }

    private Node<K,V> getEntry(int hash, K key) {
        int index = hash & (table.length - 1);
        Node<K,V> node = table[index];
        if (node == null) {
            return null;
        }
        for (; (node = table[index]) != null; index = nextKeyIndex(index, table.length)) {
            if (node.hash == hash && (node.key == key || Objects.equals(key, node.key))) {
                return node;
            }
        }
        return null;
    }


    public V remove(Object key) {
        System.out.println("delete: " + key);
        Node<K,V> node;
        return (node = removeNode(hash(key), key)) == null ? null : node.value;
    }

    private Node<K,V> removeNode(int hash, Object key) {
        int i = hash & (table.length - 1);
        // 忘了table为空的判断
        if (table == null || table.length == 0) {
            return null;
        }

        while (true) {
            Node<K,V> p = table[i];
            if (p == null) {
                return null;
            }
            if (p.hash == hash && (p.key == key || Objects.equals(key, p.key))) {
                table[i] = null;
                size--;
                closeDeletion2(i);
                return p;
            }
            i = nextKeyIndex(i, table.length);
        }
    }

    private void closeDeletion(int d) {
        Node<K,V> node;
        // 通用的方法：对从d位置通过nextKeyIndex到下一个为空的slot之间碰撞的entry进行重新hash
        // 这里的处理时采用了线性探测的特殊方法
        for (int i = nextKeyIndex(d, table.length); (node = table[i]) != null; i = nextKeyIndex(i, table.length) ) {
            int r = hash(node.key) & (table.length - 1);
            // d是被删除的地方；r是在没有冲突下应该被存储的地方
            // 如果在循环数组中，r位于d前面，最后实际存储在d后面，就要考虑前移
            // 三种情况：r-d-i i-r-d d-i-r
            if ((i < r && (r <= d || d <= i)) || (r <= d && d <= i)) {
                table[d] = node;
                table[d + 1] = null;
                d = i;
            }
        }
    }

    private void closeDeletion2(int d) {
        Node<K,V> node;
        // 通用的方法：对从d位置通过nextKeyIndex到下一个为空的slot之间碰撞的entry进行重新hash
        // 对d的后继位置进行重新hash，直至为null
        for (int i = nextKeyIndex(d, table.length); (node = table[i]) != null; i = nextKeyIndex(i, table.length) ) {
            int r = hash(node.key) & (table.length - 1);
            if (r != i) {
                table[i] = null;

                // Unlike Knuth 6.4 Algorithm R, we must scan until
                // null because multiple entries could have been stale.
                while (table[r] != null)
                    r = nextKeyIndex(r, table.length);
                table[r] = node;
            }
        }
    }


    private Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = table == null ? 0 : table.length;
        int oldThr = threshold;
        int newCap;
        int newThr = 0;
        if (oldCap > 0) {
            // 注意数组大小过大时的处理
            if (oldCap >= MAX_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 正常情况的处理，扩大为原来的两倍
            if ((newCap = oldCap << 1) < MAX_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            // 此时数组并未初始化，初始大小放在threshold中
            newCap = oldThr;
        } else {
            // 数组未初始化，也没有初始大小，使用默认值
            newCap = MIN_CAPACITY;
            newThr = (int) (newCap * loadFactor);
        }
        if (newThr == 0) {
            newThr = (int)(newCap * loadFactor);
            newThr = (newCap < MAX_CAPACITY && newThr < MAX_CAPACITY) ? newThr : Integer.MAX_VALUE;
        }
        threshold = newThr;
        Node<K,V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        // 如果旧表为null，直接返回新表
        if (oldTab == null) {
            return newTab;
        }
        // 将旧表中数据移动到新表中
        for (int i = 0; i < oldCap; i++) {
            Node<K,V> e;
            if ((e = oldTab[i]) == null) {
                continue;
            }
            // help GC，不要忘了
            oldTab[i] = null;
            int newIndex = i;
            if ((e.hash & oldCap) == 1) {
                newIndex = i + oldCap;
            }
            while (newTab[newIndex] != null) {
               newIndex = nextKeyIndex(newIndex, newCap);
            }
            newTab[newIndex] = e;
        }
        return newTab;
    }

    private static int nextKeyIndex(int i, int len) {
        return (i + 1 < len ? i + 1 : 0);
    }

    private int hash(Object key) {
        // key可以为null，所以需要使用Objects.hashCode方法
        int hash = Objects.hashCode(key);
        return key == null ? 0 : hash ^ (hash >>> 16);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                builder.append(table[i] + " ");
            }
        }
        builder.append("\n");
        return builder.toString();
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        private final int hash;
        private final K key;
        private V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
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
            if (obj == this) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry<K,V> o = (Map.Entry<K, V>) obj;
                return Objects.equals(o.getKey(), key) && Objects.equals(o.getValue(), value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
    public static void main(String[] args) {
        OpenHashMap<String, Integer> map = new OpenHashMap<>();
        String[] strings = {"a", "bc", "de", "fe",
                "hi", "dea", "cd", "hello",
                "why", "ok", "change", "the", "world"};
        for (String str : strings) {
            map.put(str, str.length());
        }
        map.put(null, null);
        System.out.println(map);

        System.out.println(map.remove("hi"));
        System.out.println(map);
        System.out.println(map.remove("cd"));
        System.out.println(map);
        System.out.println(map.remove("ok"));
        System.out.println(map);
        System.out.println(map.remove("fe"));
        System.out.println(map);
        System.out.println(map.remove(null));
        System.out.println(map);
    }
}
