/***
 * 
 *  Programming Project 2: 0/1 Knapsack<br>
 *
 *  This class represents dynamic programming solution based on 
 *  Sahni's code.
 *
 *  <br> <br>
 *  Created: <br>
 *     13 March 2018, Danielle Sarafian<br>
 *  Modifications: <br>
 *     14 March 2018, Danielle Sarafian, fix off-by-one error<br>
 *
 *  @author Danielle Sarafian, with assistance from Nora Wichmann
 */
public class DP2
{

	public DP2()
	{
		System.out.println("Dynamic Programming");
	}


	// Methods
	/**
	 *  This method finds the 2D array, array, to solve the knapsack problem iteratively
	 *    @param    profit array of object values
	 *    @param    weight array of object weights
	 *    @param    c capacity of  knapsack
	 *    @param    array 2D array of profits and weights of eligible items
	 **/
	public void solve(int[] profit, int[]weight, int c, int[][] array)
	{
		int numObj = profit.length-1;

		//initialize array[numObj][]
		int yMax = Math.min(weight[numObj]-1, c);

		for(int y = 0; y <= yMax; y++)
		{
			array[numObj][y]=0;
		}

		int y1 = weight[numObj];
		for(; y1 <= c; y1++)
		{
			array[numObj][y1]=profit[numObj];
			//assert(invariantEquality(array, profit, y1, numObj));
		}
		//assert(invariantEquality(array, profit, y1, numObj));


		//compute array[i][y], 1<i<numObj
		for(int i = numObj-1; i > 0; i--)
		{
			yMax = Math.min(weight[i]-1, c);
			for(int y = 0; y <= yMax; y++)
			{
				array[i][y] = array[i+1][y];
			}
			for(int y = weight[i]; y <= c; y++)
			{
				array[i][y] = Math.max(array[i+1][y], array[i+1][y-weight[i]]+profit[i]);
			}
			//assert(yMaxInvariant(yMax, c));
		}
		//assert(yMaxInvariant(yMax, c));


		array[1][c] = array[2][c];
		if(c >= weight[1])
		{
			array[1][c] = Math.max(array[1][c], array[2][c-weight[1]]+profit[1]);
		}
		
		int[] solution = new int[profit.length];
		traceback(array, weight, profit, c, solution);
	}

	/**
	 * Traces the table back
	 * @param array		2d array to represent table
	 * @param weight	array of weights
	 * @param c			capacity
	 * @param solution	array to hold solution
	 */
	public void traceback(int[][] array, int[] weight, int[] price, int c, int[] solution)
	{
		int numObj = weight.length-1;
		for(int i = 0; i < numObj-1; i++)
		{
			if(array[i][c] == array[i+1][c])
			{
				//don't include i
				solution[i] = 0;
			}
			else
			{
				//include i
				solution[i] = 1;
				c -= weight[i];
			}
		}
		solution[numObj] = (array[numObj][c]>0) ? 1 : 0;
		printSolution(solution, weight, price);
	}

	private void printSolution(int[] s, int[] weight, int[] price)
	{
		int currentPrice = 0;
		int currentWeight = 0;
		System.out.println("Item Weight Value");
		for (int i = 0; i < s.length; i++)
		{
			if (s[i] == 1)
			{
				System.out.println(i + "\t" + weight[i] + "\t" + price[i]);
				currentPrice = currentPrice + price[i];
				currentWeight = currentWeight + weight[i];
			}
		}
		System.out.println("Total Price: " + currentPrice);
		System.out.println("Capacity Used: " + currentWeight);
	}

	/**
	 * Checks the invariant that the last row of the table should equal
	 * the items array.
	 *  
	 * @param array2d	the 2d array
	 * @param iArray	the array of items
	 * @param index		the current index
	 * @param num		the number of objects
	 * @return	false if the invariant fails, otherwise true
	 */
	private boolean invariantEquality(int[][] array2d, int[] profit, int index, int num)
	{
		if (array2d[num][index] != (int)profit[num])
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks the invariant yMax <= c
	 * @param yMax		the yMax value
	 * @param c			the capacity
	 * @return	false if the invariant fails, otherwise true
	 */
	private boolean yMaxInvariant(int yMax, int c)
	{
		if (yMax > c)
		{
			return false;
		}
		return true;
	}
}