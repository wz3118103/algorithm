/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题60：n个骰子的点数
 * // 题目：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s
 * // 的所有可能的值出现的概率。
 */
package algorithm.math.random.offer;

import java.util.Map;

public class Java60_DicesProbability {
    // key is sum, v is pribity
    public static  Map<Integer, Double> pribility(int n) {
        return null;
    }

    public static void main(String[] args) {
        Map<Integer, Double> map = pribility(1);
        System.out.println("value-" + map.containsKey(6) + "; target-true");
        System.out.println("value-" + map.get(6) == 1.0 / 6 + "; target-true");
        map = pribility(2);
        System.out.println("value-" + map.containsKey(2) + "; target-true");
        System.out.println("value-" + map.get(2) == 1.0 / 36 + "; target-true");
    }
}
