/* EdgeWithPartner.java */

package graph;
import list.*;

/**
 * The EdgeWithPartner represent a edge between two vertices, also contain a
 * reference to its partner, which is the same edge contain at another vertice's
 * adjacent list.
 */

class EdgeWithPartner {
  WeightedVertexPair edge;
  DListNode partner;
  DListNode self;
  DListNode parent;
  // protected VertexWithAdjacent opponent;

  EdgeWithPartner(WeightedVertexPair i){
    this.edge = i;
    this.partner = null;
    this.parent = null;
    this.self = null;
  }

  public String toString(){
  	String out = "";
  	out += "[ " + edge.object1.toString() + ", " + edge.object2.toString() + " $w: " + edge.weight +  "]";
  	return out;
  }
}
