import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by noly on 16/7/14.
 */
public class ListenableFutureTest {

    public static void main(String[] args){

        System.out.println("我是主线程,哈哈哈");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("正在执行子线程" + Thread.currentThread().getId());
                Thread.sleep(5000);
                return String.valueOf(Thread.currentThread().getId());
            }
        });

        System.out.println("我是主线程,嘻嘻嘻");

        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("子线程" + s + "执行完毕!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("任务执行失败!");
            }
        });

        System.out.println("我是主线程,嘿嘿嘿");
    }

}
