import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/*
 * Stores the Graph Information i.e. Collection of Nodes
 */
public class Graph 
{
	//Stores all the nodes in the graph.
	private List<Node> nodeList;
	
	
	public Graph()
	{
		nodeList = new ArrayList<Node>();
	}
	
	
	/*
	 * Adding a dependency from a parent node(parentNode) to 
	 * a child node(childNode)
	 */
	public void addDependency(Node parentNode,Node childNode)
	{
		
		/*
		 * Adding any new node to our node list(nodeList).
		 */
		if(nodeList.isEmpty() || !nodeList.contains(parentNode) || !nodeList.contains(childNode))
		{
			parentNode.setChildren(childNode);
			childNode.setParent(parentNode);
			
			if(!nodeList.contains(parentNode))
			{
				nodeList.add(parentNode);
			}
			
			if(!nodeList.contains(childNode))
			{
				nodeList.add(childNode);
			}
			System.out.println("Success: New Dependency Added !!! " + parentNode.getId() + " -> " + childNode.getId());
		}
		else
		{
			
			/*
			 * Checking for a path from child node(childNode) to parent node(parentNode). 
			 * If any such path exist, adding a new dependency from parent node to
			 * child node will create a cycle. 
			 * If no such path exist, add child node to parent node's children node list
			 * and add parent node to child node's parent node list.
			 */
			if(isPath(childNode,parentNode))
			{
				System.out.println("Error: Adding Such Dependency Will Create A Cycle !!! " + parentNode.getId() + " -> " + childNode.getId());
			}
			else
			{
				parentNode.setChildren(childNode);
				childNode.setParent(parentNode);
				System.out.println("Success: New Dependency Added !!! " + parentNode.getId() + " -> " + childNode.getId());
			}
			
		}
	}
	
	
	/*
	 * Checks for path from node a to node b, by recursively comparing
	 * node b with node a's successors.
	 */
	public boolean isPath(Node a,Node b)
	{
		if(a.equals(b))
		{
			return true;
		}
		
		for(int i = 0;i < a.getChildren().size();i++)
		{
			if(isPath(a.getChildren().get(i),b))
			{
				return true;
			}
		}
		
		return false;	
	}
	
	
	/*
	 * Gets all the parent nodes of a given node.
	 */
	public void getParents(Node node)
	{
		
		//Check if graph contains node.
		if(!nodeList.contains(node))
		{
			System.out.println("Error: The Given Node Doesn't Exist !!! NodeID: " + node.getId() + "\n\n");
			return;
		}
		
		System.out.println("Parent Nodes Of NodeID: " + node.getId());
		
		for(int i = 0;i < node.getParents().size();i++)
		{
			System.out.println("ID: " + node.getParents().get(i).getId() + " Name: " + node.getParents().get(i).getName());
		}
		
		/*
		 * If the given node has no parents, then the given node 
		 * is a root node.
		 */
		if(node.getParents().size() == 0)
		{
			System.out.println("NULL (The Given Node Is Root Node)");
		}
		
		System.out.println("Success: Parents Printed !!!\n\n");
	
	}
		
	
	/*
	 * Gets all the child nodes of a given node.
	 */
	public void getChildren(Node node)
	{
		
		//Check if graph contains node.
		if(!nodeList.contains(node))
		{
			System.out.println("Error: The Given Node Doesn't Exist !!! NodeID: " + node.getId() + "\n\n");
			return;
		}
		
		System.out.println("Children Nodes Of NodeID: " + node.getId());
		
		for(int i = 0;i < node.getChildren().size();i++)
		{
			System.out.println("ID: " + node.getChildren().get(i).getId() + " Name: " + node.getChildren().get(i).getName());
		}
		
		/*
		 * If the given node has no children, then the given node
		 * is a leaf node.
		 */
		if(node.getChildren().size() == 0)
		{
			System.out.println("NULL (The Given Node Is Leaf Node)");
		}
		
		System.out.println("Success: Children Printed !!!\n\n");
	
	}
	
	
	/*
	 * Gets all the ancestor nodes for a given node by calling getAncestorsUtil method, 
	 * which recursively collects parent nodes.
	 * node D ->
	 * 			 node B -> node A
	 * node C ->
	 * 
	 * Ancestors of node A are: node B(parent of(node A)), node C(parent of(node B)), node D(parent of(node B)).
	 */
	public void getAncestors(Node node)
	{
		
		//Check if graph contains node.
		if(!nodeList.contains(node))
		{
			System.out.println("Error: The Given Node Doesn't Exist !!! NodeID: " + node.getId() + "\n\n");
			return;
		}
		
		List<Node> temp = new ArrayList<Node>();
		temp = getAncestorsUtil(node);
				
		System.out.println("Ancestor Nodes Of NodeId: " + node.getId());
		for(int i = 0;i < temp.size();i++)
		{
			System.out.println("ID: " + temp.get(i).getId() + " Name: " + temp.get(i).getName());
		}

		if(temp.size() == 0)
		{
			System.out.println("NULL (The Given Node Is Root Node)");
		}
		System.out.println("Success: Ancestors Printed !!!\n\n");
	}
	
	
	/*
	 * Recursive Method which collects ancestor nodes.
	 */
	public List<Node> getAncestorsUtil(Node node)
	{
		List<Node> temp = new ArrayList<Node>();
		for(int i = 0;i < node.getParents().size();i++)
		{
			temp = mergeList(mergeList(node.getParents(),getAncestorsUtil(node.getParents().get(i))),temp);
		}
		return temp;
	}
	
	
	/*
	 * Gets all the descendant nodes for a given node by calling getDescendantsUtil method, 
	 * which recursively collects child nodes.
	 *                     node C
	 * node A -> node B -> 
	 *                     node D
	 * 
	 * Descendants of node A are: node B(child of(node A)), node C(child of(node B)), node D(child of(node B)).
	 */
	public void getDescendants(Node node)
	{
		
		//Check if graph contains node.
		if(!nodeList.contains(node))
		{
			System.out.println("Error: The Given Node Doesn't Exist !!! NodeID: " + node.getId() + "\n\n");
			return;
		}
		
		List<Node> temp = new ArrayList<Node>();
		temp = getDescendantsUtil(node);
		
		System.out.println("Descendant Nodes Of NodeId: " + node.getId());
		for(int i = 0;i < temp.size();i++)
		{
			System.out.println("ID: " + temp.get(i).getId() + " Name: " + temp.get(i).getName());
		}

		if(temp.size() == 0)
		{
			System.out.println("NULL (The Given Node Is Leaf Node)");
		}
		System.out.println("Success: Descendant Printed !!!\n\n");
	}

	
	
	/*	
	 * Recursive Method which collects descendant nodes.
	 */
	public List<Node> getDescendantsUtil(Node node)
	{
		List<Node> temp = new ArrayList<Node>();
		for(int i = 0;i < node.getChildren().size();i++)
		{
			temp = mergeList(mergeList(node.getChildren(),getDescendantsUtil(node.getChildren().get(i))),temp);
		}
		return temp;
	}
	
	
	/*
	 * Merges two lists, a and b without any duplicate nodes
	 * and returns the merged list.
	 */
	public List<Node> mergeList(List<Node> a,List<Node> b)
	{

		List<Node> temp = new ArrayList<Node>(a);

		for(int i = 0;i < b.size();i++)
		{
			if(!temp.contains(b.get(i)))
			{
				temp.add(b.get(i));
			}
		}
		return temp;
	}
	
	
	/*
	 * Delete dependency if any exist between parentNode and childNode.
	 * To delete dependency, childNode is removed from children list of parentNode
	 * and parentNode is removed from parent list of childNode.
	 */
	public void deleteDependency(Node parentNode,Node childNode)
	{
		
		/*
		 * Check if nodeList contains both parentNode and childNode.
		 */
		if(nodeList.contains(parentNode) && nodeList.contains(childNode))
		{
			
			/*
			 * Check if any dependency exist between parentNode and childNode.
			 */
			if(parentNode.getChildren().contains(childNode))
			{
				parentNode.getChildren().remove(childNode);
				childNode.getParents().remove(parentNode);
				System.out.println("Success: Dependency Deleted !!! "  + parentNode.getId() + " -> " + childNode.getId() + "\n\n");
			}
			else
			{
				System.out.println("Error: Such Dependency Doesn't Exist !!! " + parentNode.getId() + " -> " + childNode.getId() + "\n\n");
			}

		}
		else
		{
			System.out.println("Error: Either Of Parent Or Child Node Doesn't Exist !!! " + parentNode.getId() + " -> " + childNode.getId() + "\n\n");
		}
	}
	
	
	/*
	 * Deletes the given node from the graph.
	 */
	public void deleteNode(Node node)
	{
		
		/*
		 * Check if nodeList(Graph) contains the given node.
		 */
		if(nodeList.contains(node))
		{
			
			/*
			 * Remove the given node from the parent lists of all the children nodes. 
			 */
			for(int i = 0;i < node.getChildren().size();i++)
			{
				node.getChildren().get(i).getParents().remove(node);
			}
			
			
			/*
			 * Removes the given node from the children lists of all the parent nodes.
			 */
			for(int i = 0;i < node.getParents().size();i++)
			{
				node.getParents().get(i).getChildren().remove(node);
			}
			
			while(node.getChildren().size() > 0)
			{
				node.getChildren().remove(0);
			}
			
			while(node.getParents().size() > 0)
			{
				node.getParents().remove(0);
			}
			
			//Remove the node from nodeList.
			nodeList.remove(node);
			System.out.println("Success: Node Deleted !!! NodeID: " + node.getId() + "\n\n");
		}
		else
		{
			System.out.println("Error: The Given Node Doesn't Exist !!! NodeID: " + node.getId() + "\n\n");
		}
	}
	
	
	/*
	 * Prints all the nodes present in the graph.
	 * Uses a printUtil method which performs a DFS.
	 */
	public void print()
	{
		
		/*
		 * Stores if a node is visited or not.
		 */
		Map<Node,Boolean> visted = new Hashtable<>();
		
		/*
		 * By default, none of the nodes are visited. So assign false value for 
		 * each node key.
		 */
		for(int i = 0;i < nodeList.size();i++)
		{
			visted.put(nodeList.get(i),false);
		}
		
		System.out.println("Nodes Are: ");
		
		for(int i = 0;i < nodeList.size();i++)
		{
			if(!visted.get(nodeList.get(i)))
			{
				printUtil(visted,nodeList.get(i));
			}
		}
		
		System.out.println("Success: Printing Nodes !!!\n\n");
	}
	
	
	/*
	 * Performs a Depth First Search and prints any node which is not visited.
	 */
	public void printUtil(Map<Node,Boolean> visted,Node node)
	{
		/*
		 * Visit if node is not visited yet.
		 */
		if(!visted.get(node))
		{
			//Raise the visited flag for the node.
			visted.put(node, true);
			
			//Print the node
			System.out.println("ID: " + node.getId() + " Name: " + node.getName());
			
			//Do the same recursively for the children nodes.
			for(int i = 0;i < node.getChildren().size();i++)
			{
				if(!visted.get(node.getChildren().get(i)))
				{
					printUtil(visted,node.getChildren().get(i));
				}
			}
			
		}
		
	}
	
	
}
