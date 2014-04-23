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
  private int edgesNum;
  private HashTableChained vertexHashTable;
  private HashTableChained edgeHashTable;
  private DList vertexList;
  private DList edgeList;

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
      DListNode walker = (DListNode) vertexList.front();
      while(walker.isValidNode()){
        vertices[index] = ((VertexWithAdjacent)walker.item()).item;
        walker = (DListNode)walker.next();
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
    vertexList.insertBack(new VertexWithAdjacent(vertex));
    vertexHashTable.insert(vertex, vertexList.back());
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
    if(!this.isVertex(vertex)){
      return;
    }
    try{
      DListNode vNode = (DListNode) vertexHashTable.find(vertex).value();
      VertexWithAdjacent v = (VertexWithAdjacent) vNode.item();
      if(!v.adjacentList.isEmpty()){
        DListNode walker = (DListNode) v.adjacentList.front();
        while(walker.isValidNode()){
          DListNode next = (DListNode) walker.next();
          Object o1 = ((EdgeWithPartner)walker.item()).edge.object1;
          Object o2 = ((EdgeWithPartner)walker.item()).edge.object2;
          this.removeEdge(o1, o2);
          walker = next;
        }
      }
      vertexHashTable.remove(vertex);
      vNode.remove();
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
  }



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
    if(!this.isVertex(vertex)){
      return 0;
    }
    try{
      DListNode v = (DListNode) vertexHashTable.find(vertex).value();
      return ((VertexWithAdjacent)v.item()).adjacentList.length();
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
    return 0;
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
  public Neighbors getNeighbors(Object vertex){
    if(!this.isVertex(vertex)){
      return null;
    }
    
    try{
      DListNode vNode = (DListNode)vertexHashTable.find(vertex).value();
      VertexWithAdjacent v = (VertexWithAdjacent) vNode.item();
      if(v.adjacentList.isEmpty()){
        return null;
      }

      Neighbors ret = new Neighbors();
      ret.neighborList = new Object[v.adjacentList.length()];
      ret.weightList = new int[v.adjacentList.length()];
      int index = 0;

      DListNode walker = (DListNode) v.adjacentList.front();
      while(walker.isValidNode()){
        
        Object o1 = ((EdgeWithPartner)walker.item()).edge.object1;
        Object o2 = ((EdgeWithPartner)walker.item()).edge.object2;
        int weight = ((EdgeWithPartner)walker.item()).edge.weight;

        if(v.item.equals(o1)){
          ret.neighborList[index] = o2;
        }else{
          ret.neighborList[index] = o1;
        }
        ret.weightList[index] = weight;

        index++;
        walker = (DListNode) walker.next();
      }
      return ret;
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
    return null;
  }

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
    try{
      
      // u, v is not vertex
      if(!this.isVertex(u) || !this.isVertex(v)){
        return;
      }

      // the edges alread exist, update the weight;
      Entry eEntry = edgeHashTable.find(new VertexPair(u, v));
      if(eEntry != null){
        EdgeWithPartner e = (EdgeWithPartner)((DListNode) eEntry.value()).item();
        e.edge.weight = weight;
        ((EdgeWithPartner)e.partner.item()).edge.weight = weight;
        return;
      }

      DListNode uNode = (DListNode)vertexHashTable.find(u).value();
      DListNode vNode = (DListNode)vertexHashTable.find(v).value();
      VertexWithAdjacent u1 = (VertexWithAdjacent)uNode.item();
      VertexWithAdjacent v1 = (VertexWithAdjacent)vNode.item();

      WeightedVertexPair weightedEdge = new WeightedVertexPair(u, v, weight);
      // if it is Self-edges
      if(u.equals(v)){
        EdgeWithPartner selfEdge = new EdgeWithPartner(weightedEdge);
        u1.adjacentList.insertBack(selfEdge);
        selfEdge.partner = (DListNode) u1.adjacentList.back();
        selfEdge.self = (DListNode) u1.adjacentList.back();
        selfEdge.parent = uNode;
        edgeHashTable.insert(new VertexPair(u, v), u1.adjacentList.back());

      }else{
        EdgeWithPartner eU = new EdgeWithPartner(weightedEdge);
        EdgeWithPartner eV = new EdgeWithPartner(weightedEdge);
        u1.adjacentList.insertBack(eU);
        v1.adjacentList.insertBack(eV);

        eU.self = (DListNode) u1.adjacentList.back();
        eU.partner = (DListNode) v1.adjacentList.back();
        eU.parent = uNode;

        eV.self = (DListNode) v1.adjacentList.back();
        eV.partner = (DListNode) u1.adjacentList.back();
        eV.parent = vNode;

        edgeHashTable.insert(new VertexPair(u, v), eU.self);
      }
      edgesNum ++;
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v){
    if(!this.isEdge(u, v)){
      return;
    }
    try{
      VertexPair key = new VertexPair(u, v);
      DListNode eNode = (DListNode) edgeHashTable.find(key).value();
      // System.out.println("self: " + eNode.item());      
      // System.out.println("partner: " +   ((DListNode)((EdgeWithPartner)eNode.item()).partner).item()  )  ;
      // System.out.println("parent:" + ((EdgeWithPartner)eNode.item()).parent.item());
      // System.out.println();
      if(!u.equals(v)){
        ((EdgeWithPartner)eNode.item()).partner.remove();  
      }
      edgeHashTable.remove(key);
      eNode.remove();
      edgesNum--;
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v){
    if(!this.isVertex(u) || !this.isVertex(v)){
      return false;
    }
    Entry eEntry = edgeHashTable.find(new VertexPair(u, v));
    if(eEntry == null){
      return false;
    }else{
      return true;
    }
  }

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
  public int weight(Object u, Object v){
    try{
      if (this.isEdge(u, v)) {
        DListNode edgeNode = (DListNode) edgeHashTable.find(new VertexPair(u, v)).value();
        return ((EdgeWithPartner)edgeNode.item()).edge.weight;
      }
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
    return 0;
  }


  /*
   **************************************************************************
   following mether for test
  */

   public HashTableChained vht(){
    return vertexHashTable;
   }










}
