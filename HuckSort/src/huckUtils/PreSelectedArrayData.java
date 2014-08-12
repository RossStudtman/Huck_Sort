/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package huckUtils;

/**
 * <h1>PresSelectedArrayData contains sets of values that can be
 * used to quickly and easily generate the array to be sorted.</h1>
 * 
 * <p>This data is inserted into the text fields of ArrayPanel.java. if 
 * the user selects one of the "Pre-Selected Data" radio button options.</p>
 * 
 * <p>
 * The array to be sorted contains three regions within it, each region
 * can contain its own set of random values. The following defines the 
 * character of the array to be sorted:
 * </p>
 * 
 * <ol>
 * 		<li>Index 0: Array size</li>
 * 		<li>Index 1: Region one minimum value</li>
 * 		<li>Index 2: Region one maximum value</li>
 * 		<li>Index 3: Region two minimum value</li>
 * 		<li>Index 4: Region two maximum value</li>
 * 		<li>Index 5: Region three minimum value</li>
 * 		<li>Index 6: Region three maximum value</li>
 * </ol>
 * 
 * 
 * @author Ross Studtman 
 */
public class PreSelectedArrayData {
	
	// Test Data A
	public static final int[] arrayData_A = {12345, 0, 100, 0, 100, 0, 100};
	
	// Test Data B
	public static final int[] arrayData_B = {12345, 0, 100, 0, 100, 0, 9};

	// Test Data C
	public static final int[] arrayData_C = {123450, 0, 100, 0, 100, 0, 100};

	// Test Data D
	public static final int[] arrayData_D = {12345, 0, 100, 0, 100, 0, 9};

}
