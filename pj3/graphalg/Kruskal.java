/* Kruskal.java */

package graphalg;

import graph.*;
import set.*;
import dict.*;


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
  	HashTableChained edgeHashTable = new HashTableChained(g.edgeCount());
  	HashTableChained vertexHashTable = new HashTableChained(g.vertexCount());
  	int numVertices = g.vertexCount();
  	Edge[] edgeList = new Edge[numVertices * numVertices];
  	int k = 0;
  	int counter = 0;

  	for (int i = 0; i < numVertices; i++){
  		Object curVertex = vertices[i];
  		vertexHashTable.insert(curVertex, counter);
  		counter++;
  		mst.addVertex(curVertex);
  		Neighbors neighbor = g.getNeighbors(curVertex);
  		Object[] verList = neighbor.neighborList;
  		int[] wList = neighbor.weightList;
  		int numNeighbors = verList.length;
  		for (int j = 0; j < numNeighbors; j++){
  			Edge edge = new Edge(curVertex, verList[j], wList[j]);
  			if (edgeHashTable.find(edge) == null){
  				edgeHashTable.insert(edge, edge);
  				edgeList[k] = edge;
  				k++;
  			}
  		}
  	}

		Edge[] sortEdgeList = edgeListResize(edgeList);
		Sort.quickSort(sortEdgeList);

		DisjointSets graph = new DisjointSets(numVertices);
		for (int i = 0; i < sortEdgeList.length; i++){
			Edge mstEdge = sortEdgeList[i];
			Object obj1 = mstEdge.getObject1();
			Object obj2 = mstEdge.getObject2();
			int ver1 = (Integer) vertexHashTable.find(obj1);
			int ver2 = (Integer) vertexHashTable.find(obj2);
			if (graph.find(ver1) != graph.find(ver2)) {
				edgesSet.union(ver1, ver2);
				mst.addEdge(obj1, obj2, mstEdge.getWeight());
			}
		}
	
  	return mst;
  	
  }

  protected static Edge[] edgeListResize(Edge[] eList){
  	int count = 0;
  	for (int i = 0; i < eList.length; i++){
  		if (eList[i] == null){
  			break;
  		}
  		count++;
  	}
  	Edge[] resizeEList = new Edge[count];
  	for (int i = 0; i < count; i++){
  		resizeEList[i] = eList[i];
  	}
  	return resizeEList;
  }


  

}
