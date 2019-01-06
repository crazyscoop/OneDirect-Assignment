import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Connection;

import java.util.LinkedList;

/*
 *Contains fetchData and calculateTax method
 *which gets executed by threads.
 */

public class ItemManager {
	
	//To store objects fetched from database (Database List)
   	LinkedList<Items> dbItemList = null;
	
	//To store objects after calculating taxes (Tax List)
	LinkedList<Items> itemList = null;
	
	Connection con = null;
	
	//To check if all objects are fetched from database
	boolean taskComplete = false;
	
	
	/*  Connects with the database	*/
	public void connectDB(String host,String username,String password)
	{
		System.out.println("Connecting To Database.....");
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Assign the database to 'Connection' object
			con = (Connection) DriverManager.getConnection(host,username,password); 
			
			//Check if Connection is made successfully
			if (con   != null) 
			{ 
				System.out.println("Successfully connected to MySQL database test. \n\n"); 
			}
		}
		catch(SQLException e)
		{
			System.out.println("Connection Failed: " + e.getMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Connection Failed: " + e.getMessage());
		}
		
		dbItemList = new LinkedList<Items>();
		
		itemList = new LinkedList<Items>();
	}
	
	
	/*
	 * Execute Sql Query, fetches data from the database and
	 * stores the object in a LinkedList.
	 */
	public void fetchData() throws SQLException, InterruptedException
	{
		
		/*
		 *  Execute a SQL SELECT query, the query result
         *  is returned in a 'ResultSet' object (rs).
		 */
		Statement statement = con.createStatement();
		String sql = "SELECT * FROM details;";
		ResultSet rs = statement.executeQuery(sql);
		Items temp = null;
		
		//Move the cursor to the next row, return false if no more row
		while(rs.next())
		{

			synchronized(this)
			{
				System.out.println("Thread1: Fetching Data From Database.....\n");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int quantity = rs.getInt("quantity");
				String type = rs.getString("type");
				
				//System.out.println(name + " " + price + " " + quantity + " " + type);
				
				temp = new Items(name);
				temp.itemType(type);
				temp.itemPrice(price);
				temp.itemQty(quantity);
				
				//Insert object in the list
				dbItemList.add(temp);
				
				//notifies the other thread that 
                //it can now read objects from the list. 
				notify();
				
				//For visualization (Not necessary) 
				Thread.sleep(1000);
			}	
		}
		
		System.out.println("Thread1: Task Completed !!!\n");
		
		//All rows from database have been read and inserted into the the list
		taskComplete = true;
	}
	
	
	/*
	 * Function called to Calculate Tax and Print the object details.
	 */
	public void calculateTax() throws InterruptedException
	{
		//Check if more data needs to be fetched from database
		while(!taskComplete)
		{
			synchronized(this)
			{
				System.out.println("Thread2 : Calculating Taxes...\n");
				
				//Wait if no object is inserted into the database List
				while(dbItemList.size() == 0)
				{
					wait();
				}
				
				//Pop the first Object from the Database list
				Items temp = dbItemList.removeFirst();
				
				//Add the object to Tax List
				itemList.add(temp);
				
				temp.printItem();
				
				
				//Notify the other thread to start fetching data
				notify();
				
				Thread.sleep(1000);
				
			}
		}
		System.out.println("Thread2: Task Completed !!!\n");
	}

}
