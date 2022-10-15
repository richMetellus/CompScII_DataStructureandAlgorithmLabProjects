
/**
 * This comparator is based on the Lexicographic representation of a string.
 * @author Richelin Metellus
 * @version 04/21/2017
 */
public class NameComparator implements Comparator<Voter> {
    
    @Override
    public int compare(Voter a, Voter b) throws NullPointerException
    {
        if( a == null || b == null)
            throw new NullPointerException("Invalid object reference");
        
        int comparisonResult = 0;
        
        String aName = a.getName();
        int aNameSize = aName.length();
        String bName = b.getName();
        int bNameSize = bName.length();
        

// finding lesser string length to lower # of loop iterationn
        int lengthDiff = aNameSize - bNameSize;
        int iterationLimit = (lengthDiff  >= 0) ? bNameSize: aNameSize;
 
        for ( int i = 0; i< iterationLimit; ++i)
        {
            char aNameCur = aName.charAt(i);        // current char testing for nameA
            char bNameCur = bName.charAt(i);        // current char testing for name of Voter b.
            if(aNameCur < bNameCur)                 // a = 97 < b =98 in ASCII value
            {
                comparisonResult = 1;
                return comparisonResult;
            }               // i.e aName comes 1st alphabeically
            else if( aNameCur > bNameCur)        
            {
                comparisonResult = -1;
                return comparisonResult;
            } 
            else if (aNameCur == bNameCur &&aNameSize==bNameSize){
               comparisonResult = 0; 
                
             }
                 
            else if (aNameCur == bNameCur &&aNameSize < bNameSize )  // special case. 
                comparisonResult = 1;                               // if comparing Ama with Aman
             else if (aNameCur == bNameCur &&aNameSize > bNameSize)  // special case. 
                comparisonResult = -1;  
        }
        return comparisonResult;            // if loop fully executes and reaches this point it return 1 or -1,0.. for sepcial case

    }
    
    //----------------Private utility ---------------------------
    
    protected static char[] stringtoCharArray(String S)
    {
        char[] charArray = new char[S.length()];
        for(int i = 0; i < S.length(); ++i)
        {
            charArray[i] = S.charAt(i);
        }
        return charArray;
    }
}
