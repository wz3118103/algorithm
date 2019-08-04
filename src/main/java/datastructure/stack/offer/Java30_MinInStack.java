/**
 * //==================================================================
 * // ����ָOffer�����������Թپ������ͱ���⡷����
 * // ���ߣ��κ���
 * //==================================================================
 * <p>
 * // ������30������min������ջ
 * // ��Ŀ������ջ�����ݽṹ�����ڸ�������ʵ��һ���ܹ��õ�ջ����СԪ�ص�min
 * // �������ڸ�ջ�У�����min��push��pop��ʱ�临�Ӷȶ���O(1)��
 */
package datastructure.stack.offer;

public class Java30_MinInStack  {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        System.out.println("value-" + myStack.min() + "; target-1");
        System.out.println("value-" + myStack.pop() + "; target-1");
        myStack.push(2);
        myStack.push(3);
        System.out.println("value-" + myStack.min() + "; target-2");
        myStack.pop();
        System.out.println("value-" + myStack.min() + "; target-2");
    }

    //实现下面3个函数
    static class MyStack {
        public void push(int i) {
        }

        public int pop() {
            return 0;
        }

        public int min() {
            return -1;
        }

    }
}
