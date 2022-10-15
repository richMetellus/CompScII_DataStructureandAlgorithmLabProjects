import java.util.Scanner;
/**
 *
 * @author Richelin.metellus
 * @version 01202017
 * The client class test the superclass Contact as well as the
 * subclasses Friend and Business. The getter method of each class
 * were explicitly tested. The setter r tested in a loop. 
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println(" Entering main");
        Scanner scan = new Scanner( System.in);
        String userChoice;
        
        System.out.print("How many conatacts would you like to create? ");
        int num = scan.nextInt();
        scan.nextLine();
        Contact[] addressBook = new Contact[num];
          
        for ( int i = 0; i < num ;  )
        {
            String name, address, phone, title, bizName, birthday, favoriteMovie;
            System.out.print("Enter B or b for business contact or F/f for friends: ");
            userChoice = scan.nextLine();
            char charChoice =userChoice.toLowerCase().charAt(0);
                        
            if(charChoice != 'b' && charChoice != 'f')
            {    
                System.out.println(" Illegal Input. type B/b or F/f" );
                continue;
            }
  // if input is legal execute statements below
            System.out.print("Enter name: ");
            name = scan.nextLine();  
            System.out.print("Enter address: ");
            address = scan.nextLine();
            System.out.print("Enter phone: ");
            phone = scan.nextLine();
            
            if (charChoice == 'b')
            {
                System.out.print("Enter title: ");
                title = scan.nextLine();
                System.out.print("Enter buisiness name: ");
                bizName = scan.nextLine();
                
                addressBook[i] = new Business(name, address, phone, title, bizName);
                System.out.println("Creating business Contact = " + addressBook[i]);
                i++;
            }
            else if(charChoice == 'f')
            {
                System.out.print("Enter birthday MM/DD/YYYY: ");
                birthday = scan.nextLine();
                System.out.print("What is your favorite movie?  ");
                favoriteMovie = scan.nextLine();
                
                addressBook[i] = new Friend(name, address, phone, birthday, favoriteMovie);
                System.out.println("Creating friend Contact = " + addressBook[i]); //invoke the toString of Friend.
                i++;
            }   
        }          
        for (int i = 1; i <= Contact.getContactCount(); i++)
        {        
            System.out.println( "[" + i + "]" + addressBook[i-1]);
            System.out.println(addressBook[i-1].getName());
            // testing setter
            addressBook[0].setName("Richelin Metellus");
            addressBook[0].setAddress("1805 Universtiy Dr N");
            addressBook[0].setPhone("754-551-4765");
            
            if ( addressBook[0] instanceof Friend )
            {

               ((Friend) addressBook[0]).setBirthday("05/10/94");
               ((Friend) addressBook[0]).setFavoriteMovie(" The Big Fish");
            }
            else if (addressBook[0] instanceof Business)
            {
               ((Business) addressBook[0]).setBusinessName(" NDSU");
               ((Business) addressBook[0]).setTitle("Student");
            }
            System.out.println( "[" + i + "]" + addressBook[i-1]);            
        }           
        System.out.println( " Exiting main");
    }  
}
