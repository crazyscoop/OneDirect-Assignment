import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

/*
 * Menu Class
 */
public class Menu {

	//Data Structure to store in session data.
	static List<Student> record = new ArrayList<Student>();
	
	public static final String FILENAME = "myObject.txt";
	static Scanner in;
	
	
	public static void main(String[] args) 
	{
		in = new Scanner(System.in);
		
		/*
		 * Gets the student records stored in File.
		 */
		try {
			getDetails();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Error: " + e + " .getDetails()");
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e + " .getDetails()");
		}
		
		menu();
		
		System.out.println("Thanks For Using !!!");
	}
	
	
	/*
	 * The Main Menu.
	 * User Input must be between 1-5.
	 */
	public static void menu()
	{
		
		int choice = 0;
		
		/*
		 * Display the Choices.
		 */
		while(choice != 5)
		{
			
			System.out.println("*********Main Menu*********\n");
			System.out.println("1. Add User Details.");
			System.out.println("2. Display User Details.");
			System.out.println("3. Delete User Details.");
			System.out.println("4. Save User Details.");
			System.out.println("5. Exit");
			System.out.println("Enter Your Choice : ");
		
		
			String temp;
			temp = in.nextLine();
			
			try 
			{
				choice  = Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Error: " + e);
				choice = 0;
			}
			catch(Exception e)
			{
				System.out.println("Error: " + e);
				e.printStackTrace();
			}
			
			/*
			 * Call the appropriate method.
			 */
			switch(choice)
			{
			
			case 1:
				addDetails();
				break;
			
			case 2:
				displayDetails();
				break;
				
			case 3:
				deleteUser();
				break;
			
			case 4:
				try 
				{
					saveDetails();
				}
				catch(ClassNotFoundException e)
				{
					System.out.println("Error: " + e + " .saveDetails()");
				}
				catch(IOException e)
				{
					System.out.println("Error: " + e + " .saveDetails()");
				}
				break;
				
			case 5:
				try
				{
					exit();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
				return;
				
			default:
				System.out.println("Error: Choice must be in range 1-5.");
				System.out.println("Enter Your Choice : ");
				break;
			}
		}
		return;
	}
	
	
	/*
	 * Gets the Student records stored in disk memory(myObject.txt).
	 */
	public static void getDetails() throws IOException, ClassNotFoundException 
	{
		
		boolean fileExist = false;
		
		File file = new File(FILENAME);
		
		/*
		 * Checks if File exists.
		 */
		if(file.exists() && !file.isDirectory())
		{
			fileExist = true;
		}
		
		List<Object> tempRecord = new ArrayList<Object>();
		
		/*
		 * For Reading data from file.
		 */
		FileInputStream fileInp = null;
		ObjectInputStream objInp = null;
		
		
		if(fileExist)
		{
			fileInp = new FileInputStream(FILENAME);
			
			boolean cont = true;
			
			try
			{
			   objInp = new ObjectInputStream(fileInp);
			   
			   while(cont)
			   {
			      Object obj = objInp.readObject();
			      
			      /*
			       * Obj will be null when there is no data to read.
			       * ObjectInputStream(fileInp) will throw an EOFException.
			       */
			      if(obj != null)
			      {
			         tempRecord.add(obj);
			      }
			      else
			      {
			         cont = false;
			      }
			   }
			}
			catch(EOFException e)
			{
				System.out.println("Data Collected From The File\n\n");
			}
			
			
			/*
			 *  Add new student records to the in session record list(record).
			 */
			for(int i = 0;i < tempRecord.size();i++)
			{
				if(!record.contains(tempRecord.get(i)))
				{
					record.add((Student)tempRecord.get(i));
				}
			}
		}
	}
	
	
	/*
	 * Adding details of new student.
	 */
	public static void addDetails()
	{
		
		/*
		 * Details.
		 */
		String name = null;
		String address = null;
		int rollno = 0;
		int age = 0;
		List<String> courses = new ArrayList<String>();
		
		String temp;
		
		/*
		 * Regular Expression to perform the required validations. 
		 */
		Pattern pattern;
		Matcher matcher;
		
		System.out.println("\n\n *********Enter User Details*********");
		System.out.println("Press X To Return.\n\n");
		
		
		/*
		 * Add User Name.
		 */
		
		boolean nameBool = true;
		
		// Name must only contain [a-z A-Z].
		pattern = Pattern.compile("[a-zA-Z ]*");
		
		while(nameBool)
		{
			
			System.out.println("Enter User Name : ");
			
			temp = in.nextLine();
			
			// Match user input(temp) with pattern.
			matcher = pattern.matcher(temp);
			
			if(Objects.equals(temp, "x") || Objects.equals(temp, "X"))
			{
				return;
			}
			else if(!matcher.matches() || temp == null || temp.isEmpty()) 
			{
				System.out.println("Error: Name Must Contain [a-z A-Z]");
			}
			else
			{
				name = temp;
				nameBool = false;
			}
		}
		
		
		/*
		 * Add User Age.
		 */
		
		boolean ageBool = true;
		
		// Age must only contain integers [0-9].
		pattern = Pattern.compile("[0-9]*");
		
		while(ageBool)
		{
			
			System.out.println("Enter User Age : ");
			
			temp = in.nextLine();
			
			matcher = pattern.matcher(temp);
			
			if(Objects.equals(temp, "x") || Objects.equals(temp, "X"))
			{
				return;
			}
			else if(!matcher.matches())
			{
				System.out.println("Error: Age Must Contain [0-9]");
			}
			else
			{
				try 
				{
					age = Integer.parseInt(temp);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Error: " + e);
				}
				
				//Age must be less than 100.
				if(age > 100 || age <= 0)
				{
					System.out.println("Error: Age Must be between 10 - 100");
				}
				else
				{
					ageBool = false;
				}
			}
		}
		
		
		/*
		 * Add User Address
		 */
		
		boolean addBool = true;
		
		//Address must only contain [a-z A-Z 0-9]. 
		pattern = Pattern.compile("[a-zA-Z0-9 ]*");
		
		while(addBool)
		{
			
			System.out.println("Enter User Address : ");
			
			temp = in.nextLine();
			
			matcher = pattern.matcher(temp);
			
			if(Objects.equals(temp, "x") || Objects.equals(temp, "X"))
			{
				return;
			}
			else if(!matcher.matches() || temp == null || temp.isEmpty()) 
			{
				System.out.println("Error: Address Must Contain [a-z A-Z 0-9]");
			}
			else
			{
				address = temp;
				addBool = false;
			}
		}
		
		
		/*
		 * Enter User RollNo
		 */
		
		boolean rollBool = true;
		
		//Roll No must only contain integers [0-9].
		pattern = Pattern.compile("[0-9]*");
		
		while(rollBool)
		{
			
			System.out.println("Enter User Roll No : ");
			
			temp = in.nextLine();
			
			matcher = pattern.matcher(temp);
			
			if(Objects.equals(temp, "x") || Objects.equals(temp, "X"))
			{
				return;
			}
			else if(!matcher.matches())
			{
				System.out.println("Error: Roll No Must Contain [0-9]");
			}
			else
			{
				try 
				{
					rollno = Integer.parseInt(temp);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Error: " + e);
				}
				
				boolean exist = false;
				
				/*
				 * Check if roll no already exists in records.
				 */
				for(int i = 0;i < record.size();i++)
				{
					if(record.get(i).getRoll() == rollno)
					{
						System.out.println("Error: The Given RollNo Is Already Present. Choose A New Roll No.");
						exist = true;
						break;
					}
				}
				
				if(!exist)
				{
					rollBool = false;
				}
				
			}
		}
		
		
		/*
		 * Add User Courses
		 */
		
		boolean patternBool = true;
		
		//Courses must only contain [a-f A-F] alphabets.
		pattern = Pattern.compile("[a-fA-F]*");
		
		/*
		 * 4 courses are mandatory.
		 * Can't repeat courses.
		 */
		System.out.println("Enter Courses [A,B,C,D,E,F] (Must Choose atleast 4) : ");
		
		for(int i = 1;i <= 4;i++)
		{
			
			System.out.println("Enter Course " + i );
			
			temp = in.nextLine();
			
			matcher = pattern.matcher(temp);
			
			temp = temp.toUpperCase();
			
			if(Objects.equals(temp, "x") || Objects.equals(temp, "X"))
			{
				return;
			}
			else if(!matcher.matches() || temp == null || temp.isEmpty() || temp.length() > 1)
			{
				System.out.println("Error: Courses Must Be Of Type [A,B,C,D,E,F].");
				i-=1;
			}
			else if(courses.contains(temp))
			{
				System.out.println("Error: " + temp + " Is Already Taken. Choose A New Course.");
				i-=1;
			}
			else
			{
				courses.add(temp);
			}
		}
		
		/*
		 * Check to add more courses (after 4 mandatory courses).
		 */
		while(patternBool)
		{
			System.out.println("Do You Want To Add A Course (Y/N) ?");
			
			temp = in.nextLine();
			
			temp = temp.toLowerCase();
			
			if(Objects.equals(temp, "y") || Objects.equals(temp, "yes"))
			{
				System.out.println("Enter Course : " );
				
				temp = in.nextLine();
				
				matcher = pattern.matcher(temp);
				
				temp = temp.toUpperCase();
				
				if(!matcher.matches() || temp == null || temp.isEmpty() || temp.length() > 1)
				{
					System.out.println("Error: Courses Must Be Of Type [A,B,C,D,E,F].");
				}
				else if(courses.contains(temp))
				{
					System.out.println("Error: " + temp + " Is Already Taken. Choose A New Course.");
				}
				else
				{
					courses.add(temp.toUpperCase());
					System.out.println("Course Successfully Added !!!");
				}
			}
			else
			{
				patternBool = false;
			}
		}
		
		
		/*
		 * Done.
		 */
		
		Student newStudent = new Student();
		
		newStudent.setName(name);
		newStudent.setRollno(rollno);
		newStudent.setAddress(address);
		newStudent.setAge(age);
		newStudent.setCourses(courses);
		
		//Adding student object to in session record list.
		record.add(newStudent);
		
		/*
		 * Default Sorting : 
		 * Sort By Name Then By Roll No
		 */
		Collections.sort(record,Student.StudentnameComparatorAscending);
		Collections.sort(record, Student.StudentrollnoComparatorAscending);
		
		System.out.println("Conguratulations....Details Have Been Successfully Recorded !!!\n\n");
		
		return;
		
	}

	
	/*
	 * Formating String before final display.
	 */
	public static String formatString(String string, int length) 
	{
	    return String.format("%1$"+length+ "s", string);
	}
	
	
	/*
	 * Display Records.
	 */
	public static void displayDetails()
	{
		
		/*
		 * Check if record list is empty.
		 */
		if(record.size() < 1)
		{
			System.out.println("No Records To Display.\n\n");
			menu();
			return;
		}
		
		System.out.println("\n\n\t\t\t\t\t\t*********Student Details*********\n\n");
		System.out.print(formatString("Name",20) + "\t");
		System.out.print(formatString("RollNo",20)+ "\t");
		System.out.print(formatString("Age",20) + "\t");
		System.out.print(formatString("Address",20) + "\t");
		System.out.print("\t\tCourses" + "\n\n");
		
		/*
		 * Displaying Records
		 */
		for(int i = 0;i < record.size();i++)
		{
			record.get(i).displayDetails();
		}

		String temp = null;
		
		System.out.println("\n\n\n");
		
		System.out.println("Do You Want To Sort The Record (Y/N)");
		
		temp = in.nextLine();
		
		temp = temp.toLowerCase();
		
		/*
		 * Check if user wants to sort records.
		 */
		if(Objects.equals(temp, "yes") || Objects.equals(temp, "y"))
		{
			sortDetails();
		}
		
		return;
	}
	
	
	/*
	 * Sorting Records.
	 * Records can be sorted based on: Name , Roll No , Age , Address.
	 */
	public static void sortDetails()
	{
		
		System.out.println("Sort The Record By: ");
		System.out.println("1. Name");
		System.out.println("2. Roll No");
		System.out.println("3. Age");
		System.out.println("4. Address");
		
		String temp = null;
		
		String category = "0";
		
		boolean categoryTruth = true;
		
		while(categoryTruth)
		{
			
			System.out.println("Enter Your Choice [1-4] (Choose x To Return): ");
			
			temp = in.nextLine();
			
			switch(temp)
			{
			case "1":
				category = "name";
				Collections.sort(record, Student.StudentnameComparatorAscending);
				break;
				
			case "2":
				category = "rollno";
				Collections.sort(record, Student.StudentrollnoComparatorAscending);
				break;
				
			case "3":
				category = "age";
				Collections.sort(record, Student.StudentageComparatorAscending);
				break;
				
			case "4":
				category = "address";
				Collections.sort(record, Student.StudentaddressComparatorAscending);
				break;
				
			case "x":
				return;
				
			case "X":
				return;
				
			default:
				System.out.println("Error: The Choice Must Be Within [1-4]");
				break;
			}
			
			if(!Objects.equals(category, "0"))
			{
				categoryTruth = false;
			}
		}
		
		displayDetails();
		
		return;
		
	}

	
	/*
	 * Delete User Record.
	 */
	public static void deleteUser()
	{
		
		/*
		 * Check if list is empty.
		 */
		if(record.size() < 1)
		{
			System.out.println("There Are 0 Records In The Register.\n\n");
			menu();
			return;
		}
		
		String temp;
		
		int rollTemp = 0;
		
		System.out.println("Enter The Roll No You Wish To Delete : ");
		
		temp = in.nextLine();
		
		try 
		{
			rollTemp = Integer.parseInt(temp);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Error: " + e + ". Try Again.\n\n");
			return;
		}
		
		/*
		 * Search and delete record from the list.
		 */
		boolean found = false;
		
		for(int i = 0;i < record.size();i++)
		{
			if(record.get(i).getRoll() == rollTemp)
			{
				found = true;
				record.remove(i);
				System.out.println("Record With Roll No " + rollTemp + " Is Successfully Removed.\n\n");
				break;
			}
		}
		
		if(!found)
		{
			System.out.println("There Is No Record With Roll No " + rollTemp + ". Try Again.");
		}
		
		return;
	}
	
	
	/*
	 * Saving in memory details of all the users to disk.
	 */
	public static void saveDetails() throws ClassNotFoundException, IOException
	{
		
		boolean fileExist = false;
		
		File file = new File(FILENAME);
		
		/*
		 * Check if file exists.
		 */
		if(file.exists() && !file.isDirectory())
		{
			fileExist = true;
		}
		
		// Contains list of records which will be stored in file. 
		List<Object> tempRecord = new ArrayList<Object>();
		 
		FileInputStream fileInp = null;
		ObjectInputStream objInp = null;
		
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		 
		/*
		 * If file exists add new records.
		 */
		if(fileExist)
		{
			
			fileInp = new FileInputStream(FILENAME);
			
			boolean cont = true;
			
			/*
			 * Get the records that are already stored in file.
			 */
			try
			{
			   objInp = new ObjectInputStream(fileInp);
			   while(cont)
			   {
			      Object obj = objInp.readObject();
			      if(obj != null)
			      {
			         tempRecord.add(obj);
			      }
			      else
			      {
			         cont = false;
			      }
			   }
			}
			catch(EOFException e)
			{
				System.out.println("Data Collected From The File\n\n");
			}
			
			 fileInp.close();
			 objInp.close();
			 
			 /*
			  * Add new records from in session list(records previously not present in file).
			  */
			 for(int i = 0;i < record.size();i++)
			 {
				 if(!tempRecord.contains(record.get(i)))
				 {
					 tempRecord.add(record.get(i));
				 }
			 }
		
			 // Clear File.
			 new FileOutputStream(FILENAME).close();
			 
			 /*
			  * Add all the records to the file.
			  */
			 try
			 {
				 fileOut = new FileOutputStream(FILENAME);
				 objOut = new ObjectOutputStream(fileOut);
			 }
			 catch(FileNotFoundException e)
			 {
				 System.out.println("Error: " + e + " .FileNotFoundException (write)");
			 }
			 catch(IOException e)
			 {
				 System.out.println("Error: " + e + " .IOException (write)");
			 }
			 
			for(int i = 0;i < tempRecord.size();i++)
			{
				objOut.writeObject(tempRecord.get(i));
			}
				
			fileOut.close();
			objOut.close();
			 
		 }
		/*
		 * Create file if file doesn't exist.
		 */
		 else
		 {
			 
			try
			{
				fileOut = new FileOutputStream(new File(FILENAME));
				objOut = new ObjectOutputStream(fileOut);
				
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Error2: " + e + " .FileNotFoundException (write)");
			}
			catch(IOException e)
			{
				System.out.println("Error2: " + e + " .IOException (write)");
			}
			 
			/*
			 * Store all the in session records in file.
			 */
			for(int i = 0;i < record.size();i++)
			{
				objOut.writeObject(record.get(i));
			}
				
			fileOut.close();
			objOut.close();
			     
		 }
		 
		System.out.println("Done: Successfully Saved !!!");
		return;
	}

	
	/*
	 * Check if user wants to save the latest changes before exiting.
	 */
	public static void exit() throws IOException
	{
		
		System.out.println("\n\nDo You Want To Save The Latest Changes ? (Y/N)");
		
		String temp = in.nextLine();
		
		temp = temp.toLowerCase();
		
		if(Objects.equals(temp, "yes") || Objects.equals(temp, "y"))
		{
			
			FileOutputStream fileOut = null;
			ObjectOutputStream objOut = null;
			
			new FileOutputStream(FILENAME).close();
			  
			try
			{
				fileOut = new FileOutputStream(FILENAME);
			 	objOut = new ObjectOutputStream(fileOut);
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Error: " + e + " .FileNotFoundException (write)");
			}
			catch(IOException e)
			{
				System.out.println("Error: " + e + " .IOException (write)");
			}
			
			/*
			 * Update the file.
			 */
			for(int i = 0;i < record.size();i++)
			{
				objOut.writeObject(record.get(i));
			}
				
			fileOut.close();
			objOut.close();
		}
		
		return;
	}

}
