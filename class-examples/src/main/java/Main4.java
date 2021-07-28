import java.util.HashMap;
import java.util.Scanner;

/**
 * @author huchonglin
 * @date 2021/3/20 19:57
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "aaazbcdeadcd";



        HashMap<Character, Integer> index_map = new HashMap<>();
        HashMap<Character, Integer> count_map = new HashMap<>();

        char[] str_char = str.toCharArray();

        for (int i = 0; i < str_char.length; i++) {
            char cur = str_char[i];
            index_map.put(cur,i);
            Integer count = count_map.get(cur);
            count_map.put(cur,count == null ? 1 : ++count);
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

                    Integer curCount = count_map.get((char)j);

                    if(curCount != null && curCount > 0) {
                        index = index_map.get((char) j);
                        index = index > i ? index : -1;
                        curJ = j;
                        break;
                    }
                }
                if(index != -1) {
                    char change = cur_char; // z
                    str_char[i] = str_char[index];
                    str_char[index] = change;
                    index_map.put(cur_char, index);
                    index_map.put((char)curJ, i);
                }
            }
        }
        for (char c : str_char) {
            System.out.print(c);
        }
    }
}
