import java.io.IOException;
import java.util.*;

class Main2 {
    private static Map<String,String> hashMap = new HashMap<>();
    private static List<String> list = new LinkedList<>();
    private static int maxKey = 0;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s;

        while(sc.hasNext()) {
            s = sc.nextLine();

            //dict:停词:我想,的,得,从,去
            if(s.startsWith("dict:")){ // 5
                String curKey = "";
                String curValue = "";
                char[] chars = s.toCharArray();
                for (int i = 5; i < chars.length; i++) {
                    char c = chars[i];
                    if(c == ':'){
                        curValue = curKey;
                        curKey = "";
                        continue;
                    }

                    if(i == chars.length - 1){
                        curKey += c;
                        hashMap.put(curKey,curValue);
                        maxKey = Math.max(curKey.length(), maxKey);
                        continue;
                    }

                    if(c == ','){
                        hashMap.put(curKey,curValue);
                        maxKey = Math.max(curKey.length(), maxKey);
                        curKey = "";
                    }else{
                        curKey += c;
                    }
                }
            }
            // query:我想春节从南京去三亚和海口潜水跟团游5天5钻携程自营
            if(s.startsWith("query:")) { // 5
                char[] chars = s.toCharArray();
                for (int i = 6; i < chars.length; i++) {
                    String str = "";
                    getMap(chars,i, str,chars.length - 1);
                }

            }
        }
        for (String value : list) {
            System.out.println(value);
        }
    }
    private static void getMap(char[] chars, int i, String s, int i1) {
        s += String.valueOf(chars[i]);
        String value = hashMap.get(s);
        if(!isEmpty(value)) {
            list.add(s+":["+value+"]");
        }
        if(s.length() > maxKey || i1 == i) {
            return;
        }

        // 我想:[停词]
        getMap(chars,i + 1,s, chars.length - 1);
    }

    private static Boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * m1(){
     *     if(!chars[i].getMap) {
     *      *     return;
     *      * }
     *      *
     *      * list.add(char);
     *      m1(char[i])
     * }
     *
     *
     */


}

// 我想 春节 从 南京 去三亚潜水跟团游5天5钻携程自营

// 解析字符串 curStr 