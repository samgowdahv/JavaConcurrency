package com.epam.concurrency;

class StaticExample {
    private static volatile int count = 0;

    public static void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

public class StaticVolatileExample {

    public static void main(String[] args) {
        StaticExample.increment();
        StaticExample.increment();
        System.out.println(" The count is " + StaticExample.getCount());

    }

}
