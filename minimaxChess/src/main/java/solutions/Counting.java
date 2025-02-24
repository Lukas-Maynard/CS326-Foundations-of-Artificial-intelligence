package solutions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Counting{

    public static int k = 0;  //shared variable

    static class Increment implements Runnable{

        public void run(){

            k++;

        }

    }

    public static void main (String[] args){

        ExecutorService es = Executors.newFixedThreadPool(4);

        for(int i=0; i<1000; i++){

            es.submit(new Increment());

        }

        es.shutdown();

        //wait until all tasks complete.

        try {
            es.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(k);

    }

}