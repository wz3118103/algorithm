/**
 * //==================================================================
 * // 《剑指Offer——名企面试官精讲典型编程题》代码
 * //
 * //==================================================================
 * <p>
 * // 面试题19：正则表达式匹配
 * // 题目：请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'
 * // 表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题
 * // 中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"
 * // 和"ab*ac*a"匹配，但与"aa.a"及"ab*a"均不匹配。
 */
package datastructure.string.offer;

import org.junit.Assert;
import org.junit.Test;

public class Java19_RegularExpressionsMatching {
    private boolean match(String reg, String text) {

        return text != null && reg != null && matchCore(reg, text);

    }

    private boolean matchCore(String reg, String text) {
        if (reg.length()==0&&text.length()==0) {
            return true;
        }
        if (text.length() == 0&&reg.length()!=0){
            return false;
        }
        return false;

    }

    @Test
    public void test() throws Exception {
        Assert.assertTrue(match("aaa", "a.a"));
        Assert.assertTrue(match("aaa", "ab*ac*a"));
        Assert.assertFalse(match("aaa", "aa.a"));
        Assert.assertFalse(match("aaa", "ab*a"));
    }
}
