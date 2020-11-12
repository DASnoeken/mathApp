package app.math.domain;

import java.math.BigInteger;

/**
 * This class just contains some helper functions
 */
public class Helper {
	private Helper() {
	}

	/**
	 * This function checks if a string contains a certain substring at a certain
	 * position.
	 * 
	 * @param str      The input string to check.
	 * @param substr   The substring to compare.
	 * @param position Where this substring should be found.
	 * @return Returns a boolean whether or not it contains the substring at the
	 *         given position.
	 */
	public static boolean checkSubstring(String str, String substr, int position) {
		for (int i = 0; i < substr.length(); i++) {
			if (i + position >= str.length() || (str.charAt(i + position) != substr.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds a character or substring to a string at a certain position and returns a
	 * new string. Proper usage is to:
	 * if(string.charAt(index)==someCharacter){index++;string=Helper.addChar(string,substr,index);index++;}
	 * This will get it in the position just after someCharacter.
	 * 
	 * @param str      The original string.
	 * @param substr   The substring that needs to be added.
	 * @param position Where the substring needs to be added.
	 * @return returns the new string which will have the substring inserted into
	 *         the position.
	 */
	public static String addChar(String str, String substr, int position) {
		if (position >= str.length()) {
			return str + substr;
		} else {
			return str.substring(0, position) + substr + str.substring(position);
		}
	}

	/**
	 * Removes a substring from a string starting at index start (exclusive) and
	 * ending at index end (inclusive)
	 * 
	 * @param str   The input string
	 * @param start Starting index
	 * @param end   Ending index
	 * @return New string with the substring between start and end edited out
	 */
	public static String removeSubstr(String str, int start, int end) {
		if (start < 0 || end < 0 || end <= start || start >= str.length() || end > str.length()) {
			String message = "Illegal indices for end and start! Where start = " + start + " and end = " + end
					+ ". Also, str.length() = " + str.length();
			throw new IllegalArgumentException(message);
		}
		return str.substring(0, start) + str.substring(end);
	}

	/**
	 * Properly texifies a polynomial like x^2-x-1, such that all powers are always
	 * written properly. Don't forget to use $ signs yourself!
	 * 
	 * @param polynomial The polynomial as a string
	 * @return new polynomial that has all the TeX syntax in place.
	 */
	public static String texify(String polynomial) {
		String ans = new String();
		for (int i = 0; i < polynomial.length(); i++) {
			if (polynomial.charAt(i) == '^') {
				i++;
				polynomial = addChar(polynomial, "{", i);
				i++;
				while (i < polynomial.length() && Character.isDigit(polynomial.charAt(i))) {
					i++;
				}
				polynomial = addChar(polynomial, "}", i);
				i++;
			}
		}
		ans = polynomial;
		return ans;
	}

	/**
	 * Turns a polynomial into a polynomial with integer coefficients and powers
	 * 
	 * @param polynomial The input polynomial
	 * @return The new polynomial with integer coefficients and powers.
	 */
	public static String integerPolynomial(String polynomial) {
		for (int i = 0; i < polynomial.length(); i++) {
			if (polynomial.charAt(i) == '.') {
				int start = i;
				i++;
				while (i < polynomial.length() && Character.isDigit(polynomial.charAt(i))) {
					i++;
				}
				int end = i;
				polynomial = removeSubstr(polynomial, start, end);
				i -= (end - start);
			}
		}
		return polynomial;
	}

	/**
	 * Counts the number of occurrences of a character in a string.
	 * 
	 * @param s The string to test.
	 * @param c The character to look for.
	 * @return The number of occurrences of c in s.
	 */
	public static int countCharOcc(String s, char c) {
		char[] scarr = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < scarr.length; i++) {
			if (scarr[i] == c) {
				ans++;
			}
		}
		return ans;
	}

	/**
	 * Gets the square root of a BigInteger
	 * @param x The BigInteger to take the square root of.
	 * @return The square root of x.
	 */
	public static BigInteger sqrt(BigInteger x) { 	// https://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger/
													// Thanks to: Edward Falk. I needed this because Heroku needs to update its
													// Java version.
		BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
		BigInteger div2 = div;
		for (;;) {
			BigInteger y = div.add(x.divide(div)).shiftRight(1);
			if (y.equals(div) || y.equals(div2))
				return y;
			div2 = div;
			div = y;
		}
	}
	
	/**
	 * Rounds a double to a certain number of digits.
	 * @param input	Input double
	 * @param toDigit Round the input to this many digits after decimal point
 	 * @return Rounded double
	 */
	public static double roundDecimalToDigits(double input,int toDigit) {
		input*=Math.pow(10.0, toDigit);
		input=Math.round(input);
		input/=Math.pow(10.0, toDigit);
		return input;
	}
}
