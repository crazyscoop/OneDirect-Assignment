/*
 * Testing our graph.
 */
public class Test 
{

	public static void main(String[] args) 
	{
		
		Node nodeA = new Node("A","Node A");
		Node nodeB = new Node("B","Node B");
		Node nodeC = new Node("C","Node C");
		Node nodeD = new Node("D","Node D");
		Node nodeE = new Node("E","Node E");
		
		Graph graph = new Graph();
		
		/*
		 * Adding Dependencies
		 */
		graph.addDependency(nodeA, nodeB);
		graph.addDependency(nodeA, nodeD);
		graph.addDependency(nodeB, nodeC);
		graph.addDependency(nodeB, nodeE);
		graph.addDependency(nodeC, nodeE);
		graph.addDependency(nodeC, nodeD);

		// Printing all graph nodes.
		graph.print();
		
		
		//Printing Parent nodes of nodeE.
		graph.getParents(nodeE);
		
		
		//Printing Children nodes of nodeB.
		graph.getChildren(nodeB);
		
		
		// Printing Ancestor nodes of nodeD.
		graph.getAncestors(nodeD);
		
		
		// Printing Descendant nodes of nodeA.
		graph.getDescendants(nodeA);
		
		
		// Deleting Dependency from nodeA to nodeB.
		graph.deleteDependency(nodeA, nodeB);

		
		// Checking if dependency is removed.
		graph.getDescendants(nodeA);
		graph.getAncestors(nodeB);
		
		
		// Deleting nodeE.
		graph.deleteNode(nodeE);
		
		
		// Checking if nodeE is deleted.
		graph.getDescendants(nodeA);
		graph.print();
		
		
		// Adding dependency from nodeD->nodeB to test cyclic condition.
		graph.addDependency(nodeD, nodeB);
		
	}

}
