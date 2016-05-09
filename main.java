import java.util.concurrent.*;

public class main {

    static int maxSearch = 50000000;
    static boolean[]  isPrime = new boolean[maxSearch];
    static int  currentPos = 2;
    static int threadCount = 18;

    

    public static void main(String[] args) { 
        // An Executor that provides methods to manage termination and methods
        // that can produce a Future for tracking progress of one or more asynchronous tasks.
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        int primes = 0;
        long startTime = System.nanoTime();

        // lets assume all integers are prime
        for (int i = 2; i < maxSearch; i++) {
            isPrime[i] = true;
        }
        

        // mark non-primes < N using Sieve of Eratosthenes
        for (int i = 2; i < maxSearch; i++) {
            if (isPrime[i]) {
                service.execute(new Threads(i));
            }
        }
        
        // wait for all threads to finish
        service.shutdown();

        try {
          service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
          System.out.println(e);
        }

        long timeTaken = System.nanoTime() - startTime;
        System.out.println("Time taken: " + timeTaken/Math.pow(10,9) + " seconds");

        for (int i = 2; i < maxSearch; i++) {
            if (isPrime[i]) primes++;
        }
        System.out.println("The number of primes < " + maxSearch + " is " + primes);
    }
}