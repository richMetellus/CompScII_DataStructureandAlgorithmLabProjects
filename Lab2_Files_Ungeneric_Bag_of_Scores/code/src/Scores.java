/**
 * @author Richelin Metellus
 * @version 01/26/2017
 * Class Scores provide implementation for the methods
 * inherited for the Bag interface.
 */
import java.util.Random;

public class Scores implements Bag {
    int[] list;
    int count;
    
    public Scores(){    // default constructor
        list = new int[50];
    }

    public Scores(int size)
    {
        list = new int[size];
    }
 
    @Override
   public int getCurrentSize()
    {
        return count;
    }
 
    @Override
    public boolean isEmpty()
    {
        if( count == 0)
            return true;
        else
            return false;
    }
 
    @Override
    public void clear()
    {
        this.count = 0;
    }
    @Override
    public void add(int num)
    {
        if (count < list.length)
        {
            list[count]= num;
            count++;
        }
        else if(count == list.length)
        {
           int temp[] = new int[list.length * 2];
           int i;
           for(i =0; i < count; i++)
               temp[i]= list[i];
           temp[i] = num;   // i is going to next open slot since it is incremented.
           count++;
           list = temp;
           temp = null;          
        }
    }
    @Override
    public int getFrequencyOf(int num)
    { 
        int numOccurrence = 0;
        for(int i = 0; i < count ;i++)
        {
            if(list[i]== num)
                numOccurrence++;      
        }
        return numOccurrence;
    } 
    @Override
    public boolean contains(int num)
    {
        boolean flag = false;
        for (int i= 0; i < count; ++i)
        {
          if(list[i]== num)
          {
             flag = true;
             break;
          } 
        }
        return flag;
    }
    @Override
    public void remove()
    {
          Random rand = new Random();
        int randomIndex = rand.nextInt(count);
        remove(list[randomIndex]);
        
    }

    @Override
    public void remove(int num){
        for (int i = 0; i < count; i++)
        {
            if (list[i] == num) // once num is found, value of i stay fix.
            {
                for( int j = i; j < count; j++ ) // j now takes over to shift left the elements on the right 0f index i. 
                    list[j]= list[j+1];         //thus replacing/removing the value at index i.
                count--;
                return;
            }
            
        }
    }
    public int get(int index) throws ArrayIndexOutOfBoundsException{
        int numAtIndex;
        if (index < 0 || index >= count )
            throw new ArrayIndexOutOfBoundsException(" Invalid:The Index not in between 0<=" + index +" <=" + count);
        numAtIndex = list[index];
        return numAtIndex;
    }
    
    @Override
    public String toString()
    {
        String listContent = "Bag: [";
        for (int i=0; i < count; ++i)
        {
             listContent = listContent + list[i] + " "; 
        }
        return listContent;
    }
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Scores))
            return false;
        Scores a = (Scores)o;
        if (a.getCurrentSize() != count)
            return false;  
        for (int i = 0; i< count; i++)
        {
            if(a.get(i) !=list[i])
                return false;
        }
        return true;        
    }
}
