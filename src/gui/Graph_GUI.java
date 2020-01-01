package gui;

import utils.Point3D;
import utils.StdDraw;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;;

public class Graph_GUI {
	public graph g;
	int i=0;

	public static void main(String[] argas) {
		Point3D p0 = new Point3D(0.5,-0.5,0);
		Point3D p1 = new Point3D(6.5,-0.5,0);
		Point3D p2 = new Point3D(6.5,-6.5,0);
		Point3D p3 = new Point3D(0.5,-6.5,0);
		Point3D p4 = new Point3D(3.5,-12.5,0);
		Point3D p5 = new Point3D(-5,-6.5,0);
		Point3D p6 = new Point3D(-3.5,-12.5,0);

		Node n0 = new Node(0,0,p0);
		Node n1 = new Node(1,0,p1);
		Node n2 = new Node(2,0,p2);
		Node n3 = new Node(3,0,p3);
		Node n4 = new Node(4,0,p4);
		Node n5 = new Node(5,0,p5);
		Node n6 = new Node(6,0,p6);

		Edge e1 = new Edge(n0, n3, 1);
		Edge e2 = new Edge(n1, n0, 3);
		Edge e3 = new Edge(n2, n6, 40);
		Edge e4 = new Edge(n4, n2, 2);
		Edge e5 = new Edge(n5, n4, 1);
		Edge e6 = new Edge(n6, n1, 6);

		Edge edge[]= {e1,e2,e3,e4,e5,e6};
		Node node[] = {n0,n1,n2,n3,n4,n5,n6};
		DGraph g = new DGraph(node , edge);
		System.out.println(g.toString());
		g.addNode(n0);

		Graph_GUI G = new Graph_GUI(g);
	}

	public  Graph_GUI(graph g){
		this.g = g;
		StdDraw.setXscale(-15,15);
		StdDraw.setYscale(-15,15);

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0,-15,0,15);
		StdDraw.line(-15,0,15,0);

		double l=1;
		while(l<15) {
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.line(l,-15,l,15);	//+y grid	
			StdDraw.line(-l,-15,-l,15);	//-y grid
			StdDraw.line(-15,l,15,l);  //+x grid
			StdDraw.line(-15,-l,15,-l);	//-x grid
			l++;
		}

		if(this.g.nodeSize() == 1) {
			node_data n = this.g.getNode(0);
			double x =  n.getLocation().x();
			double y =  n.getLocation().y();
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(x, y, 0.4);
			StdDraw.text(x, y+1, n.getKey()+"");

		}

		else {

			for (node_data n : this.g.getV()) {
				double xn =  n.getLocation().x();
				double yn =  n.getLocation().y();
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(xn, yn, 0.4);
				StdDraw.text(xn, yn+1, n.getKey()+"");
				i++;
			}

			for (node_data n : this.g.getV()) {
				if(this.g.getE(n.getKey()) != null) {
					for (edge_data e : this.g.getE(n.getKey())) {
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.setPenRadius(0.004);
						double x =   this.g.getNode(e.getSrc()).getLocation().x();
						double y =   this.g.getNode(e.getSrc()).getLocation().y();
						double x1 =  this.g.getNode(e.getDest()).getLocation().x();
						double y1 =  this.g.getNode(e.getDest()).getLocation().y();
						StdDraw.line(x, y, x1, y1);
						double midy = (y+y1)/2;
						double midx = (x+x1)/2;
						StdDraw.text(midx, midy, e.getWeight()+"");	
						StdDraw.setPenRadius();
					}
				}
			}

			for (node_data n : this.g.getV()) {
				if(this.g.getE(n.getKey()) != null) {
					for (edge_data e : this.g.getE(n.getKey())) {
						StdDraw.setPenColor(StdDraw.YELLOW);
						double x =  this.g.getNode(e.getSrc()).getLocation().x();
						double y =  this.g.getNode(e.getSrc()).getLocation().y();
						double x1 =  this.g.getNode(e.getDest()).getLocation().x();
						double y1 = this.g.getNode(e.getDest()).getLocation().y();

						if(x==x1){
							if(y1>y)
							{StdDraw.filledCircle(x, y1-(y1-y)/9, 0.3);}
							else
							{StdDraw.filledCircle(x, y1-(y1-y)/9, 0.3);}}

						if(y==y1){
							if(x1>x)
							{StdDraw.filledCircle(x1+(x-x1)/9, y, 0.3);}
							else
							{StdDraw.filledCircle(x1+(x-x1)/9, y, 0.3);}
						}
						if(x1>x  && y1>y)
						{StdDraw.filledCircle(x1+(x-x1)/9, y1-(y1-y)/9, 0.3);}
						if(x1>x  && y1<y)
						{StdDraw.filledCircle(x1+(x-x1)/9, y1-(y1-y)/9, 0.3);}
						if(x1<x  && y1>y)
						{StdDraw.filledCircle(x1+(x-x1)/9, y1-(y1-y)/9, 0.3);}
						if(x1<x  && y1<y)
						{StdDraw.filledCircle(x1+(x-x1)/9, y1-(y1-y)/9, 0.3);}
					}
				}
			}
			i=0;
		}
	}
}
