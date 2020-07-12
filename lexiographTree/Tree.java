package lexiographTree;

/**
*
* provides a lexiographic Tree which allows it to save Words under usage of data compression.
* everyone who likes recursive data structures will like it :D
*
* @author Philipp, Slebioda, 4809007
*/

public class Tree {
	
	private Node root;
	private Converter converterObj;
	private int maxHeight;
	private int tempHeight;
	private String longestWord;
	private Node reference;
	private String word;
	private String longestPrefix;
	private int depth;
	private char[] charWort;
	private String structure;
	
	public Tree() {
		this.root = new Node('\100');
		converterObj = new Converter();
		maxHeight = 0;
		longestWord = new String("");
		word = "";
		longestPrefix = "";
	}
	
	public void insertRecursive(String input) {
		System.out.println("******* Das einzufügende Wort lautet: " + input + " **********");
		String littleString = converterObj.convString(input);
		char[] inputChar = littleString.toCharArray();
		int position = -1;
		Node ref = root;

		for(int i=0; i < 26; i++) {
			try {
				if(ref.children[i].getValue() == inputChar[0]) {
					position = i;
				}	
			} catch(NullPointerException e) {
			}
		}
			if(position == -1) {	
			// if inputchar isnt already available-> create new node:
				for(int h = 0; h < 26; h++) {
					if(ref.children[h] == null) {
						ref.children[h] = new Node(inputChar[0]);
						if(inputChar.length >1) {
							char[] firstCharAway = new char[(inputChar.length - 1)];
							for(int k = 1; k < inputChar.length; k++) {
								firstCharAway[k-1] = inputChar[k];
							}
							String newInput = new String(firstCharAway);
							insertRecursive(ref.children[h],newInput);
							ref.sortChildren();
						}
						break;
					}
				}
			}
			else {	
			// wenn der selbe char gefunden wurde, nicht inserten und weiterschauen:
				if(inputChar.length > 1) {
					char[] firstCharAway = new char[(inputChar.length - 1)];
					for(int k = 1; k < inputChar.length; k++) {
						firstCharAway[k-1] = inputChar[k];
					}
					String newInput = new String(firstCharAway);
					insertRecursive(ref.children[position],newInput);
				}
			}
		
	}
	
	public void insertRecursive(Node newRef, String input) {
		Node ref = newRef;
		String littleString = converterObj.convString(input);
		char[] inputChar = littleString.toCharArray();
		int position = -1;
		for(int i=0; i < 26; i++) {
			try { 
				if(ref.children[i].value == inputChar[0]) {
					position = i;
				}	
			} catch(NullPointerException e)
			{
			}
		}
			if(position == -1) {	
			// if inputchar isnt already available-> create new node:
				for(int h = 0; h < 26; h++) {
					if(ref.children[h] == null) {
						ref.children[h] = new Node(inputChar[0]);
						if(inputChar.length > 1) {
							char[] firstCharAway = new char[(inputChar.length - 1)];
							for(int k = 1; k < inputChar.length; k++) {
								firstCharAway[k-1] = inputChar[k];
							}
							String newInput = new String(firstCharAway);
							insertRecursive(ref.children[h],newInput);
							ref.sortChildren();
						}
						break;
					}
				}
			}
			else {	
			// wenn der selbe char gefunden wurde, nicht inserten und weiterschauen:
				if(inputChar.length > 1) {
					char[] firstCharAway = new char[(inputChar.length - 1)];
					for(int k = 1; k < inputChar.length; k++) {
						firstCharAway[k-1] = inputChar[k];
					}
					String newInput = new String(firstCharAway);
					insertRecursive(ref.children[position],newInput);
				}
		  }	
	}
	
	public void insertIterative(String input) {
		String littleString = converterObj.convString(input);
		char[] inputChar = littleString.toCharArray();
		int dauer=0;
		boolean exists = false;	// wird zwar defaultmäßig mit false initialisiert aber doppelt schadet es ja nicht;)
		Node ref = root;
		while(dauer < inputChar.length) {
			for(int i=0;i<26;i++) {
				if(ref.children[i].value == inputChar[dauer]) {
					ref = ref.children[i];
					exists = true;
				}
			}
			if(exists) {
				for(int h=0;h<26;h++) {
					if(ref.children[h] == null) {
						ref.children[h] = new Node(inputChar[dauer]);
						ref.sortChildren();
					}
				}
			}
			dauer++;
			exists = false;
		}
	}
		
	public boolean search(String input) {
		String littleString = converterObj.convString(input);
		char[] inputChar = littleString.toCharArray();
		int pos;
		boolean cond;
		pos = root.getSameChar(inputChar[0]);	
		if(pos == -1) {
			return false;	// if first char isnt found as children-> the word cant be in the tree
		}
		else {
			if(inputChar.length > 1) {
				// cancel the first character of the string and go on, if there are more than 1 characters:
				char[] firstCharAway = new char[(inputChar.length - 1)];
				for(int h = 1; h < inputChar.length; h++) {
					firstCharAway[h-1] = inputChar[h];
				}
				String newInput = new String(firstCharAway);
				cond = search(root.children[pos],newInput);
				return cond;
			}
			else {	
				return true;
			}
		}
	}
	
	public boolean search(Node newRef, String CharAway){
		String littleString = converterObj.convString(CharAway);
		char[] inputChar = littleString.toCharArray();
		int pos;
		boolean cond;
		pos = newRef.getSameChar(inputChar[0]);	
		if(pos == -1) {
			return false;	// if first char isnt found as children-> the word cant be in the tree
		}
		else {
			if(inputChar.length > 1) {
				// cancel the first character of the string and go on, if there are more than 1 characters:
				char[] firstCharAway = new char[(inputChar.length - 1)];
				for(int h = 1; h < inputChar.length; h++) {
					firstCharAway[h-1] = inputChar[h];
				}
				String newInput = new String(firstCharAway);
				cond = search(newRef.children[pos],newInput);
				return cond;
			}
			else {	
	// if charLength is 1 && getSameChar(char) found the char in Node[] childrens the whole word is inside the Tree!
				return true;
			}
		}
	}
	
	public int height() {
		maxHeight = 1;
		reference = root;
		tempHeight = 1;
		for(int h = 0; h < 26; h++) {
			if(reference.children[h] != null) {
				tempHeight++;
				height(reference.children[h]);
				if(tempHeight>maxHeight) {
					maxHeight = tempHeight;
				}
				tempHeight = 1;
			}
		}
		return maxHeight;
		
	}
	
	private void height(Node current) {
		for(int h = 0; h < 26; h++) {
			if(current.children[h] != null) {
				tempHeight++;
				height(current.children[h]);
				break;
			}
		}
    }
	
	public String longestWord() {
		word = "";
		reference = this.root;
		for(int h = 0; h < 26; h++) {
			if(reference.children[h] != null) {
				word = word.concat(Character.toString(reference.children[h].getValue()));	// Konkatinieren des Strings
				reference = reference.children[h];
				longestWord(reference);
			}
			reference = this.root;
			if(longestWord.length() < word.length()) {
				longestWord = word;
			}
			word = "";
		}
		return longestWord;
	}
	
	private void longestWord(Node ref) {
		for(int h = 0; h < 26; h++) {
			if(reference.children[h] != null) {
				word = word.concat(Character.toString(reference.children[h].getValue()));	// Konkatinieren des Strings
				reference = reference.children[h];
				longestWord(reference);
				break;
			}
			
		}
	}
	
	public String longestCommonPrefix(String input) {
		longestPrefix = "";
		String littleLetters = converterObj.convString(input);
		charWort = littleLetters.toCharArray();
		depth = 0;
		for(int h = 0; h < 26; h++) {
			if(root.children[h] != null) {
				if(root.children[h].getValue() == charWort[0]) {
					depth++;
					String character = String.valueOf(charWort[0]);
					longestPrefix = longestPrefix.concat(character);
					if(charWort.length > 1) {
						longestCommonPrefix(root.children[h]);
					}
					break;
				}
			}
		}
		return longestPrefix;
	}
	
	private void longestCommonPrefix(Node ref){
		for(int h = 0; h < 26; h++) {
			if(ref.children[h] != null) {
				if(ref.children[h].getValue() == charWort[depth]) {
					String character = String.valueOf(charWort[depth]);
					longestPrefix = longestPrefix.concat(character); 
					if(charWort.length > depth+1) {
						depth++;
						longestCommonPrefix(ref.children[h]);
					}
					break;
				}
			}
		}
	}
	
	@Override
	public String toString() {
		structure = "@\n";
		for(int h = 0; h < 26; h++) {
			if(root.children[h] != null) {
				String element = String.valueOf(root.children[h].getValue());
				structure = structure.concat(element);
				structure = structure.concat(", ");
				toString(root.children[h]);
			}
		}
		return structure;
	}

	private void toString(Node newRef) {
		structure = structure.concat(" (");
		for(int h = 0; h < 26; h++) {
			if(newRef.children[h] != null) {
				String element = String.valueOf(newRef.children[h].getValue());
				structure = structure.concat(element);
				structure = structure.concat(", ");
				toString(newRef.children[h]);
			}
		}
		structure = structure.concat(") ");
	}
	
	/*	public Tree union(Tree tree) {
	// this methode is coming ;)
	}*/
}
