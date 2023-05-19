package sbu.cs.Semaphore;

import java.time.LocalTime;

public class Resource {

    public static void accessResource() {
        try {
            LocalTime localTime = LocalTime.now();
            System.out.println("thread  name :" + Thread.currentThread().getName() + " time :" + localTime);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
