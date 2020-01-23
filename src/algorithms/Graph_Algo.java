package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This  class represents the set of graph-theory algorithms 
 * @author OfirBador & ElnatanBerenson
 *
 */
public class Graph_Algo implements graph_algorithms{
	private NodeComperator compareNodes = new NodeComperator();
	private int[] k;
	private node_data[] p;
	public graph g;
	double inf = Double.POSITIVE_INFINITY;

	private List<node_data> path = new ArrayList<node_data>();
	/**
	 * This method represents constructor sets initial conditions for 
	 * The graph chosen
	 */
	public Graph_Algo(graph g){
		this.g = g;
		this.k = new  int[g.nodeSize()];
		for (int i = 0; i<k.length; i++) {
			this.k[i] = i;
		}	
	}
	/**
	 * This method sets graphs initial conditions
	 *  such as key, parent vertex 
	 *  used for algorithms
	 */
	@Override
	public void init(graph g) {
		for(node_data n : this.g.getV()) {
			n.setTag(0);
		}
		for (int i = 0; i <this.g.getV().size(); i++) {
			this.k[i]=i;
			this.p[i]=this.g.getNode(i);
		}
	}
	/**
	 * This method set initial conditions from saved file 
	 */
	@Override
	public void init(String file_name) {
		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			graph l =  (graph) in.readObject();
			this.g = l;
			in.close();
		}
		catch(Exception e) {
			System.out.println("problem with file init");
		}	
	}
	/**
	 * This method saved graph to file to specific location
	 * choose your file name
	 */
	@Override
	public void save(String file_name) {

		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.g);
			out.close();		
		}

		catch(Exception e){
			System.out.println("problem with file save");
		}
	}
	/**
	 * This method run on any graph and returns true if all graph is connected 
	 * Based on BFS algorithm (elaboration on the detail in BFS method)
	 */
	@Override
	public boolean isConnected() {	
		if(this.g.getV().size() == 0 ) return false;
		else if(this.g.getV().size() == 1 ) return true;
		else {
			for(node_data x: this.g.getV()) {
				if(this.g.getE(x.getKey()) == null) return false;
				if(BFS(x) != 2) return false;
			}
		}
		return true;
	}
	/**
	 * This method based on Dijkstra algorithm and checked the shortest path from 
	 * Two selected vertex
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(!this.g.getV().contains(g.getNode(src)) || !this.g.getV().contains(g.getNode(dest)))throw new RuntimeException( "no vertex to path");
		if(!this.g.getV().contains(g.getNode(src)) && this.g.getV().contains(g.getNode(dest))) return 0;
		else if(this.g.getV().contains(g.getNode(src)) && !this.g.getV().contains(g.getNode(dest))) return 0;
		else {
			node_data start = this.g.getNode(src);
			PriorityQueue<node_data> q = new  PriorityQueue<node_data>(this.g.getV().size(), compareNodes);
			double[] dis = new  double[g.nodeSize()];
			boolean[] vis = new boolean[g.nodeSize()];
			p = new Node[g.nodeSize()];

			for (int i = 0; i<this.g.nodeSize(); i++) {
				dis[i] = inf;
				this.p[i] = null;
				vis[i] = false;
			}	

			dis[src]=0;
			q.clear();
			q.add(start);

			if(this.g.getE(src).equals(null)) {
				q.remove(start);
				return 0;
			}
			while(!q.isEmpty()){
				node_data u = q.poll();
				if(this.g.getE(u.getKey()).isEmpty()) q.remove(u);
				else {
					for(edge_data e : this.g.getE(u.getKey())) {
						node_data v = this.g.getNode(e.getDest());

						if(vis[v.getKey()] == false ){
							if(dis[v.getKey()] > dis[u.getKey()] + e.getWeight()) {
								dis[v.getKey()] = dis[u.getKey()] + e.getWeight();
								this.p[e.getDest()] = u;
								q.remove(u);
								q.add(u);
								q.add(v);		
							}
						}
					}
				}
				vis[u.getKey()] = true;
			}
			return  dis[dest];
		}
	}
	/**
	 * This method returns (if available) the list of vertex in the path  
	 * between Two selected vertex
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if(!this.g.getV().contains(g.getNode(src)) || !this.g.getV().contains(g.getNode(dest)))throw new RuntimeException( "no vertex to path");
		path = new ArrayList<node_data>();	
		path.add(this.g.getNode(dest));
		this.shortestPathDist(src, dest);
		node_data t =  this.g.getNode(dest);
		node_data u =this.g.getNode(src);
		while(t!=u ) {
			t = this.p[t.getKey()];
			path.add(t);
		}
		return path;
	}
	/**
	 * This method returns (if available) a list contains   
	 * all vertex in targets 
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> l = new ArrayList<>();
		List<node_data> L = new ArrayList<>();
		try {
			for (int j = 0; j < targets.size()-1; j++) {		
				l=this.shortestPath(targets.get(j),targets.get(j+1));
				System.out.println(l);
				for (int i =l.size()-1 ; i >0; i--) {
					L.add(l.get(i));
				}
			}
		}
		catch(Exception e){
			System.out.println("no path between vertex");
			return null;
		}

		L.add(this.g.getNode(targets.get(targets.size()-1)));
		return L;
	}
	/**
	 * This method copy graph 
	 */
	@Override
	public graph copy() {
		graph l = this.g;
		return l;
	}
	/**
	 * This method prints a selected graph
	 */
	public String tostring() {
		String vertex = "";
		String edge = "";
		for(node_data n : this.g.getV()) {
			vertex = vertex+ "(" + n.getKey()+ ")";
			for(edge_data e : this.g.getE(n.getKey())) {
				edge = edge + "(" + e.getSrc()+ "," + e.getDest() + ")";
			}
		}
		return "(V)=" + vertex + ", (E)=" + edge;
	}
	/**
	 * BFS algorithm for vertex - checks all existing connections
	 * in this graph 
	 * return tag of the checked vertex
	 * 0 - not connected
	 * 1 - connected to another vertex but not fully checked
	 * 2 - checked all vertex connected with n
	 */
	public int BFS( node_data n) {
		Stack<node_data> q = new Stack<node_data>();
		q.empty();
		q.push(n);	
		while(!q.isEmpty()) {
			node_data u = q.pop();
			for(edge_data e : this.g.getE(u.getKey())) {
				if(e.getDest() == u.getKey() && u.getTag() == 0) {
					u.setTag(1);
					q.push(this.g.getNode(e.getDest()));
				}
			}
			u.setTag(2);
		}
		return n.getTag();
	}
}
