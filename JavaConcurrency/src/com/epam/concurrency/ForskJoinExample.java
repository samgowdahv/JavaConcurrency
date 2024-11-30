package com.epam.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForskJoinExample extends RecursiveTask<Integer> {
    private List<Integer> input;

    private ForskJoinExample(List<Integer> input) {
        this.input = input;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        long starrtime = System.currentTimeMillis();
        ForskJoinExample task = new ForskJoinExample(Arrays.asList(1, 3, 4, 8, 9, 45, 8, 9, 23));
        System.out.println(forkJoinPool.invoke(task));
        long endTime = System.currentTimeMillis();
        try {
            long result = Math.subtractExact(endTime, starrtime);
            System.out.print(" The current time taken to process the request is " + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected Integer compute() {
        return calculateSum(input);
    }

    private Integer calculateSum(List<Integer> input) {

        if (input.size() > 10) {
            int mid = input.size() / 2;
            List<Integer> firstList = input.subList(0, mid);
            List<Integer> secondList = input.subList(mid, input.size());
            ForskJoinExample task1 = new ForskJoinExample(firstList);
            ForskJoinExample task2 = new ForskJoinExample(secondList);
            task1.fork();
            task2.fork();
            int output = task1.join() + task2.join();
            return output;
        } else {

            return input.stream().reduce((Integer::sum)).get();

        }


    }
}
