
/**
 *The implementation of a stack using array of the reference of the object
 * in the list.
 * @author Rich Metelus
 * @version 2/24/2017
 */
public class ArrayListStack<E> implements Stack<E> {
    private ArrayList<E> list = new ArrayList<>();
    public ArrayListStack(){}          //empty list
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
        list.add(0,element);
    }
    @Override
    public E top(){return list.get(list.size()-1);}
    
    @Override
    public E pop(){return list.remove(0);}
}
