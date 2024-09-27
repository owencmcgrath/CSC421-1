/**
* Generic TreeNode class for storing nodes in a binary tree.
*   @author Dave Reed
*   @version 8/30/24
*/
public class TreeNode<T>
{
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
	private int leftSize;
	private int rightSize;
	private int leftHeight;
	private int rightHeight;

    /**
    * Constructs a node with the specified contents.
    *   @param d the data value to be stored
    *   @param l the left child/subtree
    *   @param r the right child/subtree
    */
    public TreeNode(T d, TreeNode<T> l, TreeNode<T> r)
    {
        this.data = d;
        this.left = l;
        this.right = r;
    }

    /**
    * Accessor method for the data value.
    *   @return the data value stored in the node
    */
    public T getData()
    {
        return this.data;
    }

    /**
    * Accessor method for the left child/subtree.
    *   @return the left child/subtree
    */
    public TreeNode<T> getLeft()
    {
        return this.left;
    }

    /**
    * Accessor method for the right child/subtree.
    *   @return the right child/subtree
    */
    public TreeNode<T> getRight()
    {
        return this.right;
    }

    /**
    * Setter method for changing the data value.
    *   @param newData the new data value
    */
    public void setData(T newData)
    {
        this.data = newData;
    }

    /**
    * Setter method for changing the left child/subtree.
    *   @param newLeft the new left child/subtree
    */
    public void setLeft(TreeNode<T> newLeft)
    {
        this.left = newLeft;
    }

    /**
    * Setter method for changing the right child/subtree.
    *   @param newRight the new right child/subtree
    */
    public void setRight(TreeNode<T> newRight)
    {
        this.right = newRight;
    }

   	public int getLeftSize()
	{
    	return leftSize;
	}

    public int getRightSize()
    {
        return rightSize;
    }

    public int getLeftHeight()
    {
        return leftHeight;
    }
    public int getRightHeight()
    {
        return rightHeight;
    }

    /**
    * a method that calculates the size in O(1) time
    * @return size -> the height of the left and right subnodes
    */
    public int size()
    {
        int size = 1; //start off with one since we will need it anyway
        if (this.left != null)
        {
            size += this.left.size();
        }

        if (this.right != null)
        {
            size += this.right.size();
        }
        return size;
    }

    /**
    * a method that calculates the height of subnodes in O(1) and stores it in the node
    * @return height -> the height of the left and right subnodes
    */
    public int height()
    {
        if (this.left != null)
        {
            leftHeight += this.left.height();
        }

        if (this.right != null)
        {
            rightHeight += this.right.height();
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
    * a method that is designed to update the left size, left height, right size, and right height
    */
	public void updateSizeAndHeight()
	{
        if (this.left != null)
        {
            this.leftSize = this.left.size();
        }
        else
        {
            this.leftSize = 0;
        }

        if (this.right != null)
        {
            this.rightSize = this.right.size();
        }
        else
        {
            this.rightSize = 0;
        }

        if (this.left != null)
        {
            this.leftHeight = this.left.height();
        }
        else
        {
            this.leftSize = 0;
        }

        if (this.right != null)
        {
            this.rightHeight = this.right.height();
        }
        else
        {
            this.rightHeight = 0;
        }
	}
}
