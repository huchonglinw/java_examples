package lambda;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author: chonglin.hu
 * @date: 2021/10/25 16:13
 */
public class FindFirst {
    public static void main(String[] args) {
        Optional<String> s = Arrays.asList("1", "2", "3").stream()
                .findFirst()
                .map(l -> {
                    if(l.equals("1")) {
                        return null;
                    }
                    return "2";
                });
    }

    static class Man {
        private String name;

        public Man(String name) {
            this.name = name;
        }
    }
}
