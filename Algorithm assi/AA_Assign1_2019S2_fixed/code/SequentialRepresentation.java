import java.io.PrintWriter;

/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {
    private T[] tree;
    private int treeLength;

    /**
     * Constructs empty graph.
     */
    @SuppressWarnings("unchecked")
    public SequentialRepresentation() {
        tree = (T[]) new Object[0];
        treeLength = 0;
        // Implement me!
    } // end of SequentialRepresentation()

    @Override
    @SuppressWarnings("unchecked")
    public void setRootNode(T nodeLabel) {
        if (treeLength > 0){
            System.err.println("Error: Root node has been set!");
        }
        else{
            tree = (T[])new Object[1];
            tree[0] = nodeLabel;
            treeLength = 1;
        }
        // Implement me!
    } // end of setRootNode()

    /**
     * find the node index that has the label
     * @param label the label of node to be search
     * @return index of node
     */
    private int findNodeIndex(T label){
        for(int i = 0; i < treeLength; i++) {
            if (tree[i].equals(label)){
                return i;
            }
        }
        return -10000;
    }

    /**
     * add length of the tree if max size exceeded.
     */
    @SuppressWarnings("unchecked")
    private T[]addArrayLength(T[] tree){
    	if (treeLength == 1) {
    		T[] newTree = (T[])new Object[4];	
    		System.arraycopy(tree,0,newTree,0,treeLength);
    		return newTree;
    	}
        T[] newTree = (T[])new Object[treeLength * 2];
        System.arraycopy(tree,0,newTree,0,treeLength);
        return newTree;
    }

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        if ( leftChild.equals("") || rightChild.equals("") || leftChild == null || rightChild == null){
            System.err.println("Error: Child can not be null!");
        }
        else{
            int index = findNodeIndex(srcLabel);
            if (index < 0) {
                System.err.println("Error: Node does not exist!");
            }
            else{
                if (treeLength <= 2*index + 2){
                    tree = addArrayLength(tree);
                }
                tree[2 * index + 1] = leftChild;
                tree[2 * index + 2] = rightChild;
                treeLength += 2;
            }
        }
        // Implement me!
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
        for (int i = 0;i<treeLength;i++){
            if (tree[i].equals(nodeLabel)){
                return true;
            }
        }
        // Implement me!
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        int index = findNodeIndex(nodeLabel);
        if(index < 0){
            System.err.println("Error: Given node does not exist!");
        }
        else if (index==0){
            return tree[index].toString();
        }
        else{
            return nodeLabel + " " + tree[(index-1)/2].toString();
        }
        // Implement me!
        return null;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
        int index = findNodeIndex(nodeLabel);
        if(index < 0){
            System.err.println("Error: Given node does not exist!");
        }
        else if(treeLength <= 2*index+1){
            return nodeLabel.toString();
        }
        else{
            return nodeLabel.toString() + " " + tree[2*index+1] + " " + tree[2*index+2];
        }
        // Implement me!
        return null;
    } // end of findParent

    private String preOrder(int index){
        if (treeLength <= index) {
        	return "";
        }
        String result = tree[index].toString();
        result += " ";
        result += preOrder(2*index+1).trim();
        result += " ";
        result += preOrder(2*index+2).trim();
        return result;
    }

    @Override
    public void printInPreorder(PrintWriter writer) {
        if(treeLength==0){
            writer.println("");
        }
        else{
            writer.println(preOrder(0));
        }
        // Implement me!
    } // end of printInPreorder

    private String inOrder(int index){
        if (treeLength <= index)
            return "";

        String result = inOrder(2*index+1).trim();
        result += " ";
        result += tree[index].toString();
        result += " ";
        result += inOrder(2*index+2).trim();
        return result;
    }
    @Override
    public void printInInorder(PrintWriter writer) {
        if(treeLength==0){
            writer.println("");
        }
        else{
            writer.println(inOrder(0));
        }
        // Implement me!
    } // end of printInInorder

    private String postOrder(int index){
        if (treeLength <= index)
            return "";

        String result = postOrder(2*index+1).trim();
        result += " ";
        result += postOrder(2*index+2).trim();
        result += " ";
        result += tree[index].toString();
        return result;
    }
    @Override
    public void printInPostorder(PrintWriter writer) {
        if(treeLength==0){
            writer.println("");
        }
        else{
            writer.println(postOrder(0));
        }
        // Implement me!
    } // end of printInPostorder

    public T[] getTree() {
        return tree;
    }

    public void setTree(T[] tree) {
        this.tree = tree;
    }

    public int getTreeLength() {
        return treeLength;
    }

    public void setTreeLength(int treeLength) {
        this.treeLength = treeLength;
    }
} // end of class SequentialRepresentation