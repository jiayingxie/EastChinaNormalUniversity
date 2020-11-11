import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// refer teaching code.
class SumTask extends RecursiveTask<Integer> implements Callable<Integer> {
    private static final int threadhold = 5;
    private int startIndex;
    private int endIndex;
    private int[] arr;

    public SumTask(int startIndex, int endIndex, int[] arr) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arr = arr;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        for (int i = startIndex; i < endIndex; ++i) {
            if (arr[i] == 50) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        if (endIndex - startIndex <= threadhold) {
            for (int i = startIndex; i < endIndex; ++i) {
                if (arr[i] == 50) {
                    count += 1;
                }
            }
        } else {
            int mid = startIndex + (endIndex - startIndex) / 2;
            SumTask task1 = new SumTask(startIndex, mid, arr);
            SumTask task2 = new SumTask(mid, endIndex, arr);

            invokeAll(task1, task2);

            int count1 = task1.join();
            int count2 = task2.join();

            count = count1 + count2;
        }
        return count;
    }
}

public class Chap05 {
    // generate random array.
    static void generateRandomArray(int[] myArray){
        for (int i = 0; i < myArray.length; ++i) {
            myArray[i] = (int) (Math.random() * 100 + 1);
        }
    }

    static int sumMySequential(int[] arr){
        int totalCount = 0;
        for (int i : arr) {
            if (i == 50) {
                totalCount += 1;
            }
        }

        return totalCount;
    }

    // refer teaching code.
    static int sumUseExecutors(int[] arr) {
        int totalCount = 0;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        List<Future<Integer>> ansList = new ArrayList<>();
        int interval = arr.length / 4;

        for (int i = 0; i < 4; ++i) {
            SumTask t = new SumTask(i * interval, (i + 1) * interval, arr);
            Future<Integer> ans = executor.submit(t);
            ansList.add(ans);
        }

        for (Future<Integer> f: ansList) {
            try {
                totalCount += f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // close executor
        executor.shutdown();

        return totalCount;
    }

    // refers teaching code.
    static int sumUseForkJoin(int[] arr) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(0, arr.length, arr);
        ForkJoinTask<Integer> ans = pool.submit((ForkJoinTask<Integer>) task);
        return ans.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] myArray = new int[10000];
        generateRandomArray(myArray);

        System.out.println("串行搜索得到50的个数是"
                + sumMySequential(myArray) + "个。");
        System.out.println("Executors搜索得到50的个数是"
                + sumUseExecutors(myArray) + "个。");
        System.out.println("Fork-Join搜索得到50的个数是"
                + sumUseForkJoin(myArray) + "个。");
    }
    /*
    * console output:
串行搜索得到50的个数是110个。
Executors搜索得到50的个数是110个。
Fork-Join搜索得到50的个数是110个。
    * */
}
