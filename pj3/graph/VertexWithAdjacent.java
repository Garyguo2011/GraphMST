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

	VertexWithAdjacent(Object i){
		this.item = i;
		this.adjacentList = new DList();
	}
	
	/*
	public String toString(){
		String out = item.toString() + " -> ";
		try{
			if(adjacentList.isEmpty()){
				out	+= "adjacentList is Empty";
			}else{
				DListNode walker = (DListNode) adjacentList.front();
				while(walker.isValidNode()){
					out += ((EdgeWithPartner)walker.item()).toString() + " - ";
					walker = (DListNode) walker.next();
				}
			}
		}catch(InvalidNodeException e){
			System.out.println(e);
		}
		return out;
	}
	*/
}