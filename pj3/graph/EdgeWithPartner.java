/* EdgeWithPartner.java */

package graph;

/**
 * The EdgeWithPartner represent a edge between two vertices, also contain a
 * reference to its partner, which is the same edge contain at another vertice's
 * adjacent list.
 */

protected class EdgeWithPartner {
  protected WeightedVertexPair edge;
  protected EdgeWithPartner partner;
  protected VertexWithAdjacent self;
  // protected VertexWithAdjacent opponent;

  protected EdgeWithPartner(WeightedVertexPair i){
    this.edge = i;
    this.partner = null;
    this.self = null;
  }
}
