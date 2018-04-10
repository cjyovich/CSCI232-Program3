import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;

public class WeightedGraph {

	private Vertex vertices[];
	private int matrix[][]; // adjacency matrix
	private final static int INFINITY = 999999;

	public WeightedGraph(int numv) {
		vertices = new Vertex[numv]; // blank vertex list
		matrix = new int[numv][numv]; // blank adjacency matrix
		// we're going to set all adjacenies to infinity for now
		for (int i = 0; i < numv; i++) {
			for (int j = 0; j < numv; j++) {
				if(i == j){
					matrix[i][j] = 0;
				}else{
					matrix[i][j] = INFINITY;
				}
			}
		}
	}

	public void addVertex(int num, String vertexList) {
		vertices[num] = new Vertex(vertexList);
	}

	public void addEdge(int start, int end, int weight) {
		matrix[start][end] = weight;
		matrix[end][start] = weight;
	}

	public void printVertex(int vert) {
		System.out.print(vertices[vert].getLabel());
	}

	public void displayMatrix() {
		
		System.out.print("    ");
		
		for(int i = 0; i < vertices.length; i++){
			System.out.format("%-4s", vertices[i].getLabel());
		}
		System.out.println();
		
		for (int i = 0; i < matrix.length; i++) {
			//printVertex(i);
			System.out.format("%-4s", vertices[i].getLabel());
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] != INFINITY){
					System.out.format("%-4s", matrix[i][j]);

				}else{
					System.out.format("%-4s" ,"\u221e");
				}

			}
			System.out.println();
		}
		System.out.println();
	}

	public void Prims() {
		int tree[][] = new int[vertices.length - 1][2];
		vertices[0].putInTree(); // start with A
		int current = 0;
		for (int i = 0; i < tree.length; i++) { // finds min edge weight, puts the vertices in the tree
			int destination = findMin(current);
			if (current != destination) {
				tree[i][0] = current;
				tree[i][1] = destination;
				vertices[destination].putInTree();
				current = destination;
			}
		}
		for (int i = 0; i < tree.length; i++) { // prints the tree
			for (int j = 0; j < 2; j++) {
				int v = tree[i][j];
				System.out.print(vertices[v].getLabel());
			}
			System.out.print(" ");
		}
		System.out.println();
	}

	public int findMin(int current) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].isReached() == false) { // if the vertex isn't already in the tree...
				if (matrix[current][i] != INFINITY) { // and if an edge exists...
					Edge edge = new Edge(current, i, matrix[current][i]); // add it to the PQ
					pq.add(edge);
				}
			}
		}
		if (pq.size() == 0) { // no edges
			return current;
		} else {
			return pq.poll().getDest();
		}
	}

	public void Kruskals() {
		for (int i = 0; i < vertices.length; i++) { // no vertices in the tree yet
			vertices[i].setReached(false);
		}
		int forest[][] = new int[vertices.length - 1][2];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices.length; j++) {
				if (matrix[i][j] != INFINITY && i != j) {
					Edge edge = new Edge(i, j, matrix[i][j]);
					pq.add(edge); // adds all vertices to pq, no duplicates
				}
			}
		}
		int count = 0;
		boolean duplicate = false;
		while (pq.peek() != null) {
			Edge edge = pq.poll();
			if (edge.getDest() > edge.getStart()) {
				if ((vertices[edge.getDest()].isEnd() == false || vertices[edge.getStart()].isStart() == false)
						&& vertices[edge.getStart()].isReached() == false) {
					forest[count][0] = edge.getStart();
					forest[count][1] = edge.getDest();
					vertices[edge.getDest()].setEnd(true);
					vertices[edge.getStart()].setStart(true);
					vertices[edge.getDest()].putInTree();
					count++;
				}
			}
		}
		for (int i = 0; i < forest.length; i++) { // prints the forest
			for (int j = 0; j < 2; j++) {
				int v = forest[i][j];
				System.out.print(vertices[v].getLabel());
			}
			System.out.print(" ");
		}
		System.out.println();
	}
	
/*	public void PrintMatrix(){
		
		System.out.println("  " + );
		
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				
			}
		}
	}*/
	
	public void ClosestPair(){
		
		for(int k = 0; k < matrix.length; k++){
			for(int i = 0; i < matrix.length; i++){
				for(int j = 0; j < matrix.length; j++){
					if(matrix[i][k] == INFINITY || matrix[j][k] == INFINITY){
					}else if(matrix[i][j] >  matrix[i][k] + matrix [k][j]){
						matrix[i][j] =  matrix[i][k] + matrix [k][j];
						
						displayMatrix();
						}
					}
			}
		}
	}

	public static void main(String args[]) {
		WeightedGraph graph = new WeightedGraph(10);
		Path path = FileSystems.getDefault().getPath("input", "matrix.csv");
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = reader.readLine(); // reads in the vertices
			String[] vertexList = line.split(",");
			int size = vertexList.length; // this will be the size of our matrix and list of vertices
			graph = new WeightedGraph(size);
			for (int i = 0; i < size; i++) {
				graph.addVertex(i, vertexList[i]);
			}
			String data;
			int counter = 0;
			while ((data = reader.readLine()) != null) {
				String[] dataIn = data.split(",");
				for (int i = 0; i < size; i++) {
					String s = dataIn[i];
					if(s.equals("INF") == false) {
						graph.addEdge(counter, i, Integer.parseInt(s));
					}
				}
				counter++;
			}

		} catch (Exception x) {
			x.printStackTrace();
		}

		graph.Prims();
		graph.Kruskals();
		graph.ClosestPair();

	}
}
