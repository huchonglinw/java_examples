import com.google.common.util.concurrent.AbstractFuture;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author: hcl
 * @date: 2021/7/19 21:32
 */
public class MyAbstractFuture extends AbstractFuture {
    @Override
    protected boolean set(@Nullable Object value) {
        return super.set(value);
    }
}
