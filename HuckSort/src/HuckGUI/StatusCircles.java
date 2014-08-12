/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.beans.Transient;

import javax.swing.JPanel;

/**
 * <h1>StatusCircles draws an icon that is used visually aid the user
 * in identifying which state a sort algorithm is currently in</h1>
 *  
 * @see SortStatus 
 * 
 * @author Ross Studtman
 *
 */
public class StatusCircles extends JPanel{

	private static final long serialVersionUID = 5299110784815632876L;

	/**
	 * Enum for current status of sort method
	 */
	private SortStatus sortStatus;
	
	/**
	 * Status circle
	 */
	private Ellipse2D.Double statusCircle = new Ellipse2D.Double(2, 2, 25, 25);
	
	/**
	 * Constructor
	 */
	public StatusCircles(){
		sortStatus = SortStatus.ready;
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		//*******************************************
		// 	Technique 1: (from an undocumented video)
		//*******************************************
		
		// Cast Graphics to Graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		// Turn on anti aliasing
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Set background
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
//		// Fill status circle with silver color.
//		g2.setColor(new Color(192,192,192));
//		g2.fill(statusCircle);
		
		// Fill status circle with color based on status field
		switch(sortStatus){
		case ready:
			// silver
			g2.setColor(new Color(192, 192, 192));
			g2.fill(statusCircle);
			break;
		case running:
			// green
			g2.setColor(new Color(0,100,0));
			g2.fill(statusCircle);
			break;			
		case finished:
			// red
			g2.setColor(new Color(255,0,0));
			g2.fill(statusCircle);
			break;
		}
				
		
		//***********************************************
		// 	Technique 2: (from undocumented book [Liang?]
		//***********************************************
		
//		//super.paintComponent(g);
//		
//		int width = getWidth();
//		int height = getHeight();
////		int width = 15;
////		int height = 15;
//		
//		switch(sortStatus){
//		case ready:
//			// silver
//			g.setColor(new Color(192, 192, 192));
//			g.fillOval(0, 0, width, height);
//			break;
//		case running:
//			// green
//			g.setColor(new Color(0,100,0));
//			g.fillOval(0, 0, width, height);
//			break;			
//		case finished:
//			// red
//			g.setColor(new Color(255,0,0));
//			g.fillOval(0, 0, width, height);
//			break;
//		}
	}	
	

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension(30, 30);				
	}


	/**
	 * <h1>setStatus assigns an Enum to the <code>sortStatus</code>
	 * variable.</h1>
	 * 
	 * @see SortStatus
	 * 
	 * @param status is an Enum representing three states:
	 * 		ready, running, and finished.
	 */
	public void setStatus(SortStatus status) {
		this.sortStatus = status;
		
		repaint();
	}	
}
