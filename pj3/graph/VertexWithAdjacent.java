/* VertexWithAdjacent.java */

package graph;

/**
 * The VertexWithAdjacent class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

protected class VertexWithAdjacent {
	Object item;
	DList adjacentList;

	protected VertexWithAdjacent(Object i){
		this.item = i;
		this.adjacentList = new DList();
	}

	protected void addAdjacentEdges(){

	}
}