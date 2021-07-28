package thread.serialThread;

/**
 * @author: hcl
 * @date: 2021/7/22 23:42
 */
public class SerialThread {
    private Runnable runnable;

    public SerialThread(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
