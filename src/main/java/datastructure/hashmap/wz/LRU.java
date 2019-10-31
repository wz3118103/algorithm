package datastructure.hashmap.wz;

public class LRU<K,V> extends LinkedHashMap<K,V> {
    private int maxCacheSize;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_MIN_CAPACITY = 32;
    private static final int DEFAULT_CACHE_SIZE = 6;

    public LRU(int initialCapacity, float loadFactor, boolean accessOrder, int maxCacheSize) {
        super(initialCapacity, loadFactor, accessOrder);
        this.maxCacheSize = maxCacheSize;
    }

    public LRU(int maxCacheSize) {
        this(DEFAULT_MIN_CAPACITY, DEFAULT_LOAD_FACTOR, true, maxCacheSize);
    }

    public LRU() {
        this(DEFAULT_CACHE_SIZE);
    }

    @Override
    protected boolean removeEldestEntry() {
        return size() > maxCacheSize;
    }

    public static void main(String[] args) {
        LRU<String, Integer> lru = new LRU<>();

        String[] strings = {"a", "bc", "de", "fe",
                "hi", "dea", "cd", "hello",
                "why", "ok", "change", "the", "world"};
        for (String str : strings) {
            lru.put(str, str.length());
        }
        lru.put(null, null);
        System.out.println(lru);

        System.out.println(lru.put("the", 4));
        System.out.println(lru);

        System.out.println(lru.get("the"));
        System.out.println(lru);

        System.out.println(lru.get(null));
        System.out.println(lru);

        System.out.println(lru.get("why"));
        System.out.println(lru);

        System.out.println(lru.remove("why"));
        System.out.println(lru);

        System.out.println(lru.remove("ok"));
        System.out.println(lru);

        System.out.println(lru.remove("world"));
        System.out.println(lru);
    }
}
