import lexiographTree.Tree;
// main in default package

/**
*
* main-function for the lexiographic tree
*
* @author Philipp, Slebioda, 4809007
*/

public class TreeMain {
	
	public static void main(String[] args) {
		Tree treeObj = new Tree();
		treeObj.insertRecursive("Hallo");
		treeObj.insertRecursive("Waaaas");
		treeObj.insertRecursive("Teetrinker");
		treeObj.insertRecursive("Teeistcool");
		treeObj.insertRecursive("Teeschmecktlecker");
		treeObj.insertRecursive("chakalacahahaha");
		String l�ngstesWort = treeObj.longestWord();
		System.out.println("Hier das laengste Wort: " + l�ngstesWort);
		int h�he = treeObj.height();
		System.out.println("Max hoehe des Baums(root + alle Zeichen des l�ngsten Worts): " + h�he);
		String prefix = treeObj.longestCommonPrefix("Tee");
		System.out.println("Der l�ngste Prefix f�r 'Tee': " + prefix);
		boolean stat = treeObj.search("Teetrinker");
		System.out.println("Ist 'Teetrinker' im Baum?: " + stat);
		stat = treeObj.search("Teeistcool");
		System.out.println("Ist 'Teeistcool' im Baum?: " + stat); 
		System.out.println(treeObj);
		
	}
}
