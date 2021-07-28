package lang.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author: hcl
 * @date: 2021/7/26 11:04
 */
public class LinkedListDemo {
    /**
     * 测试移除
     */
    @Test
    public void forRemove() {
        LinkedList<Object> objects = new LinkedList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);

        // for循环本质会生成一个迭代器
        for (Object object : objects) {
            // 在迭代器遍历时的next方法会判断count，而list.remove是不会修改count的
            objects.remove(object);
        }

        Iterator<Object> iterator = objects.iterator();
        while(iterator.hasNext()) {
            // iterator的remove会修改count方法
            iterator.remove();
        }
    }

    @Test
    public void iteratorTest() {
        LinkedList<Object> objects = new LinkedList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        Iterator<Object> iterator = objects.iterator();
        while(iterator.hasNext()) {
            // iterator的remove会修改count方法
            iterator.remove();
        }
    }
}
