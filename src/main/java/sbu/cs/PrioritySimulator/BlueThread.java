package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlueThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi whites!";
    CountDownLatch Blue;

    void printMessage()
    {
        super.printMessage(new Message(this.getClass().getName(), getMessage()));
    }

    public BlueThread(CountDownLatch blue) {
        Blue = blue;
    }

    @Override
    String getMessage()
    {
        return MESSAGE;
    }

    @Override
    public void run() {
        printMessage();
        Blue.countDown();
    }
}
