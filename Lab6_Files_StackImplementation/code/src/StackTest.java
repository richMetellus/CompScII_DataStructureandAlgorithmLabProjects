
import java.util.Random;
/**
 *Testing the various runtime of each stack implementation.
 * @author Richelin Metellus
 * @version 02/24/2017
 */
public class StackTest {

    
    public static void main(String[] args) {
        
        ArrayStack<Integer> goodArrayStack = new ArrayStack<>(100000);
        LinkedStack<Integer> goodLinkedStack = new LinkedStack<>();
        ArrayListStack<Integer> ArrListStack = new ArrayListStack<>();
        ArrayStackBad<Integer> badArrayStack = new ArrayStackBad<>(100000);
        LinkedStackBad<Integer> badLinkedStack = new LinkedStackBad<>();
        
        Random rand = new Random();
        // running time check for ArrayStack
        long startTime1 = System.currentTimeMillis();
        for ( int i = 0; i < 100000; ++i)
        {
          int randNumber = rand.nextInt(100);
          goodArrayStack.push(randNumber);
        }
        for ( int i = 0; i < 100000; ++i)
            goodArrayStack.pop();
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;
       
//************************************************************
        // runing time for LinkedStack
        long startTime2 = System.currentTimeMillis();
        for ( int i = 0; i < 100000; ++i)
        {
          int randNumber = rand.nextInt(100);
          goodLinkedStack.push(randNumber);
        }
        for ( int i = 0; i < 100000; ++i)
            goodLinkedStack.pop();
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;
//***************************************************************
        // runtime for ArrayListStack
        long startTime3 = System.currentTimeMillis();
        for ( int i = 0; i < 100000; ++i)
        {
          int randNumber = rand.nextInt(100);
          ArrListStack.push(randNumber);
        }
        for ( int i = 0; i < 100000; ++i)
            ArrListStack.pop();
        long endTime3 = System.currentTimeMillis();
        long elapsedTime3 = endTime3 - startTime3;
//*********************************************************************
        // running time for ArrayStackBad
        long startTime4 = System.currentTimeMillis();
        for ( int i = 0; i < 100000; ++i)
        {
          int randNumber = rand.nextInt(100);
          badArrayStack.push(randNumber);
        }
        for ( int i = 0; i < 100000; ++i)
            badArrayStack.pop();
        long endTime4 = System.currentTimeMillis();
        long elapsedTime4 = endTime4 - startTime4;
//*********************************************************************
        // running time for LinkedStackBad
        long startTime5 = System.currentTimeMillis();
        for ( int i = 0; i < 100000; ++i)
        {
          int randNumber = rand.nextInt(100);
          badLinkedStack.push(randNumber);
        }
        for ( int i = 0; i < 100000; ++i)
            badLinkedStack.pop();
        long endTime5 = System.currentTimeMillis();
        long elapsedTime5 = endTime5 - startTime5;
//*********************************************************************
        System.out.printf("push/pop ArrayStack \tfor N\t= 100,000 \ttime \t=  %,10d miliseconds \n", elapsedTime1);
        System.out.printf("push/pop LinkedStack \tfor N\t= 100,000 \ttime \t=  %,10d miliseconds \n", elapsedTime2);
        System.out.printf("push/pop ArrayListStack for N\t= 100,000 \ttime \t=  %,10d miliseconds \n", elapsedTime3);
        System.out.printf("push/pop ArrayStackBad \tfor N\t= 100,000 \ttime \t=  %,10d miliseconds \n", elapsedTime4);
        System.out.printf("push/pop LinkedStackBad for N\t= 100,000 \ttime \t=  %,10d miliseconds \n", elapsedTime5);
        
    }
    
}
