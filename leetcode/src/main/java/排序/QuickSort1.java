package 排序;

import java.util.Arrays;

/**
 * @author huchonglin
 * @date 2021/3/25 9:23
 */
public class QuickSort1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 31,30,3,5,7,35,987,2,43,12,87,3,56,0,2134,1,467,7, 4, 5, 3};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
    public static void quickSort(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if(left >= right) {
            return;
        }
        partion(nums, left, right);
    }

    private static void partion(int[] nums, int left, int right) {
        int less = left - 1;
        int more = right;
        while(left <= more) {
            if(nums[left] < nums[right]) {
                swap(nums, ++less, left);
            }else if(nums[left] > nums[right]) {
                swap(nums,--more,left);
            }else {
                less++;
            }
        }
    }

    private static void swap(int[] nums, int first, int second) {

    }
}
