/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题59（二）：队列的最大值
 * // 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，
 * // 如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，那么一共存在6个
 * // 滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}，
 */
package datastructure.queueandheap.offer;

import java.util.ArrayDeque;

public class Java59_02_QueueWithMax {
    public static class QueueWithMax {
        private ArrayDeque<InternalData> data = new ArrayDeque<InternalData>();
        private ArrayDeque<InternalData> maximum = new ArrayDeque<InternalData>();

        private class InternalData {
            int number;
            int index;

            public InternalData(int number, int index) {
                this.number = number;
                this.index = index;
            }
        }

        private int curIndex;

        public void push_back(int number) {
            InternalData curData = new InternalData(number, curIndex);
            data.addLast(curData);

            while (!maximum.isEmpty() && maximum.getLast().number < number) {
                maximum.removeLast();
            }
            maximum.addLast(curData);

            curIndex++;
        }

        public void pop_front() {
            if (data.isEmpty()) {
                System.out.println("队列为空，无法删除！");
                return;
            }
            InternalData curData = data.removeFirst();
            if (curData.index == maximum.getFirst().index) {
                maximum.removeFirst();
            }
        }

        public int max() {
            if (maximum == null) {
                System.out.println("队列为空，无法删除！");
                return 0;
            }
            return maximum.getFirst().number;
        }
    }
}
