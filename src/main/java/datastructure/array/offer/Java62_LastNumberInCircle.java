/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题62：圆圈中最后剩下的数字
 * // 题目：0, 1, …, n-1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈里
 * // 删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 */
package datastructure.array.offer;

import java.util.LinkedList;

public class Java62_LastNumberInCircle {
    /*
     * 方法一：采用推导出来的方法
     */
    public int LastRemaining_Solution(int n, int m) {
        if(n<1 || m<1)
            return -1; //出错
        int last = 0;
        for(int i = 2; i <= n; i++){
            last = (last + m) % i;  //这里是i不是n！！！
        }
        return last;
    }

    /*
     * 方法二：采用链表来存放，每次对长度取余来实现循环
     */
    public int LastRemaining_Solution2(int n, int m) {
        if(n < 1 || m < 1)
            return -1; //出错
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < n;i++)
            list.add(i);
        int removeIndex = 0;
        while(list.size() > 1){
            removeIndex = (removeIndex + m - 1) % list.size();
            list.remove(removeIndex);
        }
        return list.getFirst();
    }
}
