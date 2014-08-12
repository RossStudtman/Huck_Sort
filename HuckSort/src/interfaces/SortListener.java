/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package interfaces;

import java.util.Map;

/**
 * <h1>SortListener allows various sort algorithms to send the controller (HuckFrame)
 * resulting data for displaying.</h1>
 * 
 * <p>After a sort performs its tasks it will send data to the controller.</p>
 * <p>Example data:</p>
 * <ul>
 * 		<li>Duration of Huck sort (a pre-sort sort)</li>
 * 		<li>Number of turtles that were displaced ("hucked")</li>
 * 		<li>Duration of total sorting algorithm.</li>
 * </ul>
 * 
 * @author Ross Studtman
 */
public interface SortListener {
	/**
	 * <h1>receiveSortData sends sort algorithm data to the controller.</h1>
	 * 
	 * <p>A Map is used to transfer data.</p>
	 * 
	 * @param sortData is a HashMap where the key is an Enum and the values are integers.
	 */
	public void receiveSortData(Map<Enum, Integer> sortData);
}
