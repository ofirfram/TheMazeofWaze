package algorithms;

import java.util.Comparator;
import dataStructure.Node;
import dataStructure.node_data;

public class NodeComperator implements Comparator<node_data>{

	@Override
	public int compare(node_data node1, node_data node2) {
		/**Compare between 2 nodes by there distance from source node
		 * @return positive number if  the first one is bigger
		 * @return negative number if the second one is bigger
		 * @return 0 if equals
		 */
		double distance1 = node1.getWeight();
		double distance2 = node2.getWeight();
		return ((int)(distance1-distance2));
	}

}