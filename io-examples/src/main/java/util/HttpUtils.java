package util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http工具类
 */
public class HttpUtils {
    /**
     * 方法描述：通过jdk URL类获取html页面信息
     *
     * @return 一段文本 或者 ""空字符串
     */
    public static String getHtmlData(String urlStr) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream inputStream = urlConnection.getInputStream();
            // An InputStreamReader is a bridge from byte streams to character streams
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            /**
             * 读法一：使用bufferReader.readLine读取
             * 
             * 问题：当客户端使用readLine读取数据时，碰到\r \n都属于一行。如果想实现输出换行，该怎么处理？
             * 1. 自定义分隔符格式
             * 2. 在结果集前面添加行数作为标识
             * 
             */
            // 统计总字符数(包括标点符号)为：301424，汉字数728，英文字符数207825，标点符号数42303
            String readLine;
//            while((readLine = bufferedReader.readLine()) != null) {
//                 sb.append(readLine);
//            }



            /**
             * 读法二：通过声明char数组，使用BufferReader去读取
             */
            // 统计总字符数(包括标点符号)为：301690，汉字数728，英文字符数207817，标点符号数42295
            char[] array = new char[8096];
            int len;
            while ((len = bufferedReader.read(array)) != -1) {
                sb.append(new String(array, 0, len));
            }

            inputStream.close();
            bufferedReader.close();
        } catch (MalformedURLException e) {
            return "URL不存在";
        }
        return sb.toString();
    }
}
