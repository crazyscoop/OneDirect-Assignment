# Java Collections
Designing a Data Structure using Java’s Collection Framework that represents a dependency graph. A *Dependency Graph* is an acyclic multi root directional graph with the exception of a root node, which has no parents.

## Codes
Execute Test.java, it uses objects of two other classes

- Node
- Graph

Objects of Node class store node information provided by the user. Object of Graph class stores information regarding the dependency graph provided by the user.

## Results
We will test our code on the below shown dependency graph.

![image](https://user-images.githubusercontent.com/23214916/50043239-ada6e080-0096-11e9-897a-e8803d1cc246.png)


### Creating Nodes

```java
    		Node nodeA = new Node("A","Node A");
    		Node nodeB = new Node("B","Node B");
    		Node nodeC = new Node("C","Node C");
    		Node nodeD = new Node("D","Node D");
    		Node nodeE = new Node("E","Node E");
```
------

### Adding Dependencies

```java
		graph.addDependency(nodeA, nodeB);
		graph.addDependency(nodeA, nodeD);
		graph.addDependency(nodeB, nodeC);
		graph.addDependency(nodeB, nodeE);
		graph.addDependency(nodeC, nodeE);
		graph.addDependency(nodeC, nodeD);
```
------

### Printing Nodes

```java
		graph.print();
```
#### Output

![image](https://user-images.githubusercontent.com/23214916/50043333-15a9f680-0098-11e9-85fc-dfd58562ed8c.png)
------

### Printing Parent Nodes

```java
		graph.getParents(nodeE);
```
#### Output

![image](https://user-images.githubusercontent.com/23214916/50043378-ee9ff480-0098-11e9-85a7-79f833f21768.png)
------

### Printing Children Nodes

```java
		graph.getChildren(nodeB);
```
#### Output

![image](https://user-images.githubusercontent.com/23214916/50043392-522a2200-0099-11e9-91eb-3ac7d9669f8d.png)
------

### Printing Ancestor Nodes

```java
		graph.getAncestors(nodeD);
```
#### Output

![image](https://user-images.githubusercontent.com/23214916/50043415-b2b95f00-0099-11e9-9a13-d08024bb3c71.png)
------

### Printing Descendant Nodes

```java
		graph.getDescendants(nodeA);
```
#### Output

![image](https://user-images.githubusercontent.com/23214916/50043431-104dab80-009a-11e9-8be4-79e22667d86b.png)
------

### Deleting Dependency 

```java
		// Deleting Dependency from nodeA to nodeB.
		graph.deleteDependency(nodeA, nodeB);

		
		// Checking if dependency is removed.
		graph.getDescendants(nodeA);
		graph.getAncestors(nodeB);
```

#### Output

![image](https://user-images.githubusercontent.com/23214916/50043457-7b977d80-009a-11e9-9a46-7f770baccb32.png)
------

### Deleting Node

```java
		// Deleting nodeE.
		graph.deleteNode(nodeE);
		
		
		// Checking if nodeE is deleted.
		graph.getDescendants(nodeA);
		graph.print();
```

#### Output

![image](https://user-images.githubusercontent.com/23214916/50043484-efd22100-009a-11e9-8b69-a8365d932345.png)
------

### Test Cyclic Condition

```java
		// Adding dependency from nodeD->nodeB to test cyclic condition.
		graph.addDependency(nodeD, nodeB);
```

#### Output

![image](https://user-images.githubusercontent.com/23214916/50043498-3e7fbb00-009b-11e9-970b-bcb2ff2c6672.png)
------
