package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

public class TestDGraph {
	
	Point3D p0 = new Point3D(1,1,1);
	Point3D p1 = new Point3D(2,2,2);
	Point3D p2 = new Point3D(3,3,3);
	Point3D p3 = new Point3D(4,4,4);
	
	Node n0 = new Node(0,1,p0);
	Node n1 = new Node(1,1,p1);
	Node n2 = new Node(2,1,p1);
	
	Edge e1 = new Edge(n0,n1,5);
	Edge e2 = new Edge(n1,n2,5);
	Edge e3 = new Edge(n2,n0,5);
	
	Node[] h = {n0,n1,n2};
	Edge[] l = {e1,e2,e3};
	graph g = new DGraph(h, l);
	
	Graph_Algo G = new Graph_Algo(g);
	Graph_GUI GD = new Graph_GUI(g);
	
	//////////////////Node tests
	@Test
	public void testNodefromString() {
		n0 = new Node(5,1,p0);
		n0.setInfo("0,1,3,3,3");
		assertEquals(n0.getKey(),0);	
	}
	@Test
	public void testNodeSetlocation() {
		n0.setLocation(p1);
		assertEquals(n0.getLocation(),p1);	
	}
	//////////////////Edge tests
	@Test
	public void testEdge() {
		int[] a = {e1.getSrc() ,e1.getDest() , (int) e1.getWeight()};
		int [] b={0,1,5};
		assertArrayEquals(a,b);	
	}
	@Test
	public void testEdgeSetinfo() {
		e1.setInfo("0,1,8");
		assertEquals(e1.getDest(),1);	
	}
	//////////////////Test graph function on Node
	@Test
	public void GraphGetNode() {
		assertEquals(g.getNode(2), n2);
	}
	@Test
	public void GraphAddNode() {
		node_data n3 = new Node(3,1 ,new Point3D(5,5,5));
		g.addNode(n3);
		assertEquals(g.nodeSize(), 4);
	}
	@Test
	public void GraphremoveNode() {
		g.removeNode(1);
		assertEquals(g.getV().contains(1), false);
	}
	public void GraphMC() {
		assertEquals(g.getMC(), 6);
	}
    //////////////////Test graph function on Edge
	@Test
	public void GraphsGetEdge() {	
		assertEquals(g.getEdge(0, 1), e1);
	}
	@Test
	public void GraphsAddEdge() {	
		g.connect(0, 2, 5);
		assertEquals(g.edgeSize(), 4);
	}
	@Test
	public void GraphremoveEdge() {	
		g.removeEdge(0, 1);
		assertEquals(e1.getTag(), 1);
	}
    //////////////////Test graph algorithms - Graph_Algo
	@Test
	public void GraphConnected() {
		assertEquals(G.isConnected(), true);
	}
	@Test
	public void GraphShortestPath() {
		node_data n3 = new Node(3,1 ,new Point3D(5,5,5));
		node_data n4 = new Node(3,1 ,new Point3D(5,5,5));
		g.addNode(n3);
		g.addNode(n4);
		g.connect(4, 3, 5);
		g.connect(4, 0, 40);
		g.connect(3, 0, 1);
		int d =(int) G.shortestPathDist(4, 0);
		assertEquals(d, 6);
	}
	@Test
	public void GraphBfs() {
		node_data n3 = new Node(3,1 ,new Point3D(5,5,5));
		node_data n4 = new Node(3,1 ,new Point3D(5,5,5));
		g.addNode(n3);
		g.addNode(n4);
		g.connect(4, 3, 5);
		g.connect(4, 0, 40);
		g.connect(3, 0, 1);
		assertEquals(G.BFS(n0), 2);
	}
//////////////////Test graph GUI - Graph_Gui
	@Test
	public void GraphDraw() {
		assertEquals(GD.drawGraph(g), true);
	}
}
