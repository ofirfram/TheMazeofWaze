package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import utils.Point3D;

public class DGraph   implements graph {
	public HashMap<Integer, node_data> ver = new HashMap<Integer, node_data>();
	private HashMap<Integer, edge_data> ed = new HashMap<Integer, edge_data>();
	public HashMap<Integer, HashMap<Integer, edge_data>> nodeedges = new HashMap<Integer, HashMap<Integer, edge_data>>();

	private int counter=0;
	public int MC=0;
	public Edge edge[];
	public Node vertex[];
	/**
	 * DGraph constructor represents  graph 
	 * for vertex and edges connected
	 * input arrays , Node vertex[] , Edge[]
	 * set the match of vertex and edges
	 *
	 */
	public DGraph(Node vertex[] , Edge edge[]){
		if(edge.length == 0 || vertex.length == 0)throw new RuntimeException( "no edge or vertez to construct");
		ed = new HashMap<Integer, edge_data>();
		this.vertex=vertex;
		this.edge=edge;

		for(Node x: vertex) {
			this.ver.put(x.getKey(), x);
			this.MC++;
		}

		for(Edge y: edge) {
			if(nodeedges.containsKey(y.getSrc()) ) {
				ed = (HashMap<Integer, edge_data>) this.nodeedges.get(y.getSrc());
				ed.put(y.getDest(), y);	
				ed = new HashMap<Integer, edge_data>();
			}
			else {
				ed.put(y.getDest(), y);
				this.nodeedges.put(y.getSrc(), ed);
				ed = new HashMap<Integer, edge_data>();
			}	
			this.MC++;
			this.counter++;
		}

	}	

	public DGraph(){
		Node n0 = new Node(0);
		this.vertex = new Node[1];
		this.vertex[0] = n0;
		this.ver.put(0, n0);
		this.MC ++;
		this.counter ++;
	}
	public static void main(String[] argas) {
		Point3D p1 = new Point3D(0,1,0);
		Point3D p2 = new Point3D(1,1,0);
		Point3D p3 = new Point3D(1,0,0);
		Point3D p4 = new Point3D(0,0,0);
		Point3D p5 = new Point3D(0,2,0);

		Node n0 = new Node(0,1,p1);
		Node n1 = new Node(1,1,p2);
		Node n2 = new Node(2,1,p3);
		Node n3 = new Node(3,1,p4);
		Node n4 = new Node(4,1,p5);

		Edge e1 = new Edge(n0, n1, 1);
		Edge e2 = new Edge(n1, n0, 3);
		Edge e3 = new Edge(n2, n1, 40);
		Edge e4 = new Edge(n1, n2, 2);
		Edge e5 = new Edge(n0, n2, 1);
		Edge e6 = new Edge(n2, n0, 6);

		Edge edge[]= {e1,e2,e3,e4,e5,e6};
		Node node[] = {n0,n1,n2,n3,n4};

		DGraph g = new DGraph(node , edge);
		System.out.println(g.getMC());
		System.out.println(g.ver);
		System.out.println(g.nodeedges);
		System.out.println();

		g.removeEdge(0, 1);
		System.out.println(g.ver);
		System.out.print(g.nodeedges);	
		System.out.println("    remove edge 0-->1");
		System.out.println();
		System.out.println(e1.getTag());
		g.removeNode(0);
		System.out.print(g.ver);
		System.out.println("    remove Node 0");
		System.out.print(g.nodeedges);
		System.out.println("    remove edge 1-->0 , 2-->0 , 0-->2");
		System.out.println();

		g.removeNode(1);
		System.out.println(g.ver);
		System.out.println(g.nodeedges);
		System.out.println();

		g.connect(2, 0, 2);
		System.out.println(g.ver);
		System.out.println(g.nodeedges);
		System.out.println();

		g.addNode(n1);
		System.out.println(g.ver);
		System.out.println(g.nodeedges);
		System.out.println();

		g.connect(0, 1, 2);
		System.out.println(g.ver);
		System.out.println(g.nodeedges);
		System.out.println();
		System.out.println(g.getMC());
		System.out.println(g.getE(0));
	}

	@Override
	public node_data getNode(int key) {
		return  this.ver.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(!this.ver.containsKey(src) || !this.ver.containsKey(dest)) return null;
		return this.nodeedges.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.ver.put(n.getKey(), (Node) n);
		this.MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(!this.ver.containsKey(src)) this.addNode(new Node(src));
		if(!this.ver.containsKey(dest)) this.addNode(new Node(dest));
		ed = new HashMap<Integer, edge_data>();
		Node n = (Node)this.ver.get(src);
		Node n1 = (Node)this.ver.get(dest);
		Edge e = new Edge(n , n1 , w);

		if(this.nodeedges.get(e.getSrc()) != null) {ed=this.nodeedges.get(e.getSrc());}
		ed.put(n1.getKey(), e);
		this.nodeedges.put(e.getSrc(), ed);
		this.counter++;
		this.MC++;
	}

	@Override
	public Collection<node_data> getV() {	
		if(this.ver.values() == null) return null;
		Collection<node_data> v = this.ver.values(); 
		return v;
	}

	@Override
	public Collection<edge_data> getE(int node_id){
		
		if(!this.ver.containsKey(node_id)) return null;
		else if(this.nodeedges.get(node_id) == null)return null;
		else return this.nodeedges.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		Node n = (Node) this.ver.get(key);
		for(Edge e :this.edge) {
			if(e.getSrc() == key && e.dest != null && e.getTag() == 0) { 
				this.removeEdge(key, e.getDest());
				this.counter--;
				this.MC++;
				e.setTag(1);
			}
			if(e.getDest() == key && e.src != null && e.getTag() == 0) { 
				this.nodeedges.get(e.getSrc()).remove(e.getDest());
				this.counter--;
				this.MC++;
				e.setTag(1);
			}
		}
		this.ver.remove(key);
		this.MC++;
		this.nodeedges.remove(key);
		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(!this.ver.containsKey(src) || !this.ver.containsKey(dest)) throw new RuntimeException( "edge missisng");
		Edge e = (Edge) this.nodeedges.get(src).get(dest);
		if(this.ver.containsKey(src) && !this.ver.containsKey(dest)) {
			this.nodeedges.get(src).get(dest).setTag(1);
			this.nodeedges.get(src).remove(dest);
		}
		if(this.nodeedges.get(src).containsKey(dest)) {
			this.nodeedges.get(src).get(dest).setTag(1);
			this.nodeedges.get(src).remove(dest);
			if(this.nodeedges.get(src).isEmpty()) {
				this.nodeedges.remove(src);
			}
		}

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
		return this.counter;
	}

	@Override
	public int getMC() {
		return this.MC;
	}
}
