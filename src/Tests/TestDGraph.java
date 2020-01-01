package Tests;

import static org.junit.Assert.*;
import org.junit.Test;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import utils.Point3D;

public class TestDGraph {
	
	Point3D p0 = new Point3D(0,0,0);
	Point3D p1 = new Point3D(0,0,0);
	Point3D p2 = new Point3D(0,0,0);
	Point3D p3 = new Point3D(0,0,0);
	
	Node n0 = new Node(0,1,p0);
	Node n1 = new Node(1,1,p1);
	Node n2 = new Node(1,1,p1);
	
	Edge e1 = new Edge(n0,n1,5);
	Edge e2 = new Edge(n1,n2,5);
	Edge e3 = new Edge(n2,n0,5);
	
	Node[] h = {n0,n1,n2};
	Edge[] l = {e1,e2,e3};
	DGraph g = new DGraph(h, l);
	
	@Test
	public void testNode() {
		n0 = new Node(5,1,p0);
		n0.setInfo("0,1,3,3,3");
		assertEquals(n0.getKey(),0);	
	}
	
	@Test
	public void testEdge() {
		int[] a = {e1.getSrc() ,e1.getDest() , (int) e1.getWeight()};
		int [] b={0,1,5};
		assertArrayEquals(a,b);	
	}
	
	@Test
	public void GraphremoveEdge() {	
		g.removeEdge(0, 1);
		assertEquals(e1.getTag(), 1);
	}
	public void GraphremoveNode() {
		g.removeNode(1);
		assertEquals(g.ver.containsKey(1), false);
	}
}
