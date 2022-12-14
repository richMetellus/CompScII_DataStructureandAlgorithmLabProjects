
/**
 *
 * @author Richelin Metellus
 */
public interface PositionalQueue<E> {
    /**
     * 
     * @return the number of element in the queue. 
     */
    int size();
    
    /**
     * 
     * @return true if the queue is empty
     */
    boolean isEmpty();
    /**
     * 
     * @param e element to be inserted at the rear of the queue
     * @return 
     */
    Position<E> enqueue(E e);
    
    /**
     * 
     * @return  but does not remove, the first element of the queue or null if empty
     */
    E first();
    
    
    /**
     * Remove the first element of the queue(Null if empty)
     * @return the element removed
     */
    E dequeue();
}
