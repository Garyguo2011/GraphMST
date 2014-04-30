/* Sort.java */

package graphalg;

import graph.*;
import set.*;
import list.*;
import dict.*;

/**
   *  The sort algorithm used by sorting all the edges for Krustal algortithm.
   *  This implementation is imported from lab 12.
   *
   *  
   **/

public class Sort {


  /**
   *  quickSort() sort the input array using quick sort algorithm taught in lecture
   *       
   *  @param a is the input array needs to be sorted
   **/
  	public static void quickSort(Edge[] a) {
    	bestSort(a, 0, a.length - 1);
  	}

  /**
   *  swapReferences() exchanges the two values specified by the input two indeces.
   *
   *  @param a is the input array where two exchange values locate.
   *  @param index1 is the first value location
   *  @param index2 is the second value location
   *
   **/
	public static void swapReferences(Edge[] a, int index1, int index2) {
  	Edge tmp = a[index1];
  	a[index1] = a[index2];
  	a[index2] = tmp;
	}


  /**
   *  bestSort() is a sorting algorithm combined by quick sort and insertion sort
   *  the implementation details are imported from lab 12.
   *
   *  @param a is the input array needs to be sorted 
   *  @param lo0 is the first index of the input array
   *  @param hi0 is the last index of the input array
   *
   **/
	private static void bestSort(Edge[] a, int lo0, int hi0){
		if (a.length <= 50){
  		for (int p = lo0 + 1; p <= hi0; p++) {
      	Edge tmp = a[p];
      	int j;

     		for (j = p; (j > lo0) && (tmp.getWeight() < a[j - 1].getWeight()); j--) {
        		a[j] = a[j - 1];
      		}
      	a[j] = tmp;
    	}
		}
		else{
		 	int lo = lo0;
    	int hi = hi0;
    	Edge mid;

    	if (hi0 > lo0) {

     		// Arbitrarily establishing partition element as the midpoint of
     		// the array.
     		swapReferences(a, lo0, (lo0 + hi0)/2);
     		mid = a[(lo0 + hi0) / 2];

     		// loop through the array until indices cross.
     		while (lo <= hi) {
       		// find the first element that is greater than or equal to 
       		// the partition element starting from the left Index.
       		while((lo < hi0) && (a[lo].getWeight() < mid.getWeight())) {
         			lo++;
       		}

       		// find an element that is smaller than or equal to 
       		// the partition element starting from the right Index.
       		while((hi > lo0) && (a[hi].getWeight() > mid.getWeight())) {
         			hi--;
       		}
       		// if the indices have not crossed, swap them.
       		if (lo <= hi) {
         			swapReferences(a, lo, hi);
         			lo++;
         			hi--;
       		}
     		}	

     		// If the right index has not reached the left side of array
     		// we must now sort the left partition.
     		if (lo0 < hi) {
       		bestSort(a, lo0, hi);
     		}

     		// If the left index has not reached the right side of array
     		// must now sort the right partition.
     		if (lo < hi0) {
       		bestSort(a, lo, hi0);
     		}
  		}
		}
	}

}
