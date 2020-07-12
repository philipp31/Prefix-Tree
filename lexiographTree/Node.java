package lexiographTree;

/**
*
* provides the Node class(nodes) of the lexiographic tree
*
* @author Philipp, Slebioda, 4809007
*/
public class Node implements Comparable<Node> {// Interface ist generisch nicht vergessen!
	
	protected char value;
	protected Node[] children;
	
	public Node(char value) {
		this.value = value;
		children = new Node[26];
	}
	
	public char getValue() {
		return this.value;
	}
	
	
	public boolean hasChildren() {
		int anzNull = 0;
		for(int k=0; k < 26;k++) {
			if(children[k] == null) {
				anzNull++;
			}
		}
		if(anzNull >= 26) {
			return false;
		}
		return true;
	}
	
	public Node[] getChildren() {
		return children;
	}
	
	public char getChildrensChar(int k) {
		return children[k].value;
	}
	public void setValue(char k) {
		this.value = k;
	}
	
	public int getSameChar(char searched) {
		for(int i=0; i < 26; i++) {
			if(children[i] != null) {		
				if(children[i].value == searched) {
					return i;
				}	
			}
		}
		return -1;
	}
	
	// give first free position in childrens-array:
	public boolean fuelChar(char k) {	// Einsortierung soll lexiographisch erfolgen:
		for(int i = 0; i < children.length; i++) {
			if(children[i] == null) {
					children[i] = new Node(k);
					bubbleSort(children);
					return true;
			}
		}
		return false;
	}
	
	public void sortChildren() {
		bubbleSort(children);
	}
	
	public <T extends Comparable<T>> void bubbleSort(T[] elements) {
		boolean sorted;
		do {
			sorted = true;
			for( int i = 0; i < elements.length-1; i++) {
				try {
					if(elements[i].compareTo(elements[i+1]) > 0 ) {	// die Elemente mit großen Flächen werden nach oben sortiert ins array!
						// Le tausch im le dreieck:
						T tmp = elements[i];
						elements[i] = elements[i+1];
						elements[i+1] = tmp;
						sorted = false;
					}
				} catch(NullPointerException e) {
					// weil in den Children Array viele nulls sind kommt das entsprechend häufig
				}
				
			}
		} while (!sorted);
	}
	
	@Override	// compareTo-Methode des inferfaces implementieren:
	public int compareTo(Node o) {
// Nodes werden ORTHOGRAHISCH verglichen(!) durch Programm:
		char other;
		char tVal;
		try {
			other = o.getValue();
			tVal = this.getValue();
		} catch(NullPointerException e) {
			return 0;
		}
		// Definieren des Comparable-Interfaces:
		if(((int) tVal) < ((int) other)) {
			return -1;	// aktueller char kleiner return -1
		}
		else if(((int) tVal) > ((int) other)) {
			return 1;	// aktueller char größer return 1
		}
		else {
			return 0;
		}
	}
}
