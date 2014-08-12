/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <h1>StatisticPanel displays statistical information about the
 * array to be sorted by the sorting algorithms.</h1>
 * 
 * @see huckUtils.StandardDeviation
 * 
 * @author Ross Studtman 
 */
public class StatisticsPanel extends JPanel {
	
	private static final long serialVersionUID = 5696988995042648241L;

	// Create panel for holding text fields.
	JPanel statisticsFieldsPanel;

	// Create text fields for holding statistical results
	private JTextField jtMean = new JTextField(7);
	private JTextField jtVariance = new JTextField(7);
	private JTextField jtStandardDeviation = new JTextField(7);
	private JTextField jtCoefficientOfVariance = new JTextField(7);
	
	/**
	 * Constructor.
	 */
	public StatisticsPanel() {
		
		setLayout(new BorderLayout(0, 15));		
		
		// Create panel for stats fields.
		statisticsFieldsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		statisticsFieldsPanel.add(new JLabel("mean: ", JLabel.RIGHT));
		statisticsFieldsPanel.add(jtMean);
		statisticsFieldsPanel.add(new JLabel("variance: ", JLabel.RIGHT));
		statisticsFieldsPanel.add(jtVariance);
		statisticsFieldsPanel.add(new JLabel("standard deviation: ", JLabel.RIGHT));
		statisticsFieldsPanel.add(jtStandardDeviation);
		statisticsFieldsPanel.add(new JLabel("coefficient of variance: ", JLabel.RIGHT));
		statisticsFieldsPanel.add(jtCoefficientOfVariance);
		
		// Add statsField panel
		add(statisticsFieldsPanel, BorderLayout.CENTER);		
	}

	/**
	 * Sets the text of the text box.
	 * @param mean is the text to set.
	 */
	public void setMean(String mean) {
		jtMean.setText(mean);
	}

	/**
	 * Sets the text of the text box.
	 * @param variance is the text to set.
	 */
	public void setVariance(String variance) {
		jtVariance.setText(variance);
	}

	/**
	 * Sets the text of the text box.
	 * @param std is the text to set.
	 */
	public void setSTD(String std) {
		jtStandardDeviation.setText(std);
	}

	/**
	 * Sets the text of the text box.
	 * @param cov is the text to set.
	 */
	public void setCoV(String cov) {
		jtCoefficientOfVariance.setText(cov);
	}
	
	/**
	 * Clear text fields.
	 */
	public void clearTextFields(){
		
		// Get all components in the container
		Component[] comps = statisticsFieldsPanel.getComponents();
		
		// Loop over components
		for(int counter = 0; counter < comps.length; counter++){
			
			// Manipulate only JTextFields
			if(comps[counter] instanceof JTextField){
				
				// Set text to empty
				((JTextField) comps[counter]).setText("");
			}
		}
	}
	
}
