package network.nioimprove;

import util.HttpUtils;
import util.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * 自己的ChannelHandler
 * @author: hcl
 * @date: 2021/7/8 15:30
 */
public class MyChannelHandler implements ChannelHandler{
    /**
     * 从参数channel中读取数据，并将结果集封装在channelContext中
     * @param channel
     * @param bytes
     * @param channelContext
     */
    @Override
    public void channelRead(Channel channel, byte[] bytes, ChannelContext channelContext) {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = (SocketChannel)channel;
        int read = 0;
        try {
            read = socketChannel.read(readBuffer);
            String url;
            if(read > 0) {
                readBuffer.flip();
                readBuffer.clear();

                url = new String(readBuffer.array(), 0, read);
                System.out.println("客户端输入完毕：" + url);
                String count = StringUtils.count(HttpUtils.getHtmlData(url));
                channelContext.setBytes(count.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从ChannelContext中取得上下文结果集，由参数channel写出去
     * @param channel
     * @param channelContext
     */
    @Override
    public void channelWrite(Channel channel, ChannelContext channelContext) {
        SocketChannel socketChannel = (SocketChannel)channel;
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put(channelContext.getBytes());
        writeBuffer.flip();
        try {
            socketChannel.write(writeBuffer);
            writeBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
