package lambda;

import org.junit.Test;
import po.Trader;
import po.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author huchonglin
 * @date 2020/11/14 20:07
 */

public class Ten_Questions {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {

    }
    @Test
    public void test(){

    }

//       1 找出2011年发生的所有交易，并按交易额排序
//        transactions.stream()
//                .filter(s -> Objects.equals(s.getYear(),2011))
//                .sorted(comparing(po.Transaction::getValue))
//                .collect(Collectors.toList());


    //      2交易员在哪些城市工作过
//
//        transactions.stream()
//                .map(s ->s.getTrader().getCity())
//            .distinct()
//                .forEach(System.out::println);

//       3 查找所有来自剑桥的交易员，并按姓名排序
//        transactions.stream()
//                .map(po.Transaction::getTrader)
//                .filter(s -> Objects.equals(s.getCity(), "Cambridge"))
//                .distinct()
//                .sorted(comparing(po.Trader::getName))
//                .forEach(System.out::println);

//      4  返回所有交易员的姓名字符串，并按字母顺序排序
//        System.out.println(transactions.stream()
//                .map(s -> s.getTrader().getName())
//                .distinct()
//                .sorted()
//                .reduce("", (n1, n2) -> n1 + n2));

//       5 有没有交易员在米兰工作的？
//        boolean milan = transactions.stream()
//                .anyMatch(transaction -> transaction.getTrader()
//                        .getCity()
//                        .equals("Milan")
//                );

//       6 打印生活在剑桥的交易员的所有交易额
//        transactions.stream()
//                .filter(s -> Objects.equals(s.getTrader().getCity(),"Cambridge"))

//      7  所有交易中，最高的交易额是多少
//                找到交易额最小的交易
}
