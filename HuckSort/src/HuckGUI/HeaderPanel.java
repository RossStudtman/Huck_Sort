/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * HeaderPanel is for displaying the title of the program.
 * 
 * @author Ross Studtman
 *
 */
public class HeaderPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public HeaderPanel(){
		setLayout(new BorderLayout(10, 15));
		
		setBackground(new Color(240,168,48));
		
		JTextField header = new JTextField("Huck Sort");
		header.setPreferredSize(new Dimension(200, 40));
		header.setForeground(Color.BLUE);
		header.setFont(new Font("Courier", Font.BOLD, 20));
		header.setHorizontalAlignment(JTextField.CENTER);
		header.setBackground(new Color(252,235,182));
		
		add(header, BorderLayout.NORTH);
		
		String instructionsString = String.format("%s \n%s \n%s \n%s \n%s" , 
				"1. Create an array to be sorted: A) type in your own data, or B) Choose Pre-Selected Data options.",
				"     - Arrays are filled with random numbers into three equally sized partitions.",
				"     - You can define a different range of random numbers for each partition.",
				"2. Click Get Stats",
				"3. Click Perform Sorts.");
		
		JTextArea instructions = new JTextArea(instructionsString);
		instructions.setMargin(new Insets(10,10,10,10));
		//instructions.setFont(new Font("Courier", Font.ITALIC, 15));
		
		instructions.setFont(instructions.getFont().deriveFont(12f));
		
		add(instructions, BorderLayout.CENTER);
	}
}
