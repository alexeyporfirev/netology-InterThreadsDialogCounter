package threads;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        int msgNumber = new Random(System.currentTimeMillis()).nextInt(10);
        while (i < msgNumber) {
            Thread.sleep(2000);
            System.out.printf("Всем привет! Это поток %s [msgNumber = %d]!\n", Thread.currentThread().getName(), msgNumber);
            i++;
        }
        return i;
    }

}
