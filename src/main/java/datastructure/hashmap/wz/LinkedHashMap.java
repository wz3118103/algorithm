package datastructure.hashmap.wz;

/**
 * 主要是维护双链表
 * 1）默认情况，按插入顺序
 * 2）缓存LRU，修改如下方法
 *   get(key)：将访问的结点放到链表末尾，因为最新的总是放到末尾
 *   put(K,V)：更新时，同get()；插入时，需要将其插入到链表末尾；并且引入缓存机制，是否需要删除最老的结点（链表头）
 *   remove(key)：删除时，需要同步从链表中删除
 * @param <K>
 * @param <V>
 */
public class LinkedHashMap<K,V> extends HashMap<K,V> {
    private Entry<K,V> head;
    private Entry<K,V> tail;
    // 是否作为LRU缓存，若为true，则按缓存机制运作；若为false，则按插入顺序运作
    private final boolean accessOrder;

    public LinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.accessOrder = false;
    }

    public LinkedHashMap(int initialCapacity) {
        super(initialCapacity);
        this.accessOrder = false;
    }

    public LinkedHashMap() {
        this.accessOrder = false;
    }

    public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
    }

    public V get(Object key) {
        Node<K,V> node = getEntry(hash(key), key);
        if (node == null) {
            return null;
        }
        // 第一次编写时少了accessOrder判断
        if (accessOrder) {
            afterNodeAccess(node);
        }
        return node.value;
    }

    /**
     * 将p从链表中断开，并将其链接到尾部
     * @param p
     */
    @Override
    protected void afterNodeAccess(Node<K,V> p) {
        Entry<K,V> e = (Entry<K,V>)p;
        if (!accessOrder || e == tail) {
            return;
        }
        Entry<K,V> b = e.prev, n = e.next;
        // e.next不要忘了，否则会出现链接异常
        e.next = null;
        if (b != null) {
            b.next = n;
        } else {
            head = n;
        }
        if (n != null) {
            n.prev = b;
        } else {
            tail = b;
        }

        // 只有一个元素时的处理，忘记了
        if (tail == null) {
            head = e;
        } else {
            tail.next = e;
            e.prev = tail;
        }

        tail = e;
    }

    /**
     * 将其链接到链表尾部
     */
    @Override
    protected Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p =
                new LinkedHashMap.Entry<>(hash, key, value, e);
        linkNodeLast(p);
        return p;
    }

    private void linkNodeLast(Entry<K,V> node) {
        if (tail == null) {
            head = tail = node;
        } else {
            // 注意是双链表，不要少更新了某个指针
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    /**
     * 缓存机制LRU
     */
    @Override
    protected void afterNodeInsertion() {
        Entry<K,V> first = head;
        if (accessOrder && first != null && removeEldestEntry()) {
            removeNode(hash(first.key), first.key);
        }
    }

    protected boolean removeEldestEntry() {
        return false;
    }

    /**
     * 涉及更新链表
     * @param p
     */
    @Override
    protected void afterNodeRemoval(Node<K,V> p) {
        Entry<K,V> e = (Entry<K,V>)p;
        Entry<K,V> b = e.prev, n = e.next;

        // 删除处理
        e.prev = null;
        e.next = null;

        if (b != null) {
            b.next = n;
        } else {
            head = n;
        }
        if (n != null) {
            n.prev = b;
        } else {
            tail = b;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Entry<K,V> node = head; node != null; node = node.next) {
            builder.append(node + " ");
        }
        builder.append("\n");
        return builder.toString();
    }

    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> prev;
        Entry<K,V> next;

        public Entry(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }
    }
}
