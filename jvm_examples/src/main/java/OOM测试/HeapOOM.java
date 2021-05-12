package OOM测试;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huchonglin
 * @date 2021/3/29 10:07
 */
public class HeapOOM {
    /**
     * [GC (Allocation Failure) --[PSYoungGen: 16896K->16896K(18432K)] 16896K->17408K(18944K), 0.1542827 secs] [Times: user=0.19 sys=0.02, real=0.17 secs]
     * [Full GC (Ergonomics) [PSYoungGen: 16896K->10674K(18432K)] [ParOldGen: 512K->511K(512K)] 17408K->11185K(18944K), [Metaspace: 3140K->3140K(1056768K)], 0.1447943 secs] [Times: user=0.36 sys=0.00, real=0.15 secs]
     * [GC (Allocation Failure) --[PSYoungGen: 16896K->16896K(18432K)] 17407K->17407K(18944K), 0.0707206 secs] [Times: user=0.38 sys=0.00, real=0.07 secs]
     * [Full GC (Ergonomics) [PSYoungGen: 16896K->14590K(18432K)] [ParOldGen: 511K->511K(512K)] 17407K->15101K(18944K), [Metaspace: 3193K->3193K(1056768K)], 0.1571700 secs] [Times: user=0.83 sys=0.00, real=0.15 secs]
     * [GC (Allocation Failure) --[PSYoungGen: 16582K->16582K(18432K)] 17093K->17093K(18944K), 0.0744612 secs] [Times: user=0.44 sys=0.00, real=0.08 secs]
     * [Full GC (Ergonomics) [PSYoungGen: 16582K->15924K(18432K)] [ParOldGen: 511K->511K(512K)] 17093K->16435K(18944K), [Metaspace: 3203K->3203K(1056768K)], 0.1806347 secs] [Times: user=0.70 sys=0.00, real=0.18 secs]
     * [GC (Allocation Failure) --[PSYoungGen: 15924K->15924K(18432K)] 16435K->16435K(18944K), 0.0908195 secs] [Times: user=0.42 sys=0.00, real=0.09 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 15924K->15906K(18432K)] [ParOldGen: 511K->511K(512K)] 16435K->16417K(18944K), [Metaspace: 3203K->3203K(1056768K)], 0.1334964 secs] [Times: user=0.45 sys=0.00, real=0.13 secs]
     * Heap
     *  PSYoungGen      total 18432K, used 16323K [0x00000000fec80000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 16896K, 96% used [0x00000000fec80000,0x00000000ffc70ff0,0x00000000ffd00000)
     *   from space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
     *   to   space 1536K, 0% used [0x00000000ffd00000,0x00000000ffd00000,0x00000000ffe80000)
     *  ParOldGen       total 512K, used 511K [0x00000000fec00000, 0x00000000fec80000, 0x00000000fec80000)
     *   object space 512K, 99% used [0x00000000fec00000,0x00000000fec7fc00,0x00000000fec80000)
     *  Metaspace       used 3252K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 352K, capacity 388K, committed 512K, reserved 1048576K
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at OOM测试.HeapOOM.main(HeapOOM.java:14)
     * Java HotSpot(TM) 64-Bit Server VM warning: MaxNewSize (20480k) is equal to or greater than the entire heap (20480k).  A new max generation size of 19968k will be used.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<HeapOOM>();
        while (true) {
            list.add(new HeapOOM());
        }
    }
}
