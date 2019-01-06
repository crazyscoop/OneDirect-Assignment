import java.util.Objects;

/*
 * Stores the details of object. 
 */
public class Item {
	
	String name, type;
	double price,tax,finalPrice;
	int qty;
	
	//Constructor: Assigns name
	public Item(String name)
	{
		this.name = name; 
	}
	
	//To set item Type
	public void itemType(String type)
	{
		this.type = type;
	}
	
	//To set item Price
	public void itemPrice(double price)
	{
		this.price = price;
	}
	
	//To set item Quantity
	public void itemQty(int qty)
	{
		this.qty = qty;
	}
	
	
	//To calculate the final Price after including the taxes
	public void calcTax() 
	{
		if(Objects.equals(type, "raw"))
		{
			tax = 0.125*price;
		}
		else if(Objects.equals(type,"manufactured"))
		{
			tax = 0.125*price + 0.02*(price + 0.125*price);
		}
		else if(Objects.equals(type, "imported"))
		{
			tax = 0.10*price;
			double surcharge = 0.0;
			double tempPrice = tax + price;
			
			if(tempPrice <= 100)
			{
				surcharge = 5;
			}
			else if(tempPrice > 100 && tempPrice <= 200)
			{
				surcharge = 10;
			}
			else if(tempPrice > 200)
			{
				surcharge = 0.5*tempPrice;
			}
			
			tax = tax + surcharge;
		}
	}
	
	//Printing the final details of object
	public void printItem()
	{
		System.out.println("\n****Item Details*****");
		System.out.println("name " + name);
		System.out.println("price per unit item " + price);
		calcTax();
		System.out.println("sales tax liability per item " + tax);
		double finalPrice = price + tax;
		System.out.println("final price unit item " + (finalPrice));
		System.out.println("final price for " + qty + " items is " + qty*(price+tax) + "\n\n");
	}

}
