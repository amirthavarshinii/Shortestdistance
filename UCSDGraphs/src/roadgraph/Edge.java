package roadgraph;
import geography.GeographicPoint;

public class Edge  {
	// these are the parameters that a edge class should contain.
	// start and end represents the edge is between which teo locations.
	// street name is the name of the street.
	//distance is the length between start and end. 
    GeographicPoint start;
    GeographicPoint end;
     String roadName;
     String roadType;
     double distance;
     double timetaken;
     double speed;
     public Edge(GeographicPoint start, GeographicPoint end , String roadname,
	String roadType, double distance) {
		this.start = start;
		this.end = end;
		this.roadName = roadname;
		this.roadType = roadType;
		this.distance = distance;
		if(this.roadType.equals("residential") ){
			this.speed = 20;
		}else if(this.roadType.equals("primary") ){
			this.speed = 30;
		}else	if(this.roadType.equals("secondary") ){
			this.speed = 35;
		}else if(this.roadType.equals("motorway") ){
			this.speed = 45;
		}else {
			this.speed = 40;
		}
     this.timetaken = this.distance / this.speed;
}
}
