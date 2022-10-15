
/**
 *
 * @author Richelin Metellus
 * @@version 04/21/2017
 */
public class PartyComparator implements Comparator<Voter> {
      @Override
      public int compare(Voter a, Voter b) throws NullPointerException, IllegalArgumentException
    {
        if (a == null || b == null) 
            throw new NullPointerException("Invalid object reference");
        
        String aParty = a.getParty();
        String bParty = b.getParty();
        int comparisonResult = 0;
        
        switch(aParty)
        {
            case "republican" :
            {
                switch(bParty)
                {
                    case "republican": comparisonResult = 0; break;
                    
                    case "democrat"   : comparisonResult = 1; break;
                    case "other"      : comparisonResult = 1; break;
                    case "independent": comparisonResult = 1; break;
                }
            break;
            }
            case "democrat":
            {
                switch(bParty)
                {
                    case "republican" : comparisonResult = -1; break;
                    case "democrat"   : comparisonResult = 0; break;
                    case "other"      : comparisonResult = 1 ; break;
                    case "independent": comparisonResult = 1 ; break;
                }
              break;
            }
            case "other":
            {
                switch(bParty)
                {
                    case "republican" : comparisonResult = -1 ; break;
                    case "democrat"   : comparisonResult = -1 ; break;
                    case "other"      : comparisonResult =  0 ; break;
                    case "independent": comparisonResult =  1 ; break;
                }
                break;
            }
            case "independent":
            {
                switch(bParty)
                {
                    case "republican": comparisonResult = -1 ; break;
                    case "democrat"  : comparisonResult = -1 ; break;
                    case "other"     : comparisonResult = -1 ; break;
                    case "independent":comparisonResult = 0 ; break;
                }
                break;
            }
//           default:
//               throw new IllegalArgumentException("Invalid political Party");
        }
 return comparisonResult;
}
      
}
