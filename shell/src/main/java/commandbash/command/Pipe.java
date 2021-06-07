package commandbash.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 管道结构体
 * @author: hcl
 * @date: 2021/6/5 19:33
 */
public class Pipe {
    /**
     * 缓冲区
     */
    private List<String> buffer;

    public Pipe() {
    }

    public Pipe(ArrayList<String> buffer) {
        this.buffer = buffer;
    }


    public List<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(List<String> buffer) {
        this.buffer = buffer;
    }
}
