import java.util.LinkedList;
import java.util.List;


/**
 * @author huchonglin
 * @date 2020/12/19 16:03
 */
public class 静态类 {
    public static List<String> strs  = new LinkedList<>();
    public static List<String> getStaticList(){
        for (int i = 0; i < 1000; i++) {
            strs.add(String.valueOf(i));
        }
        return strs;
    }
}
