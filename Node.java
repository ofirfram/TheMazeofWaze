package dataStructure;

import utils.Point3D;

public class Node implements node_data{
	
	public int key;
	public double weight;
	public Point3D location;
	
	public static void main(String[] args){
	
	}

	public Node(){
		this.key=0;
		this.weight=0;
		this.location=Point3D.ORIGIN;
	}
	
	public Node(int key){
		this.key=key;
		this.weight=0;
		this.location=Point3D.ORIGIN;
	}

	public Node(int key, double w, Point3D p ){
		this.key=key;
		this.weight=w;
		this.location=p;
	}
	
	public  Node copy() {
		Node n = new Node();
		n.key=this.key;
		n.location=this.location;
		n.weight=this.weight;
		return n;
	}
	
	public String toString() {
		return ""+this.key;
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
		return "key num: "+this.key + " his Weight: "+this.weight+"location: "+this.location;
	}

	@Override
	public void setInfo(String s) {
		String[] t=s.split(",");
		try {
			if(t.length==2) {
				int n=Integer.parseInt(t[0]);
				double w=Double.parseDouble(t[1]);
				this.key=n;
				this.weight=w;}
		}
		catch (Exception e) {throw new RuntimeException("the string isn't 2 numbers of type 'key num,weight num'");}
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
	}
}