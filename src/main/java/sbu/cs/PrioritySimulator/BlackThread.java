package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlackThread extends ColorThread {

    private static final String MESSAGE = "hi blues, hi whites!";
    CountDownLatch Black;

    void printMessage()
    {
        super.printMessage(new Message(this.getClass().getName(), getMessage()));
    }

    public BlackThread(CountDownLatch black) {
        Black = black;
    }

    @Override
    String getMessage()
    {
        return MESSAGE;
    }

    @Override
    public void run() {
        printMessage();
        Black.countDown();
    }
}
