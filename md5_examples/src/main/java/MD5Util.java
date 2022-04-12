import java.security.MessageDigest;

public class MD5Util {
    private static final String DEFAUL_ENCODING = "UTF-8";
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public MD5Util() {
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i], true));
        }

        return resultSb.toString();
    }

    public static String byteArrayToHexStringLittleEnding(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i], false));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b, boolean bigEnding) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return bigEnding ? hexDigits[d1] + hexDigits[d2] : hexDigits[d2] + hexDigits[d1];
    }

    public static String MD5Encode(String origin) {
        return MD5Encode(origin, "UTF-8");
    }

    public static byte[] hexStringToByteArray(String s) {
        if (s.length() % 2 != 0) {
            throw new RuntimeException("Error Hex String length");
        } else {
            byte[] result = new byte[s.length() / 2];

            int bytepos;
            char c;
            char c2;
            for(int i = 0; i < s.length(); result[bytepos] = Integer.decode("0x" + c + c2).byteValue()) {
                bytepos = i / 2;
                c = s.charAt(i++);
                c2 = s.charAt(i++);
            }

            return result;
        }
    }

    public static String MD5Encode(String origin, String encoding) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (encoding == null) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(encoding)));
            }

            return resultString;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static byte[] MD5Encode(byte[] origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(origin);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}
