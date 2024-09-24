/**
 * Generic class that implements a binary search tree, building upon the
 * existing BinaryTree class.
 *   @param <T> the type of value stored, must be Comparable<T>
 *   @author Dave Reed
 *   @version 8/30/24
 */
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> {
    /**
     * Overrides the super.add method to add according to the BST property.
     *   @param value the value to be added to the tree
     */
    public void add(T value) {
        this.root = this.add(this.root, value);
    }
    private TreeNode<T> add(TreeNode<T> current, T value) {
        if (current == null) {
            return new TreeNode<T>(value, null, null);
        }

        if (value.compareTo(current.getData()) < 0) {
            current.setLeft(this.add(current.getLeft(), value));
        }
        else {
            current.setRight(this.add(current.getRight(), value));
        }
        return current;
    }

    /**
     * Overrides the super.contains method to take advantage of binary search.
     *   @param value the value to be searched for
     *   @return true if that value is in the tree, otherwise false
     */
    public boolean contains(T value) {
        return this.contains(this.root, value);
    }
    private boolean contains(TreeNode<T> current, T value) {
        if (current == null) {
            return false;
        }
        else if (value.equals(current.getData())) {
                return true;
        }
        else if (value.compareTo(current.getData()) < 0) {
            return this.contains(current.getLeft(), value);
        }
        else {
            return this.contains(current.getRight(), value);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    /// FOR TESTING PURPOSES
    ////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
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
