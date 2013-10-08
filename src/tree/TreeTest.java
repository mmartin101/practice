package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * User: max
 * Date: 9/30/13
 * Time: 10:28 PM
 */
public class TreeTest
{
    public static final int N = 10;

    public static void main(String[] args)
    {
        BST tree = new BST();
        Random r = new Random();
        int[] nums = {10, 5, 15, 2, 8, 13, 19, 1, 7, 9, 14, 17, 21};

        for (int i = 0; i < nums.length; i ++)
        {
            tree.insert(nums[i]);
        }

        tree.delete(10);

//        int[] foo = tree.inorder();
        printTree(tree);
        System.out.println("Done");
    }

    public static void printTree(BST tree)
    {
        int levels = (int) (Math.log(tree.getNodes())/Math.log(2));
        int numsAtLastLevel = (int) Math.pow(2, levels);
        int totalWidth = (numsAtLastLevel * 4) - 1;

        TreeNode root = tree.getRoot();
        StringBuilder[] sb = new StringBuilder[levels + 1];
        print(root, totalWidth/2, 0, sb);
        for(int i = 0; i < sb.length; i ++)
        {
            System.out.println(sb[i].toString());
        }
    }

    public static void print(TreeNode node, int indent, int level, StringBuilder[] sb)
    {
        if (level > sb.length - 1)
        {
            return;
        }

        if (sb[level] == null)
        {
            sb[level] = new StringBuilder();
        }

        for (int i = 0; i <= indent; i++)
        {
            sb[level].append(" ");
        }

        if (node == null)
        {
            sb[level].append("_");
            return;
        }

        sb[level].append(node.getValue());

        if (level + 1 > sb.length - 1)
        {
            return;
        }
        print(node.getLeft(), indent/2, level + 1, sb);
        for (int i = 0; i <= indent/2; i++)
        {
            sb[level + 1].append(" ");
        }
        print(node.getRight(), indent/2, level + 1, sb);

    }
}
