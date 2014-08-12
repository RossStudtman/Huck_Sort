/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package sorts;

import huckUtils.HuckConstants;
import interfaces.SortListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>NativeJavaSort uses Java's native sorting algorithm to sort
 * a collection of integers.</h1>
 * 
 * <p>NativeJavaSort tracks time to complete the sort and
 * gathers data that is sent to the controller (HuckFrame).</p>
 * 
 * @author Ross Studtman
 *
 */
public class NativeJavaSort {
	
	/**
	 * Controller's listener for this class.
	 */
	private SortListener listener;

	/**
	 * Run Java's native sort algorithm.
	 *  
	 * @param arrayToSort is the array to be sorted.
	 */
	public void runJavasSort(int[] arrayToSort) {
		// start timer
		long startTime = System.currentTimeMillis();
		Arrays.sort(arrayToSort);
		// end timer
		long finishTime = System.currentTimeMillis();
		
		// calculate method's runtime.
		long diff = finishTime - startTime;
		
		// send results to listener
		if(listener != null){
			Map<Enum, Integer> results = new HashMap<Enum, Integer>();
			results.put(HuckConstants.C.SORT_ID, HuckConstants.NATIVE);
			results.put(HuckConstants.C.TOTAL_MILLIS, (int)diff);
			listener.receiveSortData(results);
		}
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
