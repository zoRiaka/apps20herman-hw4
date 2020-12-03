package ua.edu.ucu.immutable;
import ua.edu.ucu.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue(ImmutableLinkedList queue) {
        this.queue = queue;
    }

    public Queue() {
        this.queue = new ImmutableLinkedList();
    }

    public void enqueue(Object e)
    {
        queue = queue.addLast(e);
    }

    public Object dequeue()
    {
        Object el = queue.getFirst();
        queue = queue.removeFirst();
        return el;
    }

    public Object peek()
    {
        return queue.getFirst();
    }

    public ImmutableLinkedList getQueue() {
        return queue;
    }

}
