
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This algorithm allow the evaluation of mathematical expressions
 * @author Richelin Metellus
 * @version 03/27/2017
 */
public class ShuntingYardClient {
    public static void main(String[] args) throws FileNotFoundException {
        
        LinkedPositionalQueue<String> infixQueue = new LinkedPositionalQueue();
        LinkedPositionalQueue<String> postfixQueue = new LinkedPositionalQueue();
        LinkedBinaryTree<String> expressionTree = new LinkedBinaryTree<>();
        boolean valid = false;
        
        Scanner scan = new Scanner(System.in);
        String filePath;
        String fileName;

       File f = new File("C:\\");

        while (!valid) {
            System.out.print("Enter the filePath: ");
            filePath = scan.nextLine();

            System.out.print("Enter the fileName: ");
            fileName = scan.nextLine();
       
            f = new File(filePath,fileName);
            if (f.exists()) {
                valid = true;
            } 
            else {
                System.out.println("Invalid file path or name. Enter a valid path");
            }
        }

        Scanner scanFile = new Scanner(f);

        List<String> expressionList = new LinkedList<>();

        while (scanFile.hasNextLine()) {
            String inLine = scanFile.nextLine(); // take each line which equal an expression from the file
            expressionList.add(inLine); // 
        }
        
        int temp = expressionList.size();
        String[] exp = new String[temp];        // to save each line of the file(which is an expression)

        for (int i = 0; i <temp; i++) 
        {
            exp[i] = expressionList.get(i);
            System.out.printf("\n------------------------------\n\n%s ",exp[i]);
            infixQueue = stringToQueue(exp[i]);

            
            if (validateExpression(infixQueue))     // boolean function to check for validity of the expression
            {
                System.out.println(" It is a valid expression");

                postfixQueue = infixToPostfix(infixQueue);
                System.out.printf(" Expression in Postfix:\t\t %s \n\n", postfixToString(postfixQueue));

                expressionTree = createExpressionTree(postfixQueue);
                //System.out.println("expressionTree root element" + expressionTree.root().getElement());
                
                //PreOrder traversal

                Iterator<Position<String>> preOrderIterator = expressionTree.preorder().iterator();
                while (preOrderIterator.hasNext()) {
                    System.out.print(preOrderIterator.next().getElement() + " ");
                }
                System.out.println(" PreOrder traversal of the Tree \n");
                
                 // Inorder
                Iterator<Position<String>> inOrderIterator = expressionTree.inorder().iterator();
                while (inOrderIterator.hasNext()) {
                    System.out.print(inOrderIterator.next().getElement() + " ");
                }
                System.out.println(" Inorder Traversal of the Tree\n");

                
                // PostOrder Traversal
                Iterator<Position<String>> postOrderIterator = expressionTree.postorder().iterator();
                while (postOrderIterator.hasNext()) {
                    System.out.print(postOrderIterator.next().getElement() + " ");
                }
                System.out.println(" PostOrder traversal of the Tree \n");
                
               
                System.out.printf(" Expression Result\t %10.3f \n", evaluatePostfix(postfixQueue));
            }
            else
                System.out.println(" Invalid expression");
        }  
    }// End of main

    //--------------------------Static methods-----------------------
    
    /**
     * 
     * @param expression mathematical expression as string
     * @return a queue representative of the string expression
     */
    public static LinkedPositionalQueue<String> stringToQueue(String expression) {
        LinkedPositionalQueue<String> infix = new LinkedPositionalQueue<>();
        Scanner scan = new Scanner(expression);
        scan.useDelimiter(" ");
        while (scan.hasNext()) {
            infix.enqueue(scan.next());
        }
        return infix;        // infix is just as the expression in the file.
    }
    
    /**
     * 
     * @param infix a queue of the expression without the spaces
     * @return true if the parentheses are balanced and expression format is valid
     */
     public static boolean validateExpression(LinkedPositionalQueue<String> infix)
    {
        LinkedStack<String> pStack = new LinkedStack<>(); // stack for the symbol/parenthesis
        LinkedPositionalQueue<String> infixClone = infix.clone();
        
        Iterator<Position<String>> infixIter = infixClone.positions().iterator(); // iterator to loop through 
        String currentToken ="";                                                  //all the items each having a position in the queue
        String previousToken = "";
        String nextToken ="";

        while(infixIter.hasNext())
        {
            previousToken =  currentToken;
            currentToken  = infixIter.next().getElement();
            if(infixClone.positionAfterFirst() != null)
                nextToken     = infixClone.positionAfterFirst().getElement();
            
            if(isOperand(currentToken) && infixIter.hasNext())
            {
                infixClone.dequeue();           // allow my current to advance
                if(isOpenParenthesis(nextToken) || isOperand(previousToken))
                    return false;    
            }
            
             if(isOpenParenthesis(currentToken))
            {
                pStack.push(currentToken);
                infixClone.dequeue();
                if(isOperator(nextToken))
                    return false;
            }
            else if(isCloseParenthesis(currentToken))
            {
                if(pStack.isEmpty() || !areParenthesesPaired(pStack.top(),currentToken)) 
                   return false;
                else
                {
                   pStack.pop();                // i.e the parentheses match delete both
                   infixClone.dequeue();
                }
            }
            else if(isOperator(currentToken))
            {
                if(!infixIter.hasNext()|| isOperator(nextToken))
                   return false;                // will take care of this situation )+
                else
                   infixClone.dequeue();  
            }
        }
        return true && pStack.isEmpty();        // if unbalanced parentheses pStack is not empty and will contain open symbols
    }

     /**
      * 
      * @param infix a queue of the expression in its original order without the spaces
      * @return the postfix form of the expression
      */
    public static LinkedPositionalQueue<String> infixToPostfix(LinkedPositionalQueue<String> infix) {
        LinkedPositionalQueue<String> postfix = new LinkedPositionalQueue<>();
         LinkedPositionalQueue<String> infixClone = infix.clone();
        LinkedStack<String> stack = new LinkedStack<>();
        
        while (!infixClone.isEmpty()) 
        {
            
            if (isOperand(infixClone.first())) {
                postfix.enqueue(infixClone.dequeue());
            } 
            else if (isOperator(infixClone.first())) 
            {
                while(!stack.isEmpty() && !isOpenParenthesis(stack.top())
                        && hasHigherPrecedence(stack.top(),infixClone.first()))
                {
                    postfix.enqueue(stack.pop());               // enque the element(s) of higher precedence in the stack.
                }                                               // till it finds an open parenthesis/symbol in the stack
                stack.push(infixClone.dequeue());                      // then push the operator on top of openingParenthese(s)
            }
            else if (isOpenParenthesis(infixClone.first()))
                stack.push(infixClone.dequeue());
            else if (isCloseParenthesis(infixClone.first()))
            {
                while(!stack.isEmpty() && !isOpenParenthesis(stack.top()))
                    postfix.enqueue(stack.pop());
                infixClone.dequeue();                                        // to delete the close parenthesis on the infix
                stack.pop();                                           // delete the open symbol/parenthesis in the stack
            }
            
        }
        while(!stack.isEmpty())                             // finally move all remaining element/operators in the stack to postfix queue
            postfix.enqueue(stack.pop());
        
        return postfix;
    }  
    /**
     * 
     * @param postfix a queue with each element having a position
     * @return the value of the expression
     */
    // assuming the postfix is a valid expression 
    public static double evaluatePostfix(LinkedPositionalQueue<String> postfix)
    {
        LinkedStack<String> S = new LinkedStack<>();
        LinkedPositionalQueue<String> postfixClone = postfix.clone();
        double result;
         
        while (!postfixClone.isEmpty()) 
        {
            if(isOperand(postfixClone.first())) 
                S.push(postfixClone.dequeue());
            
            else if(isOperator(postfixClone.first()) && (S.size()>=2))
            {
                String op1 = S.pop();
                String op2 = S.pop();
                result = calculate(op2,op1, postfixClone.dequeue());
                S.push(Double.toString(result));
            }
        }
        return Double.parseDouble(S.pop());
    }
    /**
     * 
     * @param postfix a queue with each item having their own position
     * @return A binary tree of the expression
     */
    public static LinkedBinaryTree<String> createExpressionTree(LinkedPositionalQueue<String> postfix)
    {
        
        LinkedStack<LinkedBinaryTree> treeStack = new LinkedStack();
        LinkedPositionalQueue<String>postfixClone = postfix.clone();
        int postfixSize = postfixClone.size();
        while(!postfixClone.isEmpty())
        {
          
           if(isOperand(postfixClone.first()))
           {
                LinkedBinaryTree<String> T1 = new LinkedBinaryTree();
                T1.addRoot(postfixClone.dequeue());
                treeStack.push(T1);  
            }
           else if(isOperator(postfixClone.first())) 
            {
                LinkedBinaryTree<String> expressionTree = new LinkedBinaryTree();
                LinkedBinaryTree<String> rightChild = treeStack.pop();
                LinkedBinaryTree<String> leftChild = treeStack.pop();
                expressionTree.attach(expressionTree.addRoot(postfixClone.dequeue()), leftChild, rightChild);
                treeStack.push(expressionTree);
                //System.out.println("expressionTree root element" + expressionTree.root().getElement());
            }
        }
        return treeStack.pop();
    }

    //--------------------- utility methods --------------------------
    public static boolean isOperand(String S) {
        try { Double.parseDouble(S);}
        catch(NumberFormatException e){ return false;}
        return true;
    }

    // this method assign a weight for each operator
    // this method is critical in defining operator precedence
    public static int getOperatorPriority(String S) {
        int weight = -1;
        switch (S) {
            case "*":
            case "/":
                weight = 2;
                break;
            case "+":
            case "-":
                weight = 1;
        }
        return weight;
    }
    // operator with higher precedence has higher priority weight

    public static boolean hasHigherPrecedence(String operator1, String operator2) {
        int priorityWeight1 = getOperatorPriority(operator1);
        int priorityWeight2 = getOperatorPriority(operator2);

        return priorityWeight1 >= priorityWeight2;
    }

    public static boolean isOperator(String S) {
        return S.equals("+") ||S.equals("-") ||S.equals("*")
                ||S.equals("/");
    }

    public static boolean isOpenParenthesis(String S) {
        char symbol = S.charAt(0);
        return (symbol == '(' || symbol == '[' || symbol == '{');
    }

    public static boolean isCloseParenthesis(String S) {
        char symbol = S.charAt(0);
        return (symbol == ')' || symbol == ']' || symbol == '}');
    }
    
    public static boolean areParenthesesPaired(String open, String close)
    {
        return    (open.equals("(")&& close.equals(")"))
                ||(open.equals("{")&& close.equals("}"))
                ||(open.equals("[")&& close.equals("]"));
    }
    
    public static double calculate(String op1, String op2,String operator )
    {
        double num1 = Double.parseDouble(op1);
        double num2 = Double.parseDouble(op2);
        
        switch(operator){
            case "+":
                return num1+ num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
    return 0;       // get rid of compiler whining
    }
    /**
     * 
     * @param postfix a queue in postfix form
     * @return a String representative of the postfix queue
     */
    public static String postfixToString(LinkedPositionalQueue<String> postfix)
    {
        String postfixString = "";
        LinkedPositionalQueue<String> postfixClone = postfix.clone();
        int queueSize = postfixClone.size();
        for(int i = 0; i < queueSize;++i )
            postfixString += postfixClone.dequeue()+" ";
        return postfixString;             
    }
    //------------------------------End of utility Methods------------------
}
    
