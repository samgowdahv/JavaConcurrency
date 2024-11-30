package com.epam.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class CompletableFeature1 {
    public static int generate() throws InterruptedException {
       throw new RuntimeException("OPPS something went wrong during processing");
      //  Thread.sleep(10000);
        // return 2;
    }

    public static void printIt(int val){
        System.out.println(val);
    }
    public  static  int processError(Throwable th){
        System.out.println("Error " + th.getMessage());
        return 0;
        //throw  new RuntimeException(" I hate you tell you this");
    }

    public static void main(String[] args) throws Exception,InterruptedException {
    // Here the origin of the value 2 from method reference
//        CompletableFuture.supplyAsync(CompletableFeature1::generate)
//                .thenApply(data -> data *2) // this is map , never give name other understand
//                .thenAccept(CompletableFeature1::printIt);
//Create the pipe line using the completable feature
//        CompletableFuture<Integer> future = new CompletableFuture<>();
//        future.thenApply(data -> data *2)
//                .thenAccept(CompletableFeature1::printIt);
//        Thread.sleep(200);
//
//        System.out.println(future.isCancelled());
//
//        future.complete(7);  // This is some thing like we are building the pipe line and asking some one use it

 //Handling the exception
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return generate();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(3000);
        integerCompletableFuture
                .exceptionally(CompletableFeature1::processError)

                .orTimeout(3,TimeUnit.SECONDS)
                .thenApply(data -> data *2)
                .thenAccept(CompletableFeature1::printIt);

       //  integerCompletableFuture.completeOnTimeout(74,2, TimeUnit.SECONDS);

          Thread.sleep(3000);
        ExecutorService ex = Executors.newFixedThreadPool(2);


    }
}
