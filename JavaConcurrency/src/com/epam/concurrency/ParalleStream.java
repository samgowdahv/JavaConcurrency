package com.epam.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParalleStream {

        public static int compute(int a)
        {
           System.out.println( "Transform "+ a + " " + Thread.currentThread().getName());
            return a;

        }

        static  int add(int total , int e)
        {
            int result1 = total + e;
           System.out.println("total: " +total+ "e: " + e + " result: " + result1);
            return result1;
        }

        static int transform(int num) throws InterruptedException {

            System.out.println("Transform " + num + " "+ Thread.currentThread());
            Thread.sleep(2000);
            return num;
        }

    private static void process(Stream<Integer> integerStream) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(50);

        forkJoinPool.submit( () -> integerStream.forEach(e -> {}));
        forkJoinPool.shutdown();
        forkJoinPool.awaitQuiescence(2, TimeUnit.SECONDS);

    }


    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);
        int result =0;
        for (int a : input)
        {
            if (a % 2 == 0)
                result += a * 2;
        }
     //   System.out.println(result);

        // There are 2 way to implement the parallel stream you can accept the stream and create the parallel stream
        int sum = input.stream().parallel().filter(a -> a % 2 == 0).map(a -> a * 2).mapToInt(i -> i).sum();
       // System.out.println(sum);

        //If you are originator of stream then you can create use parallel stream
//        int sum1 = input.parallelStream().filter(a -> a % 2 == 0).map(a -> a * 2).mapToInt(i -> i).sum();
//        System.out.println(sum1);

        //Showcase how parallel stream and stream for working
//        input.stream().map(ParalleStream::compute).forEach(System.out::println);
//        input.parallelStream().map(ParalleStream::compute).forEach(System.out::println);
//        input.stream().parallel().map(ParalleStream::compute).forEach(System.out::println);
        //
      //  input.stream().sequential().parallel().map(ParalleStream::compute).forEach(System.out::println);

       // input.stream().parallel().map(ParalleStream::compute).forEachOrdered(System.out::println);

        // You can use parallel when identify value is zero , Evan sum method will have initial identify valeu as zero
       // System.out.println(input.stream().parallel().reduce(0,(total ,e) -> add(total , e)));
        //If the initial value is some different value then do not use the parallel stream will result in inaccurate result.
        //Expected result is 55 but gave 265
       // System.out.println(input.stream().parallel().reduce(0,(total ,e) -> add(total , e)));

//        input.parallelStream().map(num -> {
//            try {
//                return transform(num);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).forEachOrdered(e -> {});
        // It will give numbers of cores in your system
      //  System.out.println(Runtime.getRuntime().availableProcessors());

//        ForkJoinPool pool = ForkJoinPool.commonPool();
//        System.out.println(pool);

        // Configure thread Programatically  for threads
        Stream<Integer> integerStream = input.parallelStream().map(num -> {
            try {
                return transform(num);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        process(integerStream);




    }


}
