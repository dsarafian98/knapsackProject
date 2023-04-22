/**
 *  Project 2: 0/1 Knapsack<br>
 *
 *  The <code>GreedyPrice</code> class provides a GreedyPrice object
 *  which will find the solution for the knapsack when taking the largest price first.
 *
 *  <br> <br>
 *  Created: <br>
 *     14 March 2018, Danielle Sarafian<br>
 *
 *  @author Danielle Sarafian
 */
public class GreedyPrice {
	Item[] items;
	double[] solution;
	int c;
	int currentC;
	int totalCUsed;
	int currentP;
	Sort sort;
	Item[] sortedPrice;

	/**
	 * Creates a new object of this class
	 * @param allItems	array of Items to find the best knapsack for
	 * @param capacity	capacity of knapsack
	 */
	public GreedyPrice(Item[] allItems, int capacity)
	{
		System.out.println("Greedy Solution: Largest Price First");

		c = capacity;
		currentC = c;
		currentP = 0;
		totalCUsed = 0;
		items = allItems;
		
		solution = new double[allItems.length];
		sort = new Sort();
		sortedPrice = sort.heapSort(items);
		
		/*System.out.println("Item Weight Value");
		for (int i = 0; i < sortedPrice.length; i++)
		{
			System.out.println(i + "\t" + sortedPrice[i].getWeight() + "\t" + sortedPrice[i].getPrice());
		}*/

		solve();
	}

	/**
	 * Solves this knapsack problem
	 */
	public void solve()
	{	
		// go through list of sorted prices from the highest price first
		// INVARIANT: the space in the knapsack is never < 0
		//assert(spaceInvariant(currentC));
		for (int i = sortedPrice.length-1; i >= 0; i--)
		{
			// check if the current capacity is greater than 0 and less than the maximum capacity
			// check that the item will fit in the knapsack
			if (currentC >= 0 && currentC <= c && (currentC - (int)(sortedPrice[i].getWeight()) >=0))
			{
				// take the item
				solution[i] = 1;
				
				// update price and capacity counters
				currentC = currentC - (int)(sortedPrice[i].getWeight());
				currentP = currentP + (int)(sortedPrice[i].getPrice());
				totalCUsed = totalCUsed + (int)(sortedPrice[i].getWeight());
			}
			else
			{
				// don't take the item
				solution[i] = 0;
			}
			//assert(spaceInvariant(currentC));
		}
		//assert(spaceInvariant(currentC));
		printSolution();

	}

	/**
	 * Prints this knapsack solution.
	 */
	private void printSolution()
	{
		System.out.println("Item Weight Value");
		for (int i = 0; i < sortedPrice.length; i++)
		{
			if (solution[i] == 1)
			{
				System.out.println(i + "\t" + sortedPrice[i].getWeight() + "\t" + sortedPrice[i].getPrice());
			}
		}
		System.out.println("Total Price: " + currentP);
		System.out.println("Capacity Used: " + totalCUsed);
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
