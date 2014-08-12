/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package interfaces;

/**
 * <h1>RunSortsListener allows the GUI SortPanel
 * to communicate with the controller (HuckFrame).</h1>
 * 
 * @author Ross Studtman
 */
public interface RunSortsListener {
	/**
	 * The controller which houses this method will use
	 * it for orchestrating calling different sort 
	 * algorithms against a given array of integers.
	 */
	public void runSorts();
}
