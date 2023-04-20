import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;


public class Queue<E> {
    private LinkedList<E> list = new LinkedList<E>();
    
    public void enqueue(E item) {
        list.addLast(item);
    }
    
    public E dequeue() {
        return list.poll();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size() {
        return list.size();
    }

    public E peek() {
        return list.peek();
    }
    
    public void addAll(Collection<? extends E> c) {
        list.addAll(c);
    }
    
    
}
