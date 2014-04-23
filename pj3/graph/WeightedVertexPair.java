/* WeightedVertexPair.java */

package graph;

/**
 * The WeightedVertexPair extends VertexPair to represent edge with weight
 */

protected class WeightedVertexPair extends VertexPair{
  protected int weight;

  protected WeightedVertexPair(Object o1, Object o2, int w) {
    super(o1, o2);
    this.weight = w;
  }
}
