
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
	
	public static void main(String args[]) {
		WeightedGraph graph = new WeightedGraph(4);
		
		graph.addVertex(0, 'A');
		graph.addVertex(1, 'B');
		graph.addVertex(2, 'C');
		graph.addVertex(3, 'D');
		
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 3, 2);
		graph.addEdge(1, 2, 6);
		
		graph.displayMatrix();
	}
}
