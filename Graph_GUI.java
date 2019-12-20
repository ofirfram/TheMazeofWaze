package gui;

import utils.Point3D;
import utils.StdDraw;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;;


public class Graph_GUI {
	public static void main(String[] argas) {
		Point3D p0 = new Point3D(0,0,0);
		Point3D p1 = new Point3D(6,0,0);
		Point3D p2 = new Point3D(6,-6,0);
		Point3D p3 = new Point3D(0,-6,0);
		Point3D p4 = new Point3D(3,-12,0);

		Node n0 = new Node(0,0,p0);
		Node n1 = new Node(1,0,p1);
		Node n2 = new Node(2,0,p2);
		Node n3 = new Node(3,0,p3);
		Node n4 = new Node(4,0,p4);

		Edge e1 = new Edge(n0, n1, 1);
		Edge e2 = new Edge(n1, n2, 3);
		Edge e3 = new Edge(n2, n3, 40);
		Edge e4 = new Edge(n3, n0, 2);
		Edge e5 = new Edge(n4, n3, 6);
		Edge e6 = new Edge(n2, n4, 1);

		Edge edge[]= {e1,e2,e3,e4,e5,e6};
		Node node[] = {n0,n1,n2,n3,n4};
		DGraph g = new DGraph(node , edge);
		Graph_GUI G = new Graph_GUI(g);
	}
	
	Graph_GUI(DGraph g){
		int i=0;
		StdDraw.setXscale(-15,15);
		StdDraw.setYscale(-15,15);
		for (Node n : g.vertex) {
			double x =  n.getLocation().x();
			double y =  n.getLocation().y();
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(x, y, 0.4);
			StdDraw.text(x, y+1, n.getKey()+"");
			StdDraw.text(i,3 , n.getKey()+",");
			i++;
		}
		for (Edge e : g.edge) {
			StdDraw.setPenColor(StdDraw.RED);
			double x =  e.src.location.x();
			double y =  e.src.location.y(); 
			double x1 =  e.dest.location.x();
			double y1 =  e.dest.location.y();
			StdDraw.line(x, y, x1, y1);
			double midy = (y+y1)/2;
			double midx = (x+x1)/2;
			StdDraw.text(midx, midy, e.getWeight()+"");	
		}
		

	}
	
}
