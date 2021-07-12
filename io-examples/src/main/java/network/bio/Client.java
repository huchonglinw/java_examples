package network.bio;

import local.contants.Constants;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(Constants.HOST, Constants.PORT);
            process5(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 代码作用：客户端读取命令行输入，write一次flush一次
     *
     * @param socket
     */
    private static void process4(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            Scanner sc = new Scanner(System.in);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println("tip：URL前缀需包含协议，如http、https");

            while (sc.hasNext()) {
                bufferedWriter.write(sc.nextLine() + "\n");
                bufferedWriter.flush();
                String res = bufferedReader.readLine();
                System.out.println(res);
            }
            bufferedWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于与NIO服务器通讯，区别于process4，该方法使用的是字节流
     * @param socket
     */
    private static void process5(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            Scanner sc = new Scanner(System.in);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            System.out.println("tip：URL前缀需包含协议，如http、https");

            while (sc.hasNext()) {
                outputStream.write(sc.nextLine().getBytes());
                byte[] bytes = new byte[1024];
                StringBuilder res = new StringBuilder();
                int len;
                len = inputStream.read(bytes);
                res.append(new String(bytes, 0, len));

                System.out.println(res);
            }
            bufferedWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 代码作用：客户端读取命令行输入，每次发送完毕之后都关闭管道
     * <p>
     * 报错情况（包含注释1）：java.net.SocketException: Socket closed
     * 流关闭了之后就不能再打开了
     *
     * @param socket
     */
    private static void process3(Socket socket) {
        OutputStream outputStream = null;
        try {
//            outputStream = socket.getOutputStream();  // 注释1
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                outputStream = socket.getOutputStream();
                String input = sc.nextLine();
                outputStream.write(input.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 客户端写 1000000 个字节
     * 写完之后进行睡眠
     *
     * @param socket
     */
    private static void process2(Socket socket) {
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String res = "1234567890";
            for (int i = 0; i < 1; i++) {
                bufferedWriter.write(res);
            }
            bufferedWriter.write("\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            Thread.sleep(50000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void process(Socket socket) {
        Scanner sc = new Scanner(System.in);
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for (int i = 0; i < 10; i++) {
                bufferedWriter.write("hello" + i);
                bufferedWriter.flush();
            }
            while (sc.hasNext()) {
                String input = sc.nextLine();
                bufferedWriter.write(input + "\n");
                bufferedWriter.flush();
                String serverResult = bufferedReader.readLine();
                System.out.println(serverResult);
            }

            bufferedReader.close();
            inputStream.close();
            bufferedWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
