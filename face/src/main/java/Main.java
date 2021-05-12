import java.util.Scanner;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int calcSimilarity(String firstName, String secondName) {

        String[] firstNameStr = firstName.split(" ");
        String[] secondNameStr = secondName.split(" ");

        int firstNameLength = firstNameStr.length;
        int secondNameLength = secondNameStr.length;
        int result = 0;
        // 5 2
        // 3
        int temp = firstNameLength - secondNameLength;
        if(temp > 0) {
            for (int i = firstNameLength - temp; i < firstNameLength; i++) {
                result += getStringASC(firstNameStr[i]);
            }
        }
        if(temp < 0) {
            for (int i = secondNameLength - temp; i < secondNameLength ; i++) {
                result += getStringASC(firstNameStr[i]);
            }
        }

        temp = firstNameLength - temp;

        for (int i = 0; i < temp; i++) {
            String sec = secondNameStr[i];
            String first = firstNameStr[i];

            int secLength = sec.length();
            int firstLength = first.length();

            char[] secChars = sec.toCharArray();
            char[] firstChars = first.toCharArray();

            int temp1 = firstLength - secLength;

            if(temp1 > 0) {
                for (int j = firstLength - temp1; j < firstLength; j++) {
                    result += getASC(firstChars[j]);
                }
            }
            if(temp1 < 0) {
                for (int j = secondNameLength - temp1 ; j < secondNameLength ; j++) {
                    result += getASC(secChars[j]);
                }
            }

            temp1 = firstLength - temp1;
            for (int i1 = 0; i1 < temp1; i1++) {
                result += check(firstChars[i1],secChars[i1]);
            }
        }

        return result;
    }
    private static Boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    private static int getStringASC(String str) {
        int result = 0;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            result += getASC(c);
        }
        return result;
    }

    private static int getASC(char firstChar) {
        return (int)firstChar;
    }


    private static int check(char secChar, char firstChar) {
        if(secChar == firstChar) {
            return 0;
        }
        return (int)secChar + (int)firstChar;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name1 = in.nextLine();
            String name2 = in.nextLine();

            int sum = calcSimilarity(name1, name2);
            System.out.println(sum);
        }
    }
}
