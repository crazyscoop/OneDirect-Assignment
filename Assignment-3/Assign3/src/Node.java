import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Stores the Node Information
 */
public class Node 
{	
	
	private String nodeId;
	private String nodeName;
	
	//Stores Child Nodes.
	private List<Node> nodeChildren;
	
	//Stores Parent Nodes.
	private List<Node> nodeParents;
	
	
	/*
	 * Constructor: Sets NodeId and NodeName.
	 */
	public Node(String nodeId, String nodeName)
	{
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		nodeChildren = new ArrayList<Node>();
		nodeParents = new ArrayList<Node>();
	}
	
	
	/*
	 * Overriding equals method to compare our objects based on nodeId.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof Node)
		{
			Node temp = (Node) obj;
			return Objects.equals(nodeId, temp.getId());
		}
		return false;
	}
	
	
	//To set Node ID.
	public void setId(String nodeId)
	{
		this.nodeId = nodeId;
	}
	
	
	//To set Node name.
	public void setName(String nodeName)
	{
		this.nodeName = nodeName;
	}
	
	
	//To get Node ID.
	public String getId()
	{
		return nodeId;
	}
	
	
	//To get Node name.
	public String getName()
	{
		return nodeName;
	}
	
	
	//Inserting child node to children node list(nodeChildren).
	public void setChildren(Node child)
	{
		nodeChildren.add(child);
	}
	
	
	//Inserting parent node to parent node list(nodeParents).
	public void setParent(Node parent)
	{
		nodeParents.add(parent);
	}

	
	//To get all children nodes.
	public List<Node> getChildren()
	{
		return nodeChildren;
	}
	
	
	//To get all parent nodes.
	public List<Node> getParents()
	{
		return nodeParents;
	}
	
}
