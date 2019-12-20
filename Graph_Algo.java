package algorithms;

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
	private double[] dis;
	private boolean[] vis;
	private Node[] p;
	private String[] colorBFS;
	private DGraph g;
	public static final int inf = 10000000;

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
		Edge e2 = new Edge(n1, n2, 1);
		Edge e3 = new Edge(n2, n3, 1);
		Edge e4 = new Edge(n3, n0, 1);
		Edge e5 = new Edge(n4, n3, 1);
		Edge e6 = new Edge(n0, n3, 1);


		Edge edge[]= {e1,e2,e3,e4,e5,e6};
		Node node[] = {n0,n1,n2,n3,n4};

		DGraph g = new DGraph(node , edge);
		System.out.println(g.nodeedges);


		Graph_Algo G = new Graph_Algo(g);
		System.out.println(G.isConnected());
		
		System.out.println(G.shortestPathDist(n0.getKey(), n3.getKey()));

	}

	Graph_Algo(DGraph g){
		this.g = g;
		this.k = new  int[g.nodeSize()];
		this.p = new  Node[g.nodeSize()];
		this.dis = new  double[g.nodeSize()];
		this.colorBFS = new String[g.nodeSize()];
		this.vis = new boolean[g.nodeSize()];
		for (int i = 0; i<k.length; i++) {
			this.k[i] = i;
			this.dis[i] = inf;
			this.p[i] = null;
			this.vis[i] = false;
			this.colorBFS[i] = "w";	
		}	
	}

	@Override
	public void init(graph g) {

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		for(Node x: this.g.vertex) {
			if(this.g.nodeedges.get(x.getKey()) == null) return false;
			else{
				this.BFS(x);
				if(this.colorBFS[x.getKey()] != "b")return false;
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		int mindist=0;
		Point3D p1 = new Point3D(0,1,0);
		Node n0 = new Node(0,1,p1);
		PriorityQueue<Node> q = new  PriorityQueue<Node>(10, compareNodes);
		q.add(n0);
		dis[src]=0;
		if(this.g.nodeedges.get(src).isEmpty()) mindist=0;
		while(!q.isEmpty()){
			Node n = q.poll();
			
			if(this.g.getE(n.getKey()) == null) {
				q.remove(n);
				return mindist = (int) dis[n.getKey()];
			}
				for(edge_data e : this.g.getE(n.getKey()) ) {
				Node v =  this.g.vertex[e.getDest()];
				if(vis[e.getDest()] == false ) {
					if(dis[v.getKey()] > dis[n.getKey()] + e.getWeight()) {
						dis[v.getKey()] = dis[n.getKey()] + e.getWeight();
						this.p[e.getDest()] = n;
						q.remove(n);
						q.add(v);
						if(v.getKey() == dest) mindist = (int) dis[v.getKey()];
					}
				}
			}
			this.vis[n.getKey()] = true;
		}

		return mindist;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {

		return null;
	}

	private String[] BFS( Node n) {
		Stack<Node> q = new Stack<Node>();
		this.colorBFS[n.getKey()]="g" ;
		q.empty();
		q.push(n);	
		while(!q.isEmpty()) {
			Node u = q.pop();
			for(Edge e : g.edge) {
				if(e.getDest() == u.getKey() && colorBFS[e.getDest()].equals("w") ) {
					this.colorBFS[e.getDest()] = "g";

					q.push(e.dest);
				}
			}
			this.colorBFS[u.getKey()] = "b";
		}
		return this.colorBFS;
	}
}
