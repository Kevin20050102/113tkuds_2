import java.util.*;

public class M08_BSTRangedSum {
    // 樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(arr);
        int sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }

    /**
     * 建立樹（層序輸入，-1 表 null）
     * 複雜度: O(n)
     */
    private static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode node = q.poll();
            if (node == null) continue;

            // 左子樹
            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;

            // 右子樹
            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    /**
     * 遞迴計算 BST 區間總和
     * 複雜度: O(h)，h 為樹高，最壞 O(n)
     */
    private static int rangeSumBST(TreeNode node, int L, int R) {
        if (node == null) return 0;
        if (node.val < L) {
            return rangeSumBST(node.right, L, R);
        } else if (node.val > R) {
            return rangeSumBST(node.left, L, R);
        } else {
            return node.val
                 + rangeSumBST(node.left, L, R)
                 + rangeSumBST(node.right, L, R);
        }
    }
}
