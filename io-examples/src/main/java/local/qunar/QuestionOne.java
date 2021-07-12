package local.qunar;

import com.google.common.io.Files;
import local.contants.Constants;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 题目一
 * 从本题对应的附件中找到 access.log 文件，并编程根据文件内容统计以下数据：
 * 1. 请求总量；
 * 2. 请求最频繁的 10 个 HTTP 接口，及其相应的请求数量；
 * 3. POST 和 GET 请求量分别为多少；
 * 4. URI 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类，输出各个类别
 * 下 URI 都有哪些。
 * 思路：截取URL（方法和参数去除）
 */
public class QuestionOne {
    @Test
    public void questionOne() {
        try {
            //===================init===================
            List<String> fileLines = Files.readLines(Constants.FILE1, Charset.defaultCharset());


            //===================var===================
            AtomicInteger lineCount = new AtomicInteger();
            AtomicInteger getCount = new AtomicInteger();
            AtomicInteger postCount = new AtomicInteger();
            Map<String, Integer> url2CountMap = new HashMap<>(fileLines.size() / 2);
            List<String> noArgsUrls = new LinkedList<>();
            ConcurrentSkipListSet<UrlCountNode> skipListSet = new ConcurrentSkipListSet<>();

            //===================post and get count===================
            fileLines.parallelStream().forEach(line -> {
                if (line.startsWith(Constants.GET)) {
                    getCount.getAndIncrement();
                } else if (line.startsWith(Constants.POST)) {
                    postCount.getAndIncrement();
                }
                lineCount.getAndIncrement();

                //===================将URL去除参数和方法前缀===================
                int first = line.indexOf("/");
                int end = line.contains("?") ? line.indexOf("?") : line.length();
                String noArgUrl = line.substring(first, end);
                noArgsUrls.add(noArgUrl);

                Integer count = url2CountMap.get(noArgUrl);
                if( count == null || count == 0) {
                    url2CountMap.put(noArgUrl, 1);
                } else {
                    url2CountMap.put(noArgUrl, ++count);
                }
            });

            //===================方法一：URL按照 URL，次数的格式存在Map中，再按照Map的value进行排序===================
            List<Map.Entry<String, Integer>> mapList = new LinkedList<>(url2CountMap.entrySet());
            mapList.sort((o1, o2) -> o2.getValue() - o1.getValue());


            //===================result===================
            System.out.println("1. 请求总量：" + lineCount.get());
            System.out.println("2. 请求最频繁的 10 个 HTTP 接口，及其相应的请求数量：");
            for (int i = 0; i < 10; i++) {
                Map.Entry<String, Integer> stringIntegerEntry = mapList.get(i);
                System.out.println(stringIntegerEntry.getKey() + "，" + stringIntegerEntry.getValue());

            }
            System.out.println("3. POST 和 GET 请求量分别为多少：" + postCount.get() + "，" + getCount.get());
            System.out.println("4. URI 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类，输出各个类别\n" +
                    "下 URI 都有哪些：");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计最频繁的10个接口（从静态文件中读取），不同的方法的性能测试
     * 1. 对于静态文件，可以使用排序去做
     *  1.1 用Map存储（Map内部保存了Node结构体），按value排序【快排】
     *  1.2 构建结构体（还得保存结构体集合），放入【最小堆】排序。对于1.1 效率会低（因为每次插入都要调整结构），但是可以实时统计
     * 2. 对于动态文件，实时排行榜，需要构建排序的集合
     */
    @Test
    public void effectCheck() throws IOException {
        //===================init===================
        List<String> fileLines = Files.readLines(Constants.FILE1, Charset.defaultCharset());
        long s = System.currentTimeMillis();


        Map<String, Integer> url2CountMap = new HashMap<>(fileLines.size() / 2);
        fileLines.parallelStream().forEach(line -> {

            //===================将URL去除参数和方法前缀===================
            int first = line.indexOf("/");
            int end = line.contains("?") ? line.indexOf("?") : line.length();
            String noArgUrl = line.substring(first, end);

            Integer count = url2CountMap.get(noArgUrl);
            if( count == null || count == 0) {
                url2CountMap.put(noArgUrl, 1);
            } else {
                url2CountMap.put(noArgUrl, ++count);
            }
        });
        //===================方法一：URL按照 URL，次数的格式存在Map中，再按照Map的value进行排序，总耗时：94-116===================
        List<Map.Entry<String, Integer>> mapList = new LinkedList<>(url2CountMap.entrySet());
        mapList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> stringIntegerEntry = mapList.get(i);

        }


        //===================方法二：假定是实时的（可以通过每读取一个Url统计一次来模拟），那么访问的时间要比修改的时间多
        // 1. 可以使用跳表的模式去实现
        // 2. 如果用Map存储，===================


        long e = System.currentTimeMillis();
        System.out.println("总耗时：" + (e-s));
    }



    /**
     * str.substring(str.indexOf("/"), str.indexOf("/", 1));
     * str.substring(str.indexOf("/"), str.indexOf("/", str.indexOf("/") + 1))
     * <p>
     * 分组写法一：先分组，再截取去除参数的uri
     *
     * @throws IOException
     */
    @Test
    public void groupingOne() throws IOException {
        List<String> lines = Files.readLines(Constants.FILE1, Charset.defaultCharset());
        Map<String, List<String>> collect = lines.stream()
                .distinct()
                // 先按url格式进行分组
                .collect(Collectors.groupingBy(str -> {
                    int first = str.indexOf("/");
                    int end = str.indexOf("/", first + 1);
                    if (end == -1) {
                        end = str.length();
                    }
                    return str.substring(first, end);
                    // 截取去除参数的uri
                }, Collectors.mapping(str -> {
                    int first = str.indexOf("/");
                    int end = str.contains("?") ? str.indexOf("?") : str.length();
                    return str.substring(first, end);
                }, Collectors.toList())));

        Set<Map.Entry<String, List<String>>> entries = collect.entrySet();
        entries.forEach(System.out::println);

//        String str ="GET /twell/querytwellDetailForMobile.htm?arg1=var1&arg2=var2";
//        for (int i = 0; i < lines.size(); i++) {
//            String str = lines.get(i);
//            try {
//                String substring = str.substring(str.indexOf("/"), str.indexOf("/", str.indexOf("/") + 1));
//                System.out.println(substring);
//            } catch (Exception e) {
//                System.out.println(str + "========================错误，" + str.indexOf("/", str.indexOf("/") + 1));
//            }
//        }
    }

    /**
     * 分组写法二：先map截取去除参数的uri，再进行分组
     * 与写法一的结果一致
     * @throws IOException
     */
    @Test
    public void groupingTwo() throws IOException {
        List<String> lines = Files.readLines(Constants.FILE1, Charset.defaultCharset());
        Map<String, List<String>> collect = lines.stream()
                .distinct()
                // 先map截取去除参数的uri
                .map(str -> {
                    int first = str.indexOf("/");
                    int end = str.contains("?") ? str.indexOf("?") : str.length();
                    return str.substring(first, end);
                })
                // 对结果集进行分组
                .collect(Collectors.groupingBy(str -> {
                    int first = str.indexOf("/");
                    int end = str.indexOf("/", first + 1);
                    if (end == -1) {
                        end = str.length();
                    }
                    return str.substring(first, end);
                }, Collectors.toList()));

        Set<Map.Entry<String, List<String>>> entries = collect.entrySet();
        entries.forEach(System.out::println);
    }

    public void top10() throws IOException {
        List<String> lines = Files.readLines(Constants.FILE1, Charset.defaultCharset());
















    }

}
