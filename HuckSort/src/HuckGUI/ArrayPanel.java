/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */


package HuckGUI;

import huckUtils.PreSelectedArrayData;
import huckUtils.RandomArrayMaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 * <h1>ArrayPanel gathers user data for constructing an array filled
 * with random integers.</h1>
 * 
 * <p>Various sort algorithms can then use the random array for testing.</p>
 * 
 * <p>User can type integer values into text fields to create array or 
 * select pre-chosen values.</p>
 * <p>Data gathered:</p>
 * <ul>
 * 		<li>Array size</li>
 * 		<li>Random integer range's minimum & maximum</li>
 * 		<li>Array is "equally" divided into 3 regions, each with
 * 			their own random integer range applied to the region.</li>
 * </ul>
 * 
 * @author Ross Studtman
 */
public class ArrayPanel extends JPanel {
	
	private static final long serialVersionUID = -4335564465920441896L;
	
	// 
	/**
	 * Listener interface to activate SortPanel's sort button, utilized by HuckFrame (the controller).
	 * 
	 * HuckFrame uses an anonymous class to implement this; pretty cool, check it out.
	 */
	public interface EnablerListener{
		public void enableSortButton(Boolean active);
	}
	
	/**
	 * enablerListener is set by HuckFrame via setEnablerListener().
	 */
	EnablerListener enablerListener;
	
	/**
	 * Need access to <code>arrayToSort</code> variable in HuckFrame so
	 * it can be set here. Not fond of the tight coupling but re-tooling
	 * the listener to accomplish this simple task isn't what I'm doing
	 * right now.
	 * 
	 * ...having done this kind of destroys the whole "EnblerListener"
	 * implementation since we now have direct access to the controller.
	 */
	HuckFrame controller;

	/**
	 *  Create buttons
	 */
	private JButton jbtReset = new JButton("reset");
	private JButton jbtGetStats = new JButton("Get Stats");

	/**
	 *  Create text fields for form
	 */
	private JTextField jtArraySize = new JTextField(10);
	private JTextField jtRegionOne_Min = new JTextField(7);
	private JTextField jtRegionOne_Max = new JTextField(7);
	private JTextField jtRegionTwo_Min = new JTextField(7);
	private JTextField jtRegionTwo_Max = new JTextField(7);
	private JTextField jtRegionThree_Min = new JTextField(7);
	private JTextField jtRegionThree_Max = new JTextField(7);

	/**
	 *  Create radio buttons for pre-selected array data
	 */
	private JRadioButton jrbTest_A = new JRadioButton("EnablerListener A");
	private JRadioButton jrbTest_B = new JRadioButton("EnablerListener B");
	private JRadioButton jrbTest_C = new JRadioButton("EnablerListener C");
	private JRadioButton jrbTest_D = new JRadioButton("EnablerListener D");
	
	/**
	 * Grouping this form's radio buttons.
	 */
	ButtonGroup radioDataGroup;
	
	/**
	 * A filter to put on JTextfields that will only accept integers
	 * as input.
	 */
	DocumentFilter digitFilter = new OnlyNumbersFilter();
	
	/**
	 * Collection of this form's JTextFields.
	 */
	JTextField[] fields = {jtArraySize, jtRegionOne_Min, jtRegionOne_Max, jtRegionTwo_Min,
			jtRegionTwo_Max, jtRegionThree_Min, jtRegionThree_Max};
	
	/**
	 * Constructor.
	 * 
	 * @param controller is the controller for the application. This produces tight coupling.
	 */
	public ArrayPanel(HuckFrame controller){
		
		this.controller = controller;
		
		// set document filter on text fields to accept only numbers.
		for(JTextField field : fields){
			((AbstractDocument)field.getDocument()).setDocumentFilter(digitFilter);
		}

		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Create array:"));
		setBackground(Color.decode("#80CCE6"));
		
		// Create panel to hold array size, reset and stats buttons.
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS ));
		
		// Create a "margin"
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		// Create invisible rigid area above array input
		leftPanel.add(Box.createRigidArea(new Dimension(10, 25)));
		
		leftPanel.add(new JLabel("Array Size:"));		
		leftPanel.add(jtArraySize);
		
		// Create invisible rigid area below array input
		leftPanel.add(Box.createRigidArea(new Dimension(10, 110)));
		
		leftPanel.add(jbtReset);
		
		// Create invisible rigid area between buttons
		leftPanel.add(Box.createRigidArea(new Dimension(10, 5)));
		
		leftPanel.add(jbtGetStats);
		
		// Add arraySizeAndClear panel to array panel.		
		add(leftPanel, BorderLayout.WEST);
		
		// Create panel to hold array's random numbers.
		JPanel rightPanel = new JPanel(new BorderLayout(0, 10));
		
		// Create a "margin"
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		// Create title header for random numbers panel.
		JLabel randomDescription = new JLabel("Random Number Ranges", JLabel.CENTER);
		randomDescription.setPreferredSize(new Dimension(200, 30));
		rightPanel.add(randomDescription, BorderLayout.NORTH);
		
		// Create panel to hold random number input.
		JPanel randomNumberInputPanel = new JPanel();
		randomNumberInputPanel.setLayout(new GridLayout(0, 3, 5, 5));
		randomNumberInputPanel.setBorder(new TitledBorder("Three equally sized partitions:"));
		
		randomNumberInputPanel.add(new JLabel(""));
		randomNumberInputPanel.add(new JLabel("Min", JLabel.CENTER));
		randomNumberInputPanel.add(new JLabel("Max", JLabel.CENTER));
		randomNumberInputPanel.add(new JLabel("Head", JLabel.RIGHT));
		randomNumberInputPanel.add(jtRegionOne_Min);
		randomNumberInputPanel.add(jtRegionOne_Max);
		randomNumberInputPanel.add(new JLabel("Middle", JLabel.RIGHT));
		randomNumberInputPanel.add(jtRegionTwo_Min);
		randomNumberInputPanel.add(jtRegionTwo_Max);
		randomNumberInputPanel.add(new JLabel("Tail", JLabel.RIGHT));
		randomNumberInputPanel.add(jtRegionThree_Min);
		randomNumberInputPanel.add(jtRegionThree_Max);
		
		// Add random number input panel to random number panel.
		rightPanel.add(randomNumberInputPanel, BorderLayout.CENTER);
		
		// Create panel for holding pre-selected data radio buttons.
		JPanel radioDataPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		radioDataPanel.setBorder(new TitledBorder("Pre-Selected Data"));
		
		// Center radio buttons
		jrbTest_A.setHorizontalAlignment(SwingConstants.CENTER);
		jrbTest_B.setHorizontalAlignment(SwingConstants.CENTER);
		jrbTest_C.setHorizontalAlignment(SwingConstants.CENTER);
		jrbTest_D.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Add radio buttons to panel.
		radioDataPanel.add(jrbTest_A);
		radioDataPanel.add(jrbTest_B);
		radioDataPanel.add(jrbTest_C);
		radioDataPanel.add(jrbTest_D);
		
		// Create radio button group
		radioDataGroup = new ButtonGroup();
		radioDataGroup.add(jrbTest_A);
		radioDataGroup.add(jrbTest_B);
		radioDataGroup.add(jrbTest_C);
		radioDataGroup.add(jrbTest_D);
		
		// Add radio data panel to random number panel.
		rightPanel.add(radioDataPanel, BorderLayout.SOUTH);
		
		// Add random numbers panel to array panel.
		add(rightPanel, BorderLayout.CENTER);
		
		// Create listener
		OneListenerFitsAll listener = new OneListenerFitsAll();
		
		// Add listener to buttons
		jbtGetStats.addActionListener(listener);
		jbtReset.addActionListener(listener);
		jrbTest_A.addActionListener(listener);
		jrbTest_B.addActionListener(listener);
		jrbTest_C.addActionListener(listener);
		jrbTest_D.addActionListener(listener);
		
	}
	
	/**
	 * Get the values from the text fields and convert them to ints.
	 * 
	 * @return int[] if all fields have values, else return null.
	 */
	public int[] getDataFromTextFields(){		
		
		if(hasValuesInAllFields()){		
			
			int[] textValues = new int[7];
			
			for(int counter = 0; counter < fields.length; counter++){
				
				textValues[counter] = Integer.parseInt(fields[counter].getText());
			}			
			
			return textValues;
			
		}else{
			
			return null;
		}
	}
	
	/**
	 * Determine if all text fields have values.
	 * 
	 * @return if any field is missing data return <code>false</code>, else
	 * return <code>true</code>.
	 */
	private boolean hasValuesInAllFields(){
		
		// Does have data?
		boolean iGotData = true;
		
		// Check if any text field is missing data
		for(JTextField field : fields){
			
			if(field.getText().trim().isEmpty()){
				iGotData = false;
			}
		}
		
		return iGotData;
	}
	
	/**
	 * De-selects radio buttons.
	 */
	public void resetRadioButtons(){
		radioDataGroup.clearSelection();
	}
	
	/**
	 * Set text fields to blank: ""
	 */
	public void resetDataFields(){
		for(JTextField field : fields){
			field.setText("");
		}
	}
	
	/**
	 * ActionListener class for handling events generated from
	 * buttons and radio buttons.
	 * 
	 * @author Ross Studtman
	 *
	 */
	class OneListenerFitsAll implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == jbtReset){
				
				enablerListener.enableSortButton(false);
				resetRadioButtons();
				resetDataFields();
				
				// clear text fields on stats panel
				controller.getStatsPanel().clearTextFields();
				
				// Clear sort panel text fields
				controller.getSortPanel().clearTextFields();
				
				// Set array to be sorted back to null;
				controller.setSortArray(null);
				
			}else if(e.getSource() == jbtGetStats){
				
				// enable SortPanel's sort button (via HuckFrame, the controller)
				enablerListener.enableSortButton(true);
				
				// get data from text fields
				int[] arrayData = getDataFromTextFields();
				
				if(arrayData != null){
					
					// Make random array & assign to controller's variable.	
					controller.setSortArray(RandomArrayMaker.getRandomArray(arrayData));
					
					// clear text fields on stats panel
					controller.getStatsPanel().clearTextFields();
					
					// Get and set random arrays statistics 
					controller.getAndSetStatistics(arrayData);
					
				}else{
					
					// Report to user some text field data is missing.
					JOptionPane.showMessageDialog(null, "Some array creation numbers are missing.");
				}
				
				
			}else if(e.getSource() == jrbTest_A){
				
				setDataFields(PreSelectedArrayData.arrayData_A);
				
			}else if(e.getSource() == jrbTest_B){
				
				setDataFields(PreSelectedArrayData.arrayData_B);
				
			}else if(e.getSource() == jrbTest_C){
				
				setDataFields(PreSelectedArrayData.arrayData_C);
				
			}else if(e.getSource() == jrbTest_D){
				
				setDataFields(PreSelectedArrayData.arrayData_D);
			}
		}		
	}
	
	private void setDataFields(int[] numbers){
		
		for(int i = 0; i < fields.length; i++){
			
			fields[i].setText("" +numbers[i]);

		}
	}	
	
	/**
	 * Sets a listener.
	 * 
	 * @param listener is a class (an anonymous class is used in
	 * HuckFrame) that implements EnablerListener interface.
	 */
	public void setEnablerListener(EnablerListener listener){
		this.enablerListener = listener;
	}
}
