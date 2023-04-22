// import java.awt.Color;
// import java.util.ArrayList;
// import java.util.Random;
/**
 *  Programming Project 2: 0/1 Knapsack<br>
 *
 *  This class represents the greedy solution using
 *  price density for a 0/1 Knapsack.
 *
 *  <br> <br>
 *  Created: <br>
 *     8 March 2018, Danielle Sarafian<br>
 *  Modifications: <br>
 *     12 March 2018, Danielle Sarafian, solve<br>
 *     13 March 2018, Danielle Sarafian, printSolution<br>
 *     14 March 2018, Danielle Sarafian, make methods work for arrays of Item objects rather than arrays of doubles<br>
 *
 *  @author Danielle Sarafian with assistance from Nora Wichmann and GitHub, Inc.
 */
public class GreedyDensity
{
	// State: instance variables and shared class variables go here.
	private int c;
	private Sort sort;
	private double[] finalKnapsack;
	private double[] priceDensity;
	private Item[] items;
	private int capacityFilled;
	private int finalPrice;

	// Constructors


	/**
	 * Constructs a new object of this class.
	 *      @param   paramArray    array of Item to find the solution for
	 *      @param	 capacity	   the capacity of the knapsack
	 */
	public GreedyDensity(Item[] paramArray, int capacity)
	{
		System.out.println("Greedy Solution: Price Density Array");

		c = capacity;
		sort = new Sort();
		priceDensity = new double[paramArray.length];
		items = paramArray;
		capacityFilled = 0;
		finalPrice = 0;
		solve();
	}

	// Methods

	/**
	 * Greedy algorithm using price density array
	 * 
	 * @return array of solutions 1 if the item was taken, 0 if it wasn't
	 */
	public void solve()
	{
		// calculate price densities and fill array
		//System.out.println("Original Array");
		//System.out.println("Item Weight Value");
		for (int i = 0; i < items.length; i++)
		{
			double temp = items[i].getPriceDensity();
			priceDensity[i] = temp;
			//System.out.println(i + "\t" + items[i].getWeight() + "\t" + items[i].getPrice() );
		}

		// create array to represent final knapsack
		finalKnapsack = new double[items.length];

		// sort the price densities
		priceDensity = sort.reverseHeapSort(priceDensity);
		
		//System.out.println("Sorted by Price Density");
		//System.out.println("Price Densities");
		/*for (int i = 0; i < priceDensity.length; i++)
		{
			System.out.println(priceDensity[i]);
		}*/

		// check if each item fits in the knapsack
		// INVARIANT: the space in the knapsack is never < 0
		//assert(spaceInvariant(c));
		for (int index = 0; index < priceDensity.length; index++)
		{
			if (items[index].getWeight() <= c)
			{
				finalKnapsack[index] = 1;
				c = c - (int) items[index].getWeight();
				capacityFilled = capacityFilled + (int)items[index].getWeight();
				finalPrice = finalPrice + (int)items[index].getPrice();
			}
			//assert(spaceInvariant(c));
		}
		//assert(spaceInvariant(c));
		
		printSolution();
	}	

	/**
	 * Prints the solution to this problem
	 */
	private void printSolution()
	{
		System.out.println("  Item Weight Value");
		for (int i = 0; i < items.length; i++)
		{
			if (finalKnapsack[i] == 1.0)
			{
				System.out.println(i + "\t" + items[i].getWeight() + "\t" + items[i].getPrice());
			}

		}		
		System.out.println("Total Price: " + finalPrice);
		System.out.println("Capacity Used: " + capacityFilled);
		System.out.println("");
	}
	
	/**
	 * checks that c >=0
	 * @param c	capacity
	 * @return false if invariant fails, otherwise true
	 */
	private boolean spaceInvariant(int c)
	{
		if (c<0)
		{
			return false;
		}
		return true;
	}
}
