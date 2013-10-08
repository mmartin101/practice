package tree;

/**
 * User: max
 * Date: 9/30/13
 * Time: 10:03 PM
 */
public class TreeNode
{
    private int value;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private boolean black = false;

    public TreeNode(int value, TreeNode parent)
    {
        this.value = value;
        this.parent = parent;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public TreeNode getParent()
    {
        return parent;
    }

    public void setParent(TreeNode parent)
    {
        this.parent = parent;
    }

    public TreeNode getLeft()
    {
        return left;
    }

    public void setLeft(TreeNode left)
    {
        this.left = left;
    }

    public TreeNode getRight()
    {
        return right;
    }

    public void setRight(TreeNode right)
    {
        this.right = right;
    }

    public void setBlack(boolean black)
    {
        this.black = black;
    }

    public boolean isBlack()
    {
        return black;
    }
}
