
/**
 *
 * @author Rich Metellus
 */
public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 25;
    private E[] data;
    private int size = 0;
    
    public ArrayList(){this(CAPACITY);}
    
    public ArrayList(int capacity)
    {
        data = (E[]) new Object[capacity];
    }
    public int size(){return size;}
    
    public boolean isEmpty(){return size==0;}
    
    public E get(int i) throws IndexOutOfBoundsException
    {
        checkIndex(i,size);
        return data[i];
    }
    public E set(int i, E e) throws IndexOutOfBoundsException
    {
        checkIndex(i,size);
        E temp = data[i];
        data[i] =e;
        return temp;
    }
    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException
    {
        checkIndex(i, size+1);
        if (size == data.length)
            resize(2*data.length);          // if full double the capacity
        for (int k = size -1; k>= i; k--)
            data[k+1] = data[k];            //shifting right
        data[i] = e;
        size++;
    }
    public E remove(int i) throws IndexOutOfBoundsException
    {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size -1; k++)
            data[k]= data[k+1];
        data[size-1] = null;
        size--;
        return temp;
    }
        
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException
    {
        if ( i < 0 || i>= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }
    // resize the array to have capcity = size
    protected void resize(int capacity)
    {
        E[] temp = (E[]) new Object[capacity];
        for(int k = 0; k < size; k++)
            temp[k] = data[k];
        data = temp;
    }
           
    
     
}
