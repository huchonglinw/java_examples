package local;

import local.execption.InPutException;

import java.util.Scanner;

/**
 * @author: hcl
 * @date: 2021/6/5 23:03
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String line = sc.nextLine();

            try {
                test(line);
                System.out.println("一次输入结束---");
            } catch (InPutException e) {
                System.out.println(e.getMessage());
            }


        }
    }

    private static void test(String line) throws InPutException {
        if(line.equals("123")) {
            throw new InPutException();
        }
    }
}
