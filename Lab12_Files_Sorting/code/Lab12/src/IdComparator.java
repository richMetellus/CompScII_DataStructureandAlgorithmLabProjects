/**
 * This comparator is base on how you count things. 1 will have a higher priority
 * than 2.
 * @author Richelin Metellus
 * @version 04/20/2017
 */
public class IdComparator implements Comparator<Voter>{
  
    public int compare(Voter a, Voter b) throws NullPointerException
    {
        if( a == null || b == null)
            throw new NullPointerException("Invalid object reference");
        
        int aId = a.getId();
        int bId = b.getId();
        
        if(aId < bId)
            return 1;
        else if(aId == bId)
            return 0;
        else
            return -1;
    }
    
    
}
