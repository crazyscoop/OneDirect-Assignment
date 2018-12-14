# Java Multithreading
Developing a multi-threaded java program where one thread reads the data from the database say, details of an Item from a mysql table while another thread fetches already created Item objects and perform certain operations on them.

## Database
We have created our database using **MySQL**. Our database contains detail records of items. Each record has four fields namely **Name**, **Price**, **Quantity** and **Type**. The Structure of our table is show below.

![image](https://user-images.githubusercontent.com/23214916/50022650-e5fcdf00-0002-11e9-8b9b-19cd8f60f5e1.png)


We have added details of 10 items in our table. 

![image](https://user-images.githubusercontent.com/23214916/50023215-87d0fb80-0004-11e9-9a78-f8044f64f783.png)

## Codes
Execute *Test.java*, it uses objects of two other classes
- Items
- ItemManager

Objects of *Items* Class store details of records fetched from database. 
*ItemManager* contains the fetchData and calculateTax methods which are executed synchronously by threads.  

## Result

Final 


