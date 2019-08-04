/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题9：用两个栈实现队列
 * // 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail
 * // 和deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。
 */
package datastructure.stack.offer;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class Java09_QueueWithTwoStacks {
    public static  Queue<Integer> getQuene() {
        Queue<Integer> queue = new MyQuene<>();
        //实现下面那个函数
//        queue.poll();
//        queue.offer(1);
        return queue;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = getQuene();
        for (int i = 0; i < 1000; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println("value-" + queue.poll().intValue() + "; target-" + i);
        }

    }

    static class MyQuene<T> extends AbstractQueue<T> {

        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();

        @Override
        public Iterator<T> iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        //需要实现-----------------------
        @Override
        public boolean offer(T t) {
            return false;
        }

        //需要实现-----------------------
        @Override
        public T poll() {
            return null;
        }

        @Override
        public T peek() {
            return null;
        }
    }
}