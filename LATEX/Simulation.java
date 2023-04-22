import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *  Project 2: 0/1 Knapsack<br>
 *
 *  The <code>Simulation</code> class provides a main method
 *  for a program that determines the best items to put in a 0/1 knapsack
 *  in order to have the highest cost.
 *
 *  <br> <br>
 *  Created: <br>
 *     5 March 2018, Danielle Sarafian<br>
 *  
 *
 *  @author Danielle Sarafian
 */
public class Simulation<T>
{
	/**
	 *  The main function initiates execution of this program.
	 *    @param    String[] args not used in this program
	 **/
	public static void main(String[] args) throws IOException
	{
		System.out.println ("Welcome to Project 2.");

		//int capacity = 175;
		//Knapsack knapsack = new Knapsack(10, 10, 10);
		//knapsack = new Knapsack(3, 4, 4);
		//Item[] items = knapsack.randomValues();		
		//DynamicProgramming dp = new DynamicProgramming(items, 40);		
		//BruteForce bf = new BruteForce(items, 40);		
		//GreedyDensity gd = new GreedyDensity(items, 5);		
		//GreedyPrice gp = new GreedyPrice(items, 5);

		// create list of different lengths to test
		ArrayList<Integer> capacities = new ArrayList<Integer>();

		for (int i = 5; i <30; i = i+10)
		{
			capacities.add(i);
		}

		Knapsack knapsack;
		int highestPrice = 100;
		int highestWeight = 100;

		PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\owner\\Documents\\AaSchool\\CS215\\projects\\Project2DanielleSarafian\\bruteForce1.txt"));

		// create list for the times it takes to sort different sizes
		ArrayList<Long> bfTimes = new ArrayList<Long>();
		ArrayList<Long> gdTimes = new ArrayList<Long>();
		ArrayList<Long> gpTimes = new ArrayList<Long>();
		ArrayList<Long> dpTimes = new ArrayList<Long>();
		

		for (Integer i : capacities)
		{
			System.out.println("capacity: " + i);
			System.out.println("");
			knapsack = new Knapsack(10, highestPrice, highestWeight, i);

			Item[] items = knapsack.randomValues();
			int profits[] = new int[items.length];
			int weights[] = new int[items.length];

			
			for (int j = 0; j < items.length; j++)
			{
				profits[j] = (int)items[j].getPrice(); 
				weights[j] = (int)items[j].getWeight(); 
			}
			int array[][] = new int[profits.length+1][i+1];

			// time for brute force
			if (i <=25)
			{
				long bfStartTime = System.nanoTime();
				BruteForce bf = new BruteForce(items, i);
				long bfEndTime = System.nanoTime();
				long bfDuration = bfEndTime - bfStartTime;
				bfTimes.add(bfDuration);
				System.out.println("brute force time: " + bfDuration);
			}
			else
			{
				bfTimes.add(Long.MAX_VALUE);
			}

			// time for greedy solution with price density
			/*long gdStartTime = System.nanoTime();
			GreedyDensity greedyDensity = new GreedyDensity(items, i);
			long gdEndTime = System.nanoTime();
			long gdDuration = gdEndTime-gdStartTime;
			gdTimes.add(gdDuration);
			System.out.println("greedy price density time: " + gdDuration);

			// time for greedy solution based on price
			long gpStartTime = System.nanoTime();
			GreedyPrice gp = new GreedyPrice(items, i);
			long gpEndTime = System.nanoTime();
			long gpDuration = gpEndTime-gpStartTime;
			gpTimes.add(gpDuration);
			System.out.println("greedy price time: " + gpDuration);

			// time for dynamic programming
			long dpStartTime = System.nanoTime();
			DP2 dp = new DP2();
			dp.solve(profits, weights, i, array);
			long dpEndTime = System.nanoTime();
			long dpDuration = dpEndTime-dpStartTime;
			dpTimes.add(dpDuration);
			System.out.println("dynamic programming time: " + dpDuration);*/
		}

		// print results into a txt file with tab deliminators
		out.println("SOLUTION TYPE \t NUM ELEMENTS \t TIME");
		for (int i = 0; i < bfTimes.size(); i++)
		{
			out.println("BRUTE FORCE \t" + capacities.get(i) + "\t" + bfTimes.get(i));
		}
		/*for (int i = 0; i < gdTimes.size(); i++)
		{
			out.println("GREEDY PRICE DENSITY \t" + capacities.get(i) + "\t" + gdTimes.get(i));
		}
		for (int i = 0; i < gpTimes.size(); i++)
		{
			out.println("GREEDY PRICE \t" + capacities.get(i) + "\t" + gpTimes.get(i));
		}
		for (int i = 0; i < dpTimes.size(); i++)
		{
			out.println("DYNAMIC PROGRAMMING \t" + capacities.get(i) + "\t" + dpTimes.get(i));
		}
		out.close();*/




		System.out.println ("Program done.");

	}//end main
}//end class
