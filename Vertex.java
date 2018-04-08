
public class Vertex {

	private char label;
	private boolean reached;
	
	public Vertex(char l) {
		label = l;
		reached = false;
	}
	
	public char getLabel() {
		return label;
	}
	
	public void putInTree() {
		reached = true;
	}
	
	public boolean isReached() {
		return reached;
	}

}