import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/*
 * Command Line Input must be of format:
 * -name <name-of-item> -price<price-of-item> -quantity<quantity> -type<raw/manufactured/imported>
 */
public class ItemsTest {

	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in); 
		
		/*
		 * itemList stores object of type Items.
		 */
		List<Item> itemList = new ArrayList<Item>();
		
		String truth = "yes";
		
		while(Objects.equals(truth, "yes") || Objects.equals(truth, "y"))
		{
			
			Item tempItem = null;
			try {
				tempItem = getParameters(args);
			}
			catch(NumberFormatException e)
			{
				System.out.println(e);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println(e);
			}
			
			// Pushing Item into the list.
			itemList.add(tempItem);			
			
			// Printing Final Item details.
			tempItem.printItem();			
			
			System.out.println("Do you want to add new item ? (yes/no)");
			
			truth = in.nextLine();
			
			truth = truth.toLowerCase();
			
			// Checking if user need to insert new item.
			if(Objects.equals(truth, "yes") || Objects.equals(truth, "y"))
			{
				System.out.println("Item must be in format... -name <name> -price <price> -quantity <qty> -type <raw/imported/manufactured>");
				args = in.nextLine().split(" ");
			}
			
		}
		
	}
	
	
	/*
	 * Extracts the values from the user input and returns
	 * the Item object with user input. 
	 */
	public static Item getParameters(String[] args)
	{
		String name = null,type = "none";
		double price = 0.0;
		int qty = 1;
		
		for(int i = 0;i < args.length;i+=2)
		{
			
			/*
			 * First Parameter must always be -name, 
			 * followed by any order of price,quantity,type.
			 */
			if(i == 0 && !Objects.equals(args[i], "-name"))
			{
				throw new IllegalArgumentException("First Argument must be -name. Item must be in format... -name <name> -price <price> -quantity <qty> -type <raw/imported/manufactured>");
			}
			
			if(Objects.equals(args[i], "-name"))
			{
				name = args[i+1];
			}
			else if(Objects.equals(args[i], "-type"))
			{
				type = args[i+1];
			}
			else if(Objects.equals(args[i], "-price"))
			{
				try {
					price = Integer.parseInt(args[i+1]);
				}
				catch (NumberFormatException e)
				{
					throw new NumberFormatException("Error in field -price. Price  must be of type float/int.");
				}
			}
			else if(Objects.equals(args[i], "-quantity"))
			{
				try {													
					qty = Integer.parseInt(args[i+1]);
				}
				catch (NumberFormatException e)
				{
					throw new NumberFormatException("Error in field -quantity. Quantity must be of type int.");
				}
			}	
		}
		
		
		/*
		 * The field -type is mandatory and must have value of raw/manufactured/imported.
		 */
		if(Objects.equals(type, "none") || !(Objects.equals(type, "raw") || Objects.equals(type,"manufactured") || Objects.equals(type, "imported")) )
		{
			throw new IllegalArgumentException("Error in field -type. Must be of type -type raw/manufactured/imported. " + type);
		}
		
		
		/*
		 * The field -price must have a non-zero value.
		 */
		if(price == 0)
		{
			throw new IllegalArgumentException("Error in field -price. Must be of type -price price-of-item");
		}
		
		
		/*
		 * Creating Object of Type Item
		 */
		Item item = new Item(name);
		item.itemType(type);
		item.itemPrice(price);
		item.itemQty(qty);
		
		return item;	
	}

}
