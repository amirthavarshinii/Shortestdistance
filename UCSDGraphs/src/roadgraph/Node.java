package roadgraph;
import java.util.*;
import geography.GeographicPoint;

public class Node implements Comparable<Node> {
      GeographicPoint location;
       ArrayList <Edge>edges;	
       //You'll also need to add a "current distance" from your start node 
       //to each node as your search progresses.
        double currdistance;
       double currenttime;
	    double predicteddistance;
	public Node(GeographicPoint location) {
		// TODO Auto-generated constructor stub
		this.location = location;
		edges = new ArrayList<Edge>();
		currdistance = Double.POSITIVE_INFINITY;
		currenttime = Double.POSITIVE_INFINITY;
		predicteddistance =Double.POSITIVE_INFINITY; 
		
	}
	// we are prioritizing the queue by using the curr distance field only, that is the distance from the start to end.
	// so the class which includes the currdistance must include the comparable interface.
//	@Override
public int compareTo(Node n) {
		if(Double.compare(this.currdistance,n.currdistance) == 0) {
			return 0;
		} else if( Double.compare(this.currdistance, n.currdistance)< 0) {
			return -1;
		}else {
			return 1;
		}
	} 
	/*public int compareTo(Node n) {
		if(Double.compare(this.currenttime,n.currenttime) == 0) {
			return 0;
		} else if( Double.compare(this.currenttime, n.currenttime)< 0) {
			return -1;
		}else {
			return 1;
		}
	} */
	

}
