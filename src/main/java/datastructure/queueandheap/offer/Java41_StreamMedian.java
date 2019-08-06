/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题41：数据流中的中位数
 * // 题目：如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么
 * // 中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * // 那么中位数就是所有数值排序之后中间两个数的平均值。
 */
package datastructure.queueandheap.offer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Java41_StreamMedian  {
    //生成最大堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11,new maxHeapComparator());
    //生成最小堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(11,new minHeapComparator());

    int maxSize = 0;//初始化堆的大小，防止NullPointerException
    int minSize = 0;

    public void reshapeHeapSize(){//平衡两堆元素的数目
        if(this.maxHeap.size() == this.minHeap.size() + 2){
            this.minHeap.add(this.maxHeap.poll());
        }
        if(this.minHeap.size() == this.maxHeap.size() + 2){
            this.maxHeap.add(this.minHeap.poll());
        }
    }

    public void insert(Integer num) {

        if(this.maxHeap.isEmpty()){
            this.maxHeap.add(num);
        }else{
            if(this.maxHeap.peek() > num){
                this.maxHeap.add(num);
            }else{
                if(this.minHeap.isEmpty()){
                    this.minHeap.add(num);
                }else{
                    if(this.minHeap.peek() <= num){
                        this.minHeap.add(num);
                    }else{
                        this.maxHeap.add(num);
                    }
                }
            }
        }
        reshapeHeapSize();
    }

    public Double getMedian() {
        if(!maxHeap.isEmpty()){
            maxSize = this.maxHeap.size();
        }
        if(!minHeap.isEmpty()){
            minSize = this.minHeap.size();
        }
        if(maxSize+minSize == 0){
            throw new RuntimeException();
        }
        Integer maxHeapNum = this.maxHeap.peek();
        Integer minHeapNum = this.minHeap.peek();
        if(((maxSize + minSize) % 2) == 0){
            return (double)((maxHeapNum+minHeapNum))/2;
        }else{
            return (double)(maxSize > minSize ? maxHeapNum : minHeapNum);
        }
    }

    public class minHeapComparator implements Comparator<Integer>{
        public int compare(Integer o1,Integer o2){
            return o1-o2;
        }
    }
    public class maxHeapComparator implements Comparator<Integer> {
        public int compare(Integer o1,Integer o2){
            return o2-o1;
        }
    }


    public static void main(String[] args) {
    }
}
