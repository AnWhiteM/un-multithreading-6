import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

       
        AtomicInteger atomicInteger = new AtomicInteger(0);

       
        List<Future<Integer>> futures = new ArrayList<>();

      
        for (int i = 0; i < 3; i++) {
            Callable<Integer> task = () -> {
                for (int j = 0; j < 10; j++) {
                    atomicInteger.incrementAndGet(); 
                }
                return atomicInteger.get(); 
            };
            futures.add(executor.submit(task)); 
        }

     
        for (Future<Integer> future : futures) {
            try {
               
                System.out.println("Результат: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}