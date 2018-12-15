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

### Adding Dependencies

```java
		graph.addDependency(nodeA, nodeB);
		graph.addDependency(nodeA, nodeD);
		graph.addDependency(nodeB, nodeC);
		graph.addDependency(nodeB, nodeE);
		graph.addDependency(nodeC, nodeE);
		graph.addDependency(nodeC, nodeD);
```
