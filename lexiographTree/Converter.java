package lexiographTree;

import java.util.InputMismatchException;

/**
*
* provides a word converter that checks the format + converts big letter in string to littler letters
*
* @author Philipp, Slebioda, 4809007
*/

public class Converter {
	
	public String convString(String bigLetters) {
		boolean cond = correctFormat(bigLetters);
		if(cond) {
			// throw exception if format isnt right:
			throw new InputMismatchException("False format!");
		}
		char[] cache = bigLetters.toCharArray();
		for(int i = 0; i < cache.length; i++) {
			if(((int) cache[i]) > 64 && ((int) cache[i]) < 91) {
				int num = ((int) cache[i]) + 32;	// convert to little character
				cache[i] = (char) num;
			}
		}
		String result = new String(cache);
		return result;
	}
	
	
	public boolean correctFormat(String input) {
		char[] cache = input.toCharArray();
		boolean status1 = true;	
		boolean status2 = true;
		boolean status3 = true;
		for(int i=0; i < cache.length; i++) {
			status1 = true;
			status2 = true;
			status3 = true;
			if( ((int) cache[i]) < 65) {
				status1 = false;
			}
			if(((int) cache[i]) > 90 && ((int) cache[i]) < 97) {
				status2 = false;
			}
			if( ((int) cache[i]) > 122) {
				status3 = false;
			}
			if(status1 == false || status2 == false || status3 == false) {
				return true;
			}
		}
		return false;
	}

}
