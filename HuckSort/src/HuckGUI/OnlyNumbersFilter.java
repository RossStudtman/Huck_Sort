/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package HuckGUI;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * <h1>OnlyNumberFilter ensures only digits are inserted into
 * text fields</h1>. 
 * 
 * @see DocumentFilter
 * 
 * @author Ross Studtman 
 */
public class OnlyNumbersFilter extends DocumentFilter {

	@Override
	public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
				
		StringBuffer buffer = new StringBuffer(str);
		
		for(int counter = buffer.length() - 1; counter >= 0; counter--){
			
			char ch = buffer.charAt(counter);
			
			if(!Character.isDigit(ch)){
				buffer.deleteCharAt(counter);
			}
		}
		super.insertString(fb, offs, buffer.toString(), a);

	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet a)
			throws BadLocationException {
		
		if(length > 0){
			fb.remove(offset, length);
		}
		
		insertString(fb, offset, string, a);
	}

}
