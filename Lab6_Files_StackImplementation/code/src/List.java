
/**
 *
 * @author Rich
 */
public interface List<E> {
    /**
     * 
     * @return the number of elements in the list
     */
    int size();
    /**
     * 
     * @return true if the list is empty
     */
    boolean isEmpty();
    /**
     * 
     * @param i index at which the element is located
     * @return the element at the ith index but does not remove.
     */
    E get(int i) throws IndexOutOfBoundsException;
    
    /**
     * 
     * @param i index at which the element should be inserted
     * @param e element to be inserted
     * @return the replaced element
     * @throws IndexOutOfBoundsException 
     */
    E set(int i, E e) throws IndexOutOfBoundsException;
    
    /**
     * 
     * @param i indexwhere to add element e
     * @param e new element to b added to the list, shifting all
     * subsequent elements later.
     * @throws IndexOutOfBoundsException 
     */
    void add(int i, E e) throws IndexOutOfBoundsException;
    
    /**
     * 
     * @param i index position of the element to be removed
     * @return the element removed
     */
    E remove(int i) throws IndexOutOfBoundsException;
}
