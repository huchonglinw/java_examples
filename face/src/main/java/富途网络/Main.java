package 富途网络;
public class Main {
    /**
     *
     * @param strs string字符串一维数组
     * @param str1 string字符串
     * @param str2 string字符串
     * @return int整型
     */
    public int minDistance (String[] strs, String str1, String str2) {
        // write code here
        if(strs == null || str1 == null || str2 == null ) return -1;
        if(strs.length == 0 || str1.length() == 0 || str2.length() == 0 || str1.equals(str2)) return -1;

        int distance = Integer.MAX_VALUE;

        int first = -1;
        int second = -1;

        // a .......b....a....a..b.a
        /**
         * 第一次碰到，标记first的下标
         *
         * 第二次碰到，更新first的下标
         */
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            boolean equalsStr1 = str.equals(str1);
            boolean equalsStr2 = str.equals(str2);
            if(equalsStr1 || equalsStr2) {
                if(equalsStr1) {
                    first = i;
                }
                if(equalsStr2){
                    second = i;
                }
                if(first != -1 && second != -1 ) {
                    distance = Math.min(distance,Math.abs(first - second));
                }
            }
        }
        return distance == Integer.MAX_VALUE ? -1 : distance;
    }
}