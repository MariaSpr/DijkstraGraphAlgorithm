import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DGraphDijkstraMain {
	
	public static void main(String[] args) throws IOException {
		
		int nNodes; //number of nodes in the graph
		int nEdges; //number of edges in the graph
		Graph<String> mGraph; //the graph
		Node<String> startingPoint; //starting node
		Node<String> goalPoint; //goal node
		int cost; //OUTPUT: cost of shortest path
		
		//creating the graph from the input
		BufferedReader in = new BufferedReader(new FileReader(args[0])); //reading the input.txt file
		String line;
		
		//Read first line and initialize number of nodes and edges
		line = in.readLine();
		String[] nNodesNEdges = line.split(" ");
		nNodes = Integer.parseInt(nNodesNEdges[0]);
		nEdges = Integer.parseInt(nNodesNEdges[1]);
		
		//Initialize graph
		mGraph = new Graph<String>();
		//Create nodes
		for(int i=0;i<nNodes;i++) {
			mGraph.addVertex(Integer.toString(i+1));
		}		
		//Create edges
		for(int i=0;i<nEdges;i++) {
			line = in.readLine();
			String[] nodeEdgePair = line.split(" ");
			mGraph.addEdge(nodeEdgePair[0], nodeEdgePair[1], Integer.parseInt(nodeEdgePair[2]));
		}
		
		//get startingPoint and goalPoint
		line = in.readLine();
		String[] startingNGoalPoints = line.split(" ");
		startingPoint = mGraph.getNode(startingNGoalPoints[0]);
		goalPoint = mGraph.getNode(startingNGoalPoints[1]);
		in.close();
		
		
		//Apply Dijkstra Algorithm
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(mGraph, startingPoint, goalPoint);
		
//		long initDijTime = System.currentTimeMillis();
		cost = dijkstra.performDijkstra();
//		long finalDijTime = System.currentTimeMillis();
//		long DijkstraTime = finalDijTime - initDijTime; 
		
		System.out.println(cost);
		  
//		System.out.println("The Cost using Dijkstra is: " +cost + " Time taken is " +DijkstraTime +" ms");
		
	}

}
