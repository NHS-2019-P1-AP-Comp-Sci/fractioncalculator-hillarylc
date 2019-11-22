/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter fractions to calculate: ");
		String userInput = s.nextLine();
		while (!userInput.equals("quit")) {
			System.out.println(produceAnswer(userInput));
			System.out.print("Please enter fractions to calculate: ");
			userInput = s.nextLine();
		}

	  }

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		Scanner s = new Scanner(input);
		String operand1 = s.next();
		String operator = s.next();
		String operand2 = s.next();

		s.close();

		String wholeAns = whole(operand2);
		String numerAns = numer(operand2);
		String denomAns = denom(operand2);

		String check2 = "whole:" + wholeAns + " numerator:" + 
						numerAns + " denominator:" + denomAns;

		return check2;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

	public static String whole(String w) {
		if (w.indexOf("_") > 0) {
			return w.substring(0, w.indexOf('_'));
		} else if (w.contains("/")) {
			return "0";
		} else
			return w;
	}

	public static String numer(String n) {
		if (n.indexOf('_') > 0) {
			return n.substring(n.indexOf('_') + 1, n.indexOf('/'));
		} else if (n.contains("/")) {
			return n.substring(0, n.indexOf('/'));
		} else
			return "0";
	}

	public static String denom(String d) {
		if (d.indexOf("/") > 0) {
			return d.substring(d.indexOf('/') + 1);
		} else
			return "1";
	}
}