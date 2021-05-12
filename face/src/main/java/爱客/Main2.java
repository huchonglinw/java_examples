package 爱客;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 删除链表
 * 3 2
 * 4 3
 * 5 2
 * 1 4
 * 3
 *
 * 2 1 5 4
 *
 * 2 5 3 4 1
 *
 * 2 5 3 4 1
 *
 * 1 4 3 5 2
 *
 *
 */
public class Main2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> list = new LinkedList<>();
        while(sc.hasNextInt()) {
            int listNum = sc.nextInt();
            int firstVal = sc.nextInt();
            list.add(firstVal);
            Integer f;
            Integer l;

            for (int i = 0; i < listNum - 1; i++) {
                // 第一个是尾节点 第二个是头节点
                f = sc.nextInt();
                l = sc.nextInt();

                int index = list.indexOf(l);
                list.add(index + 1, f);
            }
            Object target = sc.nextInt();
            list.remove(target);
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();

        }


    }

}

