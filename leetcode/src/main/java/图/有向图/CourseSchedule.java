package 图.有向图;

import java.util.Stack;

/**
 * 课程表
 * https://leetcode.cn/problems/course-schedule/
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 * @author: chonglin.hu
 * @date: 2022/12/23 16:19
 */
public class CourseSchedule {
    private boolean[] marked;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    // [[1,0]]
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        marked = new boolean[prerequisites.length];
        onStack = new boolean[prerequisites.length];

        for (int v = 0; v < prerequisites.length; v++) {
            if (!marked[v]) {
                dfs(prerequisites, v);
            }
            if (cycle != null) {
                return false;
            }
        }
        return true;
    }

    public void dfs(int[][] prerequisites, int v) {
        int[] wList = prerequisites[v];
        marked[v] = true;
        onStack[v] = true;
        for (int i = 0; i < wList.length; i++) {
            int w = wList[i];
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                dfs(prerequisites, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
            }
            onStack[v] = false;
        }
    }
}
