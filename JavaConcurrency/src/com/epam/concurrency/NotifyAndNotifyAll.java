package com.epam.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotifyAndNotifyAll {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i < 10 ; i++)
                {
                   System.out.println("Thread Name is " + Thread.currentThread().getName());
                }
            }
        });


    }



}
