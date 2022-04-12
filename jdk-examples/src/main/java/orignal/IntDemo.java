package orignal;

import java.text.NumberFormat;

/**
 * @author: chonglin.hu
 * @date: 2022/4/6 20:04
 */
public class IntDemo {
    public static void main(String[] args) {
        IntDemo intDemo = new IntDemo();
        intDemo.tets();
    }
    /**
     * 计算百分比
     */
    public void tets() {
        int success = 2;
        int count = 112111;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        String format = numberFormat.format((float) success / (float) count * 100);

        System.out.println(Integer.parseInt(numberFormat.format(
                (float) (22 - (int) 11) / (float) 22 * 100)));
        System.out.println(format);
    }
}
