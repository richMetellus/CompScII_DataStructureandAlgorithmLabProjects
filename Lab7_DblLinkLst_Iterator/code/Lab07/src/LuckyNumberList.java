
import java.util.NoSuchElementException;

/**
 *
 * @author Richelin
 * @version 03/03/2017
 */
public class LuckyNumberList {
    
    private LinkedPositionalList<LuckyNumber>  list;    // doubly linked list that will contain objects of LuckyNumber with their positions
    
    public LuckyNumberList()
    {
        list = new LinkedPositionalList();
    }
    /**
     * 
     * @param ln object to be added as last in list
     */
    public void addLuckyNumber(LuckyNumber ln)
    {
        list.addLast(ln);
    }
    
    private class PositionIterator implements Iterator<Position<LuckyNumber>>
    {
        private Position<LuckyNumber> cursor = list.first();
        private Position<LuckyNumber> recent  = null;
        
        public boolean hasNext()
        {
            return (cursor != null);  
        }

        public Position<LuckyNumber> next() throws NoSuchElementException
        {
            if(cursor == null) throw new NoSuchElementException(" No More Position");
            
            recent = cursor;
            cursor = list.after(cursor);
            return recent;
        }
        /** remove the elements retruned by most recent call to next. */
        public void remove() throws IllegalStateException
        {
            if ( recent == null) throw new IllegalStateException (" Nothing to remove");
            list.remove(recent);
            recent = null;
        }
        
    }
    //-----------------PositionIterator End-----------
    
    
    // **************Nested PositionIterable Class******    
    private class PositionIterable implements Iterable<Position<LuckyNumber>>
    {
        public Iterator<Position<LuckyNumber>> iterator( ){return new PositionIterator(); }  
    }
    // *************End of Nested PostionIterableClass***************
    
    /**
     * 
     * @return an iterable representation of the list's positions.
     */
    public Iterable<Position<LuckyNumber>> positions()
   {
       return new PositionIterable();       // after this execute, think of cursor = position/address of first luckyNumber object.
   }
    
 /* * * * *Customed Iterator for position of Even number *********************/
    
    //-----------------Nested PositionIterator class ----------
    private class EvenPositionIterator implements Iterator<Position<LuckyNumber>>
    {
        private Position<LuckyNumber> cursor = list.first();    // position of first element to report
        private Position<LuckyNumber> recent = null;            // position of last reported element
        
        public boolean hasNext() { return (cursor != null); }
        
        public Position<LuckyNumber> next() throws NoSuchElementException
        {
            if(recent == null)          // if at begining position of the list
            {
                while(cursor != null&& !isEven(cursor.getElement().getLuckyNumber()))
                    cursor = list.after(cursor);       // R. advance cursor till you find the first position of an object whose element is even 
                                                       // R. take care of first call. think of a list had only one object, recent will be null
            }
            
            if (cursor == null) throw new NoSuchElementException("Nothing left to see here"); // if at end position of the list
            
          /* if not at begining or end of the list , let's say at the a list of two objects(2positions) cursor is pointing at object 2, recent is position1
            or point at object1.
            */
            recent = cursor;              // (if in our example of two objects, cursor will point to object2(position2) of list
            cursor = list.after(cursor);  // advance cursor one more step (if our example of two objects, cursor will point to trailer(null) of list
            
            /* now need to advance to next position/node whose element is an even number */
            
            while (cursor !=null && !isEven(cursor.getElement().getLuckyNumber())) //cursor.getElement() return a node,which is the address of the current object the cursor (a position) is pointing to
                cursor = list.after(cursor);
            
            return recent;
        }
        
        public void remove() throws IllegalStateException
        {
            if (recent == null) throw new IllegalStateException("Nothing to remove");
            list.remove(recent);    // remove from outer list linKedPositional List
            recent = null;          // do not allow remove again until next is called
        }
    }  
    /************************* End of nested EvenPositionIterator  ***************/
    
        // ----------nested Class ----------
    private class EvenPositionIterable implements Iterable<Position<LuckyNumber>>
    {
        public Iterator<Position<LuckyNumber>> iterator() { return new EvenPositionIterator();}
    } //------ end of EvenPositionIterable---------------
    
    public Iterable<Position<LuckyNumber>> EvenPositions(){
        return new EvenPositionIterable();
    }
  
 /******************** End of All needed for even Iterator********************/
    
   private class PrimePositionIterator implements Iterator<Position<LuckyNumber>>{
        private Position<LuckyNumber> cursor = list.first();   // position of the next element to report
        private Position<LuckyNumber> recent = null;               // position of last reported element
        /** Tests whether the iterator has a next object. */
        @Override
        public boolean hasNext( ) { return ( cursor != null ); }
        /** Returns the next position in the iterator. */
        @Override
        public Position<LuckyNumber> next( ) throws NoSuchElementException {
            // On the first call to next (i.e. when recent == null) you need to      //<<< new code
            // advance recent until it is pointing to a vowel elemet.                //<<< new code
            if ( recent == null )                                                    //<<< new code 
            {                                                                        //<<< new code
                while ( cursor != null && !isPrime( cursor.getElement().getLuckyNumber()) )    //<<< new code
                    cursor = list.after( cursor );                               //<<< new code
            }                                                                        //<<< new code
                
            if ( cursor == null ) throw new NoSuchElementException( "nothing left " );
            recent = cursor;
            cursor = list.after( cursor );
            
            // advance cursor to the next vowel
            
            while ( cursor != null && !isPrime( cursor.getElement().getLuckyNumber()) )
                cursor = list.after( cursor );
            
            return recent;
        }
        /** Removes the element returned by most recent call to next. */
        @Override
        public void remove( ) throws IllegalStateException {
            if ( recent == null ) throw new IllegalStateException( "nothing to remove" );
            list.remove( recent );         // remove from outer list
            recent = null;              // do not allow remove again until next is called
        }
    } //----- end of nested PositionIterator class -----
    
    //----- nested PositionIterable class -----
    private class PrimePositionIterable implements Iterable<Position<LuckyNumber>>{
        @Override
        public Iterator<Position<LuckyNumber>> iterator( ) { return new PrimePositionIterator( ); }        
    } //----- end of nested PositionIterable class -----
    
    /** Returns an iterable representation of the list's positions.
     * @return  */
    public Iterable<Position<LuckyNumber>> primePositions( ) {
        return new PrimePositionIterable( );  // create a new instace of the inner class
    }
    
  
    
    public boolean isEven(int num)
    {
        return (num % 2 == 0);
    }
    public boolean isPrime(int num)
    {
        if(num == 2 ||num == 3 ){return true;}
        
        int maxRange = (int) Math.sqrt(num);
        for(int i = 2;  i <= maxRange; ++i)
            if(num % i ==  0)
                return false;
            else if (i == maxRange)
                return true;
        
        return false; // to prevent compiler whining and take care if 1 is passed as argument.        
    }
}