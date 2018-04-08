
public class Edge implements Comparable<Edge>{
	private int firstVert;
	private int secondVert;
	private int weight;
	
	public Edge(int vert1, int vert2, int w) {
		firstVert = vert1;
		secondVert = vert2;
		weight = w;
	}
	
	public int getDest() {
		return secondVert;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge o) {
		int compare = 0;
		if(this.getWeight() < o.getWeight()) {
			compare = -1;
		}
		if(this.getWeight() == o.getWeight()) {
			compare = 0;
		}
		if(this.getWeight() > o.getWeight()) {
			compare = 1;
		}
		return compare;
	}
}
