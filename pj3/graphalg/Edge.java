/* Edge.java */

package graphalg;

import graph.*;
import set.*;
import list.*;
import dict.*;

/**
 * The Edge represents a pair of objects that act as vertices in a
 * WUGraph (weighted, undirected graph).  The purpose of an Edge is to serve for 
 * Krustal algorithm (all the edges need to be sorted in order to find MST) and
 * act as a key for Java's hashCode() and equals() functions.  It is designed
 * so that the order of the two objects is immaterial; (u, v) is the same as
 * (v, u).
 */

public class Edge {
  private Object object1;
  private Object object2;
  private int weight;

  /**
   *  This constructor construct an Edge instance with given two vertices and weight
   *  
   *
   *  @param o1 is the first vertex.
   *  @param o2 is the second vertex
   *  @return w is the weight of the edge.
   **/
  public Edge(Object o1, Object o2, int w) {
    object1 = o1;
    object2 = o2;
    weight = w;
  }


  /**
   *  getObject1() obtains the first vertex of the edge.
   *
   *  @return the first vertex of the edge
   **/
  public Object getObject1(){
    return object1;
  }

  /**
   *  getObject2() obtains the second vertex of the edge.
   *
   *  @return the second vertex of the edge
   **/
  public Object getObject2(){
    return object2;
  }

  /**
   *  getWeight() obtains the weight of the edge.
   *
   *  @return the weight of the edge
   **/
  public int getWeight(){
    return weight;
  }

  /**
   * hashCode() returns a hashCode equal to the sum of the hashCodes of each
   * of the two objects of the pair, so that the order of the objects will
   * not affect the hashCode.  Self-edges are treated differently:  we don't
   * add an object's hashCode to itself, since the result would always be even.
   * We add one to the hashCode so that a self-edge will not collide with the
   * object itself if vertices and edges are stored in the same hash table.
   *
   *  @return the hashCode of this edge.
   *
   */
  public int hashCode() {
    if (object1.equals(object2)) {
      return object1.hashCode() + 1 + ((Object)weight).hashCode();
    } else {
      return object1.hashCode() + object2.hashCode() + ((Object)weight).hashCode();
    }
  }
}
