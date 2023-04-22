/**
 *  Project 2: 0/1 Knapsack<br>
 *
 *  The <code>Item</code> class represents an item that can be put in a knapsack
 *
 *  <br> <br>
 *  Created: <br>
 *     5 March 2018, Danielle Sarafian<br>
 *     
 *  Modifications: <br>
 *     14 March 2018, Danielle Sarafian, add toString method<br>
 *
 *  @author Danielle Sarafian
 */
public class Item {
	double weight;
	double price;

	/**
	 * Constructs a new item of this class
	 * @param w	the weight of the item
	 * @param p	the price of the item
	 */
	public Item(double w, double p)
	{
		weight = w;
		price = p;
	}
	
	/**
	 * 
	 * @return	the weight of this item
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * 
	 * @return	the price of this item
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * 
	 * @return	the price density of this item
	 */
	public double getPriceDensity()
	{
		return (price/weight);
	}
	
	/**
	 * @return	this item as a string
	 */
	public String toString()
	{
		return (weight + "\t" + price);
	}
}
