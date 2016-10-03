import java.util.Arrays;
import java.util.List;

public class DijkstraAlgorithm {
	
	private Graph<String> mGraph; //the graph
	private Heap nodesValues; //Heap priority queue which keeps the nodes and min-heapifies the distances 
	private Node<String> startingPoint; //starting node
	private Node<String> goalPoint; //goal node
	
	//constructor
	public DijkstraAlgorithm(Graph<String> mGraph, Node<String> startingPoint, Node<String> goalPoint) {
		this.mGraph = mGraph;
		this.startingPoint = startingPoint;
		this.goalPoint = goalPoint;
		nodesValues = new Heap(mGraph.vertexCount());
	}
	
	//Dijkstra algorithm method
	public int performDijkstra() {
		mGraph.getNode(startingPoint.vertex()).setDistance(0); //set starting point's distance to 0
		nodesValues.insert(mGraph.getNode(startingPoint.vertex())); //insert the starting point to the heap
		
		//set all the other nodes' distances to infinity
		for(int i=0;i<mGraph.vertexCount();i++) {
			if(!mGraph.getNode(Integer.toString(i+1)).equals(startingPoint)) {
				mGraph.getNode(Integer.toString(i+1)).setDistance(2147483647);
				nodesValues.insert(mGraph.getNode(Integer.toString(i+1)));
			}
		}
		
		//while there are unvisited nodes
		while(!nodesValues.isEmpty()) {
			Node<String> min = nodesValues.popMin(); //get the node with the least distance
			List<Edge<String>> currentNodeEdges = min.edges(); //get the edges of this node
			for(Edge<String> edge : currentNodeEdges) { //and for each edge				
				if(!edge.toNode().isVisited() && edge.toNode().getDistance() > ((min.getDistance() == Integer.MAX_VALUE) ? min.getDistance() : min.getDistance() + edge.getWeight())) { //if edge is unvisited and target node's distance is greater than current node's distance + the weight of the edge between them
					edge.toNode().setDistance((min.getDistance() == Integer.MAX_VALUE) ? min.getDistance() : min.getDistance() + edge.getWeight()); //set the target node's distance equal to this new value
					nodesValues.decreaseKey(Arrays.asList(nodesValues.getA()
							).indexOf(edge.toNode()), (min.getDistance() == Integer.MAX_VALUE) ? min.getDistance() : min.getDistance() + edge.getWeight()); //decrease the key of the target node from the heap and fix the heap
					
				}
			}
			min.setVisited(true); //set the current node as visited
			
			if(min.equals(goalPoint)) { //if the current value is the goal point
				return min.getDistance(); //return the cost of the cheapest path
			}
			
		}
		return -1; //there is no path from the starting point to the goal point on the graph
	}

}
