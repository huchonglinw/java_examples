package network.nioimprove;

import java.nio.channels.Channel;

/**
 * Channel执行器，其实这个接口可以拆分成两个，分别作用于读写的接口
 * tip：
 * 1. ChannelHandler应该是一个单例类，因为同一个Channel可以对应同一个ChannelHandler，因此是线程不安全的。
 * 2. 如果有阻塞的业务逻辑，应该开启一个新线程去处理，那么ChannelHandler接口的返回值应该更改为Future
 *
 * @author: hcl
 * @date: 2021/7/8 15:23
 */
public interface ChannelHandler {
    void channelRead(Channel channel, byte[] bytes,ChannelContext channelContext);

    void channelWrite(Channel channel, ChannelContext channelContext);
}
