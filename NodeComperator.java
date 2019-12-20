package algorithms;

import java.util.Comparator;
import dataStructure.Node;

public class NodeComperator implements Comparator<Node>{

	@Override
	public int compare(Node node1, Node node2) {
		double weight1 = node1.getWeight();
		double weight2 = node2.getWeight();
		return (int) (weight1-weight2);
	}
}