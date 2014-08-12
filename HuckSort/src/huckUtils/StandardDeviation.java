/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package huckUtils;

/**
 * <h1>StandardDeviation can perform the following calculations on Integer arrays:</h1>
 * <ul>
 * 		<li>Get sum of array.</li>
 * 		<li>Get average of array.</li>
 * 		<li>Get standard deviation of array.</li>
 * 		<li>Get variance of array.</li>
 * </ul>
 * 		
 * @author Ross Studtman
 */
public class StandardDeviation {

    /**
     * Calculate total of an array of integers.
     * @param scores is the array of integers.
     * @return total of array of integers.
     */
    public static int getSum(int[] array){
        int sum = 0;
        
        for(int iterator = 0; iterator < array.length; iterator++){
            sum += array[iterator];
        }
        return sum;
    }
    
    /**
     * Calculate average of an array of integers.
     * 
     * @param scores is the array of integers.
     * 
     * @return average of array of integers.
     */
    public static double getAverage(int[] array){
    	//return (double)getSum(array) / array.length;
    	
        double sum = getSum(array);
        return sum / array.length;
    }
    
    /**
     * Calculate standard deviation from an array of integers.
     * 
     * @param scores is the array of integers.
     * 
     * @return standard deviation of scores.
     */
    public static double getStandardDeviation(int[] array){
        double variance = getVariance(array);
        double standardDeviation = Math.sqrt(variance);
        return standardDeviation;        
    }
    
    /**
     * Calculate the variance of an array of integers.
     * 
     * @param scores is the array of integers.
     * 
     * @return variance of integers.
     * 
     * NOTE: do not subtract 1 from the denominator, as would do if working with a sample set.
     */
    public static double getVariance(int[] array){
        double average = getAverage(array);
        int denominator = array.length;
        double variance = getSquaredDifference(array, average) / denominator;
        return variance;
    }
    
    /**
     * Calculate the squared difference of an array of integers.
     * 
     * @param scores is the array of integers.
     * @param average is the average of the array of integers and has been
     * pre-calculated.
     * 
     * @return the squared difference of the provided integers.
     */
    private static double getSquaredDifference(int[] array, double average){
        final int SQUARE_ROOT = 2;
        double squaredDifference = 0.0;
        
        for(int iterator = 0; iterator < array.length; iterator++){
            squaredDifference += Math.pow( (array[iterator]- average), SQUARE_ROOT);
        }
        
        return squaredDifference;
    }	
}
