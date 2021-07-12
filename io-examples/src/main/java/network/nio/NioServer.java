package network.nio;

import local.contants.Constants;
import util.HttpUtils;
import util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * java nio :Buffer oriented
 * @author: hcl
 * @date: 2021/6/4 19:54
 */
public class NioServer {

    public static void main(String[] args)  {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单线程处理
     * @throws IOException
     */
    public static void start() throws IOException {
//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //===================init===================
        ServerSocketChannel bossChannel = ServerSocketChannel.open();
        bossChannel.configureBlocking(false);
        bossChannel.bind(new InetSocketAddress("localhost", Constants.PORT));
        Selector selector = Selector.open();

        //===================var===================
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        //===================register accept===================
        bossChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            //===================logic===================
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if(select > 0 || selectionKeys.size() != 0) {
                //===================Returns this selector's selected-key set.===================
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                String str = null;
                while(iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //===================remove key===================
                    iterator.remove();
                    //===================accept===================
                    if(selectionKey.isAcceptable()) {
                        try {
                            //===================如果不remove掉这个selectionKey，bossChannel accept到的请求是null===================
                            //===================可想而知，对应的关联是channel-selectionKey-fd===================
                            //===================accept业务逻辑，可开一个线程===================
                            SocketChannel socketChannel = bossChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ, readBuffer);
                            //===================如果不remove会怎么样？选择器中仍然保持着accept，下次遍历时仍然会获取到===================
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //===================read===================
                        //===================read业务逻辑，可开线程===================
                    } else if(selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        try {
                            int read = channel.read(readBuffer);
                            String url;
                            if(read > 0) {
                                readBuffer.flip();
                                readBuffer.clear();

                                url = new String(readBuffer.array(), 0, read);
                                System.out.println("客户端输入完毕：" + url);

                                String count = StringUtils.count(HttpUtils.getHtmlData(url));

                                writeBuffer.put(count.getBytes());
                                channel.register(selector, SelectionKey.OP_WRITE, writeBuffer);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //===================write===================
                    } else if(selectionKey.isWritable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        //===================如果不flip，写不出数据===================
                        writeBuffer.flip();
                        channel.write(writeBuffer);
                        writeBuffer.clear();
                        channel.register(selector,SelectionKey.OP_READ,readBuffer);
                    }
                    //===================如果不remove会怎么样？===================
                    //===================selector.select()一直返回0===================
//                    iterator.remove();
                }
            }

        }


    }
}
