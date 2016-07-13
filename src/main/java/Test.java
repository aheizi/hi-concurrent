import javafx.concurrent.Task;

import java.util.concurrent.*;

/**
 * Created by noly on 16/7/13.
 */
public class Test {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println("子进程:" + Thread.currentThread().getId() + "在运行");
                return String.valueOf(Thread.currentThread().getId());
            }
        });

        System.out.println("主线程执行任务开始");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("主线程执行任务结束");


        try{
            System.out.println("子线程进行结果" + future.get());
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }


    }

}
