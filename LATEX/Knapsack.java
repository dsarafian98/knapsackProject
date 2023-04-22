// Import statements go here.  For example,
// import java.awt.Color;
// import java.util.ArrayList;
// import java.util.Random;
import java.util.Arrays;
import java.util.Random;

/**
 *  Programming Project 2: 0/1 Knapsack<br>
 *
 *  This class represents a 0/1 knapsack.
 *
 *  <br> <br>
 *  Created: <br>
 *     8 March 2018, Danielle Sarafian<br>
 *  Modifications: <br>
 *     12 March 2018, Danielle Sarafian, randomValues<br>
 *
 *  @author Danielle Sarafian
 */
public class Knapsack
{
	// State: instance variables and shared class variables go here.
	int n;
	Item items[];
	int maxP;
	int maxW;
	int c;

	// Constructors

	/**
	 * Constructs a new object of this class.
	 *      @param   size 		    size of array
	 *      @param	 highestPrice	the highest price possible to generate
	 *      @param	 highestWeight	the highest weight possible to generate
	 *      @param	 capacity		the capacity of the knapsack
	 */
	public Knapsack (int size, int highestPrice, int highestWeight, int capacity)
	{
		n = size;
		items = new Item[size];
		maxP = highestPrice;
		maxW = highestWeight;
		c = capacity;
	}

	// Methods

	/**
	 * Generate random price and weight values
	 * 
	 * @return array of Item with random price and weight values
	 */
	public Item[] randomValues()
	{
		Random generator = new Random();
		int randPrice;
		int randWeight;
		int i = 0;

		while (i <n)
		{
			randPrice = generator.nextInt(maxP) + 1;
			randWeight = generator.nextInt(maxW) + 1;
			if (randWeight <= c)
			{
				items[i] = new Item(randWeight, randPrice);
				i++;
			}
		}
		return items;
	}	

}
