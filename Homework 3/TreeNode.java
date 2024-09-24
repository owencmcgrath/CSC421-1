/**
 * Generic TreeNode class for storing nodes in a binary tree.
 *   @author Dave Reed
 *   @version 8/30/24
 */
public class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int size;
    private int height;

    /**
     * Constructs a node with the specified contents.
     *   @param d the data value to be stored
     *   @param l the left child/subtree
     *   @param r the right child/subtree
     */
    public TreeNode(T d, TreeNode<T> l, TreeNode<T> r) {
        this.data = d;
        this.left = l;
        this.right = r;
    }

    /**
     * Accessor method for the data value.
     *   @return the data value stored in the node
     */
    public T getData() {
        return this.data;
    }

    /**
     * Accessor method for the left child/subtree.
     *   @return the left child/subtree
     */
    public TreeNode<T> getLeft() {
        return this.left;
    }

    /**
     * Accessor method for the right child/subtree.
     *   @return the right child/subtree
     */
    public TreeNode<T> getRight() {
        return this.right;
    }

    /**
     * Setter method for changing the data value.
     *   @param newData the new data value
     */
    public void setData(T newData) {
        this.data = newData;
    }

    /**
     * Setter method for changing the left child/subtree.
     *   @param newLeft the new left child/subtree
     */
    public void setLeft(TreeNode<T> newLeft) {
        this.left = newLeft;
    }

    /**
     * Setter method for changing the right child/subtree.
     *   @param newRight the new right child/subtree
     */
    public void setRight(TreeNode<T> newRight) {
        this.right = newRight;
    }

    public int getSize(int size)
    {
        this.size = size;
        size = this.getLeft().getData() + this.getRight().getData() + 1;
        return size;
    }

    public int getHeight(int height)
    {
        this.height = height;
        return height;
    }
}
