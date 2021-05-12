import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author: hcl
 * @date: 2021/4/15 16:28
 */
public class 包含7或者7的倍数 {

    public static void test1(String input,int k) {
        LinkedList<Character> list = new LinkedList<>();
        char[] chars = input.toCharArray();
        for(int i = 0; i < chars.length; i++ ) {
            char cur = chars[i];
            if(k == 0) {
                list.push(cur);
                continue;
            }

            // 1. 如果是0，加入之后size == k? 全部弹出
            int size = list.size();
            if(cur == '0') {
                if(size + 1 == k) {
                    while(!list.isEmpty()) {
                        list.pop();
                        k--;
                    }
                    // 是0，但size != k，不能弹出，加入0
                }else {
                    list.push(cur);
                }
                // 不是0，判断是不是小于最近一个数，
            }else {
                if(!list.isEmpty()) {
                    while(list.peek() > cur && k != 0) {
                        list.pop();
                        k--;
                    }
                }else{
                    list.push(cur);
                }

            }
        }

        while(k > 0) {
            list.pop();
            k--;
        }


        //syso
        Iterator<Character> it = list.iterator();
        while(it.hasNext()) {
            System.out.print(it.next());
        }

    }

    public static void main(String[] args) {
        System.out.println(70/10);
        for (int i = 1; i < 100; i++) {
            if(i % 7 == 0) {
                System.out.println(i);
            }else {
                int temp = i;
                int temp2 = i;
                do {
                    temp = temp / 10;
                    temp2 = temp2 % 10;
                    if(temp2 == 7) {
                        System.out.println(i);
                        break;
                    }
                }while(temp != 0 && temp != 1);
            }
        }
    }
}
