package 民生科技;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int goodsNumber = sc.nextInt();
        int[] goodsPrice = new int[goodsNumber];
        HashMap<Integer, Integer> priceSet = new HashMap<>();

        LinkedList<Integer> result = new LinkedList<>();
        // shuxing -> 最小价格
        // 属性 -> 价格
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int i = 0; i < goodsNumber; i++) {
            goodsPrice[i] = sc.nextInt();
        }
        for (int i = 0; i < goodsNumber; i++) {
            int type = sc.nextInt();
            int p = goodsPrice[i];
            PriorityQueue<Integer> pq = map.get(type);
            if(pq == null) {
                pq = new PriorityQueue<>();
            }
            pq.add(p);
            map.put(type,pq);
        }
        for (int i = 0; i < goodsNumber; i++) {
            int type = sc.nextInt();
            int p = goodsPrice[i];
            PriorityQueue<Integer> pq = map.get(type);
            if(pq == null) {
                pq = new PriorityQueue<>();
            }
            pq.add(p);
            map.put(type,pq);
        }

        int people = sc.nextInt();
        for (int i = 0; i < people; i++) {
            int type = sc.nextInt();

            PriorityQueue<Integer> pq = map.get(type);

            while(true) {
                Integer poll;
                if(pq == null || (poll = pq.poll()) == null) {
                    result.addLast(-1);
                    break;
                }

                Integer priceBit = priceSet.get(poll);
                if(priceBit == null || priceBit == 0) {
                    result.addLast(poll);
                    priceSet.put(poll, 1);
                    map.put(type,pq);
                    break;
                }
            }
        }

        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
