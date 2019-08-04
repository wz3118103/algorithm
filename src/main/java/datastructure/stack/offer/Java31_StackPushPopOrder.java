/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题31：栈的压入、弹出序列
 * // 题目：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是
 * // 否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1、2、3、4、
 * // 5是某栈的压栈序列，序列4、5、3、2、1是该压栈序列对应的一个弹出序列，但
 * // 4、3、5、1、2就不可能是该压栈序列的弹出序列。
 */
package datastructure.stack.offer;

import java.util.Stack;

public class Java31_StackPushPopOrder  {
    public static boolean popOrder(int[] push, int[] pop) {
        if (pop!=null&&push!=null&&pop.length!=0){
            int pushPointer = 0;
            int popPointer = 0;

            Stack<Integer> stack = new Stack<>();

            while (popPointer <pop.length){
                while (stack.empty()||stack.peek()!=pop[popPointer]) {
                    if (pushPointer == push.length){
                        break;
                    }
                    stack.push(push[pushPointer]);
                    pushPointer++;
                }
                if (stack.peek()!=pop[popPointer]) {
                    break;
                }
                stack.pop();
                popPointer++;
            }
            return stack.empty() && popPointer == pop.length;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] ints1 = {1, 2, 3, 4, 5};
        int[] ints2 = {4, 5, 3, 2, 1};
        int[] ints3 = {4, 3, 5, 1, 2};
        System.out.println("value-" + popOrder(ints1, ints2) + "; target-true");
        System.out.println("value-" + popOrder(ints1, ints3) + "; target-false");
    }
}
