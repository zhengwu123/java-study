import java.util.concurrent.CountDownLatch;

/**
 * Created by zhengwu on 11/16/16.
 */
public class CountPrimesExecutor {
    private long lower;
    private long upper;
    private int numThreads;
    private  CountPrimes[] countPrimeThreads;
    private static CountDownLatch latch;
    public CountPrimesExecutor(int numThreads,long lower,long upper){
        this.numThreads = numThreads;
        latch = new CountDownLatch(numThreads);
        this.upper = upper;
        this.lower = lower;
        countPrimeThreads= new CountPrimes[numThreads];
        long distance = (int) ((upper-lower)/numThreads);

        for(int i=0;i<numThreads;i++){
                countPrimeThreads[i] = new CountPrimes(lower+(distance*i),Math.min(upper,lower+(distance*i)+distance-1));


        }


    }
    public static void decrementCountDownLatch() {
        latch.countDown();
    }
    public void executeThreads(){
        for(int i=0;i<numThreads;i++) {
            countPrimeThreads[i].start();
        }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


}
