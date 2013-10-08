package blockingqueue;

/**
 * User: max
 * Date: 10/2/13
 * Time: 11:33 PM
 */
public class BlockingQueue<E>
{
    private Queue<E> queue;

    public BlockingQueue(int limit)
    {
        this.queue = new Queue<E>(limit);
    }

    public BlockingQueue()
    {
        this.queue = new Queue<E>();
    }

    public synchronized void enqueue(E item) throws InterruptedException
    {
        while(queue.isFull())
        {
            System.out.println("waiting to enqueue...");
            wait();
        }

        queue.enqueue(item);
        notifyAll();
    }

    public synchronized E dequeue() throws InterruptedException
    {

        while(queue.isEmpty())
        {
            System.out.println("waiting to dequeue...");
            wait();
        }

        E val = queue.dequeue();
        notifyAll();

        return val;
    }

    private class Queue<E>
    {
        private Node<E> last;
        private Node<E> first;
        private int size = 0;
        private int limit = 10;

        private Queue()
        {
        }

        private Queue(int limit)
        {
            this.limit = limit;
        }

        public void enqueue(E item)
        {
            if (size == 0)
            {
                Node<E> node = new Node<E>(item, null);
                last = first = node;
            }
            else
            {
                Node<E> node = new Node<E>(item, null);
                Node<E>lastNode = last;
                lastNode.setPrevious(node);
                node.setNext(lastNode);
                last = node;
            }

            size++;
        }

        public E dequeue()
        {

            Node<E> node = first;
            first = first.getPrevious();
            size--;

            if (size == 0)
            {
                last = null;
            }

            return node.getItem();
        }

        public boolean isEmpty()
        {
            return size == 0;
        }

        public boolean isFull()
        {
            return size == limit;
        }
    }

    private class Node<E>
    {
        private E item;
        private Node previous;
        private Node next;

        private Node(E item, Node previous)
        {
            this.item = item;
            this.previous = previous;
        }

        private E getItem()
        {
            return item;
        }

        private void setItem(E item)
        {
            this.item = item;
        }

        private Node getPrevious()
        {
            return previous;
        }

        private void setPrevious(Node previous)
        {
            this.previous = previous;
        }

        private Node getNext()
        {
            return next;
        }

        private void setNext(Node next)
        {
            this.next = next;
        }
    }
}