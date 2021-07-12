package util;

/**
 * @author: hcl
 * @date: 2021/7/9 11:59
 */
public class StringUtils {
    public static final String COUNT_TEMPLATE = "统计总字符数(包括标点符号)为：#allCount，汉字数#chineseCount，英文字符数#englishCount，标点符号数#symbolCount";

    /**
     * 统计msg中英语的个数，汉字的个数，符号的个数
     * @param msg
     * @return 形如 统计总字符数(包括标点符号)为：178，汉字数0，英文字符数111，标点符号数43 的String
     */
    public static String count(String msg) {
        int chineseCount = 0;
        int englishCount = 0;
        int symbolCount = 0;
        int allCount = 0;
        for (char c : msg.toCharArray()) {
            allCount++;
            if(ifChinese(c)) {
                chineseCount++;
            } else if(ifEnglish(c)) {
                englishCount++;
            } else if(ifSymbol(c)) {
                symbolCount++;
            }
        }
        return COUNT_TEMPLATE.replace("#allCount",String.valueOf(allCount))
                .replace("#chineseCount",String.valueOf(chineseCount))
                .replace("#englishCount",String.valueOf(englishCount))
                .replace("#symbolCount",String.valueOf(symbolCount));

    }
    /**
     * 判断c是否为汉字
     *
     * @param c
     * @return
     */
    private static boolean ifChinese(char c) {
        return 19968 <= c && c < 40869;
    }

    /**
     * 判断c是否为英文
     * 小写字母：[0x61,0x7a]（或十进制[97, 122]）
     * 大写字母：[0x41,0x5a]（或十进制[65, 90]）
     *
     * @param c
     * @return
     */
    private static boolean ifEnglish(char c) {
        return (97 <= c && c <= 122) || (65 <= c && c <= 90);
    }

    private static boolean ifSymbol(char ch) {
        if (isCnSymbol(ch)) return true;
        if (isEnSymbol(ch)) return true;

        if (0x2010 <= ch && ch <= 0x2017) return true;
        if (0x2020 <= ch && ch <= 0x2027) return true;
        if (0x2B00 <= ch && ch <= 0x2BFF) return true;
        if (0xFF03 <= ch && ch <= 0xFF06) return true;
        if (0xFF08 <= ch && ch <= 0xFF0B) return true;
        if (ch == 0xFF0D || ch == 0xFF0F) return true;
        if (0xFF1C <= ch && ch <= 0xFF1E) return true;
        if (ch == 0xFF20 || ch == 0xFF65) return true;
        if (0xFF3B <= ch && ch <= 0xFF40) return true;
        if (0xFF5B <= ch && ch <= 0xFF60) return true;
        if (ch == 0xFF62 || ch == 0xFF63) return true;
        if (ch == 0x0020 || ch == 0x3000) return true;
        return false;

    }

    private static boolean isCnSymbol(char ch) {
        if (0x3004 <= ch && ch <= 0x301C) return true;
        if (0x3020 <= ch && ch <= 0x303F) return true;
        return false;
    }

    private static boolean isEnSymbol(char ch) {

        if (ch == 0x40) return true;
        if (ch == 0x2D || ch == 0x2F) return true;
        if (0x23 <= ch && ch <= 0x26) return true;
        if (0x28 <= ch && ch <= 0x2B) return true;
        if (0x3C <= ch && ch <= 0x3E) return true;
        if (0x5B <= ch && ch <= 0x60) return true;
        if (0x7B <= ch && ch <= 0x7E) return true;

        return false;
    }
}
