/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;
import java.util.*;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	/** 
	 * Create a new empty MapGraph 
	 * 
	 */
	// A hash map is  to keep track of all the details of the edges for a given location.
	HashMap<GeographicPoint, Node> map;
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		map = new HashMap<GeographicPoint,Node>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		//Set<GeographicPoint> set = new HashSet<GeographicPoint>();
         //set = getVertices();
         
		return map.keySet().size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
	//	Set<GeographicPoint> set = new HashSet<GeographicPoint>();
	//	set.addAll(map.keySet());
		return map.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{ int numEdges =0;
		//TODO: Implement this method in WEEK 3
		for( GeographicPoint location : map.keySet()) {
			          Node n =map.get(location);
			        numEdges = numEdges+  n.edges.size();
					}
		return numEdges;
	}

	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		 if(this.map.containsKey(location)||location == null) {
			 return false;
		 }
		Node newVertex = new Node(location);
		map.put(location, newVertex);
		return true;
		
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		if(! (map.containsKey(from)|| map.containsKey(to))) {
			throw new IllegalArgumentException();
		}else {
			Edge newEdge = new Edge(from , to , roadName, roadType, length);
			Node fromEdgeList = map.get(from);
		    fromEdgeList.edges.add(newEdge);
		  //  Node toEdgeList = map.get(to);
		    //toEdgeList.edges.add(newEdge);
		}
	}
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
        Queue<GeographicPoint> q= new LinkedList<GeographicPoint>();
        // visitedset to avoid revisiting the nodes
        HashSet<GeographicPoint> visitedset = new HashSet<GeographicPoint>();
        // to keep track of the parents of each node, that is through which node we reached the current node.
        HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();
        q.add(start);
        visitedset.add(start);
        while(!q.isEmpty()) {
        	GeographicPoint curr = q.remove();
    		// Hook for visualization.  See writeup.
    		nodeSearched.accept(curr);
    		/* compare X and Y values
    		 do not compare object
    		dont do like if(curr == goal) they are two different objects 
    		 here we are using backtracking, and getting the path of the bfs through which we reached the goal,
    		that is the reason why we are using a parent map in which we will include the neighbour and its parent(the node 
    		through which we reached the neighbour node.
    		 now imagine we are having a graph like chennai --- trichy -- pudukotai
    		 now lets say our start is chennai and end is pudukotai. how will our parent map be ?
    		 first our neighbour of chennai is trichy then our parent map will be
    		 <map> key -trichy , chennai  
    		 then the neighbour of trichy is pudukotai and chennai but since chennai is already there in the visisted set 
    		 dont include that in queue and visited set
    		 <map>key - pudukotai, trichy
    		 now the goal is reached , how will we return the path ?
    		 traverse the map --
    		 until the start chennai is  reached from the goal. */
    		     		 // compare X and Y values
    		// do not compare object
    		if(curr.toString().equals(goal.toString())) {
        		List<GeographicPoint> retList = new ArrayList<>();
         		while(curr != start) {
        			retList.add(curr);
        			curr = parentMap.get(curr);
        		}
         		retList.add(start);
         		Collections.reverse(retList);
         		return retList;
        	}

           Node n = map.get(curr);
           for( Edge e:  n.edges) {
        	   if( !visitedset.contains(e.end)) {
        		   q.add(e.end);
        		   visitedset.add(e.end);
        		   parentMap.put(e.end, curr);
        	   }
           }
        }

        return null;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
		//Implemnt in week 4
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		// now since we have implemented comparable interface for the node class in which currdistance is there
		// we should change the priority class to hold the node objects , only then it can function efficiently.
		// but the problem here is we only have the geographic points as our parameters to the function , so 
		// we have to change the geographic points to nodes.
		int count =0;
		Node startnode = map.get(start);
		Node goalnode = map.get(goal);
		// we haven't included any comparator since we are using the comparable class , comparator = null. 
		Queue<Node> q= new PriorityQueue<Node>();
        // visitedset to avoid revisiting the nodes
        HashSet<GeographicPoint> visitedset = new HashSet<GeographicPoint>();
        // to keep track of the parents of each node, that is through which node we reached the current node.
        HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();
       startnode.currdistance = 0;/// imp
        q.add(startnode);
        while(!q.isEmpty()) {
        	//when it is removing now it will remove the highest priority 
        	Node curr = q.remove();
			System.out.println(++count);
        	System.out.println("cur "+curr.location.toString());
        	if( !visitedset.contains(curr.location)) {
        	visitedset.add(curr.location);
        	
    		// Hook for visualization.  See writeup.
    		nodeSearched.accept(curr.location);
    		
    		if(curr.location.toString().equals(goalnode.location.toString())) {
        		List<GeographicPoint> retList = new ArrayList<>();
        		GeographicPoint currPoint = curr.location;
         		while(!currPoint.toString().equals(start.toString())) {
        			retList.add(currPoint);
        			currPoint = parentMap.get(currPoint);
        		}
         		retList.add(start);
         		Collections.reverse(retList);
         		return retList;
        	}
    		for( Edge e:  curr.edges) {
           // 	System.out.println("next  "+e.end.toString());
    			if(! visitedset.contains(e.end)) {
    				//System.out.println(count ++);
    		Node temp = map.get(e.end);
    		double newdistance = curr.currdistance  +e.distance;
        //	System.out.println("dist  "+ newdistance + " and " + temp.currdistance);
    		if(newdistance < temp.currdistance) {
    			temp.currdistance = newdistance;
    			q.add(temp);
        	    parentMap.put(temp.location, curr.location);
        	    }
    		 }
          }
      	}  	
        }
            return null; 		
	}
	/*public List<GeographicPoint> dijkstra(GeographicPoint start, 
			  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
{
// TODO: Implement this method in WEEK 4
// Hook for visualization.  See writeup.
//nodeSearched.accept(next.getLocation());
// now since we have implemented comparable interface for the node class in which currdistance is there
// we should change the priority class to hold the node objects , only then it can function efficiently.
// but the problem here is we only have the geographic points as our parameters to the function , so 
// we have to change the geographic points to nodes.
int count =0;
Node startnode = map.get(start);
Node goalnode = map.get(goal);
// we haven't included any comparator since we are using the comparable class , comparator = null. 
Queue<Node> q= new PriorityQueue<Node>();
// visitedset to avoid revisiting the nodes
HashSet<GeographicPoint> visitedset = new HashSet<GeographicPoint>();
// to keep track of the parents of each node, that is through which node we reached the current node.
HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();
startnode.currenttime = 0;
q.add(startnode);
while(!q.isEmpty()) {
//when it is removing now it will remove the highest priority 
Node curr = q.remove();
System.out.println(count ++);
System.out.println("cur "+curr.location.toString());
if( !visitedset.contains(curr.location)) {
visitedset.add(curr.location);

// Hook for visualization.  See writeup.
nodeSearched.accept(curr.location);

if(curr.location.toString().equals(goalnode.location.toString())) {
List<GeographicPoint> retList = new ArrayList<>();
GeographicPoint currPoint = curr.location;
while(!currPoint.toString().equals(start.toString())) {
retList.add(currPoint);
currPoint = parentMap.get(currPoint);
}
retList.add(start);
Collections.reverse(retList);
return retList;
}
for( Edge e:  curr.edges) {
// 	System.out.println("next  "+e.end.toString());
if(! visitedset.contains(e.end)) {
//System.out.println(count ++);
Node temp = map.get(e.end);
double newtime= curr.currenttime  +e.timetaken;
//	System.out.println("dist  "+ newdistance + " and " + temp.currdistance);
if(newtime < temp.currenttime) {
temp.currenttime = newtime;
q.add(temp);
parentMap.put(temp.location, curr.location);}
}
}
}  	
}
return null; 		
} */

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{   int count =0;
		// TODO: Implement this method in WEEK 4
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		Node startnode = map.get(start);
		Node goalnode = map.get(goal);
		// we haven't included any comparator since we are using the comparable class , comparator = null. 
		Queue<Node> q= new PriorityQueue<Node>();
        // visitedset to avoid revisiting the nodes
        HashSet<GeographicPoint> visitedset = new HashSet<GeographicPoint>();
        // to keep track of the parents of each node, that is through which node we reached the current node.
        HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<>();
       startnode.currdistance = 0;
       startnode.predicteddistance = 0;
        q.add(startnode);
        while(!q.isEmpty()) {
        	//when it is removing now it will remove the highest priority 
        	Node curr = q.remove();
			System.out.println(++count);
        	System.out.println("cur "+curr.location.toString());
        	if( !visitedset.contains(curr.location)) {
        	visitedset.add(curr.location);
        	
    		// Hook for visualization.  See writeup.
    		nodeSearched.accept(curr.location);
    		
    		if(curr.location.toString().equals(goalnode.location.toString())) {
        		List<GeographicPoint> retList = new ArrayList<>();
        		GeographicPoint currPoint = curr.location;
         		while(!currPoint.toString().equals(start.toString())) {
        			retList.add(currPoint);
        			currPoint = parentMap.get(currPoint);
        		}
         		retList.add(start);
         		Collections.reverse(retList);
         		return retList;
        	}
    		for( Edge e:  curr.edges) {
           // 	System.out.println("next  "+e.end.toString());
    			if(! visitedset.contains(e.end)) {

    		Node temp = map.get(e.end);
    		// new distance = currdistance (start vertex distance)+ neighbourvertexdistance+fromneighbourvertextogoalvertex
    		double newdistance = curr.currdistance  +e.distance+e.end.distance(goal);	
        //	System.out.println("dist  "+ newdistance + " and " + temp.currdistance);
    		if(newdistance < temp.currdistance) {
    			temp.currdistance = newdistance;
    			q.add(temp);
        	    parentMap.put(temp.location, curr.location);
        	    }
    		 }
          }
      	}  	
        }
            return null; 		
	}

	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
	//You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
	/*	MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd); */
		
		
		
		/* Use this code in Week 3 End of Week Quiz */
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		System.out.println("Astar");
		theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		
	}
	
}
