package 排序;

import java.util.Arrays;

/**
 * 空间复杂度 O(logn)，时间复杂度 O(nlogn)。
 *
 * 对于规模为 n 的问题，一共要进行 log(n) 次的切分，和基准值进行 n-1 次比较，n-1 次比较的时间复杂度是 O(n)，
 * 所以快速排序的时间复杂度为 O(nlogn)。
 *
 * 但是，如果每次在选择基准值的时候，都不幸地选择了子数组里的最大或最小值。即每次把把数组分成了两个更小长度的数组，
 * 其中一个长度为 1，另一个的长度是子数组的长度减 1。这样的算法复杂度变成 O(n²)。
 *
 * 和归并排序不同，快速排序在每次递归的过程中，只需要开辟 O(1) 的存储空间来完成操作来实现对数组的修改；
 * 而递归次数为 logn，所以它的整体空间复杂度完全取决于压堆栈的次数。
 */

/**
 * 归并排序和快速排序都是利用分治的思想，代码都通过递归来实现，过程非常相似。
 * 归并排序非常稳定，时间复杂度始终都是 [公式]，但不是原地排序；快速排序虽然最坏情况下时间复杂度为 [公式]，
 * 但平均情况下时间复杂度为 [公式]，最坏情况发生的概率也比较小，而且是原地排序算法，因此应用得更加广泛。
 */
public class QuickSort {

    private static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        // 不能再分时，返回
        if (left >= right) {
            return;
        }
        int[] p = sortAndPartition(nums, left, right);
        quickSort(nums, left, p[0] - 1);
        quickSort(nums, p[0] + 1, right);
    }

    /**
     * 排序 + 分区
     * 默认选择第一个数，排序后，基于中间点划分成两个子数组
     */
    private static int[] sortAndPartition(int[] nums, int left, int right) {
        int leftPoint = left - 1; // 选取左分区
        int rightPoint = right; // 选取右分区
        while (left < rightPoint) {
            if (nums[left] < nums[right]) {
                swap(nums, ++leftPoint, left++); // 左分区往右走
            } else if (nums[left] > nums[right]) { // 左大于右
                swap(nums, --rightPoint, left); //
            } else {
                left++;
            }
        }
        swap(nums, rightPoint, right); //
        return new int[] {leftPoint + 1}; // 基准数的下标
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 31,30,3,5,7,35,987,2,43,12,87,3,56,0,2134,1,467,7, 4, 5, 3};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}