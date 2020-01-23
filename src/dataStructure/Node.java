package dataStructure;

import java.io.Serializable;

import utils.Point3D;
/**
 * This class represents a vertex in graph.
 * In this class you can construct a vertex, and choose features for vertex such - key , place in space and weight 
 * @author OfirBador & ElnatanBerenson
 */
public class Node implements node_data,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key;
	private double weight;
	public Point3D location;
	private int tag;
	
	public static void main(String[] args){
	Node n = new Node(0,1,new Point3D(0,0,0));
	System.out.println(n);
	Node l = n.copy();
	System.out.println(l);
	System.out.println(l.getInfo());
	l.setInfo("1,2,1,1,1");
	System.out.println();
	System.out.println(l.getInfo());
	}
	/**
	 * empty Node constructor represents  vertex in G graph 
	 * ** Default key=0,weight=0,location=ORIGIN. 
	 */
	public Node(){
		this.key=0;
		this.weight=0 ;
		this.location = Point3D.ORIGIN;
		this.tag=0;
	}
	/**
	 * empty Node constructor represents vertex in G graph 
	 * ** Default weight=0,location=ORIGIN. 
	 */
	public Node(int key){
		this.key=key;
		this.weight=0;
		this.location = Point3D.ORIGIN;
		this.tag=0;
	}
	/**
	 * empty Node constructor represents  vertex in G graph 
	 * Integer key , double weight , point (x,y,z) 
	 */
	public Node(int key, double w, Point3D p ){
		this.key=key;
		this.weight=w;
		this.location = p;
		this.tag=0;
	}
	/**
	 *  creating deep copy of this Node
	 */
	public  Node copy() {
		Node n = new Node();
		n.key=this.key;
		n.location=this.location;
		n.weight=this.weight;
		n.tag=tag;
		return n;
	}
	
	public String toString() {
		return "(v"+this.getKey()+")" ;
	}
	
	@Override
	public int getKey() {
		return this.key;
	}
	
	@Override
	public Point3D getLocation() {
		return this.location;
	}
	
	@Override
	public void setLocation(Point3D p) {
		this.location=p;
	}
	
	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight=w;
	}

	@Override
	public String getInfo() {
		return "(k="+this.getKey()+")[w="+this.getWeight() +",p="+this.location+"]";
	}

	@Override
	public void setInfo(String s) {
		s.replace(" ", "");	
		try {
			String[] t=s.split(",");
			if(t.length==5) {
				this.key=Integer.parseInt(t[0]);
				this.setWeight(Double.parseDouble(t[1]));;
				double x =  Double.parseDouble(t[2]);
				double y =  Double.parseDouble(t[3]);
				double z =  Double.parseDouble(t[4]);
				this.setLocation(new Point3D(x,y,z)) ;
				}
		}
		catch (Exception e) {throw new RuntimeException("please type'[key,weight,x,y,z] your type: " +s);}
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}
}