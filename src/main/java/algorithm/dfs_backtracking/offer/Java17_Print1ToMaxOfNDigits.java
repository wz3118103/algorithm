/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题17：打印1到最大的n位数
 * // 题目：输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则
 * // 打印出1、2、3一直到最大的3位数即999。
 */
package algorithm.dfs_backtracking.offer;

public class Java17_Print1ToMaxOfNDigits {
    public static  void Print1ToMaxOfNDigits_1(int n) {
        if (n <= 0) {
            return;
        }
        char[] num = new char[n];
        for (int i = 0; i < n; ++i) {
            num[i] = '0';
        }
        while (!increment(num)) {
            printNum(num);
        }
    }

    private static boolean increment(char[] num) {
        boolean isOverflow = false;
        int carry = 0;
        int length = num.length;

        for (int i = length - 1; i >= 0; --i) {
            int sum = num[i] - '0' + carry;
            if (i == length - 1) {
                sum++;
            }

            if (sum >= 10) {
                if (i == 0) {
                    isOverflow = true;
                } else {
                    sum -= 10;
                    carry = 1;
                    num[i] = (char)('0' + sum);
                }
            } else {
                num[i] = (char)('0' + sum);
                break;
            }
        }
        return isOverflow;
    }

    public static void print1ToMaxOfNDigits_2(int n) {
        if (n <= 0) {
            return;
        }
        char[] num = new char[n];

        for (int i = 0; i < 10; ++i) {
            num[0] = (char)(i + '0');
            print1ToMaxOfNDigitsRecursively(num, 0);
        }
    }

    private static void print1ToMaxOfNDigitsRecursively(char[] num, int index) {
        if (index == num.length - 1) {
            printNum(num);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            num[index + 1] = (char)(i + '0');
            print1ToMaxOfNDigitsRecursively(num, index + 1 );
        }
    }

    private static void printNum(char[] num) {
        boolean isBegining0 = true;

        for (int i = 0; i < num.length; ++i) {
            if (isBegining0 && num[i] != '0') {
                isBegining0 = false;
            }

            if (!isBegining0) {
                System.out.printf("%c", num[i]);
            }
        }
        System.out.printf("\t");
    }

    public static void main(String[] args) {

    }
}
