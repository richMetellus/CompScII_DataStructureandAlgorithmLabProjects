
import java.util.Random;

/**
 * a class for voter in which initialization may be base on randomization
 * or correctly specifying the fields.
 * @author Richelin Metellus
 * @version 04/20/2017
 */
public class Voter {
    int ID;
    String name;
    String party;
    String voted;
    Random rand = new Random();
    
    public Voter()
    {
        ID = rand.nextInt(100000000);      // 0 - 99,999,999
        name = generateRandomName();
        party = generateRandomParty();
        voted = hasVoted();
        
  
    }
    
    public Voter(int id, String name, String party, String Voted )
    {
        ID = id;
        this.name = name;
        this.party = party;
        voted = Voted;      
    }
            
    public int getId(){return ID;}
    public String getName(){return name;}
    public String getParty(){ return party;}
    public String getVoted(){return voted;}
    
    /**
     * 
     * @return a random stream of character with length between 5 and 10(inclusive)
     */
    private String generateRandomName()
    {
        int stringLength =  10- rand.nextInt(6);        // name has to be btw 5 and 10(inclusive)
        StringBuilder name = new StringBuilder(stringLength);
        int randChar;               // random ASCII value of character btw a and z as an integer.
                
        for(int i = 0; i < stringLength; i++)
        {
            //create a number btw 97(a) and 122(z) inclusive
            randChar = 97 + rand.nextInt(122-97+1);
            name.append((char) randChar);
        }
       return  name.toString();
    }
    /**
     * 
     * @return  random political party such that 30% is republican, and on.
     * 
     */
    private String generateRandomParty()
    {
        int randNum = rand.nextInt(100);
        
        if( randNum >= 0 && randNum < 30)
                return "democrat";
        else if(randNum >= 30 && randNum < 60)
            return "republican";
        else if (randNum >= 60 && randNum < 80)
            return "independent";
        else
            return "other"; 
    }
    /** @return a random voting status such that 58% might be yes */
    private String hasVoted()
    {
        int randNum = rand.nextInt(100);
        
        if( randNum >= 0 && randNum < 58)
            return "Yes";
        else
            return "No";
    }
    
    @Override
    public String toString()
    {
        return getClass().getName() + ":" + name + ":" + ID +":" + party + ":" + voted;
    }

}
