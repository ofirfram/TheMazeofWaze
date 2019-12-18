package dataStructure;

import java.util.HashMap;

public class Edge implements edge_data {
	public Node src;
	public Node dest;
	public double Weight;
	
	public 	Edge() {
		this.src = null;
		this.dest = null;
		this.Weight = 1;
	}
	
	public Edge(Node src , Node dest , double Weight){
		this.src = src;
		this.dest = dest;
		this.Weight = Weight;	
	}
	
	@Override
	public int getSrc() {	
		return this.src.key;
	}

	@Override
	public int getDest() {
		return this.dest.key;
	}

	@Override
	public double getWeight() {	
		return this.Weight;
	}

	@Override
	public String getInfo() {
		return ""+this.getSrc()+" "+this.getDest()+" "+this.Weight;
	}

	@Override
	public void setInfo(String s) {
	
	}

	@Override
	public int getTag() {
		return 0;
	}

	@Override
	public void setTag(int t) {		
	}
	
	public String toString() {
		return "("+this.getSrc()+","+this.getDest()+")";
	}

}
