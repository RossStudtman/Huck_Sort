/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package huckUtils;

/**
 * <h1>Constants used to identify sort algorithms and the data
 * those algorithms return for display to the GUI.</h1>
 * 
 * @author Ross Studtman
 *
 */
public class HuckConstants {
	
	/**
	 * Keys for hashmap that ferries data between
	 * sort classes and HuckFrame controller.
	 */
	public static enum C{
		SORT_ID, TOTAL_MILLIS, HUCK_MILLIS, HUCKED_TURTLES		
	}
	
	/**
	 * Value for SORT_ID key: classic insertion sort.
	 */
	public static final int INSERTION = 0;
	
	/**
	 * Value for SORT_ID key: Huck sort version one.
	 */
	public static final int HUCK_V1 = 1;
	
	/**
	 * Value for SORT_ID key: Huck sort version two.
	 */
	public static final int HUCK_V2 = 2;
	
	/**
	 * Value for SORT_ID key: Java's native sort.
	 */
	public static final int NATIVE = 100;
}
