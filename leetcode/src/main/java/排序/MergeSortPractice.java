package 排序;

/**
 * @author huchonglin
 * @date 2021/3/25 11:12
 */
public class MergeSortPractice {
    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if(left == right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        sort(nums, left, mid, right);
    }

    private static void sort(int[] nums, int left, int mid, int right) {
        int[] array = new int[right - left + 1];
        int index = 0;

        int l = left;
        int m = mid + 1;
        while(left <= mid && m <= right) {
            array[index ++] = nums[l] < nums[m] ? nums[l++] : nums[m++];
        }
        while(l <= mid) {
            array[index++] = nums[l++];
        }
    }
}
