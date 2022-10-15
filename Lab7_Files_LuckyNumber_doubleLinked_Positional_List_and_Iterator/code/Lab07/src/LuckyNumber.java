/**
 *
 * @author Richelin Metellus
 * @version 03/03/2017
 * This class define an object that store two elements
 */
import java.util.Random;


public class LuckyNumber {
    private String name;
    private int luckyNumber;
    
  public LuckyNumber(String name)
  {
      this.name = name;
      Random  rand = new Random();
      luckyNumber = rand.nextInt(10);
  }
  
  public String getName(){return name;}
  
  public int getLuckyNumber(){return luckyNumber;}
  
    @Override
  public String toString()
  {
      return getClass().getName() +": "+ getName() + "\t" + getLuckyNumber();
  }
    @Override
  public boolean equals(Object o)
  {
      if(!(o instanceof LuckyNumber))
          return false;
      LuckyNumber n = (LuckyNumber) o;
      
      return name.equalsIgnoreCase(n.getName()) && (luckyNumber == n.luckyNumber);
  }
          
}
