package 算法;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author huchonglin
 * @date 2020/12/21 18:28
 */
public class 字符串 {
    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
     * 示例1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
     *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
     *
     * 总结：记录最长无重复的子串的长度
     * 思路：最快的是一次遍历出结果，需要一个动态的双端数据结构实时计算结果，动态滑动窗口，
     * 这题不需要保存字符串，所以滑动窗口可以由两个指针下标实现。只用于计算长度。如果遇到相同的，移动左标。
     *
     */
    @Test
    public void test1(){
        String s = "pwwkew";

        int start = 0, end = 0, res = 0;
        final HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if(map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, i);
            res = Math.max(res, end - start + 1);
            end++;
        }
        System.out.println(res);

    }
}
