import java.util.concurrent.*;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(3);

       
        FutureTask<String> task1 = new FutureTask<>(new MyTask("First thread"));
        FutureTask<String> task2 = new FutureTask<>(new MyTask("Second thread"));
        FutureTask<String> task3 = new FutureTask<>(new MyTask("Third thread"));

       
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);

        try {
            
            System.out.println("First thread state: " + task1.get());
            System.out.println("Second thread state: " + task2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
           
            task3.cancel(true);
        }

      
        executor.shutdown();
    }

    
    static class MyTask implements Callable<String> {
        private final String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public String call() {
            try {
               
                System.out.println(name + " starts execution");
              
                Thread.sleep(2000);
               
                return name + " completed successfully";
            } catch (InterruptedException e) {
               
                System.out.println(name + " was interrupted");
                return name + " was interrupted";
            }
        }
    }
}