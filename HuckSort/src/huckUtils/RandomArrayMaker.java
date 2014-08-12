/**
 * Author: Ross Studtman
 * 
 * July, 2013.
 */

package huckUtils;

/**
 * <h1>RandomArrayMaker creates an array filled with random integers.</h1>
 * 
 * <p>The array is partitioned into three equal regions, each being able
 * to contain its own range of random numbers. The ability to
 * fill an array with different ranges at its head, middle,
 * and tail can stress some sort methods.</p>
 * 
 * 
 * <p>For example, a user can define an array containing 100,000 elements where the
 * array's character is:</p>
 * <ul>
 * 		<li>range 1:  contains numbers in the range of 1 to 100</li>
 * 		<li>range 2:  contains numbers in the range of 5 to 60</li>
 * 		<li>range 3:  contains numbers in the range of 1 to 10</li>
 * </ul>
 */
public class RandomArrayMaker {

	/**
	 * Number of ranges within the array.
	 */
	public static final int NUMBER_OF_RANGES = 3;
	
	/**
	 * The first range in the array.
	 */
	public static final int RANGE_1 = 1;
	
	/**
	 * The second range in the array.
	 */
	public static final int RANGE_2 = 2;
	
	/*
	 * ! The third range in the array need NOT be defined,
	 * for it is the last range and consumes the rest of the
	 * array starting where RANGE_2 ends.
	 */

	/**
	 * Used in range calculation for random numbers. Makes the range inclusive
	 * of the "MAX" number.
	 */
	private static final int INCLUSIVE = 1;

	/**
	 * Method getArray creates a custom array for the user.
	 * 
	 * @param array is an integer array where each element
	 * defines the character of the array of randoms to
	 * be returned:
	 * 
	 * 		array[0] --> the array size
	 * 		array[1] --> the minimum int value of range one.
	 * 		array[2] --> the maximum int value of range one.
	 * 		array[3] --> the minimum int value of range two.
	 * 		array[4] --> the maximum int value of range two.
	 * 		array[5] --> the minimum int value of range three.
	 * 		array[6] --> the maximum int value of range three.
	 * 
	 * @return an int array filled with random integers.
	 */
	public static int[] getRandomArray(int[] array) {
		
		int arraySize = array[0];
		int r1min = array[1];
		int r1max = array[2];
		int r2min = array[3];
		int r2max = array[4];
		int r3min = array[5];
		int r3max = array[6];

		// size of each range within the array
		int rangeSize = arraySize / NUMBER_OF_RANGES;

		// create array
		int[] intArray = new int[arraySize];

		// length of new array
		int arrayLength = intArray.length;

		// fill array with values.
		for (int counter = 0; counter < arrayLength; counter++) {

			// java random guide:
			// http://stackoverflow.com/questions/363681/generating-random-number-in-a-range-with-java

			// fill region 1 with user's random number range for region 1.
			if (counter < rangeSize * RANGE_1) {
				
				int random = r1min + (int) (Math.random() * ((r1max - r1min) + INCLUSIVE));
				intArray[counter] = random;

			// fill region 2 with user's random number range for region 2.
			} else if (counter < rangeSize * RANGE_2) {
				
				int random = r2min + (int) (Math.random() * ((r2max - r2min) + INCLUSIVE));
				intArray[counter] = random;

			// fill region 3 with user's random number range for region 3.
			} else {
				
				int random = r3min + (int) (Math.random() * ((r3max - r3min) + INCLUSIVE));
				intArray[counter] = random;
			}

		}

		// return new array
		return intArray;
	}
}

/*
 * Used to test created array
 * 
 * 
 * 		for (int counter = 0; counter < intArray.length; counter++) {
 * 
 * 			if (iterate % 10 == 0) { 
 * 				System.out.print("\n");
 * 			}
 * 
 * 			System.out.print(intArray[counter] + ", ");
 * 		}
 * 
 * 
 */
