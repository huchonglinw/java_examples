package lambda;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import po.Goods;
import po.父子类.Base;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author huchonglin
 * @date 2020/11/24 18:09
 */
public class 多条件分组 {
    public static void main(String[] args) {
        /**
         * 返回像 [xx,xxx,xx]的字段
         */

    }

    /**
     * 编码 规格 批次
     */
    Goods goods1 = new Goods("编码A", "编码A规格A", "编码A规格A批次A");
    Goods goods2 = new Goods("编码A", "编码A规格A", "编码A规格A批次B");
    Goods goods3 = new Goods("编码A", "编码A规格B", "编码A规格B批次A");
    Goods goods4 = new Goods("编码A", "编码A规格B", "编码A规格B批次B");
    Goods goods5 = new Goods("编码B", "编码B规格A", "编码B规格A批次A");
    Goods goods6 = new Goods("编码B", "编码B规格A", "编码B规格A批次B");
    Goods goods7 = new Goods("编码B", "编码B规格B", "编码B规格B批次A");
    Goods goods8 = new Goods("编码C", "编码B规格B", "编码B规格B批次B");
    Goods goods9 = new Goods("编码C", "编码B规格B", "编码B规格B批次C");
    List<Goods> goodsList = Arrays.asList(goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8, goods9);

    /**
     * key:编码+规格，value第一个goods
     */
    @Test
    public void Test2() {
        Map<String, Optional<Goods>> collect = goodsList.stream().collect(Collectors.groupingBy(g -> g.getGoodsCode() + g.getGoodsSpecs(), Collectors.reducing((a, b) -> a)));
        collect.entrySet().forEach(k -> {
            System.out.println(k.getKey() +","+ k.getValue());
        });
    }

    /**
     * 按照编码和规格分组
     */
    @Test
    public void test2() {
        Collection c = goodsList.parallelStream().map( g -> {

            Map<String,Goods> map = new HashMap<>(16);
            map.put(g.getGoodsCode() + g.getGoodsSpecs() ,g);
            return map;

        }).reduce((m1, m2) -> {

            Map<String,Goods> r = new HashMap<>();
            m1.keySet().forEach(k1 ->{
                String k2 = m2.keySet().iterator().next();
                if(k1.equals(k2)){
                    Goods g1 = m1.get(k1);
                    Goods g2 = m2.get(k1);
                    g1.setGoodsBatch(g1.getGoodsBatch() + "," + g2.getGoodsBatch());
                }else{
                    r.putAll(m2);
                }
            });
            r.putAll(m1);
            return r;
        }).orElse(new HashMap<>()).values();

        List<Goods> goods11  = new ArrayList<>(c);

        System.out.println(goods11);

    }

    // [Goods(goodsCode=编码A, goodsSpecs=编码A规格B, goodsBatch=编码A规格B批次A,编码A规格B批次B), Goods(goodsCode=编码B, goodsSpecs=编码B规格A, goodsBatch=编码B规格A批次A,编码B规格A批次B), Goods(goodsCode=编码B, goodsSpecs=编码B规格B, goodsBatch=编码B规格B批次A,编码B规格B批次B,编码B规格B批次3), Goods(goodsCode=编码A, goodsSpecs=编码A规格A, goodsBatch=编码A规格A批次A,编码A规格A批次B)]
    // [Goods(goodsCode=编码B, goodsSpecs=编码B规格A, goodsBatch=编码B规格A批次A,编码B规格A批次B), Goods(goodsCode=编码B, goodsSpecs=编码B规格B, goodsBatch=编码B规格B批次A,编码B规格B批次B,编码B规格B批次3), Goods(goodsCode=编码A, goodsSpecs=编码A规格B, goodsBatch=编码A规格B批次A,编码A规格B批次B), Goods(goodsCode=编码A, goodsSpecs=编码A规格A, goodsBatch=编码A规格A批次A,编码A规格A批次B)]
    // [Goods(goodsCode=编码A, goodsSpecs=编码A规格B, goodsBatch=编码A规格B批次A,编码A规格B批次B), Goods(goodsCode=编码A, goodsSpecs=编码A规格A, goodsBatch=编码A规格A批次A,编码A规格A批次B), Goods(goodsCode=编码B, goodsSpecs=编码B规格A, goodsBatch=编码B规格A批次A,编码B规格A批次B), Goods(goodsCode=编码B, goodsSpecs=编码B规格B, goodsBatch=编码B规格B批次A,编码B规格B批次B,编码B规格B批次3)]
    @Test
    public void test3() {
        List<Goods> goods12 =goodsList.stream().collect(Collectors.groupingBy(g -> g.getGoodsCode() + g.getGoodsSpecs(),Collectors.toList()))
                .values().stream().map(l -> {
                   Goods ng = new Goods();
                   l.forEach(goods -> {
                       ng.setGoodsCode(goods.getGoodsCode());
                       ng.setGoodsSpecs(goods.getGoodsSpecs());
                       ng.setGoodsBatch((ng.getGoodsBatch() == null ? "" : (ng.getGoodsBatch() + ","))  + goods.getGoodsBatch());
                   });
                   return ng;
        }).collect(Collectors.toList());

        System.out.println(goods12);
    }

    /**
     * 有一个实体对象集合
     * 实体对象的属性有多个String类型的，比如有创建人id，采购人id，领用人id，需要将所有的String抽取成一个集合
     */
    @Test
    public void test5(){
        Base base = new Base();
        base.setName("1");
        base.setTelphone("2");
        Base base1 = new Base();
        base1.setName("3");
        base1.setTelphone("4");
        LinkedList<Base> objects = new LinkedList<>();
        objects.add(base);
        objects.add(base1);
        List<String> strings = objects.stream().map(o -> {
            List<String> list = new LinkedList<>();
            String name = o.getName();
            String telphone = o.getTelphone();
            if (StringUtils.isNotEmpty(name)) {
                list.add(name);
            }
            if (StringUtils.isNotEmpty(telphone)) {
                list.add(telphone);
            }
            return list;
        }).reduce((x, y) -> {
            x.addAll(y);
            return x;
        }).orElse(null);
        System.out.println(strings);
    }

    @Test
    public void test4(){
//        goodsList.stream().collect(Collectors.groupingBy(g -> g.getGoodsCode() + g.getGoodsSpecs(),Collectors.toList()))
//                .values().stream().map(l ->{
//                    l.stream().collect(Collectors.joining(","));
//        })
    }


    @Test
    public void test() {

        List<Goods> goods10 = goodsList.stream()
                .collect(Collectors.groupingBy(Goods::getGoodsCode,
                        Collectors.groupingBy(Goods::getGoodsSpecs)))
                .values()
                .stream()
                .map(p -> p.values()
                        .stream()
                        .map(g -> {
                            Goods goods = g.get(0);
                            goods.setGoodsBatch(g
                                    .stream()
                                    .map(Goods::getGoodsBatch)
                                    .distinct()
                                    .reduce((x, y) -> x.concat(",".concat(y)))
                                    .orElse(null));
                            return goods;
                        }).collect(Collectors.toList()))
                .reduce((x, y) -> {
                    x.addAll(y);
                    return x;
                }).orElse(null);
        System.out.println(goods10);
    }

    /**
     * 方法二
     */
    public void test1(){
//        Function<Goods,List<Object>> key = wr -> Arrays.asList(wr.getGoodsCode(),wr.getGoodsBatch());
//        goodsList.stream()
//                .collect(Collectors.groupingBy(key,Collectors.);
    }

    @Test
    public void test51(){
        long l1 = 20481;
        System.out.println(l1/1024+","+l1 % 1024);
        Long aLong = Long.getLong("20");
        Long aLong1 = Long.valueOf("20");
        System.out.println(aLong1);
    }

    @Test
    public void test23() {
        Map<String, Goods> collect = goodsList.stream()
                .collect(Collectors.toMap(s -> {
                    return s.getGoodsSpecs() + 1;
                }, Function.identity()));
    }
}
