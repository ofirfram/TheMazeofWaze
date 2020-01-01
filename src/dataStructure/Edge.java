package dataStructure;

import utils.Point3D;

public class Edge implements edge_data {
	public node_data src;
	public node_data dest;
	private double Weight;
	private int tag;

	public static void main(String[] args){
		Node n0 = new Node(0,1,new Point3D(0,0,0));
		Node n1 = new Node(1,2,new Point3D(1,1,1));
		System.out.println(n0);
		System.out.println(n1);

		Edge e = new Edge(n0,n1,5);
		System.out.println(e);
		System.out.println(e.getInfo());
		e.setInfo("1,0,6");
		System.out.println(e);
	}

	public 	Edge() {
		this.src = null;
		this.dest = null;
		this.Weight = 1;
		this.tag=0;
	}


	public Edge(Node src , Node dest , double Weight){
		this.src = src;
		this.dest = dest;
		this.Weight = Weight;	
		this.tag=0;
	}

	@Override
	public int getSrc() {	
		return this.src.getKey();
	}

	@Override
	public int getDest() {
		return this.dest.getKey();
	}

	@Override
	public double getWeight() {	
		return this.Weight;
	}

	@Override
	public String getInfo() {
		return "from="+this.getSrc()+" to="+this.getDest()+" w="+this.Weight;
	}

	@Override
	public void setInfo(String s) {
		s.replace(" ", "");	
		try {
			String[] t=s.split(",");
			if(t.length==3) {
				this.src=new Node(Integer.parseInt(t[0]));
				this.dest=new Node(Integer.parseInt(t[1]));
				this.Weight=Double.parseDouble(t[2]);
			}
		}
		catch (Exception e) {throw new RuntimeException("please type'[src,dest,weight] your type: " +s);}
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {	
		this.tag=t;
	}

	public String toString() {
		return "(v"+this.getSrc()+",v"+this.getDest()+")";
	}

}
