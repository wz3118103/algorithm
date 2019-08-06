/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题59（一）：滑动窗口的最大值
 * // 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，
 * // 如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，那么一共存在6个
 * // 滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}，
 */
package datastructure.queueandheap.offer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Java59_01_MaxInSlidingWindow {

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if(num == null || num.length == 0||size == 0||size > num.length){
            return result;
        }
        // 此队列装的是数组的下标，为了判断最大的是不是还在滑动窗口中
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i < num.length; i++){
            if(!queue.isEmpty()){
                //queue.peek()获取但不移除此列表的头
                if(i >= queue.peek() + size){
                    //获取并移除第一个元素
                    queue.pop();
                }
                //queue.getLast()返回此列表的最后一个值
                while(!queue.isEmpty() && num[i] >= num[queue.getLast()]){
                    //移除并返回queue的最后一个元素
                    queue.removeLast();
                }
            }
            //入队列，将指定元素添加到队列
            queue.offer(i);
            if(i + 1 >= size){
                result.add(num[queue.peek()]);
            }
        }
        return result;
    }
}
