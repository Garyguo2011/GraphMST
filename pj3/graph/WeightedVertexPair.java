/* WeightedVertexPair.java */

package graph;

/**
 * The WeightedVertexPair extends VertexPair to represent edge
 * with a extra attribute weight to set a weight associate
 * with edge.
 **/
class WeightedVertexPair extends VertexPair{
  int weight;

  /**
   * VertexWithAdjacent() is constructor to create a VertexWithAdjacent object,
   * iniilize item as vertex
   *
   * @param i - a object that reprents vetex
   * @return a new WeightedVertexPair object
   *
  **/
  WeightedVertexPair(Object o1, Object o2, int w) {
    super(o1, o2);
    this.weight = w;
  }
}
