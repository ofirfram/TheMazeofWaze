package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import utils.Point3D;

public class DGraph implements graph {
	HashMap<Integer, Node> ver = new HashMap<Integer, Node>();
	HashMap<Integer, Edge> ed = new HashMap<Integer, Edge>();
	HashMap<Integer, HashMap<Integer, Edge>> path = new HashMap<Integer, HashMap<Integer, Edge>>();

	private int counter=0;
	private int MC=0;
	private Edge edge[];
	public Node vertex[];
	
	public DGraph(Node vertex[] , Edge edge[]){
		ed = new HashMap<Integer, Edge>();
		this.vertex=vertex;
		this.edge=edge;
		
		for(Node x: vertex) {
			ver.put(x.getKey(), x);
			this.MC++;
		}

		for(Edge y: edge) {
			if(path.containsKey(y.getSrc())  ) {
				ed = this.path.get(y.getSrc());
				ed.put(y.getDest(), y);	
			}
			else {
				ed.put(y.getDest(), y);
				this.path.put(y.getSrc(), ed);
				ed = new HashMap<Integer, Edge>();
			}
			this.MC++;
			this.counter++;
		}

	}	

	public static void main(String[] argas) {
		Point3D p1 = new Point3D(0,1,0);
		Point3D p2 = new Point3D(1,1,0);
		Point3D p3 = new Point3D(1,0,0);
		Point3D p4 = new Point3D(0,0,0);
		Point3D p5 = new Point3D(0,2,0);

		Node n1 = new Node(1,1,p1);
		Node n2 = new Node(2,1,p2);
		Node n3 = new Node(3,1,p3);
		Node n4 = new Node(4,1,p4);
		Node n5 = new Node(5,1,p5);
		Node n6 = new Node(6,1,p5);

		Edge e1 = new Edge(n1, n2, 1);
		Edge e2 = new Edge(n2, n3, 1);
		Edge e3 = new Edge(n4, n3, 1);
		Edge e4 = new Edge(n4, n1, 1);
		Edge e5 = new Edge(n1, n5, 1);

		Edge edge[]= {e1,e2,e3,e4,e5};
		Node node[] = {n1,n2,n3,n4,n5};
		
		DGraph g = new DGraph(node , edge);	
	}

	@Override
	public node_data getNode(int key) {

		return  this.ver.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		counter++;
		return this.path.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.ver.put(n.getKey(), (Node) n);
		this.MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		ed = new HashMap<Integer, Edge>();
		Node n = this.ver.get(src);
		Node n1 = this.ver.get(dest);
		Edge e = new Edge(n , n1 , w);
		
		if(this.path.get(e.getSrc()) != null) {ed=this.path.get(e.getSrc());}
		ed.put(n1.getKey(), e);
		this.path.put(e.getSrc(), ed);
		this.counter++;
		this.MC++;
	}

	@Override
	public Collection<node_data> getV() {	
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		Node n = this.ver.get(key);
		for(Edge x :this.edge) {
			if(x.getDest() == key) { 
				this.path.get(x.getSrc()).remove(x.getDest()); 
				this.counter--;
				this.MC++;
				}
			if(x.getSrc() == key) { 
				this.path.get(x.getSrc()).remove(x.getDest());
				this.counter--;
				this.MC++;
				}
		}
		this.ver.remove(key);
		this.MC++;
		this.path.remove(key);
		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		Edge e = this.path.get(src).get(dest);
		this.path.get(src).remove(dest);
		if(this.path.get(src).isEmpty()) {this.path.remove(src);}
		this.counter--;
		this.MC++;
		return  e;
	}

	@Override
	public int nodeSize() {
		
		return this.ver.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return this.counter;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return this.MC;
	}

}
