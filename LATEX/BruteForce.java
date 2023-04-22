/**
 *  Programming Project 2: 0/1 Knapsack<br>
 *
 *  This class represents a brute force solution to the 0/1 knapsack.
 *
 *  <br> <br>
 *  Created: <br>
 *     8 March 2018, Danielle Sarafian<br>
 *  Modifications: <br>
 *     12 March 2018, Danielle Sarafian, greedyDensity<br>
 *     14 March 2018, Danielle Sarafian, restructure to use Items
 *     									 array in constructor rather 
 *     									than 2 arrays for price and weight<br>
 *
 *  @author Danielle Sarafian with assistance from Nora Wichmann and GitHub, Inc.
 */

public class BruteForce {
	private Item items[];
	private int numItems;
	private int capacity;
	private double best;
	private boolean solution[];
	private boolean current[];
	private double capacityFilled;


	/**
	 * Constructs an object of this class
	 * 		@param	allItems	an array of Item objects to find the solution for
	 *		@param	c			the capacity
	 */
	public BruteForce(Item[] allItems, int c)
	{
		System.out.println("Brute Force Solution");

		// number of items
		numItems = allItems.length;

		// capacity of knapsack
		capacity = c;

		capacityFilled = 0;

		items = allItems;

		// populate items array
		/*System.out.println("Item Weight Value");
		for (int i = 0; i < numItems; i++)
		{
			System.out.println(i + "\t" + items[i].getWeight() + "\t" + items[i].getPrice());
		}*/
		best = Integer.MIN_VALUE;
		solution = new boolean[numItems];
		current = new boolean[numItems];

		solve(numItems-1);
		printSolution();
	}	

	/**
	 * Solves this solution to the knapsack problem
	 * 		@param num	number of items to solve for
	 */
	private void solve(int num)
	{
		// check that there are items to solve for
		if (num < 0)
		{
			double weight = 0;
			double price = 0;
			double prevWeight = 0;
			double prevPrice = 0;

			// go through all items
			// INVARIANT: if the item is selected, weight-prevWeight = weight of the item
			// vacuously true
			for (int i = 0; i < numItems; i++)
			{
				// check if the current item is being taken
				if (current[i])
				{
					// increase weight and price counters
					prevWeight = weight;
					weight = weight + items[i].getWeight();
					prevPrice = price;
					price = price + items[i].getPrice();
				}
				//assert(solveInvariant(prevWeight, prevPrice, weight, price, i));
			}
			//assert(solveInvariant(prevWeight, prevPrice, weight, price, i));


			// check that the item will fit in the knapsack and that the price is better 
			// than the current best price
			if ((weight <= capacity) && (price > best))
			{ 
				// update info
				best = price;
				capacityFilled = weight;

				// copy current best solution
				copySolution();
			}
			return;
		}

		// check when this item is true
		current[num] = true;
		solve(num-1);

		// check when this item is false
		current[num] = false;
		solve(num-1);
	}

	/**
	 * Copies the current best solution into the solution array
	 */
	private void copySolution()
	{
		for (int i = 0; i<numItems; i++)
		{
			solution[i] = current[i];
		}
	}


	/**
	 * Prints the solution
	 */
	private void printSolution()
	{
		System.out.println(" Item Weight Value");
		for (int i = 0; i < numItems; i++)
		{
			if (solution[i])
			{
				System.out.println(i + "\t" + (int) items[i].getWeight() + "\t" + (int) items[i].getPrice());
			}
		}
		System.out.println("Total Price: " + best);
		System.out.println("Capacity Used: " + capacityFilled);
		System.out.println("");
	}

	private boolean solveInvariant(double prevWeight, double prevPrice, double weight, double price, int index)
	{
		if (current[index])
		{
			if ((items[index].getWeight() != weight-prevWeight) && (items[index].getPrice() != price-prevPrice))
			{
				return false;
			}

		}
		return true;
	}
}
