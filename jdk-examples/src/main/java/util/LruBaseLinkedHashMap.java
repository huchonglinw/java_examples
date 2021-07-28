package util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: hcl
 * @date: 2021/7/20 11:06
 */
public class LruBaseLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final Integer size;

    public LruBaseLinkedHashMap() {
        super(16,
                0.75f,
                true);
        this.size = 16;
    }

    public LruBaseLinkedHashMap(int initialCapacity) {
        super(16,
                0.75f,
                true);
        this.size = initialCapacity;
    }

    /**
     * <p>The {@link #removeEldestEntry(Map.Entry)} method may be overridden to
     * impose a policy for removing stale mappings automatically when new mappings
     * are added to the map.
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }
}
