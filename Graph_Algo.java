package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private NodeComperator compareNodes = new NodeComperator();
	private int[] k;
	private Node[] p;
	private DGraph g;

	public static final int inf = 10000000;
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
		Edge e2 = new Edge(n1, n2, 3);
		Edge e3 = new Edge(n2, n3, 40);
		Edge e4 = new Edge(n3, n0, 2);
		Edge e5 = new Edge(n4, n3, 6);
		Edge e6 = new Edge(n2, n4, 1);

		Edge edge[]= {e1,e2,e3,e4,e5,e6};
		Node node[] = {n0,n1,n2,n3,n4};
		DGraph g = new DGraph(node , edge);
		Graph_Algo G = new Graph_Algo(g);

		System.out.println(G.shortestPathDist(n0.getKey(), n3.getKey()));
		System.out.println(G.shortestPath(n0.getKey(), n3.getKey()));
		G.save("ofir");
		G.init("ofir");
	}

	public Graph_Algo(DGraph g){
		this.g = g;
		this.k = new  int[g.nodeSize()];
		for (int i = 0; i<k.length; i++) {
			this.k[i] = i;
		}	
	}

	@Override
	public void init(graph g) {

	}

	@Override
	public void init(String file_name) {
		try {		  
			FileInputStream fileIn = new FileInputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.k = (int[]) in.readObject();
			in.close();
			fileIn.close();		
		}
		catch(Throwable e){
			System.out.println("problem with file");
		}
	}

	@Override
	public void save(String file_name) {
		try {
			FileOutputStream fileIn = new FileOutputStream("C:\\Users\\Obador\\eclipse-workspace\\ofir.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileIn);
			out.writeObject(this.k); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean isConnected() {
		for(Node x: this.g.vertex) {
			if(this.g.nodeedges.get(x.getKey()) == null) return false;

			if(BFS(x)[x.getKey()] != "b") return false;

		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		Node start = this.g.vertex[src];
		PriorityQueue<Node> q = new  PriorityQueue<Node>(10, compareNodes);
		double[] dis = new  double[g.nodeSize()];
		boolean[] vis = new boolean[g.nodeSize()];
		p = new Node[g.nodeSize()];

		for (int i = 0; i<this.k.length; i++) {
			dis[i] = inf;
			this.p[i] = null;
			vis[i] = false;
		}	
		dis[src]=0;
		q.clear();
		q.add(start);

		if(this.g.getE(src).equals(null)) {
			q.remove(start);
		}
		while(!q.isEmpty()){
			Node u = q.poll();
			if(this.g.getE(u.getKey()) == null) q.remove(u);
			else {
				for(edge_data e : this.g.getE(u.getKey())) {
					Node v = this.g.vertex[e.getDest()];
					if(vis[e.getDest()] == false ){
						if(dis[v.getKey()] > dis[u.getKey()] + e.getWeight()) {
							dis[v.getKey()] = dis[u.getKey()] + e.getWeight();
							this.p[e.getDest()] = u;
							q.add(u);
							q.add(v);;		
						}
					}
				}
			}
			vis[u.getKey()] = true;
		}
		return  dis[dest];
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		this.shortestPathDist(src, dest);
		Node t =  this.g.vertex[dest];
		Node u = this.g.vertex[src];
		while(t!=u ) {
			t = this.p[t.getKey()];
			this.path.add(t);
		}
		return this.path;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		return this.g;
	}

	private String[] BFS( Node n) {
		Stack<Node> q = new Stack<Node>();
		this.k = new  int[g.nodeSize()];
		String[] colorBFS = new String[g.nodeSize()];
		for (int i = 0; i<k.length; i++) {
			this.k[i] = i;
			colorBFS[i] = "w";	
		}
		colorBFS[n.getKey()]="g" ;
		q.empty();
		q.push(n);	
		while(!q.isEmpty()) {
			Node u = q.pop();
			for(Edge e : g.edge) {
				if(e.getDest() == u.getKey() && colorBFS[e.getDest()].equals("w") ) {
					colorBFS[e.getDest()] = "g";
					q.push(e.dest);
				}
			}
			colorBFS[u.getKey()] = "b";
		}
		return colorBFS;
	}
}
