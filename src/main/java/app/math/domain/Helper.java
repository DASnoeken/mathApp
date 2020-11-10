package app.math.domain;

/**
 * This class just contains some helper functions
 * Constructor takes no arguments
 */
public class Helper {
	
	/**
	 * This function checks if a string contains a certain substring at a certain position. 
	 * @param str The input string to check.
	 * @param substr The substring to compare.
	 * @param position Where this substring should be found.
	 * @return Returns a boolean whether or not it contains the substring at the given position.
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
	 * Adds a character or substring to a string at a certain position and returns a new string.
	 * Proper usage is to: if(string.charAt(index)==someCharacter){index++;string=Helper.addChar(string,substr,index);index++;}
	 * This will get it in the position just after someCharacter.
	 * @param str The original string.
	 * @param substr The substring that needs to be added.
	 * @param position Where the substring needs to be added.
	 * @return returns the new string which will have the substring inserted into the position.
	 */
	public static String addChar(String str, String substr, int position) {
		if (position >= str.length()) {
			return str + substr;
		} else {
			return str.substring(0, position) + substr + str.substring(position);
		}
	}
	
	/**
	 * Properly texifies a polynomial like x^2-x-1, such that all powers are always written properly.
	 * Don't forget to use $ signs yourself!
	 * @param polynomial The polynomial as a string
	 * @return new polynomial that has all the TeX syntax in place. 
	 */
	public static String texify(String polynomial) {
		String ans = new String();
		for(int i = 0;i<polynomial.length();i++) {
			if(polynomial.charAt(i) == '^') {
				i++;
				polynomial = addChar(polynomial, "{", i);
				i++;
				while(Character.isDigit(polynomial.charAt(i))) {
					i++;
				}
				polynomial = addChar(polynomial, "}", i);
				i++;
			}
		}
		ans=polynomial;
		return ans;
	}
}
