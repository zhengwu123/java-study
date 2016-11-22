import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhengwu on 11/16/16.
 */
public class CountPrimes extends Thread{
    private long lower;
    private long upper;
    private static AtomicInteger numPrimes = new AtomicInteger(0);

    public CountPrimes(long lower,long upper){
        this.lower = lower;
        this.upper = upper;


    }
    @Override
    public void run() {
        for(long i=lower;i<=upper;i++){
            if(isPrime(i))
                numPrimes.incrementAndGet();

        }

        CountPrimesExecutor.decrementCountDownLatch();
    }

    public static int getNumPrimes(){
        return numPrimes.get();
    }

    public static void resetNumPrimes(){
        numPrimes.set(0);
    }

    private boolean isPrime(long num){
        if (num <= 1) return false;
        if (num == 2||num==3) return true;
        if (num % 2 == 0) return false;
        if (num % 3 == 0) return false;
        for (int i = 3;  i<= Math.sqrt(num); i += 2)
            if (num % i == 0) return false;
        return true;

    }

}
