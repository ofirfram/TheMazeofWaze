package algorithms;

import java.util.Comparator;
import dataStructure.Node;
import dataStructure.node_data;

public class NodeComperator implements Comparator<node_data>{

	@Override
	public int compare(node_data node1, node_data node2) {
		double weight1 = node1.getWeight();
		double weight2 = node2.getWeight();
		return ((int)(weight1-weight2));
	}

}