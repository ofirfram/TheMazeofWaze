package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
/**
 * This  class represents the set of graph-theory algorithms 
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private NodeComperator compareNodes = new NodeComperator();
	private int[] k;
	private node_data[] p;
	public graph g;
	public boolean connected;
	double inf = Double.POSITIVE_INFINITY;

	private List<node_data> path = new ArrayList<node_data>();
	public static void main(String[] argas) {
		Point3D p1 = new Point3D(0,1,0);
		Point3D p2 = new Point3D(1,1,0);
		Point3D p3 = new Point3D(1,0,0);
		Point3D p4 = new Point3D(0,0,0);
		Point3D p5 = new Point3D(0,2,0);

		Node n0 = new Node(0,0,p1);
		Node n1 = new Node(1,0,p2);
		Node n2 = new Node(2,0,p3);
		Node n3 = new Node(3,0,p4);
		Node n4 = new Node(4,0,p5);

		Edge e1 = new Edge(n0, n1, 1);
		Edge e2 = new Edge(n1, n2, 2);
		Edge e3 = new Edge(n2, n3, 5);
		Edge e4 = new Edge(n3, n4, 3);
		Edge e5 = new Edge(n4, n0, 4);
		Edge e6 = new Edge(n0, n4, 10);

		Edge edge[]= {e1,e2,e3,e4,e5,e6};
		Node node[] = {n0,n1,n2,n3,n4};
		graph g = new DGraph(node , edge);
		Graph_Algo G = new Graph_Algo(g);

		System.out.println(G.tostring());
		System.out.println(G.isConnected());
		System.out.println(G.shortestPathDist(0, 2));
		System.out.println(G.shortestPath(0, 2));
		//		G.save("ofir");
		//		G.init("ofir");




	}



	public Graph_Algo(graph g){
		this.g = g;
		this.k = new  int[g.nodeSize()];
		for (int i = 0; i<k.length; i++) {
			this.k[i] = i;
		}	
	}

	@Override
	public void init(graph g) {
		this.connected = this.isConnected();
	}

	@Override
	public void init(String file_name) {
		try {
			edge_data t = new Edge(new Node(3) , new Node(2), 4);
			FileInputStream fileIn = new FileInputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			t = (edge_data) in.readObject();
			in.close();
			System.out.println(t);
		}
		catch(Exception e) {
			System.out.println("problem with file init");
		}	
	}

	@Override
	public void save(String file_name) {
		node_data n = new Node (1);
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(n);
			out.close();		
		}

		catch(Exception e){
			System.out.println("problem with file save");
		}
	}


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

	@Override
	public graph copy() {
		return this.g;
	}

	public String tostring() {
		String vertex = "";
		String edge = "";
		int i=0;
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
	private int BFS( node_data n) {
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
