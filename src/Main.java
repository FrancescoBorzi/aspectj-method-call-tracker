
import java.io.*;

public class Main {

	public static void main(String args[]) {
		
		SampleTest.testA();

		GenericTree<String> tree = new GenericTree<String>();

		BufferedReader reader;
		String line;

		try {
			reader = new BufferedReader(new FileReader("output.txt"));
			
			reader.readLine();
			
			while ((line = reader.readLine()) != null) {
				importLine(line, tree);
			}

			System.out.println(tree.toStringWithDepth());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void importLine(String line, GenericTree<String> tree) {
		int i, start, end;
		String parent, child;
		GenericTreeNode<String> parentNode, childNode;

		i = 0;

		while (i < line.length() && line.charAt(i) != '\"')
			i++;

		if (i >= line.length())
			return;

		i++;
		start = i;

		while (line.charAt(i) != '\"')
			i++;

		end = i;
		
		parent = line.substring(start, end);
		i++;

		while (line.charAt(i) != '\"')
			i++;

		i++;
		start = i;

		while (line.charAt(i) != '\"')
			i++;

		end = i;
		
		child = line.substring(start, end);

		
		System.out.println("Adding child\t\"" + child + "\"\tto parent \"" + parent + "\"");
		
		childNode = new GenericTreeNode<String>(child);
		
		if (tree.getRoot() == null)
		{
			parentNode = new GenericTreeNode<String>(parent);
			parentNode.setDepth(0);
			tree.setRoot(parentNode);

			parentNode.addChild(childNode);

			return;
		}

		parentNode = tree.find(parent);
		
		if (parentNode == null)
			System.out.println("Error: parent node \"" + parent + "\" not found!");
		
		parentNode.addChild(childNode);
	}
}
