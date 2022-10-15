/**
 * @author Richelin Metellus
 * A stack is a collection of object that insert and removed objects 
 * according to the Last-in First-out principle(LIFO)
 */
public interface Stack <E>{
  /**
   * 
   * @return number of elements in the stack
   */
    int size();
    
    /**
     * 
     * @return true if stack is empty.
     */
    boolean isEmpty();
    
    /**
     * 
     * @param e element/object to be inserted in the stack 
     */
    void push(E e);
    
    /**
     * return does not remove, the last element in the stack
     * @return the top object 
     */
    E top();
    
    /**
     * 
     * @return and removed the last inserted element or null if list is empty.
     */
    E pop();
        
}
