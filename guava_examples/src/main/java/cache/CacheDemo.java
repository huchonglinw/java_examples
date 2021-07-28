package cache;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author: hcl
 * @date: 2021/7/21 17:24
 */
public class CacheDemo {
    private static final Logger log = LoggerFactory.getLogger(CacheDemo.class);

    public static void main(String[] args) {
        // LocalCache$LocalLoadingCache
        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .weigher((k, v) -> {
                    // 根据kv计算权重
                    return 1;
                })
                .removalListener(new RemovalListener<String, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Object> notification) {
                        // remove listener
                        log.info("remove a key from cache. key : {}", notification.getKey());
                    }
                })
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                        // a signal to reload key
                        return null;
                    }

                    @Override
                    public Object load(String key) {
                        // load the value from somewhere
                        return new Object();
                    }
                });

        // 删除所有key
        loadingCache.invalidateAll();
    }


}
