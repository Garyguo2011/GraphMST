/* WeightedVertexPair.java */

package graph;

/**
 * The WeightedVertexPair extends VertexPair to represent edge with weight
 */

class WeightedVertexPair extends VertexPair{
  int weight;

  WeightedVertexPair(Object o1, Object o2, int w) {
    super(o1, o2);
    this.weight = w;
  }
}
