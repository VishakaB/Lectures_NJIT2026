package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //ExecutorService service = Executors.newFixedThreadPool(10);
        //ExecutorService service2 = Executors.newCachedThreadPool();
        //
        // Thread thread = new Thread(new Task());
        // thread.start();
        // System.out.println("Thread name: "+ Thread.currentThread().getName());
        // Thread thread2 = new Thread(new Task());
        // thread2.start();
        Executors.newFixedThreadPool(0)
        System.out.println("Thread: "+ Thread.currentThread().getName());
        Thread thread = new Thread(new Task());
        thread.start();
    
        //creating separate threads to run the method Task()
        // for(int i=0; i< 5; i++){
        //     //service2.execute(new Task());
        //     Thread thread_ = new Thread(new Task());
        //     thread_.start();
        // }

        System.out.println("Thread: "+ Thread.currentThread().getName());
    }
    //run a task asynchronously
    static class Task implements Runnable {
        public void run(){
            System.out.println("Thread specific Name: "+ Thread.currentThread().getName());
        }
    }
}
