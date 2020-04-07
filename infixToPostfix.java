import java.util.Scanner;
import java.util.Stack;
  
class RPN	//Reverse Polish Notation. 
{ 
	public static Scanner scanner = new Scanner(System.in);
	
    public static void main(String[] args)  
    { 
    	String result = new String();
    	while(true)
    	{
    		System.out.print("Enter Infix Expression:\t");
        	String input = scanner.nextLine(); 
        	
        	while(!input.matches("[0-9\\+\\-\\*/\\(\\)]*"))
        	{
        		result = infixToPostfix(input);
           		System.out.println("Postfix Notation:\t" + result);
        		System.out.print("Enter a valid mathematical expression containing constants only to evaluate:\t");
            	input = scanner.nextLine(); 
        	}//while
        	
        	result = infixToPostfix(input);
       		System.out.println("Postfix Notation:\t" + result);
        	result = infixToPostfix(input);
       		System.out.println("Postfix Notation:\t" + result);
           	System.out.println("postfix evaluation:\t"+evaluatePostfix(result)); 
    	}//while
   	}//main() 
    
   	static int precedence(char ch) 
   	{ 
       	switch (ch) 
       	{ 
       	case '+': 
       	case '-': 
           	return 1; 
      
       	case '*': 
       	case '/': 
           	return 2; 
       
       	case '^': 
           	return 3; 
       	}//switch() 
       	return -1; 
   	}//precedence() 
       
   	static String infixToPostfix(String exp) 
   	{ 
   		String result = new String(""); 
          
   		Stack<Character> stack = new Stack<>(); 
          
   		for (int i = 0; i<exp.length(); ++i) 
    	{ 
   			char c = exp.charAt(i); 
              
    		if (Character.isLetterOrDigit(c)) 
   				result += c; 
               
   			else if (c == '(') 
    			stack.push(c); 
             
    		else if (c == ')') 
    		{ 
   				while (!stack.isEmpty() && stack.peek() != '(') 
   					result += stack.pop(); 
                  
   				if (!stack.isEmpty() && stack.peek() != '(') 
   					return "Invalid Expression";          
   				else
   					stack.pop(); 
   			}//else_if 
            
   			else 
   			{ 
   				while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) 
    				result += stack.pop(); 
   				stack.push(c); 
    		}//else 
       
   		}//for() 
       
    	while (!stack.isEmpty()) 
   			result += stack.pop(); 
       
   		return result; 
   	}//infixToPostfix() 
        
   	static int evaluatePostfix(String exp) 
   	{ 
    	Stack<Integer> stack = new Stack<>(); 
              
    	for(int i=0;i<exp.length();i++) 
   		{ 
    		char c=exp.charAt(i); 
    			
    		if(Character.isDigit(c)) 
   				stack.push(c - '0'); 
                  
    		else
            { 
    			int val1 = stack.pop(); 
                int val2 = stack.pop(); 
                    
                switch(c) 
                { 
                	case '+': 
                	  stack.push(val2+val1); 
                	  break; 
                          
                	case '-': 
                      stack.push(val2- val1); 
                      break; 
                         
                    case '/': 
                      stack.push(val2/val1); 
                      break; 
                          
                    case '*': 
                      stack.push(val2*val1); 
                      break; 
                 }//switch 
              }//else
           }//for 
           return stack.pop();     
        }//evalluatePostfix() 
    }//class