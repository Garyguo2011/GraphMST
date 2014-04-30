/* EdgeWithPartner.java */

package graph;
import list.*;

/**
 * The EdgeWithPartner class represents a weighted edge between two vertices
 * (WeightedVertexPair edges), also contain references to its partner, parent,
 * and its DListNode position which is the same edge contain at another 
 * vertice's adjacent list.
 **/
class EdgeWithPartner {
  WeightedVertexPair edge;
  DListNode partner;
  DListNode self;
  DListNode parent;

  /**
   * EdgeWithPartner() is constructor to create a EdgeWithPartner object,
   * iniilize all references as null.  
   *
   * @param i - a WeightedVertexPair object represent edges with weight
   * @return a new EdgeWithPartner object
   *
  **/
  EdgeWithPartner(WeightedVertexPair i){
    this.edge = i;
    this.partner = null;
    this.parent = null;
    this.self = null;
  }
}
