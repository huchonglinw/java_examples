package local;

import local.contants.Constants;
import org.junit.Test;

import java.io.*;

/**
 * @author: hcl
 * @date: 2021/6/2 16:55
 */
public class BufferReaderTest {


    /**
     * 使用BufferReader循环读取文件
     * br.readLine() 返回String
     */
    public static void fileReader(File file) {
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str;
            StringBuilder result = new StringBuilder();
            int count = 0;
            while ((str = br.readLine()) != null) {
//                inRead(str);
            }
            System.out.println(count);
            System.out.println(result.toString());
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 二、有效代码行数统计
     * 从本题对应的附件中找到 StringUtils.java 文件，将其复制到工程的 classpath 下，编程
     * 统计附件中 的 StringUtils.java 文件的有效代码行数（一个数字）到一个新文 件
     * validLineCount.txt 中。请注意，
     * 1) 有效不包括空行、注释；
     * 2) 考虑代码里有多行注释的情况；
     * 3) 不用考虑代码和注释混合在一行的情况。
     */
    @Test
    public void questionTwo() {
        FileReader fr;
        BufferedReader br;
        FileOutputStream fos;
        File file = Constants.FILE2;
        Integer count = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            fos = new FileOutputStream(Constants.OUT_PUT_FILE);
            String str;
            boolean multiLine = false;

            while ((str = br.readLine()) != null) {
                str = str.trim();
                if (str.length() == 0 || str.startsWith("//")) {
                    continue;
                }

                if (str.startsWith("/*")) {
                    multiLine = true;
                }

                if (str.endsWith("*/")) {
                    multiLine = false;
                }

                if (!multiLine) {
                    count++;
                }
            }
//            fos.write(count); // 这样写不出去
            fos.write(String.valueOf(count).getBytes());
            fr.close();
            br.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 三、文本解密
     * 从本题对应的附件中找到 sdxl_prop.txt 和 sdxl_template.txt。根据 sdxl_prop.txt 中内
     * 容替换掉 sdxl_template.txt 里$function(index)形式文字，将其还原成一本完整小说，写
     * 到文件 sdxl.txt 中，输出在 classpath 下。
     * 其中 function 有 4 种类型，替换规则如下：
     * 1) natureOrder 自然排序，即文本中排列顺序
     * 2) indexOrder 索引排序，文本中每行第一个数字为索引
     * 3) charOrder 文本排序，java 的字符排序
     * 4) charOrderDESC 文本倒序，java 的字符倒序
     */
    @Test
    public void questionThree() {

    }

    private void getTotalRequest() {

    }

}
