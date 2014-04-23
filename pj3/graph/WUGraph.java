/* WUGraph.java */

package graph;

import list.*;
import dict.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

  // private vertexNum;
  private edgesNum;
  HashTableChained vertexHashTable;
  HashTableChained edgeHashTable;
  DList vertexList;
  DList edgeList;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph(){
    this.edgesNum = 0;
    this.vertexHashTable = new HashTableChained();
    this.edgeHashTable = new HashTableChained();
    this.vertexList = new DList();
    this.edgeList = new DList();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount(){
    return vertexList.length();
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount(){
    return edgesNum;
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices(){
    Object[] vertices = new Object[vertexList.length()];
    if (vertexList.length() == 0){
      return vertices;
    }

    try{
      int index = 0;
      DListNode walker = vertexList.front();
      while(walker.isValidNode()){
        vertices[index] = ((VertexWithAdjacent)walker.item()).item;
        walker = walker.next();
        index ++;
      }
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
    return vertices;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex){
    /* If this object is already a vertex of the graph, the graph is unchanged. */
    if(vertexHashTable.find(vertex) != null){
      return;
    }
    VertexWithAdjacent newVertex = new VertexWithAdjacent(vertex);
    vertexList.insertBack(newVertex);
    vertexHashTable.insert(vertex, newVertex);
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex);

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
    if (vertexHashTable.find(vertex) == null){
      return false;
    }else{
      return true;
    }
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex){
    VertexWithAdjacent v = vertexHashTable.find(vertex);
    if(v = null){
      return 0;
    }else{
      return v.adjacentList.length();
    }
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex);

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight){
    VertexWithAdjacent u1 = (VertexWithAdjacent) vertexHashTable.find(u);
    VertexWithAdjacent v1 = (VertexWithAdjacent) vertexHashTable.find(v);
    // not find either vertex
    if(u1 == null || v1 == null){
      return;
    }

    EdgeWithPartner e = (EdgeWithPartner) edgeHashTable.find(new VertexPair(u, v));
    // the edges alread exist, update the weight;
    if(e != null){
      e.edge.weight = weight;
      e.partner.edge.weight = weight;
      return;
    }

    WeightedVertexPair weightedEdge = new WeightedVertexPair(u, v, weight);
    // if it is Self-edges
    if(u.equals(v)){
      EdgeWithPartner selfEdge = new EdgeWithPartner(weightedEdge);
      selfEdge.partner = selfEdge;
      selfEdge.self = u1;
      u1.adjacentList.insertBack(selfEdge);
      edgeHashTable.insert(new VertexPair(u, u), selfEdge);
    }else{
      EdgeWithPartner eU = new EdgeWithPartner(weightedEdge);
      EdgeWithPartner eV = new EdgeWithPartner(weightedEdge);
      eU.partner = eV;
      eV.partner = eU;
      eU.self = u1;
      ev.self = v1;

      u1.adjacentList.insertBack(eV);
      v1.adjacentList.insertBack(eU);
      edgeHashTable.insert(new VertexPair(u, v), eU);
    }
    edgesNum ++;
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v);

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v);

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v);

}
