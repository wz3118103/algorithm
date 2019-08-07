/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题66：构建乘积数组
 * // 题目：给定一个数组A[0, 1, …, n-1]，请构建一个数组B[0, 1, …, n-1]，其
 * // 中B中的元素B[i] =A[0]×A[1]×… ×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 */
package algorithm.math.others.offer;

public class Java66_ConstuctArray {
    public static int[] multiply(int[] A) {
        if(A == null || A.length < 2)
            return null;
        int[] B = new int[A.length];
        B[0] = 1;
        for(int i = 1; i < A.length; i++)
            B[i] = B[i - 1] * A[i - 1];
        int temp = 1;
        for(int i = A.length - 2; i >= 0; i--){
            temp *= A[i + 1];
            B[i] *= temp;
        }
        return B;
    }
}
