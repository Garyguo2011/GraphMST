/* Kruskal.java */

package graphalg;

import graph.*;
import set.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g){
  	WUGraph mst = new WUGraph();
  	Object[] vertices = g.getVertices();
  	for (int i = 0; i < g.vertexCount(); i++){
  		Object curVertex = vertices[i]
  		mst.addVertex(curVertex);
  		Neighbors neighbor = g.getNeighbors(curVertex);
  		Object[] verList = neighbor.neighborList;
  		int[] wList = neighbor.weightList;
  	}
  }


  

}
