package kk360;

import java.util.Scanner;

public class Main2 {
    static int[] distance;
    static int[] goldNum;
    static int flyStartNum;
    static int flyLong;
    static int goodsNum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        goodsNum = sc.nextInt();
        flyLong = sc.nextInt();
        flyStartNum = sc.nextInt();
        distance = new int[goodsNum];
        goldNum = new int[goodsNum];


        for (int i = 0; i < goodsNum; i++) {
            distance[i] = sc.nextInt();
            goldNum[i] = sc.nextInt();
        }

        int result = goldNum[0];

        /**
         * 5 10 2
         *
         *
         * 7 11
         * 8 6
         * 10 8
         * 19 12
         * 22 15
         */
        getResult(0,result);
    }

    private static void getResult(int i1, int i) {
        if(i1 == goodsNum) {
            return;
        }
        int goldNums = goldNum[i1];
        int dis = distance[i1];
        dis += flyLong;

        getResult(i1++,dis);

    }

}
