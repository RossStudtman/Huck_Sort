/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import javax.swing.SwingUtilities;

/**
 * Entry point of Huck Sort program.
 * 
 * @author Ross Studtman
 * 
 */
public class Sandbox {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				new HuckFrame();				
			}
		});
	}
}
