/* VertexWithAdjacent.java */

package graph;
import list.*;

/**
 * The VertexWithAdjacent class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

class VertexWithAdjacent {
	Object item;
	DList adjacentList;

	protected VertexWithAdjacent(Object i){
		this.item = i;
		this.adjacentList = new DList();
	}
}