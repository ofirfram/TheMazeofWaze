package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import utils.Point3D;
/**
 * This class represents a graph by connecting each vertex and edges  
 * @author OfirBador & ElnatanBerenson
 *
 */
public class DGraph implements graph,Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
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
	/**
	 * DGraph constructor represents default graph 
	 */
	public DGraph(){
		Node n0 = new Node(0);
		this.vertex = new Node[1];
		this.vertex[0] = n0;
		this.ver.put(0, n0);
		this.MC ++;
		this.counter = 0;
	}
	/**
	 * this method return the requested Node's key (info)
	 * @return node info
	 */
	@Override
	public node_data getNode(int key) {
		return  this.ver.get(key);
	}
	/**
	 * this method return the requested edge connected to the 
	 * selected src vertex and dest vertex
	 * @return edge info
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		if(!this.ver.containsKey(src) || !this.ver.containsKey(dest)) return null;
		return this.nodeedges.get(src).get(dest);
	}
	/**
	 * this method add the selected vertex to the graph 
	 */
	@Override
	public void addNode(node_data n) {
		this.ver.put(n.getKey(), (Node) n);
		this.MC++;
	}
	/**
	 * this method creates and connect edge between
	 * the selected vertex
	 * direction of the edge will set 
	 * from Src vertex to Dest vertex 
	 * and will set the edge weight (w)
	 */
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
	/**
	 * this method returns the set of vertex 
	 * in the selected graph
	 * @return the vertex graph set
	 */
	@Override
	public Collection<node_data> getV() {	
		if(this.ver.values() == null) return null;
		Collection<node_data> v = this.ver.values(); 
		return v;
	}
	/**
	 * this method returns collection of all edges 
	 * associated with the selected vertex
	 * @return Node edge collection
	 */
	@Override
	public Collection<edge_data> getE(int node_id){
		if(!this.ver.containsKey(node_id)) return null;
		else if(this.nodeedges.get(node_id) == null)return null;
		else return this.nodeedges.get(node_id).values();
	}
	/**
	 * this method removes the selected vertex
	 * note- remove all connected edge associated with
	 * this vertex in the graph
	 * @return the constructed node
	 */
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
	/**
	 * this method removes the selected edge
	 * between the selected vertex
	 * from Src vertex to Dest vertex
	 * note - if existed edge between Dest vertex to Src vertex
	 * it will NOT remove!
	 * @return the constructed edge
	 */
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
	/**
	 * this method checked the number of vertex in the graph
	 * @return number of vertex
	 */
	@Override
	public int nodeSize() {
		return this.ver.size();
	}
	/**
	 * this method checked the number of edges in the graph
	 * @return number of edges
	 */
	@Override
	public int edgeSize() {
		return this.counter;
	}
	/**
	 * @return number of changes from the graph construction start
	 */
	@Override
	public int getMC() {
		return this.MC;
	}
	/**
	 * this method sets graph from String
	 */
	public void init(String s) {
		Edge e1 = new Edge();
		Node ns = new Node();
		Node nd = new Node();
		Edge[] edge = new Edge[1000];
		Node[] vertex = new Node[50];
		int cnt = 0;

		do {
			s = s.substring(s.indexOf("src"));
			s = s.substring(s.indexOf("src")+5); 
			String f = s.substring(0, s.indexOf(","));		 
			int k = Integer.parseInt(f);		 
			ns = new Node(k);					 
			s = s.substring(s.indexOf(':')+1);	
			f = s.substring(0, s.indexOf(','));
			double w = Double.parseDouble(f);	
			s = s.substring(s.indexOf(",")+8);
			f = s.substring(0, s.indexOf('}'));
			k = Integer.parseInt(f);
			nd = new Node(k);
			e1 = new Edge(ns,nd,w);
			edge[cnt] = e1;
			cnt++;
			
		}while(s.indexOf("Nodes") > 6);	
		cnt=0;
		s = s.substring(s.indexOf(",")+1);
		do {
			s = s.substring(s.indexOf("pos")+6); 
			String f = s.substring(0, s.indexOf(','));
			double x = Double.parseDouble(f);
			s = s.substring(s.indexOf(",")+1);
			f = s.substring(0, s.indexOf(','));
			double y = Double.parseDouble(f);
			s = s.substring(s.indexOf(":")+1);
			f = s.substring(0, s.indexOf('}'));
			int k = Integer.parseInt(f);
			s = s.substring(s.indexOf("}"));
			Point3D p = new Point3D(x,y,0);
			Node n = new Node(k,0,p);
			this.addNode(n);
		} while(s.length()>3);
		
		cnt = 0;
		for (int i = 0; i < edge.length; i++) {
			if(edge[i] != null) {
				cnt++;
			}
		}
		Edge[] edgefinal = new Edge[cnt-1];
		cnt=0;
		for (int i = 1; i < edge.length; i++) {
			if(edge[i] != null) {
				Edge e3 = edge[i];
				edgefinal[cnt] = e3;
				cnt++;
				this.connect(e3.getSrc(), e3.getDest(), e3.getWeight());
				}
		}	
		this.edge = edgefinal;
		this.vertex = vertex;
	}
}
