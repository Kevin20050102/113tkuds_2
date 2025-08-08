import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入前序遍歷：");
        int[] preorder = Arrays.stream(scanner.nextLine().split(" "))
                               .mapToInt(Integer::parseInt).toArray();

        System.out.println("請輸入中序遍歷：");
        int[] inorder = Arrays.stream(scanner.nextLine().split(" "))
                              .mapToInt(Integer::parseInt).toArray();

        TreeNode root = buildTree(preorder, inorder);
        System.out.println("重建完成，根節點：" + (root != null ? root.val : "null"));
    }

    static int preIndex = 0;
    static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return helper(preorder, 0, inorder.length - 1, inMap);
    }

    static TreeNode helper(int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;
        int val = preorder[preIndex++];
        TreeNode root = new TreeNode(val);
        int inIndex = inMap.get(val);
        root.left = helper(preorder, inStart, inIndex - 1, inMap);
        root.right = helper(preorder, inIndex + 1, inEnd, inMap);
        return root;
    }
}
