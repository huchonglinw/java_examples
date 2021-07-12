package network.bio;

import local.contants.Constants;
import util.HttpUtils;
import util.StringUtils;

import java.io.*;
import java.net.*;

/**
 * java bio：Stream oriented
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Constants.PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            //===================一个连接一个线程===================
            /**
             * 为什么需要多线程呢
             * 因为accept、read、write是阻塞方法。BIO不同于NIO，BIO没有事件机制，不知道是否可读，是否有连接。因此对于阻塞方法，只能
             * 开启一个新线程处理。
             */
            new Thread(new BioServerTask(socket)).start();
        }
    }

    static class BioServerTask implements Runnable {
        private final Socket socket;

        public BioServerTask(Socket socket) {
            System.out.println("连上了一个新的客户端");
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                OutputStream outputStream = socket.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

                //==================
                /**
                 * while (inputStream.read(bytes) != -1)
                 * 文档中的注释：-1</code> if there is no more data because the end of
                 * the stream has been reached.（意味着当流关闭的时候才会跳出while循环）
                 *
                 * 对于聊天室不用while去判断，那么可以用read(byte[] b)、readLine()去读
                 * 那如何考虑用户输入多行数据的形式呢？答案：以分割号去判断
                 */
//                while (inputStream.read(bytes) != -1) {
//
//                }
                //==================

                String clientData;

                /**
                 * 代码作用：通过readLine读取客户端数据
                 *
                 * 问题：while循环只走一次，第二次卡着不动了
                 * 查看文档：or null if the end of the stream has been reached
                 * （意味着当流关闭的时候才会跳出while循环）
                 */
                while ((clientData = bufferedReader.readLine()) != null) {
                    System.out.println("客户端输入完毕：" + clientData);

                    String count = StringUtils.count(HttpUtils.getHtmlData(clientData));

                    bufferedWriter.write(count + "\n");
                    bufferedWriter.flush();
                }


                outputStream.close();
                bufferedWriter.flush();
                bufferedWriter.close();

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}