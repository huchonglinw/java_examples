package 同城58;

import java.util.Arrays;

/**
 * @author: hcl
 * @date: 2021/4/25 19:34
 */
public class Solution {
    public int tdepth (int[] arr) {
        // write code here
        // 2
        // 1 2 3 4 5 7 8 9
        // 58同城
        int root = arr[1];
        int index = 0;
        // index = 1
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if(root == arr[i]) {
                index = i;
            }
        }

        return Integer.max(build(arr, 0, index),build(arr, index, arr.length - 1)) + 1;
    }

    private int build(int[] arr, int start, int end) {
        // 只有一个节点
        if(end - start == 1) {
            return 1;
        }

        // 否则就循环创建
        int mid = start + (end - start) / 2;
        build(arr, 0, mid);
        build(arr, mid, end);

        return 1;

    }

    /**
     * 最长回文串
     * @param str string字符串
     * @return string字符串
     */
    public String longestPalindrom (String str) {
        // write code here
        if(str == null || str == "") return "";
        int n = str.length();
        String ret = "";
        boolean[] dp = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            char f = str.charAt(i);
            for (int j = n - 1; j >= i ; j--) {
                char l = str.charAt(j);
                int dis = j - i;
                dp[j] = f == l && (dis < 3 || dp[j -1]);
                if(dp[j] && dis + 1 > ret.length())
                    ret = str.substring(i, j + 1);
            }
        }
        return ret;
    }

    /**
     *
     * @param a int整型一维数组 店铺1的价格列表
     * @param b int整型一维数组 店铺2的价格列表
     * @return int整型一维数组
     */
    public int[] mergePrice (int[] a, int[] b) {
        // write code here
        int al = a.length;
        int bl = b.length;
        int as = 0, bs = 0, index = 0;
        int[] ret = new int[al + bl];

        while(as < al && bs < bl ) {
            if(a[as] > b[bs]) {
                ret[index++] = b[bs];
                bs++;
            }else {
                ret[index++] = a[as];
                as++;
            }
        }

        while(bs < bl) {
            ret[index++] = b[bs++];
        }
        while(as < al) {
            ret[index++] = a[as++];
        }

        return ret;
    }
}
