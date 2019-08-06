/**
 // 面试题30：包含min函数的栈
 // 题目：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min
 // 函数。在该栈中，调用min、push及pop的时间复杂度都是O(1)。
 */
package datastructure.stack.offer;

import java.util.ArrayDeque;

public class Java30_MinInStack  {


    static class  StackWithMin<T extends Comparable<T>> {
        private ArrayDeque<T> data = new ArrayDeque<>();
        private ArrayDeque<T> min = new ArrayDeque<>();

        public void push(T value) {
            data.push(value);
            if (min.size() == 0 || value.compareTo(min.peek()) < 0) {
                min.push(value);
            } else {
                min.push(min.peek());
            }
        }

        public void pop() {
            if (data.size() <= 0 && min.size() <= 0) {
                throw new IllegalStateException();
            }
            data.pop();
            min.pop();
        }

        public T min() {
            if (data.size() <= 0 && min.size() <= 0) {
                throw new IllegalStateException();
            }
            return min.peek();
        }

    }
}
