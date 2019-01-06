import java.sql.SQLException;

/*
 * Executes fetchData Thread and
 * calculateTax Thread. 
 */

public class Test {

	public static void main(String[] args) 
	{
		String host = "jdbc:mysql://localhost:3306/items?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "Himanshu007!";
		
		//Object of ItemManger Class that has fetchData and taxCalculate Functions
		final ItemManager manager = new ItemManager();
		
		//Connect with the Database
		manager.connectDB(host, username, password);
		
		//This Thread will fetch data from database
		Thread t1 = new Thread(new Runnable() 
		{
			
			public void run() 
			{
				try
				{
					manager.fetchData();
				}
				catch(SQLException e)
				{
					System.out.println("Error: " + e.getMessage());
				}
				catch(InterruptedException e)
				{
					System.out.println("Error: " + e.getMessage());
				}
			}
			
		});
		
		//This Thread will calculateTax and print the item details
		Thread t2 = new Thread(new Runnable()
		{

			public void run() 
			{
				try 
				{
					manager.calculateTax();
				}
				catch(InterruptedException e)
				{
					System.out.println("Error: " + e.getMessage());
				}
			}
			
		});

		//Start Threads
		t1.start();
		t2.start();
		
		//t2 thread(calculateTax) will terminate after t1 thread(fetchData)
		try
		{
			t1.join();
			t2.join();
		}
		catch(InterruptedException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
	}

}
