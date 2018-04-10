/* Courtney Yovich and Tyler Fleetwood
* 4/10/2018
* Class to define and manipulates vertices within the matrices
*/

public class Vertex {

	private String label;
	private boolean reached;
	private boolean isStart;
	private boolean isEnd;
	
	public Vertex(String l) {
		label = l;
		reached = false;
		isStart = false;
		isEnd = false;
	}
	
	public String getLabel() {
		return label;
	}
	
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public void putInTree() {
		reached = true;
	}
	
	public boolean isReached() {
		return reached;
	}

	public void setReached(boolean reached) {
		this.reached = reached;
	}

}
