package local;

import local.contants.Constants;
import local.utils.Utils;

import java.io.*;

/**
 * 对流传输（FileInputStream、BufferInputStream）进行测试
 *
 * @author: hcl
 * @date: 2021/6/2 17:19
 */
public class InputStreamTest {
    public static void main(String[] args) {
//        copyFile(local.contants.Constants.FILE1);
        copyFileByBuffer(Constants.FILE1);
        copyFileByBufferedStream(Constants.FILE1);
    }

    /**
     * 使用fis进行拷贝操作，耗时：20619、22020
     * 如若声明Byte数组，耗时36
     * 无缓冲区
     *
     * @param file
     */
    private static void copyFile(File file) {
        FileInputStream fis;
        FileOutputStream fos;
        long start = Utils.getTime();
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(Constants.OUT_PUT_FILE);
            int len;
            while ((len = fis.read()) != -1) {
                fos.write(len);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = Utils.getTime();
        System.out.println(end - start);
    }

    /**
     * 使用fis进行拷贝操作
     * 耗时：20619、22020
     * 如若声明Byte数组，耗时36
     *
     * @param file
     */
    private static void copyFileByBuffer(File file) {
        FileInputStream fis;
        FileOutputStream fos;
        long start = Utils.getTime();
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(Constants.OUT_PUT_FILE);
            long length = file.length();
            int length2 = (int) length / 10;
            byte[] bytes = new byte[length2];
            while ((fis.read(bytes)) != -1) {
//                fos.write(bytes);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = Utils.getTime();
        System.out.println(end - start);
    }


    /**
     * 报错：java.io.IOException: Stream Closed： bos.close(); -》 解决：bos.flush
     *
     * 使用BufferedInputStream进行拷贝文件
     * 耗时：193（无byte[]）14（有byte[]）
     *
     * @param file
     */
    public static void copyFileByBufferedStream(File file) {
        long start = Utils.getTime();
        BufferedInputStream bis;
        FileInputStream fis;
        FileOutputStream fos;
        BufferedOutputStream bos;
        try {
            bis = new BufferedInputStream((fis = new FileInputStream(file)));
            bos = new BufferedOutputStream(fos = new FileOutputStream(Constants.OUT_PUT_FILE));
            byte[] bytes = new byte[1024];
            while ((bis.read(bytes)) != -1) {
//                bos.write(bytes);
            }
            bos.flush();
            bis.close();
            fis.close();
            fos.close();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = Utils.getTime();
        System.out.println(end - start);
    }


    private static void fileStream() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(Constants.FILE1);
            int read = fis.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
