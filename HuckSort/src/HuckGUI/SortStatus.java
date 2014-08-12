/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;
/**
 * <h1>SortStatus signifies the state a sorting algorithm is currently in:</h1>
 *<ol>
 * 		<li>Ready to be run</li>
 * 		<li>Running</li>
 * 		<li>Finished running</li>
 * </ol>
 * @author Ross Studtman
 *
 */
public enum SortStatus {
	
	// the sort method has yet to be run.
	ready,
	
	// the sort method is running.
	running,
	
	// the sort method is finished.
	finished
}
