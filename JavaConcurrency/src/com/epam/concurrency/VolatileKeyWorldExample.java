package com.epam.concurrency;

public class VolatileKeyWorldExample {
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread mythread = new Thread(() -> {
            while (running) {
                //Task given by your customer
            }
            System.out.println("Worker Thread Stopped");

        });
        mythread.start();
        Thread.sleep(1000);
        running = false;

    }
}
