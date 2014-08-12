/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package sorts;

import huckUtils.HuckConstants;
import huckUtils.StandardDeviation;
import interfaces.SortListener;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *<h1>HuckSort_V1 is an algorithm for sorting a collection of integers, small
 * to large.</h1>
 * 
 * <p>HuckSort_V1 tracks time to complete the sort and
 * gathers data that is sent to the controller (HuckFrame).</p>
 * 
 * <p>The algorithm is as follows:</p>
 * <ul>
 * <li> Obtains statistical mean of array to sort (<b>ATS</b>).
 * <li> Hard codes the number of iterations the outer loop runs.
 * <li> Hard coded value for number of iterations of outer loop
 * 		is also used as denominator in determining turtle value
 * 		threshold.
 * <li> Each iteration of the outer loop:
 * 		<ul>
 * 		<li> decrements the hard coded iteration number.
 * 		<li> determines a new turtle value by dividing the ATS's
 * 			 statistical mean by the decremented iteration number.
 * 			 Thus, turtle value threshold increases with each
 * 			 iteration because the denominator is decreasing.
 * 		</ul>
 * <li> The inner loop does the following:
 * 		<ol>
 * 		<li> iterate over the ATS.
 * 		<li> compare current index value to turtle threshold value.
 * 		<li> values less than turtle value are hucked (thrown towarard the front of
 * 			 the collection; the hucked turtle count determines the index to which
 * 			 the turtle is hucked).
 * 		<li> a hucked turtle is swapped with the element at the index determined by
 * 			 the turtle count.
 * 		<li> if turtle is hucked, increment turtle count.
 * 		<li> loop counter assigned turtle count number, hence subsequent loop
 * 			 iterations begin at the index immediately after the previous
 * 			 iteration's last hucked turtle. <i>For example, if the previous loop
 * 			 iteration resulted in a turtle count of 1,000 then turtles would occupy
 * 			 indices 0 - 999 and counter would be set to 1,000, the hucked turtle number.
 * 			 In this way hucked turtles are never re-evaluated once they have been
 * 			 hucked.</i>
 * 		</ol> 
 * </ul>
 * </p>
 * <p>
 * The difference between HuckSort_V1 and HuckSort_V2 is all in the outer loop, ie, they
 * have the same inner loops. The outer loops differ in how they calculate turtle
 * threshold values and in loop conditions. Both of these Huck sort version use hard
 * coded values for determining the number of times the outer loop runs. It would be
 * clever to come up with an algorithm that allowed the number of iterations of the
 * outer loop to change based on some kind of statistical analysis of the array to
 * be sorted.</p>
 * 
 * @author Ross Studtman
 *
 */
public class HuckSort_V1 {
	
	/**
	 * Controller's listener for this class.
	 */
	private SortListener listener;

	
	/**
	 * Run Huck sort version one.
	 * 
	 * @param array is the array to be sorted.
	 */
	public void runHuckedInsertionSort(int[] array){
		// start timer
		long startTime = System.currentTimeMillis();
		// start Huck specific timer
		long startHuckTime = System.currentTimeMillis();
		
		// length of array
		int length = array.length;
		
		// Array's statistical mean.
		int mean = (int)StandardDeviation.getAverage(array);
		
		// Number of iterations to take through the list && denominator for turtleValue calculation.	
		int iterationDenominator = 10;	
			/*
			 * tried many values, fast with all. Anything greater than mean results mean/mean+ = integer 0.
			 * Same basic speed with a value of 10.
			 */
		
		// The value for determining a turtle.
		int turtleValue;
				
		//Hucked Turtle count.
		int huckedTurtles = 0;		
		

		
		while(iterationDenominator > 0){
			
			/*
			 * Each pass divides the mean by smaller numbers, ie, "turtleValue" grows larger.
			 */
			turtleValue = mean / iterationDenominator;
			iterationDenominator--;		
			
			// pre-sort turtle hucking; Start inserting next sequence of turtles at conclusion of last iteration.
			for(int counter = huckedTurtles; counter < length; counter++){
				if (array[counter] < turtleValue ) { 
					
					// swap
					int temp = array[huckedTurtles];
					array[huckedTurtles] = array[counter];
					array[counter] = temp;

					// increment hucked turtles
					huckedTurtles++;
				}
			}	
		}
		
		// end Huck specific timer
		long finishHuckTime = System.currentTimeMillis();
		
		///////////////////////////////
		///		INSERTION SORT		///
		///////////////////////////////
		
		for(int i = 1; i < length; i++){
			// temporarily save current value
			int temp = array[i];
			
			// loop variable created outside loop to increase scope
			int checkPrevious;
			
			// loop over previous elements while checkPrevious >= to index 0 of array AND current element > temp value.
			for( checkPrevious = i - 1; checkPrevious >= 0 && array[checkPrevious] > temp; checkPrevious--){
				array[checkPrevious + 1] = array[checkPrevious];
			}
			
			// insert temp value into array[checkPrevious + 1];
			array[checkPrevious +1] = temp;
		}
		
		// end timer
		long finishTime = System.currentTimeMillis();
		
		// calculate method's runtime.
		long diff = finishTime - startTime;
		
		// calculate Huck's run time
		long huckDiff = finishHuckTime - startHuckTime;
		
		// send results to listener
		if(listener != null){
			Map<Enum, Integer> results = new HashMap<Enum, Integer>();
			results.put(HuckConstants.C.SORT_ID, HuckConstants.HUCK_V1);
			results.put(HuckConstants.C.TOTAL_MILLIS, (int)diff);
			results.put(HuckConstants.C.HUCK_MILLIS, (int)huckDiff);
			results.put(HuckConstants.C.HUCKED_TURTLES, huckedTurtles);
			listener.receiveSortData(results);
		}
		
//		// print out results
//		System.out.println("\tHuck time millis: "+ huckDiff);
//		System.out.println("\tHuckInsertionSort millis: " + diff);
//		System.out.println("\thucked turtles: " +huckedTurtles + "\n");
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
