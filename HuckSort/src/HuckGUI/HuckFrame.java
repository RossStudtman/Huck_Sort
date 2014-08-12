/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import huckUtils.HuckConstants;
import huckUtils.RandomArrayMaker;
import huckUtils.StandardDeviation;
import interfaces.SortListener;
import interfaces.RunSortsListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import HuckGUI.ArrayPanel.EnablerListener;
import sorts.*;

/**
 * <h1>HuckFrame is the controller for the application.</h1>
 * <p>HuckFrame is probably too large for its britches.</p>
 * <p>HuckFrame constructs the GUI and sort classes used for testing.</p>
 * 
 * <p> NOTE: A question was posted to StackOverflow (reference below) regarding why
 * the sort times run faster on a second press of the "Sort" button. The answer
 * by Jon Skeet is illuminating (JIT issues, measuring in nanoTime) and portions
 * of that answer should be implemented but at this point, 7/21/2013, have not.</p>
 * 
 * 
 * <p> The issue right now is to get out the beta version of this program quicker
 * rather than later.</p>
 * <p> StackOverflow question reference: to problems regarding recording time; and
 * JIT making optimizations on subsequent use of the program (ie, the JIT
 * optimizes code):</p>
 * <ul><li>http://stackoverflow.com/questions/17774073/understanding-why-how-javas-native
 * -sorting-of-an-int-array-is-optimized-on-succ</li></ul>
 * 
 * @author Ross Studtman
 * 
 */
public class HuckFrame extends JFrame implements RunSortsListener, SortListener {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The array sort algorithms will use to test their sort speed.
	 */
	private int[] arrayToSort;
	
	/**
	 * GUI components.
	 */
	private HeaderPanel headerPanel;
	private ArrayPanel arrayPanel;
	private StatisticsPanel statsPanel;
	private SortPanel sortPanel;
	
	/**
	 * Sort classes.
	 */
	private ClassicInsertionSort insertion;
	private HuckSort_V1 huck1;
	private HuckSort_V2 huck2;
	private NativeJavaSort nativeSort;

	/**
	 * Constructor.
	 */
	public HuckFrame() {
		super("Huck Sort");
		
		// Set frame properties.
		setSize(600, 1020);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		// Make new Panels
		headerPanel = new HeaderPanel();	
		arrayPanel = new ArrayPanel(this);
		statsPanel = new StatisticsPanel();
		sortPanel = new SortPanel();
		
		// Anonymous class implementation of interface: enables sort button.	
		arrayPanel.setEnablerListener(new EnablerListener(){

			@Override
			public void enableSortButton(Boolean enabled) {
				
				if(enabled){
					sortPanel.jbtRunSorts.setEnabled(true); 			
				}else{
					sortPanel.jbtRunSorts.setEnabled(false) ;	
				}
			}			
		});
		
		// Make a larger panel to hold SortPanel (acts as a border)
		JPanel largerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		largerPanel.setBackground(new Color(240,120,24));
		
		// Add sortPanel to largerPanel
		largerPanel.add(sortPanel);
				
		// Add panels to frame
		add(headerPanel);
		add(arrayPanel);
		add(statsPanel);
		add(largerPanel);	
		
		// Set sort panel listener
		sortPanel.setRunSortsListener(this);
		
		// Instantiate sorts.
		insertion = new ClassicInsertionSort();
		huck1 = new HuckSort_V1();
		huck2 = new HuckSort_V2();
		nativeSort = new NativeJavaSort(); 
		
		// Set listeners on sort classes
		insertion.setSortListener(this);
		huck1.setSortListener(this);
		huck2.setSortListener(this);
		nativeSort.setSortListener(this);
	}
	
	/**
	 * <h1>Organizes sorting tasks.</h1>
	 * 
	 * <p>Each of the sorts to be tested are tested in their own SwingWorker thread.</p>
	 * <p>This method invokes the first sort method and the other sort methods are
	 * daisy-chained together via the SwingWorker's <code>done()</code> method.</p>
	 * 
	 * <p>Future optimization notes:</p>
	 * <ul>
	 * 		<li>It was suggested via StackOverflow that a better approach could be
	 * 			used; particularly the comments of "trashgod" and "kleopatra" of the 
	 * 			selected answer. </li>
	 * 		<li>Ross Studtman's StackOverflow question regarding this:
	 * 			http://stackoverflow.com/questions/17662380/dynamically-change-color-of-custom-graphic 
	 * 			</li>
	 * 		<li>However, the it <b>appears</b> like the general purpose of the 
	 * 			program is working so these improvements are for a later time.</li>
	 * </ul>
	 */
	public void runSorts() {
		
		// Clear text from text fields.
		sortPanel.clearTextFields();
		
		// Repaint status icons
		resetStatusIcons();
		
		// Perform sorts (They are daisy-chained via SwingWorker thread's done() method.)
		runInsertionSort(arrayToSort);		
	}

	/**
	 * Obtain the statistical measurements of the random array, then update
	 * GUI.
	 * 
	 * @param a the random array in need of analysis.
	 */
	public void getAndSetStatistics(int[] a) {		
		
		// Get statistical measurements
		double mean = StandardDeviation.getAverage(a);
		double variance = StandardDeviation.getVariance(a);
		double std = StandardDeviation.getStandardDeviation(a);
		double cov = mean / std;
		
		// Update GUI with statistical information
		statsPanel.setMean(String.format("%.4f", mean));
		statsPanel.setVariance(String.format("%.4f", variance));
		statsPanel.setSTD(String.format("%.4f", std));
		statsPanel.setCoV(String.format("%.4f", cov));
	}
	
	/**
	 * <h1>Run a classic insertion sort.</h1>
	 * 
	 * <p>This method uses a SwingWorker thread. The <code>done()</code> method
	 * invokes the next sort to be executed.</p>
	 * 
	 * @param mArray is an int[] to be cloned. A <code>clone()</code> of 
	 * this array is what the sort algorithm is applied to.
	 */
	public void runInsertionSort(final int[] mArray){

		// change color of status icon to reflect method is starting
		sortPanel.insertionSortStatusCircle.setStatus(SortStatus.running);

		// Run sort in its own thread.
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// make a clone of the array that was sent
				int[] clone = mArray.clone();
				
				insertion.runClassicInsertionSort(clone);

				return null;
			}

			@Override
			protected void done() {				
				// change color of status icon to reflect method is finished.
				sortPanel.insertionSortStatusCircle.setStatus(SortStatus.finished);
				
				// Call next sort to run
				runHuck_V1(mArray);
			}
			
		};		
		worker.execute();
	}
	
	/**
	 * <h1>Run Huck sort version one.</h1>
	 * 
	 * <p>This method uses a SwingWorker thread. The <code>done()</code> method
	 * invokes the next sort to be executed.</p>
	 * 
	 * @param mArray is an int[] to be cloned. A <code>clone()</code> of 
	 * this array is what the sort algorithm is applied to.
	 */
	public void runHuck_V1(final int[] mArray){
		// change color of status icon to reflect method is starting
		sortPanel.statusHuck_V1.setStatus(SortStatus.running);

		// Run sort in its own thread.
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// make a clone of the array that was sent
				int[] clone = mArray.clone();
				
				// Run Huck sort, version 1.
				huck1.runHuckedInsertionSort(clone);

				return null;
			}

			@Override
			protected void done() {				
				// change color of status icon to reflect method is finished.
				sortPanel.statusHuck_V1.setStatus(SortStatus.finished);
				
				// Call next sort to run
				runHuck_V2(mArray);
			}
			
		};		
		worker.execute();
	}
	
	/**
	 * <h1>Run Huck sort version two.</h1>
	 * 
	 * <p>This method uses a SwingWorker thread. The <code>done()</code> method
	 * invokes the next sort to be executed.</p>
	 * 
	 * @param mArray is an int[] to be cloned. A <code>clone()</code> of 
	 * this array is what the sort algorithm is applied to.
	 */
	public void runHuck_V2(final int[] mArray){
		// change color of status icon to reflect method is starting
		sortPanel.statusHuck_V2.setStatus(SortStatus.running);

		// Run sort in its own thread.
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// make a clone of the array that was sent
				int[] clone = mArray.clone();
				
				// Run Huck sort, version 2
				huck2.runHuckedInsertionSortV2(clone);

				return null;
			}

			@Override
			protected void done() {				
				// change color of status icon to reflect method is finished.
				sortPanel.statusHuck_V2.setStatus(SortStatus.finished);
				
				// Call next sort to run
				runNativeSort(mArray);
			}
			
		};		
		worker.execute();
	}
	
	/**
	 * <h1>Run Java's native sort.</h1>
	 * 
	 * <p>This method uses a SwingWorker thread. The <code>done()</code> method
	 * invokes the next sort to be executed.</p>
	 * 
	 * @param mArray is an int[] to be cloned. A <code>clone()</code> of 
	 * this array is what the sort algorithm is applied to.
	 */
	public void runNativeSort(final int[] mArray){
		// change color of status icon to reflect method is starting
		sortPanel.statusNativeJavaSort.setStatus(SortStatus.running);
		
		// Run sort in its own thread.
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// make a clone of the array that was sent
				int[] clone = mArray.clone();
				
				// Run native Java sort
				nativeSort.runJavasSort(clone);

				return null;
			}

			@Override
			protected void done() {				
				// change color of status icon to reflect method is finished.
				sortPanel.statusNativeJavaSort.setStatus(SortStatus.finished);
				
				// Call next sort to run
				// ...no more sorts to run. Daisy-chain finished.
			}
			
		};		
		worker.execute();
	}
	
	/**
	 * Reset the status icons (graphics) to color indicating
	 * the sort methods are ready to be run.
	 */
	public void resetStatusIcons(){
		sortPanel.insertionSortStatusCircle.setStatus(SortStatus.ready);
		sortPanel.statusHuck_V1.setStatus(SortStatus.ready);
		sortPanel.statusHuck_V2.setStatus(SortStatus.ready);
		sortPanel.statusNativeJavaSort.setStatus(SortStatus.ready);		
	}


	/**
	 * <h1>receiveSortData is the handler method for SortListener interface.</h1>
	 * 
	 * @see SortListener.java
	 * 
	 * @param sortData is a Map that contains relevant information gathered
	 * by each sorting algorithm.
	 */
	@Override
	public void receiveSortData(Map<Enum, Integer> sortData) {
		
		int sortId = sortData.get(HuckConstants.C.SORT_ID);
		
		switch(sortId){
		case HuckConstants.INSERTION:			
			int insertion_millis = sortData.get(HuckConstants.C.TOTAL_MILLIS);
			
			sortPanel.jtInsertionSort_millis.setText(String.valueOf(insertion_millis));			
			break;
			
		case HuckConstants.HUCK_V1:
			int huckV1_total_millis = sortData.get(HuckConstants.C.TOTAL_MILLIS);
			int huckV1_huck_millis = sortData.get(HuckConstants.C.HUCK_MILLIS);
			int huckV1_huckedTurtles = sortData.get(HuckConstants.C.HUCKED_TURTLES);
			
			sortPanel.jtHuckSort_V1_millis.setText(String.valueOf(huckV1_total_millis));
			sortPanel.jtHuckSort_V1_huckMillis.setText(String.valueOf(huckV1_huck_millis));
			sortPanel.jtHuckSort_V1_huckedTurtles.setText(String.valueOf(huckV1_huckedTurtles));
			break;
			
		case HuckConstants.HUCK_V2:
			int huckV2_total_millis = sortData.get(HuckConstants.C.TOTAL_MILLIS);
			int huckV2_huck_millis = sortData.get(HuckConstants.C.HUCK_MILLIS);
			int huckV2_huckedTurtles = sortData.get(HuckConstants.C.HUCKED_TURTLES);
			
			sortPanel.jtHuckSort_V2_millis.setText(String.valueOf(huckV2_total_millis));
			sortPanel.jtHuckSort_V2_huckMillis.setText(String.valueOf(huckV2_huck_millis));
			sortPanel.jtHuckSort_V2_huckedTurtles.setText(String.valueOf(huckV2_huckedTurtles));
			break;
			
		case HuckConstants.NATIVE:
			int native_millis = sortData.get(HuckConstants.C.TOTAL_MILLIS);
			
			sortPanel.jtNativeJavaSort_millis.setText(String.valueOf(native_millis));	
			break;
		}		
	}
	
	/**
	 * <h1>setSortArray assigns an array to <code>arrayToSort</code>.</h1>
	 * 
	 * @param sortArray is the integer array filled with random integers. 
	 */
	public void setSortArray(int[] sortArray){
		this.arrayToSort = sortArray;
	}
	
	/**
	 * Obtain a StatisticsPanel object.
	 * @return the StatisticsPanel object.
	 */
	public StatisticsPanel getStatsPanel(){
		return statsPanel;
	}
	
	/**
	 * Obtain a SortPanel object.
	 * @return the SortPanel object.
	 */
	public SortPanel getSortPanel(){
		return sortPanel;
	}	
}
