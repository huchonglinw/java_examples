package base;

/**
 * @see com.google.common.base.Strings
 * @author: hcl
 * @date: 2021/7/19 22:31
 */
public class MyStrings {
    public String repeat(String str, int count) throws Exception {
        if (count < 1) {
            throw new Exception();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
