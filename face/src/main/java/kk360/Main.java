package kk360;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] array = new int[number];
        long result = 0;

        for (int i = 0; i < number; i++) {
            int input = sc.nextInt();
            array[i] = input;
            result += input;
        }

        for (int i = 0; i < array.length; i++) {
            int first = array[i];
            for (int j = i + 1; j < array.length; j++) {
                result += (first | array[j]);
            }
        }
        System.out.println(result);
    }
}
