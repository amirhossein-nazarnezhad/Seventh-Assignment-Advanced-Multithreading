package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    Semaphore semaphore;

    public Operator(Semaphore semaphore , String name) {
        super(name);
        this.semaphore=semaphore;
    }

    @Override
    public void run()
    {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 10; i++)
        {
            Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        semaphore.release();
    }
}
