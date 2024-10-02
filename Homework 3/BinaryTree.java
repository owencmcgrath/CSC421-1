import java.util.ArrayList;
import java.util.List;

/**
* Generic class that implements a binary tree structure.
* @author Dave Reed, Owen McGrath
* @version 8/30/24
*/
public class BinaryTree<T>
{
    protected TreeNode<T> root; //the root is defined as a TreeNode class

    /**
    * Constructs an empty binary tree.
    */
    public BinaryTree()
    {
        this.root = null; //immediately set the root to a null value
    }

    /**
    * Adds a value to the binary tree (at a random  location).
    * @param value the value to be added
    */
    public void add(T value)
    {
        this.root = this.add(this.root, value);
    }

    private TreeNode<T> add(TreeNode<T> current, T value)
    {
        if (current == null) //if the current value is null...
        {
            current = new TreeNode<T>(value, null, null); //... create a new tree node with a ?value?
        }
        //randomly choose which side the value is added to;
        else if (Math.random() < 0.5)
        {
            current.setLeft(this.add(current.getLeft(), value));
        }
        else
        {
            current.setRight(this.add(current.getRight(), value));
        }
        return current;
    }

    /**
    * Determines the size of the binary tree.
    * @return the size (number of nodes in the tree)
    */
    public int size()
    {
        if (this.root == null)
        {   
            return 0;
        }
        //System.out.println("Calculating size of the tree...");
        return this.root.getSize(); //this is stored in each TreeNode
    }

    /**
    * Determines the size of the binary tree.
    * @return the size (number of nodes in the tree)
    */
    public int height()
    {
        if (this.root == null)
        {
            return 0;
        }
        //System.out.println("Calculating height of the tree...");
        return this.root.getHeight(); //this is stored in each TreeNode
    }

    /**
    * Determines whether the tree contains a particular value.
    * @param value the value to be searched for
    * @return true if value is in the tree, otherwise false
    */
    public boolean contains(T value)
    {
        return this.contains(this.root, value);
    }

    private  boolean contains(TreeNode<T> current, T value)
    {
        if (current == null)
        {
            return false;
        }
        else
        {
            return value.equals(current.getData())||this.contains(current.getLeft(), value)||this.contains(current.getRight(), value);
        }
    }

    /**
    * Removes one occurrence of the specified value.
    * @param value the value to be removed
    * @return true if the value was found and removed, else false
    */
    public boolean remove(T value)
    {
        if (!this.contains(value))
        {
            return false;
        }
        else
        {
            this.root = this.remove(this.root, value);
            return true;
        }
    }

    private TreeNode<T> remove(TreeNode<T> current, T value)
    {
        if (value.equals(current.getData()))
        {
            if (current.getLeft() == null)
            {
                current = current.getRight();
            }
            else
            {
                TreeNode<T> righty = current.getLeft();
                while (righty.getRight() != null)
                {
                    righty = righty.getRight();
                }
                current.setData(righty.getData());
                current.setLeft(this.remove(current.getLeft(), current.getData()));
            }
        }
        else if (this.contains(current.getLeft(), value))
        {
            current.setLeft(this.remove(current.getLeft(), value));
        }
        else
        {
            current.setRight(this.remove(current.getRight(), value));
        }
        return current;
    }

    /**
    * Converts the tree to a String using an inorder traversal.
    * @return the String representation of the tree.
    */
    public String toString()
    {
        List<T> listRepresentation = this.asList();
        System.out.println(listRepresentation);
        return listRepresentation.toString();
    }

    private String toString(TreeNode<T> current)
    {
        if (current == null) //if the root is empty, then there is no need to parse
        {
            return "";
        }
        return this.toString(current.getLeft()) + current.getData().toString() + "," + this.toString(current.getRight()); //the tree as a string
    }

    /**
    * Converts the contents of the tree to a list
    * @return contentsOfTree -> everything in the tree as a list
    */
    public List <T> asList()
    {
        if (this.root == null)
        {
            return new ArrayList<>(); //return an empty
        }
        return this.asList(this.root);
    }

    private List <T> asList(TreeNode<T> current)
    {
        List<T> contentsOfTree = new ArrayList<>(); //arraylist for O(1) gets

        if (current == null) //if the root is null, then there is no need to parse
        {
            return contentsOfTree;
        }

        contentsOfTree.addAll(this.asList(current.getLeft())); //have to use add all because we aer adding a collection, not just a single element
        contentsOfTree.add(current.getData()); //whihc is why we get away with it here
        contentsOfTree.addAll(this.asList(current.getRight()));

        return contentsOfTree;
    }

    ////////////////////////////////////////////////////////////////////////////
    /// FOR TESTING PURPOSES
    ////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args)
    {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(7);
        tree.add(2);
        tree.add(12);
        tree.add(1);
        tree.add(5);
        tree.add(6);
        tree.add(99);
        tree.add(88);
        System.out.println(tree);

        System.out.println("size = " + tree.size());
        System.out.println(tree.contains(2) + " " + tree.contains(7)
                                            + " " + tree.contains(8));

        tree.remove(99);
        tree.remove(7);
        System.out.println(tree);
        System.out.println("size = " + tree.size());
        System.out.println(tree.contains(2) + " " + tree.contains(7)
                                            + " " + tree.contains(8));
    }
}
