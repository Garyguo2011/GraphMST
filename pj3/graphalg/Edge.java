/* Edge.java */

package graphalg;

import graph.*;
import set.*;
import dict.*;

/**
 * The VertexPair represents a pair of objects that act as vertices in a
 * WUGraph (weighted, undirected graph).  The purpose of a VertexPair is to
 * act as a key for Java's hashCode() and equals() functions.  It is designed
 * so that the order of the two objects is immaterial; (u, v) is the same as
 * (v, u).
 */

class Edge {
  private Object object1;
  private Object object2;
  private int weight;

  protected Edge(Object o1, Object o2, int w) {
    object1 = o1;
    object2 = o2;
    weight = w;
  }

  public Object getObject1(){
    return object1;
  }

  public Object getObject2(){
    return object2;
  }

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
   */
  public int hashCode() {
    if (object1.equals(object2)) {
      return object1.hashCode() + 1 + ((Object)weight).hashCode();
    } else {
      return object1.hashCode() + object2.hashCode() + ((Object)weight).hashCode();
    }
  }
}
