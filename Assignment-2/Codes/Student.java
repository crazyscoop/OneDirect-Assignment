import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Stores the details of Student.
 */
public class Student implements Serializable{

	private String name,address;
	private int age,rollno ;
	private List<String> courses = new ArrayList<String>();
	

	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public void setAddress(String address)
	{
		this.address = address;
	}

	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	
	public void setRollno(int rollno)
	{
		this.rollno = rollno;
	}
	
	
	public void setCourses(List<String> courses)
	{
		this.courses = courses;
	}
	
	
	public int getRoll()
	{
		return rollno;
	}
	
	
	/*
	 * Overriding equals method to compare our student objects based on rollno.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof Student)
		{
			Student temp = (Student) obj;
			return temp.getRoll() == getRoll();
		}
		return false;
	}
	
	
	/*
	 * Formating String before final display.
	 */
	public String formatString(String string, int length) 
	{
	    return String.format("%1$"+length+ "s", string);
	}
	
	
	/*
	 * Display Student Details.
	 */
	public void displayDetails()
	{
		System.out.print(formatString(name,20) + "\t");
		System.out.print(formatString(Integer.toString(rollno),20)+ "\t");
		System.out.print(formatString(Integer.toString(age),20) + "\t");
		System.out.print(formatString(address,20) + "\t");
		System.out.print("\t\t" + courses + "\n");
	}
	
	
	/*
	 * Comparator:
	 * Property  : Name
	 * Order     : Ascending
	 */
	public static Comparator<Student> StudentnameComparatorAscending = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {
		   String StudentName1 = s1.name.toUpperCase();
		   String StudentName2 = s2.name.toUpperCase();

		   //ascending order
		   return StudentName1.compareTo(StudentName2);
	    }
	};
	    
	  
	/*
	 * Comparator:
	 * Property  : Name
	 * Order     : Descending
	 */
	 public static Comparator<Student> StudentnameComparatorDescending = new Comparator<Student>() {
		
		 public int compare(Student s1,Student s2)
		 {
			 String StudentName1 = s1.name.toUpperCase();
			 String StudentName2 = s2.name.toUpperCase();
			 
			 return StudentName2.compareTo(StudentName1);
		 }
	 };
	 
	 
   /*
	* Comparator:
	* Property  : Address
	* Order     : Ascending
	*/
	 public static Comparator<Student> StudentaddressComparatorAscending = new Comparator<Student>() {
		
		 public int compare(Student s1,Student s2)
		 {
			 String add1 = s1.address.toUpperCase();
			 String add2 = s2.address.toUpperCase();
			 
			 return add1.compareTo(add2);
		 }
	 };
	 
	 
   /*
	* Comparator:
	* Property  : Address
	* Order     : Descending
	*/
	 public static Comparator<Student> StudentaddressComparatorDescending = new Comparator<Student>() {
		 
		 public int compare(Student s1,Student s2)
		 {
			 String add1 = s1.address.toUpperCase();
			 String add2 = s2.address.toUpperCase();
			 
			 return add2.compareTo(add1);
		 }
	 };
	 
	 
   /*
	* Comparator:
	* Property  : rollno
	* Order     : Ascending
	*/
	 public static Comparator<Student> StudentrollnoComparatorAscending = new Comparator<Student>() {
		 
		 public int compare(Student s1,Student s2)
		 {
			 return s1.rollno - s2.rollno;
		 }
	 };
	 
	 
   /*
	* Comparator:
	* Property  : rollno
	* Order     : Descending
	*/
	 public static Comparator<Student> StudentrollnoComparatorDescending = new Comparator<Student>() {
		 
		 public int compare(Student s1,Student s2)
		 {
			 return s2.rollno - s1.rollno;
		 }
	 };
	 
	 
   /*
	* Comparator:
	* Property  : Age
	* Order     : Ascending
	*/
	 public static Comparator<Student> StudentageComparatorAscending = new Comparator<Student>() {
		
		 public int compare(Student s1,Student s2)
		 {
			 return s1.age - s2.age;
		 }
	 };
	 
	 
   /*
	* Comparator:
	* Property  : Age
	* Order     : Descending
	*/
	 public static Comparator<Student> StudentageComapartorDescending = new Comparator<Student>() {
		
		 public int compare(Student s1,Student s2)
		 {
			 return s2.age - s1.age;
		 }
	 };
	
}
