// M09_AVLValidate.java
// 題目：檢查一棵樹是否為合法 AVL 樹 (同時滿足 BST 與 |BF| <= 1)

import java.util.*;

public class M09_AVLValidate {

    // 節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 建立樹 (層序，-1 代表 null)
    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode cur = q.poll();
            // left child
            if (i < arr.length && arr[i] != -1) {
                cur.left = new TreeNode(arr[i]);
                q.add(cur.left);
            }
            i++;
            // right child
            if (i < arr.length && arr[i] != -1) {
                cur.right = new TreeNode(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    // 檢查 BST (利用遞迴上下界)
    static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) &&
               isBST(root.right, root.val, max);
    }

    // 檢查 AVL 平衡：回傳高度；若不平衡回傳 -INF
    static int checkAVL(TreeNode root) {
        if (root == null) return 0;
        int lh = checkAVL(root.left);
        if (lh == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rh = checkAVL(root.right);
        if (rh == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (Math.abs(lh - rh) > 1) return Integer.MIN_VALUE;
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        TreeNode root = buildTree(arr);

        // Step1: 檢查 BST
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        // Step2: 檢查 AVL
        if (checkAVL(root) == Integer.MIN_VALUE) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }
}
