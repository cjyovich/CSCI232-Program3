import java.util.Comparator;
import java.util.PriorityQueue;

public class WeightedGraph {
	
	private Vertex vertices[];
	private int matrix[][]; //adjacency matrix
	private final int INFINITY = 999999;

	public WeightedGraph(int numv) {
		vertices = new Vertex[numv]; //blank vertex list
		matrix = new int[numv][numv]; //blank adjacency matrix
		//we're going to set all adjacenies to infinity for now
		for(int i = 0; i < numv; i++) {
			for(int j = 0; j < numv; j++) {
				matrix[i][j] = INFINITY;
			}
		}
	}
	
	public void addVertex(int num, char label) {
		vertices[num] = new Vertex(label);
	}
	
	public void addEdge(int start, int end, int weight) {
		matrix[start][end] = weight;
		matrix[end][start] = weight;
	}
	
	public void displayVertex(int vert) {
		System.out.print(vertices[vert].getLabel());
	}
	
	public void displayMatrix() {
		for(int i = 0; i < matrix.length; i++) {
			displayVertex(i);
			System.out.print(" ");
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void Prims() {
		int tree[][] = new int[vertices.length - 1][2];
		vertices[0].putInTree(); // start with A
		int current = 0;
		for (int i = 0; i < tree.length; i++) { //finds min edge weight, puts the vertices in the tree
			int destination = findMin(current); 
			if (current != destination) {
				tree[i][0] = current;
				tree[i][1] = destination;
				vertices[destination].putInTree();
				current = destination;
			}
		}
		for (int i = 0; i < tree.length; i++) { //prints the tree
			for (int j = 0; j < 2; j++) {
				int v = tree[i][j];
				System.out.print(vertices[v].getLabel());
			}
			System.out.print(" ");
		}

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

	public static void main(String args[]) {
		WeightedGraph graph = new WeightedGraph(6);

		graph.addVertex(0, 'A');
		graph.addVertex(1, 'B');
		graph.addVertex(2, 'C');
		graph.addVertex(3, 'D');
		graph.addVertex(4, 'E');
		graph.addVertex(5, 'F');

		graph.addEdge(0, 1, 6);
		graph.addEdge(0, 3, 4);
		graph.addEdge(1, 3, 7);
		graph.addEdge(1, 4, 7);
		graph.addEdge(1, 2, 10);
		graph.addEdge(2, 4, 5);
		graph.addEdge(2, 5, 6);
		graph.addEdge(2, 3, 8);
		graph.addEdge(3, 4, 12);
		graph.addEdge(4, 5, 7);

		graph.Prims();

	}
}
