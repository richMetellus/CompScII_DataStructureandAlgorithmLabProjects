
import java.io.File;
/**
 *
 * @author Richelin Metellus
 * @version 02/10/2017
 * This class implement the methods to find basic operation, and file finder;
 */
public class Recursion {
    
/**
 * 
 * @param num the log base2 of the parameter;
 * @return 
 */
    public static boolean match = false;
    public static int log2(int num)
    {
        if ( num == 1)
            return 0;
        else
            return 1+ log2(num/2);
    }
    /**
     * 
     * @param num1 multiplicand
     * @param num2 multiplier
     * @return 
     */
    public static int prodOf2PositiveInt(int num1, int num2)
    {

        int temp;
        // swap multiplican to multiplier if mulitplier is larger
        if (num2 > num1)
        {
            temp = num1;
            num1 = num2;
            num2 = temp;
        }
        
        if (num2 == 0 || num1== 0)
            return 0; 
        else
           return num1 + prodOf2PositiveInt(num1, (num2-1));
    }
    
    /**
     * 
     * @param A array of variable size
     * @return the sum of integer in the array.
     */
    public static int isabelTech4ArrSum( int[] A)
    {
       int []  B = new int[A.length/2];
       
       for(int i = 0; i < A.length/2; i++)
       {
           B[i] = A[2*i] + A[2*i + 1];
       }
       if(B.length == 1)
           return B[0];
       else
           return isabelTech4ArrSum(B);   
    }
    
    /**
     * 
     * @param path directory of the file
     * @param name name of the file searching for
     */
    public static void find(String path,String name)
    {
        File f = new File(path);
        if(f.isFile())
        {
            
            if(f.getName().equalsIgnoreCase(name))
            {
                {
                    match = true;
                    System.out.println("File Found at: " + f.getAbsolutePath());
                }
     
            }
   
            
        }
        
        else if(f.isDirectory())
        {
           File [] folder = f.listFiles();
           if(folder != null)
           {
            
               for(File item: folder)
               {
                   find(item.getAbsolutePath(), name);    
               }
           }
           
        }

        
                
    }
}
