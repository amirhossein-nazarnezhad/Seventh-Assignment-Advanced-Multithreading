package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     * Experiment with different algorithms to find accurate results.
     * <p>
     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.
     *
     * @return pi in string format (the string representation of the BigDecimal object)
     */



    public static class PI implements Runnable{
        int n;
        int floatingPoint;
        MathContext mc = new MathContext(1002);

        public PI(int n, int floatingPoint) {
            this.n = n;
            this.floatingPoint = floatingPoint;
        }

        @Override
        public void run() {
            BigDecimal temp = new BigDecimal(2);
            BigDecimal n2 = temp.pow(this.n +1);  // 2^ n+1
            BigDecimal n1 = (fact(this.n)).pow(2);  // n! ^ 2
            BigDecimal n = n1.multiply(n2, mc);
            BigDecimal denominator = fact(2* this.n +1);
            BigDecimal result = n.divide(denominator, mc);
            addTouSum(result);
        }

        public BigDecimal fact(int x)
        {
            BigDecimal t = new BigDecimal(1);
            for (int i = 1; i <= x; i++)
            {
                t = t.multiply(new BigDecimal(i), mc);
            }

            return t;
        }
    }
    public static BigDecimal sum;

    public static synchronized void addTouSum(BigDecimal z){
        sum = sum.add(z);
    }

    public static String calculate(int floatingPoint)
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(16);
        sum = new BigDecimal(0);
        for (int i = 0 ; i < 5000; i++)
        {
            PI task = new PI(i, floatingPoint);
            threadPool.execute(task);
        }
        threadPool.shutdown();


        try {
            threadPool.awaitTermination(60002, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sum = sum.setScale(floatingPoint, RoundingMode.DOWN);
        return sum.toString();
    }


    public static void main(String[] args) {
        System.out.println( calculate(7));
    }
}
