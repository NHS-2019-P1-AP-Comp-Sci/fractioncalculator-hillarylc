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
		String input = s.nextLine();
		while (!input.equals("quit")) {
			System.out.println(produceAnswer(input));
			System.out.print("Please enter fractions to calculate: ");
			input = s.nextLine();
			String answer = produceAnswer(input);
			System.out.println(answer);

		}
		s.close();
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

		String first;
		String second;
		String result;

		// For multiplication operator
		if (input.indexOf('*') != -1) {
			first = input.substring(0, input.indexOf("*"));
			second = input.substring(input.indexOf("*") + 1);
			first = fracAt(first);
			second = fracAt(second);

			String firstNum = first.substring(0, first.indexOf('/'));
			String firstDenom = first.substring(first.indexOf('/') + 1);

			String secondNum = second.substring(0, second.indexOf('/'));
			String secondDenom = second.substring(second.indexOf('/') + 1);
			result = "" + Integer.parseInt(firstNum) * Integer.parseInt(secondNum) + '/'
					+ Integer.parseInt(firstDenom) * Integer.parseInt(secondDenom);

			// For addition operator
		} else if (input.indexOf('+') != -1) {
			first = input.substring(0, input.indexOf('+'));
			second = input.substring(input.indexOf('+') + 1);
			first = fracAt(first);
			second = fracAt(second);
			String firstNum = first.substring(0, first.indexOf('/'));
			String firstDenom = first.substring(first.indexOf('/') + 1);

			String secondNum = second.substring(0, second.indexOf('/'));
			String secondDenom = second.substring(second.indexOf('/') + 1);
			result = ""
					+ (Integer.parseInt(firstNum) * Integer.parseInt(secondDenom)
							+ Integer.parseInt(secondNum) * Integer.parseInt(firstDenom))
					+ '/' + Integer.parseInt(firstDenom) * Integer.parseInt(secondDenom);

			// For division operator
		} else if (input.indexOf(" / ") != -1) {
			first = input.substring(0, input.indexOf(" / "));
			second = input.substring(input.indexOf(" / ") + 2);
			first = fracAt(first);
			second = fracAt(second);

			String firstNum = first.substring(0, first.indexOf('/'));
			String firstDenom = first.substring(first.indexOf('/') + 1);

			String secondNum = second.substring(0, second.indexOf('/'));
			String secondDenom = second.substring(second.indexOf('/') + 1);
			result = "" + Integer.parseInt(firstNum) * Integer.parseInt(secondDenom) + '/'
					+ Integer.parseInt(firstDenom) * Integer.parseInt(secondNum);

			// For subtraction operator
		} else if (input.substring(1).indexOf("-") != -1) {
			first = input.substring(0, input.substring(1).indexOf("-") + 1);
			second = input.substring(input.substring(1).indexOf("-") + 2);
			first = fracAt(first);
			second = fracAt(second);
			String firstNum = first.substring(0, first.indexOf('/'));
			String firstDenom = first.substring(first.indexOf('/') + 1);

			String secondNum = second.substring(0, second.indexOf('/'));
			String secondDenom = second.substring(second.indexOf('/') + 1);
			result = ""
					+ (Integer.parseInt(firstNum) * Integer.parseInt(secondDenom)
							- Integer.parseInt(secondNum) * Integer.parseInt(firstDenom))
					+ '/' + Integer.parseInt(firstDenom) * Integer.parseInt(secondDenom);

			// For invalid input
		} else {
			result = "no result";
		}

		result = otherFrac(result);
		return result;
	}

	public static String fracAt(String in) {
		in = in.replaceAll(" ", "");
		if (in.indexOf('/') == -1)
			return in + "/1";
		else if (in.indexOf('_') == -1)
			return in;
		else {
			boolean neg = false;
			if (in.charAt(0) == '-') {
				neg = true;
				in = in.substring(1);
			}

			// For negative results
			int und = in.indexOf('_');
			int div = in.indexOf('/');
			int whole = Integer.parseInt(in.substring(0, und));
			int numer = Integer.parseInt(in.substring(und + 1, div));
			int denom = Integer.parseInt(in.substring(div + 1));
			String result = whole * denom + numer + "/" + denom;
			if (neg) {
				result = "-" + result;
			}
			return result;
		}
	}

	public static String simplify(String in) {
		int ndx = in.indexOf('/');
		int numer = Integer.parseInt(in.substring(0, ndx));
		int denom = Integer.parseInt(in.substring(ndx + 1));
		for (int i = 2; i < denom; i++) {
			if (numer % i == 0 && denom % i == 0) {
				numer /= i;
				denom /= i;
				i--;
			}
		}
		return numer + "/" + denom;
	}

	public static void name(String str) {
		System.out.println(str);
	}

	// When the fraction does not have '_'
	public static String otherFrac(String in) {
		name(in);
		if (in.indexOf('/') == -1)
			return in;
		else {
			in = simplify(in);
			int ind = in.indexOf('/');
			int numer = Integer.parseInt(in.substring(0, ind));
			int denom = Integer.parseInt(in.substring(ind + 1));

			boolean neg = false;

			if (numer < denom) {
				return in;
			} else if (numer % denom == 0) {
				return "" + numer / denom;
			} else {
				if (numer < 0) {
					neg = !neg;
					numer = 0 - numer;
				}
				if (denom < 0) {
					neg = !neg;
					denom = 0 - denom;
				}
				String result = numer / denom + "_" + numer % denom + "/" + denom;
				if (neg)
					result = '-' + result;
				return result;
			}
		}
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

	// Part of Checkpoint 2
	// Parses the whole components of the operands
	public static String whole(String w) {
		if (w.indexOf("_") > 0) {
			return w.substring(0, w.indexOf('_'));
		} else if (w.contains("/")) {
			return "0";
		} else
			return w;
	}

	// Parses the numerator components of the operands
	public static String numer(String n) {
		if (n.indexOf('_') > 0) {
			return n.substring(n.indexOf('_') + 1, n.indexOf('/'));
		} else if (n.contains("/")) {
			return n.substring(0, n.indexOf('/'));
		} else
			return "0";
	}

	// Parses the denominator components of the operands
	public static String denom(String d) {
		if (d.indexOf("/") > 0) {
			return d.substring(d.indexOf('/') + 1);
		} else {
			return "1";
		}
	}

	// Finds the greatest common factor
	public static int gcf(int numer, int denom) {
		int gcf = 1;
		int checkNumber = 0;
		if (numer > denom) {

			checkNumber = denom;

		} else {

			checkNumber = numer;

		}

		for (int i = 1; i <= checkNumber; i++) {
			if (numer % i == 0 && denom % i == 0) {
				gcf = i;
			}
		}

		return gcf;
	}

	public static String reduce(int numer, int denom) {
		int gcf = gcf(numer, denom);
		numer = numer / gcf;
		denom = denom / gcf;
		return numer + "/" + denom;
	}
}