package datastructure.hashmap.wz;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put(null, null);
        System.out.println(map.containsKey("hello"));
        System.out.println(map.containsKey(null));
    }
}
