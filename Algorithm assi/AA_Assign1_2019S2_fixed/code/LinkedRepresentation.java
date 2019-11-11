import java.io.PrintWriter;

/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016. 
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation<T> implements BSPTree<T> {
    private T label; //label of current node
    private LinkedRepresentation<T> parent; // parent child of current node
    private LinkedRepresentation<T> left; // left child of current node
    private LinkedRepresentation<T> right; // right child of current node

    /**
     * Constructs empty tree.
     */
    public LinkedRepresentation() {
        label = null;
        left = null;
        right = null;
        parent = null;
        // Implement me!
    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (label == null){
            label = nodeLabel;
        }
        else{
            System.err.println("Error: Root node has been set!");
        }
        // Implement me!
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
            
        if ( leftChild.equals("") || rightChild.equals("") || leftChild == null || rightChild == null){
            System.err.println("Error: Child can not be null!");
        }
        else{
//        	System.out.println("label: " + label + "; " + srcLabel);
            if (label.equals(srcLabel)){
                LinkedRepresentation<T> lc = new LinkedRepresentation<>();
                LinkedRepresentation<T> rc = new LinkedRepresentation<>();
                lc.setLabel(leftChild);
                rc.setLabel(rightChild);
                setLeft(lc);
//                left = lc;
                setRight(rc);
//                right = rc;
                lc.setParent(this);
                rc.setParent(this);
            }
            else{
                LinkedRepresentation<T> srcNode = null; //the source node to be split
                srcNode = findNodeHelper(left, srcLabel);
                if (srcNode == null)
                    srcNode = findNodeHelper(right, srcLabel);
                
                if (srcNode == null)
                    System.err.println("Error: Node does not exist!");
                else{
                    LinkedRepresentation<T> lc = new LinkedRepresentation<>();
                    LinkedRepresentation<T> rc = new LinkedRepresentation<>();
                    lc.setLabel(leftChild);
                    rc.setLabel(rightChild);
                    srcNode.setLeft(lc);
                    srcNode.setRight(rc);
                    lc.setParent(srcNode);
                    rc.setParent(srcNode);
                }
            }
        }
    }

    /**
     * recursively find if a node exist in the tree
     * @param lr the start node for searching
     * @param nodelabel the label of node to be searched
     * @return the node object if the node exist;
     * null otherwise
     */
    private LinkedRepresentation<T> findNodeHelper(LinkedRepresentation<T> lr, T nodelabel){
        if (nodelabel.equals(""))
            return null;

        if (lr == null)
            return null;

        LinkedRepresentation<T> lc = null; //left child
        LinkedRepresentation<T> rc = null; // right child

        if (lr.getLabel().equals(nodelabel))
            return lr;
        
        lc = findNodeHelper(lr.getLeft(),nodelabel);
        rc = findNodeHelper(lr.getRight(),nodelabel);

        if (lc != null)
            return lc;

        return rc;
    }

    @Override
    public boolean findNode(T nodeLabel) {
        // check if root the node
        if (label.equals(nodeLabel))
            return true;

        // check if the node in left child
        if (findNodeHelper(left, nodeLabel) != null)
            return true;
        
        //check if the node in right child
        if (findNodeHelper(right, nodeLabel) != null)
            return true;
            
        // Implement me!
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        if (label == null)
            System.err.println("Error: Given node does not exist!");
            
        else if (label.equals(nodeLabel))
            return label.toString();

        else{
            LinkedRepresentation<T> node = findNodeHelper(left, nodeLabel);
            if (node != null)
                return node.getLabel() + " " + node.getParent().getLabel();
            
            node = findNodeHelper(right, nodeLabel);
            if (node != null)
                return node.getLabel() + " " + node.getParent().getLabel(); 
        }
        // Implement me!
        return null;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
//        String lc = "";
//        String rc = "";

        if (label.equals(nodeLabel)) {
            if (left != null)
                return label + " " + left.getLabel() + " " + right.getLabel();
            return label.toString();
        }

        LinkedRepresentation<T> node = findNodeHelper(left, nodeLabel);
        if (node != null) {
            if (node.getLeft() != null) 
                return node.getLabel() + " " + node.getLeft().getLabel() + " " + node.getRight().getLabel();
            return node.getLabel().toString();
        }

        node = findNodeHelper(right, nodeLabel);
        if (node != null) {
            if (node.getLeft() != null) 
                return node.getLabel() + " " + node.getLeft().getLabel() + " " + node.getRight().getLabel();
            return node.getLabel().toString();
        }
        
        System.err.println("Error: Given node does not exist!");
        // Implement me!
        return null;
    } // end of findParent

    private String preOrder(LinkedRepresentation<T> node){
        if (node == null)
            return "";       
        String result = node.getLabel().toString();
        result = result + " ";
        result += preOrder(node.getLeft());
        result += preOrder(node.getRight());

        return result;
    }

    @Override
    public void printInPreorder(PrintWriter writer) {
        if (label == null){
            writer.println("");
        }
        else {
            writer.println(label + " " + preOrder(left).trim() + " " + preOrder(right).trim());
        }
// Implement me!
    } // end of printInPreorder


    private String inOrder (LinkedRepresentation<T> node){
        if (node == null)
            return "";

        String result = inOrder(node.getLeft()).trim();
        result = result + " ";
        result = result + node.getLabel();
        result = result + " ";
        result += inOrder(node.getRight()).trim();

        return result;
    }

    @Override
    public void printInInorder(PrintWriter writer) {
        if (label == null){
            writer.println("");
        }
        else{
            writer.println(inOrder(left).trim() + " " + label + " " + inOrder(right).trim());
        }
        // Implement me!
    } // end of printInInorder

    private String postOrder(String result, LinkedRepresentation<T> node){
        if (node == null)
            return "";
        result = postOrder(result,node.getLeft()).trim();
        result = result + " ";
        result += postOrder(result,node.getRight()).trim();
        result = result + " ";
        result = result + node.getLabel();

        return result;
    }
    @Override
    public void printInPostorder(PrintWriter writer) {
        if (label == null){
            writer.println("");
        }
        else{
            writer.println(postOrder("",left).trim() + " " + postOrder("",right).trim() + " " + label);
        }
        // Implement me!
    } // end of printInPostorder


    /**
     * accessor of label
     * @return {@code label} label of current node
     */
    public T getLabel() {
        return label;
    }

    /**
     * mutator of label
     * @param label label of current node
     */
    public void setLabel(T label) {
        this.label = label;
    }

    /**
     * accessor of parent node
     * @return {@code parent} parent node
     */
    public LinkedRepresentation<T> getParent() {
        return parent;
    }

    /**
     * mutator of parent node
     * @param parent parent node
     */
    public void setParent(LinkedRepresentation<T> parent) {
        this.parent = parent;
    }

    /**
     * mutator of left child
     * @return {@code left} left child node
     */
    public LinkedRepresentation<T> getLeft() {
        return left;
    }

    /**
     * accessor of left child
     * @param left left child node
     */
    public void setLeft(LinkedRepresentation<T> left) {
        this.left = left;
    }

    /**
     * accessor of right child node
     * @return {@code right} right child node
     */
    public LinkedRepresentation<T> getRight() {
        return right;
    }

    /**
     * mutator of right child node
     * @param right right child node
     */
    public void setRight(LinkedRepresentation<T> right) {
        this.right = right;
    }

} // end of class LinkedRepresentation
