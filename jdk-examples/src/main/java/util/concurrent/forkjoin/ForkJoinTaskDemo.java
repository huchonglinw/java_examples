package util.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * demo2:求1+2+3+4的结果
 *
 * Fork/Join框架分割任务，将每个子任务最多执行两个数的相加，那么我们设置分割的阈值是2，由于是4个数字相加，
 * 所以Fork/Join框架会把这个任务fork成两个子任务，子任务一负责计算1+2，子任务二负责计算3+4，然后再join
 * 两个子任务的结果。因为是有结果的任务，所以必须继承RecursiveTask
 */
public class ForkJoinTaskDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1,4);
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println("计算结果=" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CountTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = -7524245439872879478L;

        private static final int THREAD_HOLD = 2;

        private int start;
        private int end;

        public CountTask(int start,int end){
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //如果任务足够小就计算
            boolean canCompute = (end - start) <= THREAD_HOLD;
            if(canCompute){
                for(int i=start;i<=end;i++){
                    sum += i;
                }
            }else{
                int middle = (start + end) / 2;
                CountTask left = new CountTask(start,middle);
                CountTask right = new CountTask(middle+1,end);
                //执行子任务
                left.fork();
                right.fork();
                //获取子任务结果
                int lResult = left.join();
                int rResult = right.join();
                sum = lResult + rResult;
            }
            return sum;
        }
    }
}

