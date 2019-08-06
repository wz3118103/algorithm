/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题50（一）：字符串中第一个只出现一次的字符
 * // 题目：在字符串中找出第一个只出现一次的字符。如输入"abaccdeff"，则输出
 * // 'b'。
 */
package datastructure.string.offer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Java50_01_FirstNotRepeatingChar {

    public static char first(String str) {
        if (str == null || str.length() <= 0) {
            throw new IllegalArgumentException();
        }

        //统计字符串的个数，注意用LinkedHashMap
        LinkedHashMap<Character, Integer> lhm = new LinkedHashMap<Character, Integer>();
        for (char c : str.toCharArray()) {
            if (lhm.containsKey(c)) {
                lhm.put(c, lhm.get(c) + 1);
            } else {
                lhm.put(c, 1);
            }
        }

        //遍历map,寻找第一个只出现一次的数
        char result = 0;
        Set<Map.Entry<Character, Integer>> set = lhm.entrySet();
        for (Map.Entry<Character, Integer> es : set) {
            char key = es.getKey();
            int value = es.getValue();
            if (value == 1) {
                result = key;
                break;
            }
        }

        return result;


    }

    public static void main(String[] args) {
        System.out.println("value-" + first("abaccdeff") + "; target-b");
        System.out.println("value-" + first("aabbvf") + "; target-v");
    }
}
