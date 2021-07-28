package task;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * @author: hcl
 * @date: 2021/7/19 21:45
 */
public class OptionalDemo {
    @Test
    public void test() {
        // Make an Optional containing the given non-null value, or fail fast on null.
        Optional.of(5);
//        Optional.of(null);

        Optional.absent();

        Optional<Object> objectOptional = Optional.fromNullable(null); //Optional.absent()
    }
}
