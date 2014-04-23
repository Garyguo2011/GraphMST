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
  // protected VertexWithAdjacent opponent;

  EdgeWithPartner(WeightedVertexPair i){
    this.edge = i;
    this.partner = null;
    this.self = null;
  }
}
