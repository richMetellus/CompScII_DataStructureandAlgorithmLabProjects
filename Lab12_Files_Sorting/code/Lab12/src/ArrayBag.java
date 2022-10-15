
import java.util.Random;
/**
 *
 * @author Richelin Metellus
 * @version 02/02/2017
 * @param <T> Generic
 */
public class ArrayBag<T> {
    private T[] list;
    private int count;
    
    public ArrayBag()
    {
       list = (T[]) new Object[50];
    }
    
    public ArrayBag(int num)
    {
        list = (T[]) new Object[num];
    }
    public int getCurrentSize()
    {
        return count;
    }
    
    public boolean isEmpty()
    {
        return count == 0;
    }
    public boolean isFull()
    {
        if(count < list.length)
            return false;
        else if(count == list.length)
        {
            try
            {
                T[] temp = list;
                temp = (T[]) new Object[count* 2];
                       
            }
            catch(OutOfMemoryError e)
            {
                return true;
            }

        }
        return false;
    }
    
    public boolean add( T obj)
    {
  
        if(!(isFull()))
        {
            if (count < list.length)
            {
                list[count] = obj;
                count++ ;
            }
            else if ( count == list.length)
            {
                T[] temp = (T[]) new Object[count*2];
                int i;
              for(i=0; i < count; i++)
                 temp[i] = list[i];
              
              list = temp;
              list[i] = obj;
              count++;
              temp = null;
            }
            System.out.println("Item successfully added to the bag");
            return true; // if object successfully added to the bag.
        }
        return false;
    }
    
    public T remove()
    {
        Random rand = new Random();
        int randomIndex = rand.nextInt(count);
        T objAtRandomIndex = list[randomIndex];
        remove(list[randomIndex]); 
        
        return objAtRandomIndex;
    }
    public boolean remove(T obj){
        for (int i = 0; i < count; i++)
        {
            if (list[i].equals(obj)) 
            {
                for( int j = i; j < count-1; j++ ) 
                    list[j]= list[j+1];
                count--;
                return true;
            } 
        }
        return false;
    }
    
     public void clear()
     {
         count = 0;
     }
     
    public int getFrequencyOf(T obj)
    { 
        int numOccurrence = 0;
        for(int i = 0; i < count ;i++)
        {
            if(list[i]== obj)
                numOccurrence++;      
        }
        return numOccurrence;
    }
    
    public boolean contains(T obj)
    {
        boolean flag = false;
        for (int i= 0; i < count; ++i)
        {
          if(list[i]== obj)
          {
             flag = true;
             break;
          } 
        }
        return flag;
    }
    
        public T get(int index) throws ArrayIndexOutOfBoundsException{
        T objAtIndex;
        if (index < 0 || index >= count )
            throw new ArrayIndexOutOfBoundsException(" Invalid:The Index not in between 0<=" + index +" <=" + count);
        objAtIndex = list[index];
        return objAtIndex;
    }
    
    public int getCurrentCapacity()
    {
        return list.length;
    }
    
     @Override
    public String toString()
    {
        String listContent = "[";
        for (int i=0; i < count; ++i)
        {
             listContent = listContent + list[i] + ", "; 
        }
        return listContent + "]";
        
    }   
}
