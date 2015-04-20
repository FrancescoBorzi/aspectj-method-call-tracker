
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String args[]) {
		
		SampleTest.testA();

		GenericTree<String> tree = new GenericTree<String>();
		GenericTree<String> tree2 = new GenericTree<String>();

		List<GenericTreeNode<String>> list;
		List<GenericTreeNode<String>> list2;

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
		
		try {
			reader = new BufferedReader(new FileReader("output2.txt"));
			
			reader.readLine();
			
			while ((line = reader.readLine()) != null) {
				importLine(line, tree2);
			}

			System.out.println(tree2.toStringWithDepth());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		list = tree.build(GenericTreeTraversalOrderEnum.PRE_ORDER);
		//list2 = tree2.build(GenericTreeTraversalOrderEnum.PRE_ORDER);
		
		Iterator<GenericTreeNode<String>> itr = list.iterator();
		
		while (itr.hasNext()) {
			GenericTreeNode<String> node = itr.next();
			String data = node.getData();
			
			GenericTreeNode<String> node2 = tree2.find(data);

			if (node2 != null) {
				System.out.println("Node: " + data);
				System.out.println("Tree1 depth:" + node.getDepth());
				System.out.println("Tree2 depth:" + node2.getDepth());
				System.out.println("\n");
			}
					
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
