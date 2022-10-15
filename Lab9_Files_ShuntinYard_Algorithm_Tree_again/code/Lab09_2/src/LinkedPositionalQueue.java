
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an implementation of a queue with each item in the
 * queue having a position that can be use to iterate the queue and
 * do some operations such as validate a mathematical expression represented
 * in infix notation of lab 09.
 * 
 * @author Richelin Metellus
 * @version 03/26/2017
 */
public class LinkedPositionalQueue<E> implements PositionalQueue<E> {

    private LinkedPositionalList<E> list;   // doubly linked list for a queue with positions
   
    public LinkedPositionalQueue()
    {
        list = new LinkedPositionalList();  // new queu relies on the inital empty list
    }
    /**
     * 
     * @return the number of element in the queue
     */
    @Override
    public int size() { return list.size();}
    

    /**
     * 
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() { return list.isEmpty();}
    
    /**
     * 
     * @param e element to b inserted at the end of the queue
     * @return  the position of the new element inserted
     */
    @Override
    public Position<E> enqueue(E e) 
    {
        Position<E> p ;
        p =list.addLast(e);
        return p;
    }

    /**
     * 
     * @return but does not remove the element at the front of the queue
     */
    @Override
    public E first() { return list.first().getElement();}

    /**
     * 
     * @return the element removed at the front of the queue
     */
    @Override
    public E dequeue() {
        return  list.remove(list.first());
                //(Position<E>) list.remove(list.last());
    }
    
    /**
     * 
     * @return does not remove the position of the item at the front of the queue
     */
    public Position<E> firstPosition()
    {
        return list.first();
    }
    /**
     * 
     * @return the position of the next item in the queue
     */
    public Position<E> positionAfterFirst()
    {
        return list.after(list.first());
    }
    //------------------------------Start of Position Iterator Class-------------
     private class PositionIterator implements Iterator<Position<E>>
    {
        private Position<E> cursor = list.first();
        private Position<E> recent  = null;
        
        @Override
        public boolean hasNext()
        {
            return (cursor != null);  
        }

        @Override
        public Position<E> next() throws NoSuchElementException
        {
            if(cursor == null) throw new NoSuchElementException(" No More Position");
            
            recent = cursor;
            cursor = list.after(cursor);
            return recent;
        }
        /** remove the element returned by most recent call to next. */
        @Override
        public void remove() throws IllegalStateException
        {
            if ( recent == null) throw new IllegalStateException (" Nothing to remove");
            list.remove(recent);
            recent = null;
        }
        
    }
    
    //-----------------PositionIterator End-----------
     
      // **************Nested PositionIterable Class******    
    private class PositionIterable implements Iterable<Position<E>>
    {
        @Override
        public Iterator<Position<E>> iterator( ){return new PositionIterator(); }  
    }
    // *************End of Nested PostionIterableClass***************
    
    /**
     * 
     * @return an iterable representation of the list's positions.
     */
    public Iterable<Position<E>> positions()
   {
       return new PositionIterable();       // after this execute, think of cursor = position/address of first luckyNumber object.
   }
    
   /**
    * This utility method is important as it retains the contents
    * of the original queue without having to dequeue the original one.
    * 
    * @return a copy of a queue with each item having their own position.
    */
    public LinkedPositionalQueue<E> clone() {
        LinkedPositionalQueue<E> temp = new LinkedPositionalQueue<>();

        for (Position<E> e : list.positions()) {
            if (e != null) { 
                temp.enqueue(e.getElement());
            }
        }
        return temp;
    }
}
