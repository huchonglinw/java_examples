package util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture tools, it contains some tool methods for {@link CompletableFuture}
 *
 * @author: hcl
 * @date: 2021/7/18 0:58
 */
public class CompletableFutures {
    private CompletableFutures() {

    }
    /**
     * return all results of futures{@code CompletableFuture}, and the results is in the same order.
     * and if any of the provided futures fails or is canceled, the result of it is {@code null}
     * @param futures the futures to combine
     * @return a future that contains all of result from the provided futures
     */
    public static <V> CompletableFuture<List<V>> successfulAsList(
            CompletableFuture<? extends V>... futures){

        List<V> array = new ArrayList<>(futures.length);

        for (CompletableFuture<? extends V> future : futures) {
            future.thenAccept(array::add);
            future.exceptionally(e -> {
                array.add(null);
                return null;
            });
        }

        return CompletableFuture.supplyAsync(() -> array);
    }

}
