package com.epam.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class CompletableFeature {

    public static int generate() throws InterruptedException {
        System.out.println(" I am doing the task" + Thread.currentThread());
        //Thread.sleep(2000);
        return 2;
    }

    public static void printIt(int num){
    System.out.println("The number is "+num + "  " + Thread.currentThread());

    }
    public static void main(String[] args) throws Exception {


        CompletableFuture.runAsync(() -> System.out.println("Do work"));
//                        .thenAccept( );

        // With this approach we block and wait for the result

//       System.out.println( CompletableFuture.supplyAsync(() -> {
//           try {
//               return generate();
//           } catch (InterruptedException e) {
//               throw new RuntimeException(e);
//           }
//       }).getNow(-1));

//Demonstrate how computable threads works internally
//        ForkJoinPool pool1 = new ForkJoinPool(10);
//        ForkJoinPool pool2 = new ForkJoinPool(5);
//        CompletableFuture<Integer> now = CompletableFuture.supplyAsync(() -> {
//            try {
//                return generate();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },pool1);
       // Thread.sleep(2000);
        /*If we use the thenAcceptAsync method then we can pass the executor that needs to be used to run the task
        if we use then accept then we cannot give executor it will use main thread along with fork join pool

         */
      //  now.thenAcceptAsync(CompletableFeature::printIt,pool2);
        //Thread.sleep(2000);

        //System.out.println(" I am in main ");


    }
}
