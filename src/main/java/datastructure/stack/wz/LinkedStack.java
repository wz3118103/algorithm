package datastructure.stack.wz;

import java.util.LinkedList;
import java.util.List;

/**
 * 注意事项：
 * 1.pop时，需要注意特殊情况，当栈为空时的特殊处理
 * 2.完成每一个操作时，需要完整实现其所需功能，因此最好列出其完整的步骤
 */

public class LinkedStack<E> {
    private List<E> stack = new LinkedList<>();

    public LinkedStack() {
    }

    public void push(E e) {
        stack.add(0, e);
    }

    public E pop() {
        if (stack.size() <= 0) {
            throw new IllegalStateException("stack is empty!");
        }
        E ret = stack.get(0);
        stack.remove(0);
        return ret;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        stack.pop();
    }
}
