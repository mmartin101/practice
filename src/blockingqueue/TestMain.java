package blockingqueue;

import blockingqueue.BlockingQueue;

import java.util.Random;

/**
 * User: max
 * Date: 10/4/13
 * Time: 8:50 PM
 */
public class TestMain
{
    private static class Producer implements Runnable
    {
        BlockingQueue<Integer> bq;
        Random rand = new Random();

        private Producer(BlockingQueue<Integer> bq)
        {
            this.bq = bq;
        }

        private void produce()
        {
            try
            {
                bq.enqueue(rand.nextInt());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            while (true)
            {
                System.out.println("producing");
                produce();
            }
        }
    }

    private static class Consumer implements Runnable
    {
        BlockingQueue<Integer> bq;

        private Consumer(BlockingQueue<Integer> bq)
        {
            this.bq = bq;
        }

        private void consume()
        {
            try
            {
                Integer foo = bq.dequeue();
                System.out.println(foo);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            while (true)
            {
                System.out.printf("consuming...");
                consume();
            }
        }
    }

    public static void main(String[] args)
    {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<Integer>();
        Thread p1 = new Thread(new Producer(blockingQueue));
        Thread c1 = new Thread(new Consumer(blockingQueue));

        p1.start();
        c1.start();
    }
}
