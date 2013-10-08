package tree;

/**
 * User: max
 * Date: 9/30/13
 * Time: 10:14 PM
 */
public class BST
{
    private TreeNode root;
    private int nodes = 0;

    public void insert(int val)
    {
        // same as saying the root is null
        if(nodes < 1)
        {
            //insert new root
            root = new TreeNode(val, null);
            nodes++;
        }
        else
        {
            // normal insert
            TreeNode node = root;

            //should prevent duplicates
            while (node != null && val != node.getValue())
            {
                if (val < node.getValue())
                {
                    if (node.getLeft() != null)
                    {
                        node = node.getLeft();
                    }
                    else
                    {
                        // insert node here
                        TreeNode zNode = new TreeNode(val, node);
                        node.setLeft(zNode);
                        nodes++;
                        break;
                    }
                }
                else if (val > node.getValue())
                {
                    if (node.getRight() != null)
                    {
                        node = node.getRight();
                    }
                    else
                    {
                        // insert node here
                        TreeNode zNode = new TreeNode(val, node);
                        node.setRight(zNode);
                        nodes++;
                        break;
                    }
                }
            }
        }
    }

    public TreeNode find(int val)
    {
        if(nodes < 1)
        {
            // tree is empty
            return null;
        }
        else
        {
            // normal find
            TreeNode node = root;

            while (node != null && val != node.getValue())
            {
                if (val < node.getValue())
                {
                    node = node.getLeft();
                }
                else
                {
                    node = node.getRight();
                }
            }

            return node;
        }
    }

    public void delete(int val)
    {
        TreeNode node = find(val);

        // if the node does not exist then there is nothing to do...
        if (node == null)
        {
            return;
        }

        nodes--;

        if (node.getLeft() == null)
        {
            transplant(node, node.getRight());
        }
        else if (node.getRight() == null)
        {
            transplant(node, node.getLeft());
        }
        else
        {
            TreeNode successor = minimum(node.getRight());
            if (successor.getParent() != node)
            {
                transplant(successor, successor.getRight());
                successor.setRight(node.getRight());
                successor.getRight().setParent(successor);
            }

            transplant(node, successor);
            successor.setLeft(node.getLeft());
            successor.getLeft().setParent(successor);
        }
    }

    private void transplant(TreeNode a, TreeNode b)
    {
        if (a == null)
            return;

        if (a.getParent() == null)
        {
            root = b;
        }
        else if (a.getParent().getLeft() == a)
        {
            a.getParent().setLeft(b);
        }
        else
        {
            a.getParent().setRight(b);
        }
        if (b != null)
        {
            b.setParent(a.getParent());
        }
    }

    private TreeNode minimum(TreeNode node)
    {
        TreeNode min = node;

        while(min.getLeft() != null)
        {
            min = min.getLeft();
        }

        return min;
    }

    public int[] inorder()
    {
        int[] result = new int[nodes];
        inorder(root, result, 0);

        return result;
    }

    private int inorder(TreeNode node, int[] result, int count)
    {

        if(node == null)
        {
            return count;
        }
        else if(node != null && node.getLeft() == null && node.getRight() == null)
        {
            result[count] = node.getValue();
            return count + 1;
        }
        else
        {
            count = inorder(node.getLeft(), result, count);
            result[count] = node.getValue();
            count++;
            count = inorder(node.getRight(), result, count);

            return count;
        }

    }

    public int getNodes()
    {
        return nodes;
    }

    public TreeNode getRoot()
    {
        return root;
    }
}
