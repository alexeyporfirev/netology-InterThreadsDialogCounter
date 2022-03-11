package application;

import threads.MyCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        usingSubmit();
//        usingInvokeAll();
//        usingInvokeAny();
    }


    /**
     * Реализация запуска задач через метод submit()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void usingSubmit() throws ExecutionException, InterruptedException {
        int totalMessages = 0;
        int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();

        final ExecutorService threadPool = Executors.newFixedThreadPool(numberOfAvailableProcessors);
        final List<Future<Integer>> tasks = new ArrayList<Future<Integer>>();

        for(int i = 0; i < numberOfAvailableProcessors; i++) {
            final MyCallable myCallable = new MyCallable();
            final Future<Integer> task = threadPool.submit(myCallable);
            tasks.add(task);
        }

        for(Future<Integer> result: tasks) {
            totalMessages += result.get();
        }
        threadPool.shutdown();

        System.out.println("Всего отправлено " + totalMessages + " сообщений.");
    }

    /**
     * Реализация запуска задач через метод invokeAll()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public  static void usingInvokeAll() throws InterruptedException, ExecutionException {
        int totalMessages = 0;
        int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();

        final ExecutorService threadPool = Executors.newFixedThreadPool(numberOfAvailableProcessors);

        List<MyCallable> calls = new ArrayList<MyCallable>();
        for (int i = 0; i < numberOfAvailableProcessors; i++) {
            calls.add(new MyCallable());
        }
        final List<Future<Integer>> tasks = threadPool.invokeAll(calls);

        for(Future<Integer> result: tasks) {
            totalMessages += result.get();
        }
        threadPool.shutdown();
        System.out.println("Всего отправлено " + totalMessages + " сообщений.");
    }


    /**
     * Реализация запуска задач через метод invokeAny()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void usingInvokeAny() throws ExecutionException, InterruptedException {
        int totalMessages = 0;
        int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();

        final ExecutorService threadPool = Executors.newFixedThreadPool(numberOfAvailableProcessors);

        List<MyCallable> calls = new ArrayList<MyCallable>();
        for (int i = 0; i < numberOfAvailableProcessors; i++) {
            calls.add(new MyCallable());
        }

        totalMessages = threadPool.invokeAny(calls);
        threadPool.shutdown();
        System.out.println("Всего отправлено " + totalMessages + " сообщений.");
    }

}