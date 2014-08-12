/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package sorts;

import huckUtils.HuckConstants;
import interfaces.SortListener;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>ClassicInsertionSort uses an insertion sort algorithm to sort a
 * collection of integers.</h1>
 * 
 *  <p>This is nearly a straight port of the Insertion sort depicted in
 * Y. Daniel Laing's text "Introduction to Java Programming, 9th ed.".</p>
 * 
 * <p>ClassicInsertionSort tracks time to complete the sort and
 * gathers data that is sent to the controller (HuckFrame).</p>
 * 
 * <p>Insertion Sort: (explanation assumes sorting small to large)<p>
 * 
 * <p>Insertion sort is a comparison sort that assumes the first element of
 * a list is already sorted, which means that at the very beginning of this
 * sort there exist two sub lists inside the list to be sorted:</p>
 * 
 * 		1) an already-sorted portion 
 * 		2) an un-sorted portion
 * 
 * <p>The insertion sort begins each iteration by comparing the first element of 
 * the un-sorted portion to the last element of the already-sorted portion, 
 * i.e., compare the current element with the previous element.</p>
 * 
 * <p>If the un-sorted element is smaller than the previous element, move the
 * previous element forward one index, move the list pointer back one 
 * element, and repeat the comparisons until the proper location of the
 * un-sorted element is found.</p>
 * 
 * @author Ross Studtman
 *
 */
public class ClassicInsertionSort {

	/**
	 * Controller's listener for this class.
	 */
	private SortListener listener;
	
	/**
	 * Perform Insertion sort.
	 * 
	 * @param array is the array to be sorted.
	 */
	public void runClassicInsertionSort(int[] array){
		// start timer
		long startTime = System.currentTimeMillis();
		
		int length = array.length;
		
		for(int currentElement = 1; currentElement < length; currentElement++){
			
			// temporarily save current value
			int currentElementValue = array[currentElement];
			
			// loop variable created outside loop to increase scope
			int previousElement;
			
			// loop over previous elements while previousElement >= to index 0 of array AND previousElement value > currentElementValue.
			for( previousElement = currentElement - 1; previousElement >= 0 && array[previousElement] > currentElementValue; previousElement--){
				
				// Move previous element value forward one element.
				array[previousElement + 1] = array[previousElement];
			}
			
			// insert currentElementValue value into array[previousElement + 1];
			array[previousElement +1] = currentElementValue;
		}
		
		// end timer
		long finishTime = System.currentTimeMillis();
		
		// Calculated method's runtime.
		long diff = finishTime - startTime;
		
		// send results to listener
		if(listener != null){
			Map<Enum, Integer> results = new HashMap<Enum, Integer>();
			results.put(HuckConstants.C.SORT_ID, HuckConstants.INSERTION);
			results.put(HuckConstants.C.TOTAL_MILLIS, (int)diff);
			listener.receiveSortData(results);
		}
		//System.out.println("\tClassic Insertion sort millis: " + diff + "\n");
	}
	
	/**
	 * Set the listener for sending resulting data to.
	 * 
	 * @param listener the class listening to this one (HuckFrame).
	 */
	public void setSortListener(SortListener listener){
		this.listener = listener;
	}
}
