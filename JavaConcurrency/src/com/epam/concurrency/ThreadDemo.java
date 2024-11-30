package com.epam.concurrency;

import java.time.LocalDateTime;

//Below 2 overloaded method are created
public class ThreadDemo extends  Thread {
    public void run(){
        for (int i =0 ; i <= 5 ;i++)
        {

            System.out.println("Thread " + Thread.currentThread().getName() +"Value is "+ i + " Curent time is " + LocalDateTime.now() +" The Current priority is "+ Thread.currentThread().getPriority());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int run(int a , int b){
            return a + b ;
    }



}
class Main {

    public static void main(String[] args) throws InterruptedException {
        for (int i =0 ; i < 5; i++) {


            ThreadDemo threadDemo = new ThreadDemo();
           // threadDemo.run();
            Thread.startVirtualThread(new ThreadDemo());

           threadDemo.start();

        }
        /*ThreadDemo threadDemo1 = new ThreadDemo();
    threadDemo1.start();
    threadDemo1.setPriority(10);
    threadDemo1.run(3 , 5);
    Runnable myRunnable = () -> {
        for (int i=0 ; i < 5 ; i ++)
        {
          System.out.println(" Thread priority is  " + Thread.currentThread().getPriority() + " Current thread name is "+ Thread.currentThread().getName());
        }
    };

        new Thread(myRunnable).start();
    */
    }
}

