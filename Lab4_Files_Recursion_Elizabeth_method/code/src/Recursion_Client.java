
/**
 * @author rich
 * @version 02/10/2017
 * The main class test the recursion methods using JOptionPane box dialog.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Recursion_Client {
    public static void main(String[] args) throws FileNotFoundException {
        
        boolean done = false;
        int val = 0, val2 = 0; // value variable to test whether input is valid
        while (!done)
        {
            boolean valid = false;
            String choice = JOptionPane.showInputDialog(null,"Please enter an Option by selecting a number 1 to 5\n" 
                                                           +"1. LogBase2\n"
                                                           +"2. Multiply\n"
                                                           +"3. Isabel Technique\n"
                                                           +"4. Find a file\n"
                                                           +"5. Exit");
            
           System.out.println("Menu of choice by selecting a number 1 to 5\n" 
                                                           +"1. LogBase2\n"
                                                           +"2. Multiply\n"
                                                           +"3. Isabel Technique\n"
                                                           +"4. Find a file\n"
                                                           +"5. Exit\n"
                  + "your selection:" + choice);
           
            switch (choice) {
                case "1":
                {        
                    System.out.println("Finding LogBase2");
                    
                    String argument = "";
                    while(!valid)
                    {
                         argument = JOptionPane.showInputDialog(null,"Please enter an intenger as the argument log base 2");
                        
                        try
                        {
                             val = Integer.parseInt(argument);
                             valid = true;
                             
                        }
                        catch(NumberFormatException nno)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid input. Try again");
                        }      
                    }
                    System.out.println("Log_2(" + argument + ") returned " + Recursion.log2(val));
                    break;    
                }
                case "2":
                {        
                    System.out.println("Finding product of two positive integers");
                    
                    String num1 = "", num2 = "";

                    while(!valid)
                    {
                         num1 = JOptionPane.showInputDialog(null,"Please enter a positive intenger as the multiplicand");
                         num2 = JOptionPane.showInputDialog(null,"Please enter another positive intenger as the multiplier");
                        
                        try
                        {
                             val = Integer.parseInt(num1);
                             val2 = Integer.parseInt(num2);
                             valid = true;
                             
                        }
                        catch(NumberFormatException nno)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid input. Try again");
                        }      
                    }
                    System.out.println(val + " X " + val2 + " = " + Recursion.prodOf2PositiveInt(val, val2));
                    break;    
                }
                
               
                case "3":
                    {
                        int[] array;
                        System.out.println("Testing Isabel's method for summing an array");
                        
                        File f= new File("C:\\");
                        while(!valid)
                        {
                            String filepath = JOptionPane.showInputDialog(null,"Please enter the path of the File");
                            String fileName = JOptionPane.showInputDialog(null,"Please enter the name of the File");
                            f = new File(filepath,fileName);
                            if(f.exists())
                                valid = true;
                            else
                            {
                                System.out.println("Not a valid path. Enter a valid path");
                            }
                        }       Scanner scanFile = new Scanner(f);
                        scanFile.useDelimiter(",");
                        int counter = 0;
                        while(scanFile.hasNext()){
                            if(scanFile.hasNextInt())
                                counter++;              // only icnrement when an int is found
                            scanFile.next();
                        }          //Sets scanner back to the beginning of file
                        scanFile = new Scanner(f);
                        scanFile.useDelimiter(",");
                        array = new int[counter]; // create an array with the right size for all the int
                        for(int i=0; scanFile.hasNext();i++){
                            while(!scanFile.hasNextInt() && scanFile.hasNext())
                                scanFile.next();
                            if(scanFile.hasNextInt()){
                                array[i]=scanFile.nextInt();
                            }
                        }          System.out.println("sum of array " + printArray(array) + "is = " + Recursion.isabelTech4ArrSum(array));
                        break;
                    }
                case "4":
                    {
                        System.out.println("Testing File finder");
                        String filePath = JOptionPane.showInputDialog(null,"Please enter the path of the File");
                        String fileName = JOptionPane.showInputDialog(null,"Please enter the name of the File");
                        Recursion.match = false;
                        Recursion.find(filePath, fileName);
                        if (!Recursion.match)
                            System.out.println("File " + fileName + " Not found");
                        break;
                    }
                case "5":
                    done = true;
                    break;
                
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
  
        }
        
      
    }
    
    public static String printArray(int[] A)
    {
        String arrayContent = "[";
        for(int i = 0; i < A.length; ++i)
        {
           arrayContent += A[i] + " ";
            
        }
        return arrayContent + "]";
    }
    
}
