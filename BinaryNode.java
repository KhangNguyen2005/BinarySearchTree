package assign08;
/**
 * Represents a node in a binary tree.
 * Each node contains an element and references to its left and right child nodes.
 * 
 * @author Phuc Do and Khang Nguyen
 * @version March 21, 2024
 */
public class BinaryNode<Type> {
    public Type element;
    public BinaryNode<Type> leftChild;
    public BinaryNode<Type> rightChild;

    public int dotLabel;

    /**
     * Constructs a BinaryNode with the specified element, left child, and right child.
     * 
     * @param element the element to be stored in the node
     * @param lNode the left child node
     * @param rNode the right child node
     */
    public BinaryNode(Type element, BinaryNode<Type> lNode, BinaryNode<Type> rNode) {
        this.element = element;
        this.leftChild = lNode;
        this.rightChild = rNode;
        this.dotLabel++;
    }

    /**
     * Constructs a BinaryNode with the specified element and no children.
     * 
     * @param element the element to be stored in the node
     */
    public BinaryNode(Type element) {
        this.element = element;
        this.leftChild = null;
        this.rightChild = null;
        this.dotLabel++;
    }

    /**
     * Generates a DOT representation of the binary tree rooted at this node.
     * 
     * @return the DOT representation of the binary tree
     */
    public String generateDot() {
        String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
        if (leftChild != null) {
            ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
        }
        if (rightChild != null) {
            ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
        }

        return ret;
    }
}