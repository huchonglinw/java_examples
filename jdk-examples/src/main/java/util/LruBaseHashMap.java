package util;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于HashMap简单实现LRU
 *
 * @author: hcl
 * @date: 2021/7/20 16:32
 */
public class LruBaseHashMap<K, V> {
    private final Map<K, V> hashMap;

    private final Integer lruSize;

    private final Node<K> head = new Node<>(null);

    private final Node<K> tail = new Node<>(null);

    public LruBaseHashMap(int lruSize) {
        this.hashMap = new HashMap<>();
        this.lruSize = lruSize;
    }

    {
        head.next = tail;
        tail.pre = head;
    }

    private static class Node<K> {
        K key;
        Node<K> next;
        Node<K> pre;

        public Node(K key) {
            this.key = key;
        }
    }

    public V put(K key, V value) {
        V res = hashMap.put(key, value);
        putList(key);
        return res;
    }

    public V get(K key) {
        V value = hashMap.get(key);
        if (value != null) {
            putList(key);
        }
        return value;
    }

    private boolean isFull() {
        return hashMap.size() > lruSize;
    }

    private void removeLast() {
        Node<K> temp = tail.pre;
        hashMap.remove(temp.key);
        temp.pre.next = tail;
        tail.pre = temp.pre;
        temp = null; // help gc
    }

    private void putList(K key) {
        if (isFull()) {
            removeLast();
        }

        Node<K> curNode = find(key);
        if (curNode == null) {
            curNode = new Node<>(key);
        } else {
            // 1. 将curNode前后连接起来
            Node<K> next = curNode.next;
            Node<K> pre = curNode.pre;
            next.pre = pre;
            pre.next = next;
        }
        // 2. 将curNode移动到next后面

        Node<K> next = head.next;
        head.next = curNode;
        curNode.pre = head;

        if (next != null) {
            next.pre = curNode;
            curNode.next = next;
        }
    }

    private Node<K> find(K key) {
        if (head.next == null) {
            return null;
        }
        Node<K> cur = head.next;
        do {
            if (key == cur.key) {
                return cur;
            }
            cur = cur.next;
        } while (cur != null);

        return null;
    }

    public int size() {
        return hashMap.size();
    }


    public static void main(String[] args) {
        LruBaseHashMap<Object, Object> objectObjectSimpleLruContext = new LruBaseHashMap<>(4);
        objectObjectSimpleLruContext.put(1, 2);
        objectObjectSimpleLruContext.put(6, 7);
        objectObjectSimpleLruContext.put(4, 5);
        objectObjectSimpleLruContext.put(2, 3);
        objectObjectSimpleLruContext.put(9, 10);
        System.out.println(objectObjectSimpleLruContext.get(1)); // null
        System.out.println(objectObjectSimpleLruContext.get(1)); // null
        System.out.println(objectObjectSimpleLruContext.get(1)); // null
        System.out.println(objectObjectSimpleLruContext.get(1)); // null
        System.out.println(objectObjectSimpleLruContext.get(6)); // 7
        System.out.println(objectObjectSimpleLruContext.get(6)); // 7
        System.out.println(objectObjectSimpleLruContext.get(6)); // 7
        System.out.println(objectObjectSimpleLruContext.get(6)); // 7
        System.out.println(objectObjectSimpleLruContext.get(6)); // 7
        System.out.println(objectObjectSimpleLruContext.get(64)); // null
        System.out.println(objectObjectSimpleLruContext.get(4)); // 5
        System.out.println(objectObjectSimpleLruContext.get(6)); // 7
        System.out.println(objectObjectSimpleLruContext.get(9)); // 10
        System.out.println(objectObjectSimpleLruContext.get(9)); // null
        System.out.println(objectObjectSimpleLruContext.size()); // 4
        objectObjectSimpleLruContext.put(7, 8);
        System.out.println(objectObjectSimpleLruContext.get(6)); // null
        System.out.println(objectObjectSimpleLruContext.size()); // 4
    }


}
