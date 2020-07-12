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
		String längstesWort = treeObj.longestWord();
		System.out.println("Hier das laengste Wort: " + längstesWort);
		int höhe = treeObj.height();
		System.out.println("Max hoehe des Baums(root + alle Zeichen des längsten Worts): " + höhe);
		String prefix = treeObj.longestCommonPrefix("Teetrkalso");
		System.out.println("Der längste gemeinsame Prefix für 'Teetrkalso': " + prefix);
		boolean stat = treeObj.search("Teetrinker");
		System.out.println("Ist 'Teetrinker' im Baum?: " + stat);
		stat = treeObj.search("Teeistcool");
		System.out.println("Ist 'Teeistcool' im Baum?: " + stat); 
		stat = treeObj.search("Waaaas");
		System.out.println("Ist 'Waaaas' im Baum?: " + stat);
		stat = treeObj.search("Hallo");
		System.out.println("Ist 'Hallo' im Baum?: " + stat);
		stat = treeObj.search("DieseswortIstnichtdrinnen");
		System.out.println("Ist 'DieseswortIstnichtdrinnen' im Baum?: " + stat);
		System.out.println(treeObj);
		
	}
}
