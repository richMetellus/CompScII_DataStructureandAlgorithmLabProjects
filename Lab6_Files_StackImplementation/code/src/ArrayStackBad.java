
/**
 * Implementation of the stack where the newest element is always added at index 0
 * @author Richelin Metellus
 * @version 02/24/2017
 * @param <E>
 */
public class ArrayStackBad<E> implements Stack<E> {
     public static final int CAPACITY = 1000;
    private E[] data;
    private int top_oldest = -1;                         //oldest element = indexReference/ position of first element added in the array. 
                                                         // so last element added always at indext 0
    public ArrayStackBad(){ this(CAPACITY);}
    public ArrayStackBad(int capacity)
    {
        data = (E[]) new Object[capacity];
    } 
    
    @Override
    public int size() 
    {
        return top_oldest+1;
    }
    
     @Override
    public boolean isEmpty()
    {
        return top_oldest==-1;
    }
     @Override
    public void push(E e) throws IllegalStateException
    {
        if (size() == data.length) throw new IllegalStateException("Stack is full");
        
        if(!(isEmpty()))
        {
            int temp = top_oldest+1;    
            for(int i = top_oldest; i>=0; --i) 
            {
              data[temp]= data[i];              // shifting each element right to a higher index.
              temp--;
            }
            data[temp] = e;           // temp is = 0 after loop exit. this is similar to data[i]= e, i is = 0 after loop exit
                                        // new element/last item in the stack is added at index 0.
            top_oldest++;               // update index since one more element is added.
        }
        else
            data[++top_oldest] = e;
    }
     @Override
    public E top()
    {
        if (isEmpty()) return null;
        return data[0];
    }
     @Override
    public E pop()
    {
        if(isEmpty()) return null;
        E answer = data[0];
        
        for(int i=0; i <top_oldest; ++i)
        {
            int j = i+1;
            data[i] = data[j];          
        }
        top_oldest--;  
        return answer;
    }
     
}
