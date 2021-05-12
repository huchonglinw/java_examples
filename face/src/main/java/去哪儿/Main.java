package 去哪儿;

import java.util.*;

public class Main2 {

    // k -> 数字
    // 遍历 得到每个数字的重复次数


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/


    // 数字 -> 次数
    static int topK(int[] array, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            int cur = array[i];
            Integer red = map.get(cur);
            if(red == null) {
                map.put(cur,1);
            }else{
                map.put(cur, red + 1);
            }
        }
        LinkedList<Map.Entry<Integer, Integer>> list = new LinkedList<>();

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }

        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

        return list.get(k - 1).getKey();
    }

    private static void sort(int[] array, int l, int r, int k) {
        int[] partion = partion(array, l, r);
        if(partion[0] >= k && partion[1] <= k) {

        }
        sort(array,l,partion[0],k);
        sort(array,partion[1],r,k);
    }

    private static int[] partion(int[] array, int l, int r) {
        int less = l - 1;
        int more = r + 1;
        int target = array[r];
        while(l < more) {
            if(array[l] < target) {
                swap(array,l++,++less);
            }else if(array[l] > target) {
                swap(array, --more,l);
            }else{
                l++;
            }
        }
        return new int[]{less,more};
    }

    private static void swap(int[] array, int l, int r) {
        int temp = array[l];
        array[l] = array[r];
        array[r] = temp;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _array_size = 0;
        _array_size = Integer.parseInt(in.nextLine().trim());
        int[] _array = new int[_array_size];
        int _array_item;
        for(int _array_i = 0; _array_i < _array_size; _array_i++) {
            _array_item = Integer.parseInt(in.nextLine().trim());
            _array[_array_i] = _array_item;
        }

        int _k;
        _k = Integer.parseInt(in.nextLine().trim());

        res = topK(_array, _k);
        System.out.println(String.valueOf(res));

    }
}
