/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题48：最长不含重复字符的子字符串
 * // 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子
 * // 字符串的长度。假设字符串中只包含从'a'到'z'的字符。
 */
package algorithm.dynamicprogramming.offer;

public class Java48_LongestSubstringWithoutDup  {
    //动态规划
    //dp[i]表示以下标为i的字符结尾不包含重复字符的最长子字符串长度
    public static int longestSubstringWithoutDup(String str){
        if(str == null || str.length()== 0) {
            throw new IllegalArgumentException();
        }
        //dp数组可以省略，因为只需记录前一位置的dp值即可
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int maxdp = 1;
        for(int dpIndex = 1; dpIndex < dp.length; dpIndex++){
            //i最终会停在重复字符或者-1的位置,要注意i的结束条件
            int i = dpIndex - 1;
            for(;i >= dpIndex - dp[dpIndex - 1];i--){
                if(str.charAt(dpIndex) == str.charAt(i))
                    break;
            }
            dp[dpIndex] =  dpIndex - i;
            if(dp[dpIndex] > maxdp)
                maxdp = dp[dpIndex];
        }
        return maxdp;
    }

    public static void main(String[] args) {
        System.out.println("value-" + longestSubstringWithoutDup("abc") + "; target-abc");
        System.out.println("value-" + longestSubstringWithoutDup("abcbc") + "; target-abc");
        System.out.println("value-" + longestSubstringWithoutDup("aabcdff") + "; target-abcdf");
    }
}
