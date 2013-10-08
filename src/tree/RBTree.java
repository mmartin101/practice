package tree;

/**
 * User: max
 * Date: 10/7/13
 * Time: 10:39 PM
 */
public class RBTree
{
    private TreeNode root;

    public RBTree()
    {
    }

    public void insert(int x)
    {
        TreeNode prev = null;
        TreeNode curr = root;

        while (curr != null)
        {
            prev = curr;
            if (x == curr.getValue())
                return;

            if (x < curr.getValue())
            {
                // go left
                curr = curr.getLeft();
            }
            else
            {
                curr.getRight();
            }
        }

        TreeNode z = new TreeNode(x, prev);
        if (prev == null)
        {
            root = z;
        }
        else if (z.getValue() < prev.getValue())
        {
            prev.setLeft(z);
        }
        else
        {
            prev.setRight(z);
        }

        //no need to set z.right or z.left because those are already null
        //z.black is false (default) indicating that it is red

        insertFixup(z);
    }

    private void insertFixup(TreeNode z)
    {
        while (!z.getParent().isBlack())
        {
            if (z.getParent() == z.getParent().getParent().getLeft())
            {
                //z's uncle
                TreeNode y = z.getParent().getParent().getRight();
                if (!y.isBlack())
                {
                    z.getParent().setBlack(true);
                    y.setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    z = z.getParent().getParent();
                }
                else if (z == z.getParent().getRight())
                {
                    z = z.getParent();
                    rotateLeft(z);


                }
            }
        }
    }

    private void rotateLeft(TreeNode x)
    {
        TreeNode y = x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != null)
        {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());
        if (x.getParent() == null)
        {
            root = y;
        }
        else if (x == x.getParent().getLeft())
        {
            x.getParent().setLeft(y);
        }
        else
        {
            x.getParent().setRight(y);

        }

        y.setLeft(x);
        x.setParent(y);
    }

    private void rotateRight(TreeNode x)
    {
        TreeNode y = x.getLeft();
        x.setLeft(y.getRight());

        if (y.getRight() != null)
        {
            y.getRight().setParent(x);
        }

        y.setParent(x.getParent());
        if (x.getParent() == null)
        {
            root = y;
        }
        else if (x == x.getParent().getLeft())
        {
            x.getParent().setLeft(y);
        }
        else
        {
            x.getParent().setRight(y);

        }

        y.setRight(x);
        x.setParent(y);
    }

}
