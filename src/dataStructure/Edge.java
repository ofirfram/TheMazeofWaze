package dataStructure;

import java.io.Serializable;
/**
 * This class represents an edge to be construct in graph 
 * @author OfirBador & ElnatanBerenson
 */
public class Edge implements edge_data, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public node_data src;
	public node_data dest;
	private double Weight;
	private int tag;
	/**
	 * Empty edge constructor
	 * Default initialize
	 */
	public 	Edge() {
		this.src = null;
		this.dest = null;
		this.Weight = 1;
		this.tag=0;
	}
	/**
	 * Edge constructor
	 * src - start edge location
	 * dest - direction edge location
	 * Weight - edge Weight used for algorithms
	 */
	public Edge(Node src , Node dest , double Weight){
		this.src = src;
		this.dest = dest;
		this.Weight = Weight;	
		this.tag=0;
	}
	/**
	 *@return the start edge location
	 */
	@Override
	public int getSrc() {	
		return this.src.getKey();
	}
	/**
	 *@return the direction edge location
	 */
	@Override
	public int getDest() {
		return this.dest.getKey();
	}
	/**
	 *@return the weight of edge 
	 */
	@Override
	public double getWeight() {	
		return this.Weight;
	}
	/**
	 *@return String of edge info
	 */
	@Override
	public String getInfo() {
		return "from="+this.getSrc()+" to="+this.getDest()+" w="+this.Weight;
	}
	/**
	 *this method set an edge info from String
	 *String should be - (from vertex , to vertex , weight of edge)
	 */
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
	/**
	 *@return edge tag can be - 0,1,2
	 */
	@Override
	public int getTag() {
		return this.tag;
	}
	/**
	 *set the edge tag 
	 */
	@Override
	public void setTag(int t) {	
		this.tag=t;
	}

	public String toString() {
		return "(v"+this.getSrc()+",v"+this.getDest()+")";
	}

}
