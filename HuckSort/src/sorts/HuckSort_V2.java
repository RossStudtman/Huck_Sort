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
 * <h1>HuckSort_V2 is an algorithm for sorting a collection of integers, small
 * to large.</h1>
 * 
 * <p>HuckSort_V2 tracks time to complete the sort and
 * gathers data that is sent to the controller (HuckFrame).</p>
 * 
 * <p>The algorithm is as follows:</p>
 * <ul>
 * <li> Obtains statistical mean of array-to-sort (<b>ATS</b>).
 * <li> Obtains standard deviation of ATS.
 * <li> Calculates coefficient of variance (CoV) for ATS.
 * <li> Creates an array of values whose size determines the number of 
 * 		iterations the outer loop will perform; and whose elements are the 
 * 		result of dividing a hard coded value by CoV. <i>The hard coded element
 * 		values increase in size to ensure the turtle threshold value increases</i>.
 * <li> Outer loop iterates once for each value in array mentioned above. 
 * 		Each iteration of the outer loop recalculates turtle value threshold.  
 * <li> Turtle value threshold calculated by multiplying ATS's statistical mean
 * 		by the value held at the index of the array of doubles described above.
 * the result of that by the statistical mean of ATS.
 * <p>
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
 * 
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
public class HuckSort_V2 {
	/**
	 * Controller's listener for this class.
	 */
	private SortListener listener;

	/**
	 * Run Huck sort version two.
	 * 
	 * @param array is the array to be sorted.
	 */
	public void runHuckedInsertionSortV2(int[] array){
		// start timer
		long startTime = System.currentTimeMillis();
		
		// start Huck specific timer
		long startHuckTime = System.currentTimeMillis();
		
		// length of array
		int length = array.length;
		
		// Array's statistical mean.
		double mean = StandardDeviation.getAverage(array);
		
		// Array's statistical standard deviation
		double deviation = StandardDeviation.getStandardDeviation(array);
		
		// Array's coefficient of variation
		double cv = mean / deviation;
		
		// The value for determining a turtle.
		int turtleValue;
				
		// array of denominators, elements divided by coefficient of variation (reason: allows for mild dynamic response to data).
		double[] calculationsArray = {0.25/cv, 0.50/cv, 2.0/cv, 2.25/cv, 2.5/cv};
		
		//Hucked Turtle count.
		int huckedTurtles = 0;		
		
		
		for(int i = 0; i < calculationsArray.length; i++){
			
			// calculate turtle threshold
			turtleValue = (int)(mean * calculationsArray[i]);	
			
			//System.out.print("t: " + threshold +", ");

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
			//System.out.println("ht: " + huckedTurtles +", ");		
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
			results.put(HuckConstants.C.SORT_ID, HuckConstants.HUCK_V2);
			results.put(HuckConstants.C.TOTAL_MILLIS, (int)diff);
			results.put(HuckConstants.C.HUCK_MILLIS, (int)huckDiff);
			results.put(HuckConstants.C.HUCKED_TURTLES, huckedTurtles);
			listener.receiveSortData(results);
		}
		
//		// print out results
//		System.out.println("\tHuck time millis: "+ huckDiff);
//		System.out.println("\tHuckInsertionSortV2 millis: " + diff);
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
