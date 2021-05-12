package userlogin;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * 测试编码解码
 * @author huchonglin
 * @date 2020/11/17 9:40
 */
public class UserLogin {
    public static void main(String[] args) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        String username = new String(decoder.decodeBuffer("huchonglin"));
        String password = new String(decoder.decodeBuffer("123809zxjwj123mzn~"));
        System.out.println(username+","+password);

    }
}
