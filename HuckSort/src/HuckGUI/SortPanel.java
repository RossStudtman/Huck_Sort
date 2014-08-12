/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import interfaces.RunSortsListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * <h1>SortPanel displays data gathered from performing sorting algorithms.</h1>
 * <p>SortPanel contains:</p>
 * <ul>
 * 		<li>1 button --> start sorting algorithms</li>
 * 		<li>Graphics that indicate if a particular sort algorithm is ready, 
 * 			has started, or has stopped.</li>
 *  	<li>Text fields for displaying sort algorithm data.</li>
 * </ul>
 * 
 * <p>SortPanel's controller is HuckFrame.</p>
 * 
 * @author Ross Studtman
 *
 */
public class SortPanel extends JPanel {

	private static final long serialVersionUID = -7901236955919273632L;

	/**
	 * Event listener
	 */
	RunSortsListener listener;
	
	/**
	 * Buttons
	 */
	public JButton jbtRunSorts = new JButton("Perform Sorts");

	// 
	/**
	 * Create drawables for depicting sort method's running status.
	 */
	public StatusCircles insertionSortStatusCircle = new StatusCircles();
	public StatusCircles statusHuck_V1 = new StatusCircles();
	public StatusCircles statusHuck_V2 = new StatusCircles();
	public StatusCircles statusNativeJavaSort = new StatusCircles();
	
	/**
	 * Panel for holding result text fields.
	 */
	JPanel resultsPanel;

	/**
	 * Text fields.
	 */
	protected JTextField jtInsertionSort_millis = new JTextField(10);
	protected JTextField jtHuckSort_V1_millis = new JTextField(10);
	protected JTextField jtHuckSort_V1_huckMillis = new JTextField(10);
	protected JTextField jtHuckSort_V1_huckedTurtles = new JTextField(10);
	protected JTextField jtHuckSort_V2_millis = new JTextField(10);
	protected JTextField jtHuckSort_V2_huckMillis = new JTextField(10);
	protected JTextField jtHuckSort_V2_huckedTurtles = new JTextField(10);
	protected JTextField jtNativeJavaSort_millis = new JTextField(10);
	
	/**
	 * Constructor.
	 */
	public SortPanel(){
		setLayout(new BorderLayout(0, 15));
		
		// Create panel to hold run button
		JPanel runButtonPanel = new JPanel(new FlowLayout());
		Border innerboarder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border outerboarder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		runButtonPanel.setBorder(BorderFactory.createCompoundBorder(outerboarder, innerboarder));
		
		// Alter size of run button
		jbtRunSorts.setPreferredSize(new Dimension(200, 30));
		
		// Disable the button
		jbtRunSorts.setEnabled(false);
		
		// Add run button to runbutton panel
		runButtonPanel.add(jbtRunSorts);

		// Add runbutton panel to SortPanel
		add(runButtonPanel, BorderLayout.NORTH);			
		
		// Create panel for sort results.
		resultsPanel = new JPanel(new GridBagLayout());
		
		// GridbagConstraints --> why comment if it adds nothing?
		GridBagConstraints c = new GridBagConstraints();
		
			///// ROW 0: Insertion sort /////
	
			c.insets = new Insets(10, 10, 10, 10);
			c.anchor = GridBagConstraints.EAST;
			c.gridy = 0;	// row 0
			c.gridx = 0;	// column 0
				resultsPanel.add(insertionSortStatusCircle, c);	// icon
			c.gridx = 1;	// column 1
				resultsPanel.add(new JLabel("Insertion Sort", JLabel.RIGHT), c);
			c.gridx = 2;	// column 2
				resultsPanel.add(jtInsertionSort_millis, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("milliseconds"), c);
			
			///// ROW 1: Huck V1 /////
			
			c.insets = new Insets(0, 10, 0, 10);
			c.anchor = GridBagConstraints.EAST;
			c.gridy = 1;	// row 1
			c.gridx = 0;	// column 0
				resultsPanel.add(statusHuck_V1, c);			// icon
			c.gridx = 1;	// column 1
				resultsPanel.add(new JLabel("Huck-Insertion V1", JLabel.RIGHT), c);
			c.gridx = 2;	// column 2
				resultsPanel.add(jtHuckSort_V1_millis, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("milliseconds"), c);
			
			///// ROW: 2: Huck V1 milliseconds /////
			
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets(0, 10, 10, 10);
			c.gridy = 2;	// row 2
			c.gridx = 2;	// column 2
				resultsPanel.add(jtHuckSort_V1_huckMillis, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("huck millis"), c);
			
			///// ROW 3: Huck V1 hucked turtles /////
			
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets(0, 10, 10, 10);
			c.gridy = 3;	// row 3
			c.gridx = 2;	// column 2
				resultsPanel.add(jtHuckSort_V1_huckedTurtles, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("hucked turtles"), c);
			
			///// ROW 4: Huck V2 /////
			
			c.insets = new Insets(10, 10, 0, 10);
			c.anchor = GridBagConstraints.EAST;
			c.gridy = 4;	// row 4
			c.gridx = 0;	// column 0
				resultsPanel.add(statusHuck_V2, c);			// icon
			c.gridx = 1;	// column 1
				resultsPanel.add(new JLabel("Huck-Insertion V2", JLabel.RIGHT), c);
			c.gridx = 2;	// column 2
				resultsPanel.add(jtHuckSort_V2_millis, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("milliseconds"), c);
			
			///// ROW 5: Huck V2 milliseconds /////
			
			c.insets = new Insets(0, 10, 10, 10);
			c.anchor = GridBagConstraints.EAST;
			c.gridy = 5;	// row 5
			c.gridx = 2;	// column 2
				resultsPanel.add(jtHuckSort_V2_huckMillis, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("huck millis"), c);
			
			///// ROW 6: Huck V2 hucked turtles /////
			
			c.insets = new Insets(0, 10, 10, 10);
			c.anchor = GridBagConstraints.EAST;
			c.gridy = 6;	// row 6
			c.gridx = 2;	// column 2
				resultsPanel.add(jtHuckSort_V2_huckedTurtles, c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("hucked turtles"), c);
			
			///// ROW 7: Native Java sort /////
			
			c.insets = new Insets(10, 10, 10, 10);
			c.anchor = GridBagConstraints.EAST;
			c.gridy = 7;	// row 7
			c.gridx = 0;	// column 0
				resultsPanel.add(statusNativeJavaSort, c);	// icon
			c.gridx = 1;	// column 1
				resultsPanel.add(new JLabel("Native Java sort", JLabel.RIGHT), c);
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 2;	// column 2
				resultsPanel.add(jtNativeJavaSort_millis, c);
			c.gridx = 3;	// column 3
				resultsPanel.add(new JLabel("milliseconds"), c);
				
		// Set background color of results panel
		setBackground(Color.decode("#80CCE6"));
		
		// Add results panel to sort panel.
		add(resultsPanel, BorderLayout.CENTER);
		
		// Add button listener
		jbtRunSorts.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(listener != null){
					listener.runSorts();
				}
			}
		});		

		// Right align results panel text fields.
		rightAlignedText(resultsPanel);
	}
	
	/**
	 * <h1>Right-align text in text fields.</h1>
	 * 
	 * <p>Could use either of these techniques to determine if the
	 * component is a JTextField:</p>
	 * <ul>
	 * 		<li>if(components[counter].getClass().getName().equals("javax.swing.JTextField"))</li>
	 * 		<li>if(components[counter] instanceof JTextField)</li>
	 * </ul>
	 * 
	 * @param panel is a JPanel with components.
	 */
	private void rightAlignedText(JPanel panel) {
		
		// Get all components
		Component[] components = panel.getComponents();		
		
		// Loop over components
		for(int counter = 0; counter < components.length; counter++){
			
			// Only manipulate JTextFields
			if(components[counter].getClass().getName().equals("javax.swing.JTextField")){
				
				// Set text to be right aligned.
				((JTextField)components[counter]).setHorizontalAlignment(JTextField.RIGHT);
			}
		}
		
	}
	
	/**
	 * Clear text fields.
	 */
	public void clearTextFields(){
		
		// Get all components
		Component[] comps = resultsPanel.getComponents();
		
		// Loop over components
		for(int counter = 0; counter < comps.length; counter++){
			
			// Only manipulate JTextFields
			if(comps[counter] instanceof JTextField){
				
				// Set text to empty
				((JTextField) comps[counter]).setText("");
			}
		}
	}

	/**
	 * Assign a listener for the SortPanel.
	 * 
	 * @param listener is the component (eg, a JFrame) that
	 * is listening for an event from this SortPanel.
	 */
	public void setRunSortsListener(RunSortsListener listener){
		this.listener = listener;
	}
}
