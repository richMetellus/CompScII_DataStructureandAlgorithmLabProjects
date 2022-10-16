/**
 *
 * @author Rich
 */
public class LuckyNumberClient {
    public static void main(String[] args) {
        
        LuckyNumberList aList = new LuckyNumberList();
        
        aList.addLuckyNumber(new LuckyNumber("Sam"));
        aList.addLuckyNumber(new LuckyNumber("Ken"));
        aList.addLuckyNumber(new LuckyNumber("Laura"));
        aList.addLuckyNumber(new LuckyNumber("Davis"));
        aList.addLuckyNumber(new LuckyNumber("Samantha"));
        aList.addLuckyNumber(new LuckyNumber("Jordan"));
        aList.addLuckyNumber(new LuckyNumber("Sania"));
        aList.addLuckyNumber(new LuckyNumber("Kenny"));
        aList.addLuckyNumber(new LuckyNumber("Kara"));
        aList.addLuckyNumber(new LuckyNumber("Widner"));
        aList.addLuckyNumber(new LuckyNumber("Emmanuel"));
        aList.addLuckyNumber(new LuckyNumber("Maria"));
        
      Iterator<Position<LuckyNumber>> defaultListIterator = aList.positions().iterator();
      Iterator<Position<LuckyNumber>> evenListIterator  = aList.EvenPositions().iterator();
      
      
      System.out.println("Lucky Number List Contents (printed with default Iterator)");
      while(defaultListIterator.hasNext() )
      {
          Position<LuckyNumber> currentPosition = defaultListIterator.next();
          String message = null, evenMessage = null;
          if(aList.isPrime(currentPosition.getElement().getLuckyNumber()) == true)
              message = "Prime";
          else
              message = "Not Prime"; 
          
          if(aList.isEven(currentPosition.getElement().getLuckyNumber()) == true)
              evenMessage = "Even";
          else
              evenMessage = "Odd"; 
                      
          System.out.printf("%-10s\t %10d \t ", currentPosition.getElement().getName(),currentPosition.getElement().getLuckyNumber());
          System.out.printf("%-10s\t %-3s\n", message, evenMessage);
      }
     
      /*****************     PrimeIterator      ******************/
      
      Iterator<Position<LuckyNumber>> primeListIterator = aList.primePositions().iterator();
      System.out.println("\n\nLucky Number List Contents (printed with prime Iterator)");
      while(primeListIterator.hasNext() )
      {
          Position<LuckyNumber> currentPosition = primeListIterator.next();
          String message;
          if(aList.isPrime(currentPosition.getElement().getLuckyNumber()) == true)
              message = "Prime";
          else
              message = "Not Prime"; 
                      
          System.out.printf("%-10s\t %10d \t ", currentPosition.getElement().getName(),currentPosition.getElement().getLuckyNumber());
          System.out.printf("%-10s\t \n", message);
      }
      /*****************    Even Iterator   *******************/
      System.out.println("\n\nLucky Number List Contents (printed with even Iterator)");
      while(evenListIterator.hasNext() )
      {
          Position<LuckyNumber> currentPosition = evenListIterator.next();
          String message = null, evenMessage = null;
          if(aList.isPrime(currentPosition.getElement().getLuckyNumber()) == true)
              message = "Prime";
          else
              message = "Not Prime"; 
          if(aList.isEven(currentPosition.getElement().getLuckyNumber()) == true)
              evenMessage = "Even";
          else
              evenMessage = "Odd"; 
                      
          System.out.printf("%-10s\t %10d \t ", currentPosition.getElement().getName(),currentPosition.getElement().getLuckyNumber());
          System.out.printf("%-3s \t %-10s\n" ,evenMessage, message);
      }   
    } 
    
}
 