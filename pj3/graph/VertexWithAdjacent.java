/* VertexWithAdjacent.java */

package graph;
import list.*;

/**
 * The VertexWithAdjacent class represents a vertex associate with a DList 
 * of its adjacent edges (EdgeWithParner as object in DList)
 */

class VertexWithAdjacent {
	Object item;
	DList adjacentList;
  
  /**
   * VertexWithAdjacent() is constructor to create a VertexWithAdjacent object,
   * iniilize item as vertex
   * 
   * @param i - a object that reprents vetex
   * @return a new VertexWithAdjacent object
   *
  **/
	VertexWithAdjacent(Object i){
		this.item = i;
		this.adjacentList = new DList();
	}
}