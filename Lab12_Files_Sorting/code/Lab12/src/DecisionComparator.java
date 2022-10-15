/**
 * this comparator yes has a higher priority than no
 * @author Richelin Metellus
 * @@version 04/20/2017
 */
public class DecisionComparator implements Comparator<Voter> {
    @Override
    public int compare(Voter a, Voter b) throws NullPointerException
    {
        if (a == null || b == null) 
            throw new NullPointerException("Invalid object reference");
        
        String aDecision = a.getVoted();    // return a string Yes or No
        String bDecision = b.getVoted();
        int comparisonResult = 0;
        
        switch(aDecision)
        {
            case "Yes":
            {
                switch(bDecision)
                {
                    case "Yes" : comparisonResult = 0; break;
                    case "No"  : comparisonResult = 1; break;
                }
             break;  
            }
            case "No":
            {
                switch(bDecision)
                {
                    case "Yes" : comparisonResult = -1 ; break;
                    case "No"  : comparisonResult = 0  ; break;
                }
             break;
            }
                    
        }
      return comparisonResult;
    }  
}
