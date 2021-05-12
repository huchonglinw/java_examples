
    // 2次 字典序最小
    // 遍历一次，存储字母和下标的对应关系  hashMap<zimu,index> a -> 7 .... z -> 3
    // 维护一个int值，至多2次
    // 维护一个char[] 数组，表示出现的次数。要减掉当前。 a -> 3
    // 再遍历一次，判断当前有没有比当前字母小的数字，
    // 如何找到比当前字母小的数字？并且存在于char数字当中

    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();



            HashMap<Character, Integer> index_map = new HashMap<>();
            HashMap<Character, Integer> count_map = new HashMap<>();

            char[] str_char = str.toCharArray();

            for (int i = 0; i < str_char.length; i++) {
                char cur = str_char[i];
                index_map.put(cur,i);
                Integer count = count_map.get(cur);
                count_map.put(cur,count == null ? 0 : ++count);
            }
            // 'a' -> 97
            // 进来一个数，减掉出现的次数，char_count[] --，
            // 当前 - 97 如果大于0，从97，到temp遍历，去chars1[] 中看次数> 0 ，去indexMap取值，返回


            /**
             * aaazbcdeadcd
             */
            for (int i = 0; i < str_char.length; i++) {
                char cur_char = str_char[i]; // z .i = 3
                int distince = cur_char - 'a';

                Integer currentCharCount = count_map.get(cur_char);
                count_map.put(cur_char,--currentCharCount);

                if(distince > 0) {
                    int index = - 1;
                    int curJ = -1;
                    for(int j = 97; j < distince + 97; j ++) {

                        int curCount = count_map.get((char)j);

                        if(curCount > 0) {
                            index = index_map.get((char) j);
                            index = index > i ? index : -1;
                            curJ = j;
                            break;
                        }
                    }
                    if(index != -1) {
                        str_char[i] = str_char[index];
                        str_char[index] = cur_char;
                        index_map.put(cur_char, index);
                        index_map.put((char)curJ, i);
                    }
                }
            }
            System.out.println(Arrays.toString(str_char));
        }
    }
