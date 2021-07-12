package network.nioimprove;

/**
 * 执行上下文 主要用于保存结果集
 * @author: hcl
 * @date: 2021/7/8 15:38
 */
public class ChannelContext {

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
