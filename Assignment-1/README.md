# Java I/O, String
Writing a java program that accepts details (item name, item type, item prize) of different items from
Command line and outputs the item name, item prize, sales tax liability per item, final prize (sales tax + item prize) to the console.

## Codes
Execute itemsTest.java, it uses objects of one other class
- Items

Objects of Item Class store item details provided by the user. The *calcMethod* method calculates tax value according to the item type. The *printItem* method prints the final item details. 

## Execution
Command Line Input must be of format:
```
-name <name-of-item> -price <price-of-item> -quantity <quantity> -type <raw/manufactured/imported>
```

## Results
A part of output is shown in below screenshot.

![image](https://user-images.githubusercontent.com/23214916/50033597-28ccb000-001f-11e9-8ea1-e178715260cc.png)
