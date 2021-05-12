package 爱客;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int peopleNum = sc.nextInt();
        int postNum = sc.nextInt();
        int[] distance = new int[peopleNum];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = sc.nextInt();
        }

        int[][] w = new int[peopleNum + 1][peopleNum + 1];
        for (int i = 0; i < distance.length; i++) {
            for (int j = i + 1; j < distance.length; j++) {
                w[i][j] = w[i][j - 1] + distance[j] - distance[(i + j) >> 1];
            }
        }

        int[][] dp = new int[postNum][peopleNum];
        int[][] s = new int[postNum][peopleNum];
        for (int i = 0; i < distance.length; i++) {
            dp[0][i] = w[0][i];
            s[0][i] = 0;
        }

        for (int i = 1; i < postNum; i++) {
            for (int j = distance.length - 1; j > i; j--) {
                int minD = s[i - 1][j];
                int maxD = j == distance.length - 1 ? distance.length - 1 : s[i][j + 1];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = minD; k <= maxD; k++) {
                    int cur = dp[i - 1][k] + w[k + 1][j];
                    if(cur <= dp[i][j]) {
                        dp[i][j] = cur;
                        s[i][j] = k;
                    }
                }
            }
        }
        System.out.println(dp[postNum - 1][distance.length - 1]);

    }
}
