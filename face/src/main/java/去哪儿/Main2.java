package 去哪儿;

import java.util.*;

/**
 * @author: hcl
 * @date: 2021/4/12 16:07
 */

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static void getAll(int length, int start, int end, String input) {
        final String less = input.substring(0, start);
        final String more = input.substring(end, length);
        final String mid = input.substring(start + 1, end);
        List<String> res = new LinkedList<>();
        // mid = abcd  -> 16种可能
        par(mid.toCharArray(), res, 0);
        // 转大写

        Collections.sort(res);
//        Collections.sort(res, (o1, o2) -> {
//            int len = o1.length();
//            for (int i = 0; i < len; i++) {
//                if (o2.charAt(i) > o1.charAt(i)) {
//                    return 1;
//                }
//            }
//            return 0;
//        });
        for (int i = 0; i < res.size(); ++i) {
            if (i == res.size() - 1) {
                System.out.print(less + res.get(i) + more);
            } else {
                System.out.println(less + res.get(i) + more);
            }
        }
    }

    private static void par(char[] mid, List<String> res, int cur) {
        if (cur == mid.length) {
            res.add(String.valueOf(mid));
            return;
        }
        char ch = mid[cur];
        if (Character.isLetter(ch)) {
            mid[cur] = Character.toLowerCase(ch);
            par(mid, res, ++cur);
            mid[cur] = Character.toUpperCase(ch);
        }
        par(mid, res, ++cur);
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine().trim();

        int endIndexOfLength = line.indexOf(' ');
        int length = Integer.parseInt(line.substring(0, endIndexOfLength));

        int endIndexOfStart = line.indexOf(' ', endIndexOfLength + 1);
        int start = Integer.parseInt(line.substring(endIndexOfLength + 1, endIndexOfStart));

        int endIndexOfEnd = line.indexOf(' ', endIndexOfStart + 1);
        int end = Integer.parseInt(line.substring(endIndexOfStart + 1, endIndexOfEnd));

        String input = line.substring(endIndexOfEnd + 1);

        getAll(length, start, end, input);

    }
}


