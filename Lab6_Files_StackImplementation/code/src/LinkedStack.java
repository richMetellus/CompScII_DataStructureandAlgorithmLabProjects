
/**
 * This is based on textbook code fragment 6.4
 * This stack implementation add the last in element at the head.
 * @author Richelin Metellus
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStack(){}          //empty list
    @Override
    public int size()
    {
        return list.size();
    }
    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    @Override
    public void push(E element)
    {
        list.addFirst(element);
    }
    @Override
    public E top(){return list.first();}
    @Override
    public E pop(){return list.removeFirst();}
}
