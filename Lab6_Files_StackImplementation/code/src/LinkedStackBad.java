
/**
 * Using the adapter pattern to implement a stack as
 * a linked list. Each new element is added as the last node and the
 * last node element is removed.(LiFO)
 * @author Rich
 * @version 02/24/2017
 */
public class LinkedStackBad<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStackBad(){}          //empty list
    public int size()
    {
        return list.size();
    }
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    public void push(E element)
    {
        list.addLast(element);
    }
    public E top(){return list.first();}
    public E pop(){return list.removeLast();}
}

