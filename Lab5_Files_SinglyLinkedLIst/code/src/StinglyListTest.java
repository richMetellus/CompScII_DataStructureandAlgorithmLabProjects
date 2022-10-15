
/**
 *
 * @author Rich
 */
import java.util.Random;

public class StinglyListTest {
    public static void main(String[] args) {
        
        StinglyLinkedList<Integer> linkedList = new StinglyLinkedList<>();
        
        Random rand = new Random();        
        for (int i = 0; i < 10; ++i)
        {
            int randNum = rand.nextInt(101);
            linkedList.addFirst(randNum);
        }
        System.out.println("Printing the contents of the List\n" + linkedList.toString());
        
        System.out.println("The first Element in the list: "  + linkedList.first());

        System.out.println("The last Element in the list "  + linkedList.last() );
        
        StinglyLinkedList<Integer> linkedList2 = new StinglyLinkedList<>();
        linkedList2.addLast(1);
        linkedList2.addLast(2);
        linkedList2.addLast(3);
        System.out.println("The contents of List # 2\n" + linkedList2.toString());
        
        StinglyLinkedList<Integer> linkedList3 = new StinglyLinkedList<>();
        linkedList3.addFirst(3);
        linkedList3.addFirst(2);
        linkedList3.addFirst(1);
        System.out.println("The contents of List # 3\n" + linkedList3.toString());
        
        System.out.println("Are List 2 and List 3 equal? " + linkedList2.equals(linkedList3));
        
        linkedList2.removeFirst();
        System.out.println("The contents of List # 2 after removal of first node\n" + linkedList2.toString());
        linkedList3.removeLast();
        System.out.println("The contents of List # 3 after remove of last element\n" + linkedList3.toString());
        System.out.println("Are List 3 and List 2 equal?" + linkedList2.equals(linkedList3));
    }
   
}
