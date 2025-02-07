package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {


	/**
	* Converts an operator to its precedence priority
	*
	* We expect you to be able to handle +, -, *, /, ^, and (
	* (why don't you need ")" as well? see algorithm in part 4)
	*
	* The order of these is as follows:
	*    ^, * and /, + and -, (
	*
	* @param op a string representing an operator, e.g. "+" or "-"
	* @return an integer value reflecting its precedence
	*/
	private static int opToPrcd(String op) {
		//TODO: Lab Part 2.1
		switch (op) {
        case "^":
            return 3;
        case "*":
        case "/":
            return 2;
        case "+":
        case "-":
            return 1;
        case "(":
            return 0;
        default:
            return -1;
    }
	}

	/**
	*  Determines if the first operator has same or greater
    *  precedence than the second
	*
	* @param op1 the first operator
	* @param op2 the second operator
	* @return the boolean result
	*/
	private static boolean hasPrecedence(String op1, String op2) {
		//TODO: Lab Part 2.2
		return opToPrcd(op1) >= opToPrcd(op2);
	}

	/**
	* determines if the input token is an operator
	*
	* @param token the string token to check
	* @return a boolean reflecting the result
	*/
	private static boolean isOperator(String token) {
		//TODO: Lab Part 2.3		
		switch(token) {
			case("^"):
			case("+"):
			case("-"):
			case("/"):
			case("*"):
				return true;
			default:
				return false;
		}
	}

	/**
    * Evaluates an expression
    *
    * NOTE Beware the order of pop and evaluation when receiving operand1
    * and operand2 as input.
    *
    * @param operand1 the first operand
    * @param operator the operator to apply
    * @param operand2 the second operand
    * @return a double expressing the result
    * @throws IllegalArgumentException if operator passed is not one of
    *  "+", "-", "*", "/", or "^"
    *
	*/
	private static double evaluate(double operand1, String operator, double operand2){
		//TODO: Lab Part 2.4
		double calculation = 0.0;
        if (isOperator(operator)) {
            switch(operator) {
                case "^":
                    calculation = Math.pow(operand1, operand2);
                    break;
                case "*":
                    calculation = operand1 * operand2;
                    break;
                case "/":
                    calculation = operand1 / operand2;
                    break;
                case "+":
                    calculation = operand1 + operand2;
                    break;
                case "-":
                    calculation = operand1 - operand2;
                    break;
            }
        } else {
            throw new IllegalArgumentException("Not an Operator");
        }
        return calculation;
	}


	/**
	 * Evaluates an arithmetic expression written in infix notation.
	 * The method processes the expression using two stacks: one for operators and one for operands.
	 * It respects operator precedence and parentheses to evaluate the expression correctly.
	 *
	 * @param line A string containing a fully parenthesized infix expression.
	 * @return The evaluated result of the expression as a double.
	 */
	public static double infixEvaluator(String line){
		//TODO: Lab Part 3
//		1. Scan the input string (infix notation) from left to right. One pass is sufficient. Take one token at a time.
		StringSplitter data = new StringSplitter(line);
		Stack<Double> operands = new Stack<Double>();
		Stack<String> operators = new Stack<String>();
//		Each type of token is treated differently:
		while(data.hasNext()) {
//			1.1. Number: push it onto the operand stack.
			String token = data.next();
			
			if (!isOperator(token) && !token.equals("(") && !token.equals(")")) {
                operands.push(Double.parseDouble(token));
			}
//			1.2. Left parenthesis: push it onto the operator stack.
			else if(token.equals("(")) {
				operators.push(token);
			}
//			1.3. Right parenthesis:
			else if(token.equals(")")) {
//				1.3.1. While the thing on top of the operator stack is not a left parenthesis
				while(!operators.peek().equals("(")) {
//					1.3.1.1. Pop an operator from the operator stack, pop two operands from the operand stack, apply the
//					operator to the operands in the correct order, push the result onto the operand stack.
					String operator = operators.pop();
                    double operand2 = operands.pop();
                    double operand1 = operands.pop();
                    operands.push(evaluate(operand1, operator, operand2));

				}
//				1.3.2. Pop the left parenthesis from the operator stack and discard it.
				operators.pop();
			}
//			1.4. Operator (call it current operator):
			else if (isOperator(token)) {
//				1.4.1. While the operator stack is not empty and the top thing on the operator stack has the same or
//				greater precedence as the current operator [“(“ should have the smallest precedence as is suggested in
//				the code comments]:
				while(!operators.isEmpty() && hasPrecedence(operators.peek(), token)) { 
//					1.4.1.1. Pop an operator from the operator stack, pop two operands from the operand stack, apply the
//					operator to the operands in the correct order, push the result onto the operand stack.
					String operator = operators.pop();
                    double operand2 = operands.pop();
                    double operand1 = operands.pop();
                    operands.push(evaluate(operand1, operator, operand2));
					
				}
//				1.4.2. Push the current operator onto the operator stack.
				operators.push(token);
			}
		}
//		2. (After you have traversed the entire StringSplitter queue) While the operator stack is not empty:
		while(!operators.isEmpty()) {
//			2.1. Pop an operator from the operator stack, pop two operands from the operand stack, apply the
//			operator to the operands in the correct order, push the result onto the operand stack.
			String operator = operators.pop();
            double operand2 = operands.pop();
            double operand1 = operands.pop();
            operands.push(evaluate(operand1, operator, operand2));
			
		}
//		3. At this point, the operator stack MUST be empty and the operand stack MUST have a single value,
//		which is the final result.
//		4. Pop that value and return it.
		return operands.pop();
		
		//HINT: You must use the algorithm described in Lab Part 4

	}

	/**
	 * Converts an arithmetic expression written in infix notation into postfix notation.
	 * The method processes the expression using a stack for operators and builds the postfix expression by following precedence rules and handling parentheses.
	 *
	 * @param line A string containing a fully parenthesized infix expression.
	 * @return A string representing the equivalent postfix notation of the input expression.
	 */
	public static String toPostfix(String line){
//		1. Scan the input string (infix notation) from left to right (one pass).
	    StringSplitter data = new StringSplitter(line); 
	    Stack<String> operators = new Stack<>(); 
	    StringBuilder postfix = new StringBuilder(); 

	    while (data.hasNext()) {
	        String token = data.next();

//	        2. If the current token is an operand:
	        if (!isOperator(token) && !token.equals("(") && !token.equals(")")) {
//				2.1. Append it to the postfix string.
	            postfix.append(token);
	        } 
//			3. If the current token is an operator (call it the current operator):
	        else if (isOperator(token)) {
	            //3.1. Pop from the operator stack and append to the postfix string every operator on the stack that:
	            while (!operators.isEmpty() && hasPrecedence(operators.peek(), token)) {
//					3.1.1. Is above the most recently scanned left parenthesis, and
//					3.1.2. Has precedence higher than that of the current operator.
	                postfix.append(operators.pop());
	            }
//				3.2. Push the current operator onto the stack.
	            operators.push(token);
	        } 
//			4. If the current token is a left parenthesis:
	        else if (token.equals("(")) {
//				4.1. Push it onto the stack.
	            operators.push(token);
	        } 
//			5. If the current token is a right parenthesis:
	        else if (token.equals(")")) {
//				5.1. Pop all operators down to the most recently scanned left parenthesis and append them to the postfix string.
	            while (!operators.isEmpty() && !operators.peek().equals("(")) {
	                postfix.append(operators.pop()); 
	            }
//				5.2. Pop the corresponding left parenthesis and discard this pair of parentheses.
	            operators.pop(); 
	        }
	    }

	    while (!operators.isEmpty()) {
	        postfix.append(operators.pop());
	    }

	    return postfix.toString();
	    
	}


	public static void main(String[] args){

		//Add Tests for Lab part 2
		//opToPrcd(String op) Tests
		System.out.println(opToPrcd("-"));
		System.out.println(opToPrcd("^"));
		System.out.println(opToPrcd("*"));
		System.out.println(opToPrcd("(") + "\n");
		//hasPrecedence(String op1, String op2) Tests
		System.out.println(hasPrecedence("-", "/"));
		System.out.println(hasPrecedence("^", "*"));
		System.out.println(hasPrecedence("-", "+") + "\n");
		//isOperator(String token) Tests
		System.out.println(isOperator("-"));
		System.out.println(isOperator("("));
		System.out.println(isOperator("a"));
		System.out.println(isOperator("^") + "\n");
		//evaluate(double operand1, String operator, double operand2) Tests
		System.out.println(evaluate(2, "^", 3));
		System.out.println(evaluate(2, "-", 3));
		System.out.println(evaluate(2, "/", 3) + "\n");
//		System.out.println(evaluate(2, "a", 3) + "\n");

		//Uncomment when you are ready to test Lab Part 3
        if (infixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test2 failed --> your answer should have been 7");

        if (infixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test3 failed --> your answer should have been 212");

        if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test4 failed --> your answer should have been 1400");

        if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test5 failed --> your answer should have been 100");
		

        System.out.println("Lab Testing Done!!!");

        /* uncomment the below lines for assignment */
		 if (!toPostfix(new String("(4+5)")).equals("45+"))
		     System.err.println("test1 failed --> should have been 45+");

		 if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
		     System.err.println("test2 failed --> should have been 45+6*");

		 if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
		     System.err.println("test3 failed --> should have been 456*7/+8-");

		 if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
		     System.err.println("test4 failed --> should have been 4567-*+8/");

		 if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
		     System.err.println("test5 failed --> should have been 987*654^/3*-2*+");


         System.out.println("Assignment Testing Done!!!");


	}

}
