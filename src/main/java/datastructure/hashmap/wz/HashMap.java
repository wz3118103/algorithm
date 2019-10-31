package datastructure.hashmap.wz;

import java.util.Map;
import java.util.Objects;

public class HashMap<K,V> {
    Node<K,V>[] table;
    private int size;
    private int threshold;
    private float loadFactor;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAX_CAPACITY = 1 << 30;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0 || loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException();
        }

        if (initialCapacity > MAX_CAPACITY) {
            initialCapacity = MAX_CAPACITY;
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    private int tableSizeFor(int initialCapacity) {
        int n = initialCapacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n > MAX_CAPACITY ? MAX_CAPACITY : n + 1;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value);
    }

    private V putVal(int hash, K key, V value) {
        // 数组table为空
        Node<K,V> p;
        int i;
        if (table == null || table.length == 0) {
            table = resize();
            table[hash & (table.length - 1)] = new Node<>(hash, key, value, null);
        } else if ((p = table[i = (hash & (table.length - 1))]) == null) {
            // slot为空
            table[i] = new Node<>(hash, key, value, null);
        } else {
            // 遍历链表，查看是否有key，如果有则更新value；否则放到链表尾部
            Node<K,V> node;
            if (p.hash == hash && ((p.key == key) || Objects.equals(key, p.key))) {
                node = p;
            }  else {
                node = p.next;
                do{
                    // 到了链表末尾，同时是首先检测node是否为null
                    if (node == null) {
                        p.next = new Node<>(hash, key, value, null);
                        break;
                    }
                    // 这里需要node不为null
                    if (node.hash == hash && ((node.key == key) || Objects.equals(key, node.key))) {
                        break;
                    }
                    p = node;
                    node = node.next;
                } while (p != null); // 此处之前写的是node != null，导致无法在链表末尾插入
            }
            if (node != null) {
                V old = p.value;
                p.value = value;
                return old;
            }
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

    public V remove(Object key) {
        Node<K,V> node;
        return (node = removeNode(hash(key), key)) == null ? null : node.value;
    }

    private Node<K,V> removeNode(int hash, Object key) {
        Node<K,V> p;
        int i;
        // 忘了table为空的判断
        if (table == null || table.length == 0 || (p = table[i = (hash & (table.length - 1))]) == null) {
            return null;
        }
        // 第一次编写时，这种情况忘了
        if (p.hash == hash && (p.key == key || Objects.equals(key, p.key))) {
            table[i] = p.next;
            // 忘了更新size
            --size;
            return p;
        }
        Node node;
        while ((node = p.next) != null) {
            if (node.hash == hash && (node.key == key || Objects.equals(key, node.key))) {
                p.next = node.next;
                node.next = null;
                // 忘了更新size
                --size;
                return node;
            }
            p = node;
        }
        return null;
    }

    private Node<K,V> getEntry(int hash, K key) {
        Node<K,V> node;
        if ((node = table[hash & (table.length - 1)]) == null) {
            return null;
        }
        do {
            if (node.hash == hash && (node.key == key || Objects.equals(key, node.key))) {
                return node;
            }
        }while ((node = node.next) != null);
        return null;
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
            newCap = DEFAULT_INITIAL_CAPACITY;
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
            // 特殊情况，只有一个元素
            if (e.next == null) {
                newTab[e.hash & (newCap - 1)] = e;
            } else {
                // tail用于方便在末尾置null
                Node<K,V> loHead = null, loTail = null;
                Node<K,V> hiHead = null, hiTail = null;
                // 遍历链表，而且链表至少有两个元素
                Node<K,V> next;
                do {
                    next = e.next;
                    if ((e.hash & oldCap) == 1) {
                        if (hiHead != null) {
                            hiTail.next = e;
                            hiTail = e;
                        } else {
                            hiHead = hiTail = e;
                        }
                    } else {
                        if (loHead != null) {
                            loTail.next = e;
                            loTail = e;
                        } else {
                            loHead = loTail = e;
                        }
                    }
                }while ((e = next) != null);

                if (loTail != null) {
                    loTail.next = null;
                    newTab[i] = loHead;
                }
                if (hiTail != null) {
                    hiTail.next = null;
                    newTab[i + oldCap] = hiHead;
                }
            }

        }
        return newTab;
    }

    private int hash(Object key) {
        int hash = key.hashCode();
        return key == null ? 0 : hash ^ (hash >>> 16);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                builder.append(i + ": ");
                Node<K,V> node = table[i];
                while (node != null) {
                    builder.append(node + " ");
                    node = node.next;
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
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
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry<?,?> o = (Map.Entry<?,?>)obj;
                return Objects.equals(o.getKey(), key) && Objects.equals(o.getValue(), value);
            }
            return false;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] strings = {"a", "bc", "de", "fe",
                "hi", "dea", "cd", "hello",
                "why", "ok", "change", "the", "world"};
        for (String str : strings) {
            map.put(str, str.length());
        }
        System.out.println(map);

        System.out.println(map.remove("hi"));
        System.out.println(map);
        System.out.println(map.remove("cd"));
        System.out.println(map);
        System.out.println(map.remove("ok"));
        System.out.println(map);
        System.out.println(map.remove("fe"));
        System.out.println(map);
    }
}
