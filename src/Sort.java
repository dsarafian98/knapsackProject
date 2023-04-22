import java.util.ArrayList;

/**
 *  Project 2: 0/1 Knapsack<br>
 *
 *  Sorts an ArrayList using heap sort
 *
 *  <br> <br>
 *  Created: <br>
 *     8 March 2018, Danielle Sarafian<br>
 *     
 *  Modifications: <br>
 *     8 March 2018, Danielle Sarafian, switch methods from using ArrayLists to using arrays<br>
 *
 *  @author Danielle Sarafian
 */
public class Sort
{
	// State: instance variables and shared class variables go here.

	// Constructors

	/**
	 * Constructs a new object of this class.
	 */
	public Sort()
	{
	}

	// Methods
	/**
	 * This method will find the left child of a given node in the max heap
	 * 
	 * 		@param	index	the index of the element in the heap to find the left child of
	 * 		@return	the index in the ArrayList where the left child node is
	 */
	private int left(int index)
	{
		return 2*index;
	}

	/**
	 * This method will find the right child of a given node in the max heap
	 * 
	 * 		@param	index	the index of the element in the heap to find the right child of
	 * 		@return	the index in the ArrayList where the right child node is
	 */
	private int right(int index)
	{
		return ((2*index)+1);
	}

	/**
	 * This method will swap the items given as parameters
	 * 
	 * 		@param	list	the ArrayList that contains the values that should be switched
	 *		@param	index1	the index of one of the values that is being switched
	 *		@param	index2	the index of the other value that is being switched
	 *		@return	the ArrayList with the swapped values
	 */
	private double[] swap (double[] list, int index1, int index2)
	{
		// set item at index1 to a temp
		double temp = list[index1];

		// set item at index2 in index1
		list[index1] = list[index2];

		// set temp at index2
		list[index2] = temp;
		
		return list;
	}

	/**
	 * This method will make sure that the heap is a max heap and if it isn't,
	 * the method will rearrange the terms to make it a max heap
	 * 
	 * 		@param	list		ArrayList to sort
	 * 		@param	index		index to make max heap from
	 * 		@param	endIndex	the last index to use in the heap
	 * 		@return	an ArrayList that represents a max heap
	 */
	private double[] minHeapify(double[] list, int index, int endIndex)
	{
		// get indices of left and right children
		int left = left(index);
		int right = right(index);

		// set largest to index
		int smallest = index;

		// check if largest is smaller than the left child

		if (left <= endIndex && (list[smallest] > list[left]))
		{
			// set largest equal to the left child
			smallest = left;
		}

		// check if largest is smaller than the right child
		if ((right <= endIndex) && (list[smallest] > list[right]))
		{
			// set largest equal to the right child
			smallest = right;
		}

		// if the item at index isn't the largest
		if (smallest != index)
		{
			// swap the item at index with the largest item
			list = swap(list, index, smallest);

			// recursively check that the next heap is a max heap
			list = minHeapify(list, smallest, endIndex);
		}
		return list;
	}

	/**
	 * Makes the ArrayList a max heap
	 * 
	 * 		@param	list		the ArrayList to make a max heap
	 * 		@param	endIndex	the last index to use in the heap
	 * 		@return	the ArrayList as a max heap
	 */
	private double[] buildMinHeap(double[] list, int endIndex)
	{
		int heapSize = list.length;
		
		// vacuously true
		//assert(heapAssert(list, heapSize));

		// make sure the ArrayList represents a max heap
		while (heapSize > 0)
		{
			//assert(heapAssert(list, heapSize));
			minHeapify(list, heapSize-1, endIndex);
			heapSize--;
		}		
		
		// check invariant before returning list
		//assert(heapAssert(list, heapSize));
		return list;
	}

	/**
	 * Takes an unsorted list and sorts it
	 * 
	 * 		@param	list	the ArrayList to sort
	 * 		@return	the sorted ArrayList
	 */
	public double[] reverseHeapSort(double[] list)
	{
		// set variable for list size
		int size = list.length;

		// set variable to count index
		int i = size-1;

		// create a max heap with the list
		buildMinHeap(list, i);

		while (i > 0)
		{
			// switch largest number (located at beginning index)
			// and smallest number (located at ending index)
			list = swap(list, 0, i);

			// remove A[n]
			i--;

			// reconfigure into another max heap
			list = minHeapify(list, 0, i);			
		}
		return list;
	}
	//////////////////////////////////////////////
	/**
	 * This method will make sure that the heap is a max heap and if it isn't,
	 * the method will rearrange the terms to make it a max heap
	 * 
	 * 		@param	list		ArrayList to sort
	 * 		@param	index		index to make max heap from
	 * 		@param	endIndex	the last index to use in the heap
	 * 		@return	an ArrayList that represents a max heap
	 */
	private Item[] maxHeapify(Item[] list, int index, int endIndex)
	{
		// get indices of left and right children
		int left = left(index);
		int right = right(index);

		// set largest to index
		int largest = index;

		// check if largest is smaller than the left child

		if (left <= endIndex && (list[largest].getPrice() < list[left].getPrice()))
		{
			// set largest equal to the left child
			largest = left;
		}

		// check if largest is smaller than the right child
		if ((right <= endIndex) && (list[largest].getPrice() < list[right].getPrice()))
		{
			// set largest equal to the right child
			largest = right;
		}

		// if the item at index isn't the largest
		if (largest != index)
		{
			// swap the item at index with the largest item
			list = swapItem(list, index, largest);

			// recursively check that the next heap is a max heap
			list = maxHeapify(list, largest, endIndex);
		}
		return list;
	}

	/**
	 * Makes the ArrayList a max heap
	 * 
	 * 		@param	list		the ArrayList to make a max heap
	 * 		@param	endIndex	the last index to use in the heap
	 * 		@return	the ArrayList as a max heap
	 */
	private Item[] buildMaxHeap(Item[] list, int endIndex)
	{
		int heapSize = list.length;
		
		// vacuously true
		//assert(heapAssert(list, heapSize));

		// make sure the ArrayList represents a max heap
		while (heapSize > 0)
		{
			//assert(heapAssert(list, heapSize));
			maxHeapify(list, heapSize-1, endIndex);
			heapSize--;
			
		}		
		
		// check invariant before returning list
		//assert(heapAssert(list, heapSize));
		return list;
	}

	/**
	 * Takes an unsorted list and sorts it
	 * 
	 * 		@param	list	the ArrayList to sort
	 * 		@return	the sorted ArrayList
	 */
	public Item[] heapSort(Item[] list)
	{
		// set variable for list size
		int size = list.length;

		// set variable to count index
		int i = size-1;

		// create a max heap with the list
		buildMaxHeap(list, i);

		while (i > 0)
		{
			// switch largest number (located at beginning index)
			// and smallest number (located at ending index)
			list = swapItem(list, 0, i);

			// remove A[n]
			i--;

			// reconfigure into another max heap
			list = maxHeapify(list, 0, i);			
		}
		return list;
	}
	/**
	 * This method will swap the items given as parameters
	 * 
	 * 		@param	list	the ArrayList that contains the values that should be switched
	 *		@param	index1	the index of one of the values that is being switched
	 *		@param	index2	the index of the other value that is being switched
	 *		@return	the ArrayList with the swapped values
	 */
	private Item[] swapItem (Item[] list, int index1, int index2)
	{
		// set item at index1 to a temp
		Item temp = list[index1];

		// set item at index2 in index1
		list[index1] = list[index2];

		// set temp at index2
		list[index2] = temp;
		
		return list;
	}

}
