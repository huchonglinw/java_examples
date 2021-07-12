package network.nioimprove;

import local.contants.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * java nio improve
 * 改善点：
 * 1. 将IO处理和业务处理拆分（业务操作可能包含阻塞操作，如DB操作，RPC等。只要有阻塞，就需要单独的线程。）
 * 2. 利用多核CPU
 * @author: hcl
 * @date: 2021/6/4 19:54
 */
public class NioServerImprove {
    //===================var===================
    //===================确保在程序的运行过程中，同一个Channel只会由同一个ChannelHandler去执行===================
    private static Map<Channel, ChannelHandler> channel2ChannelHandlerMap = new HashMap<>();
    //===================channelHandler单例池===================
    private static Map<String, ChannelHandler> channelHandlerIoc = new HashMap<>();

    private static Map<Channel, ChannelContext> channel2ChannelContextMap = new HashMap<>();

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

        //===================ioc init===================
        channelHandlerIoc.put("myChannelHandler",new MyChannelHandler());

        bossChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if(select > 0 || selectionKeys.size() != 0) {
                //===================Returns this selector's selected-key set.===================
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                String str = null;
                //===================改进点1：改善为事件分发器，单线程选择就绪的事件，并且分发给相应的处理器===================
                //===================对应的关系是：channel（n）-EventLoop-channelHandler（1）===================
                while(iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    //===================accept===================
                    if(selectionKey.isAcceptable()) {
                        try {
                            SocketChannel accept = bossChannel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ);
                            //===================register channelHandler===================
                            channel2ChannelHandlerMap.put(accept, channelHandlerIoc.get("myChannelHandler"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //===================read===================
                    } else if(selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ChannelHandler channelHandler = getChannelHandler(channel);
                        if(channelHandler != null) {
                            ChannelContext channelContext = new ChannelContext();
                            channel2ChannelContextMap.put(channel,channelContext);
                            channelHandler.channelRead(channel, new byte[1024], channelContext);
                        }
                        channel.register(selector, SelectionKey.OP_WRITE);
                        //===================write===================

                    } else if(selectionKey.isWritable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        getChannelHandler(channel).channelWrite(channel,channel2ChannelContextMap.get(channel));
                        channel.register(selector,SelectionKey.OP_READ);
                    }
                }
            }

        }


    }

    private static ChannelHandler getChannelHandler(Channel channel) {
        return channel2ChannelHandlerMap.get(channel);
    }
}
