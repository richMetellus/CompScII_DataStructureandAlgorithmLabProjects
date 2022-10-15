/**
 * @author Richelin Metellus.
 * @version 01/26/2017
 * Bag consist of all the methods to append, clear, get, remove
 * item from a list.
 */
public interface Bag {
    /**
     * returns a count of numbers in the bag
     * @return size of the bag
     */
    public int getCurrentSize();
    
    /**
     * check if bag is empty
     * @return  true when it's empty.
     */
    public boolean isEmpty();
    
    /**
     * add num to the bag
     * @param num 
     */
    public void add(int num);
    
    /**
     * remove a randomly selected num from the bag.
     */
    public void remove();
    
    /**
     *remove the first occurrence of the number num
     * @param num 
     */
    public void remove (int num);
    
    /**
     *clear the entire content of the list
     */
    public void clear();
    
    /**
     * 
     * @param num
     * @return the frequence of occurrent of the argument
     */
    public int getFrequencyOf(int num);
    
    /**
     * 
     * @param num
     * @return true when the argument is present in the list.
     */
    public boolean contains(int num);
  
    /**
     * 
     * @return the contents of the list 
     */
    @Override
    public String toString();

    /**
     * 
     * @param o Object
     * @return true if equals to the class defined.
     */
    @Override
    public boolean equals(Object o);
    
}
