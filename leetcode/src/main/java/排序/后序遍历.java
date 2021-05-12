package 排序;

/**
 * @author huchonglin
 * @date 2021/3/2 21:18
 */
public class 后序遍历 {
//    public void posOrderUnRecur2(Node h) {
//        System.out.print("pos-order: ");
//        if (h != null) {
//            Stack<Node> stack = new Stack<Node>();
//            stack.push(h);
//            Node c = null;
//            while (!stack.isEmpty()) {
//                c = stack.peek();
//                if (c.left != null && h != c.left && h != c.right) {
//                    stack.push(c.left);
//                } else if (c.right != null && h != c.right) {
//                    stack.push(c.right);
//                } else {
//                    System.out.print(stack.pop().value + " ");
//                    h = c;
//                }
//            }
//        }
//        System.out.println();
//    }
}
