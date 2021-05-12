package 算法;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author huchonglin
 * @date 2020/12/24 13:47
 */
public class 树 {

    /**
     * 监控二叉树
     *
     * @date 2020/12/24 13:49
     * @author huchonglin
     * @param root
     * @return
     */
    @Test
    public int minCameraCover(TreeNode root) {
        int result = 0;
        if(dfs(root)==1){
            result++;
        }
        return result;
    }
    // 0：可被观测但无监控，上一层节点为1
    // 1：不可被观测到，上一层节点为2
    // 2：有摄像机，上一层节点为0
    private int dfs(TreeNode root){
        if(root==null){
            return 0;
        }
        LinkedList<Object> objects = new LinkedList<>();
        ArrayList<Object> objects1 = new ArrayList<>();
//        int leftStatus = dfs(root.left);
//        int rightStatus = dfs(root.right);
//        if(leftStatus == 1 || rightStatus == 1){
//            result ++;
//            return 2;
//        }else if(leftStatus == 2 || rightStatus == 2){
//            return 0;
//        }else{
//            return 1;
//        }
        return 0;
    }
    //=============================================
}
