/**
 *
 * @author Richelin Metellus
 * @version 01/27/2017
 * Client test the methods defined in the Bag interface.
 */
import java.util.Random;
public class Client {

    public static void main(String[] args) {
        
        System.out.println("Entering Main");
        Random rand= new Random();
        Scores cart = new Scores(100);
        int num;
        for(int i = 0; i< 100; i++)
        {
            cart.list[i]= rand.nextInt(201)-100;
            cart.count++;
        }
        System.out.println("Printing numbers in The " + cart +"]" +
                "\nAdding 86 to the list");
        
        cart.add(86);
        System.out.println("86 is successfully appended to the end of list\n" + cart 
                + "]\nList Current size: " + cart.getCurrentSize());
        cart.remove();
        System.out.println("Random number in the list successfully removed\n" + cart+ 
                "] \nCurrent size: " + cart.getCurrentSize());
        num = cart.get(75);
        System.out.println("Frequency of " + num + " is: "+ cart.getFrequencyOf(num));
        cart.remove(num);
        System.out.println("Frequency of " + num + " after removal of its 1st occurrence: "
        + cart.getFrequencyOf(num));
        System.out.println("Frequency of the number 86 in the bag: " + cart.getFrequencyOf(86));
        System.out.println("Checking if the list contain 86: " + cart.contains(86));
    }
}
