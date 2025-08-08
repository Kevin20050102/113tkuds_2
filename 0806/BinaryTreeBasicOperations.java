import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class BinaryTreeBasicOperations {
    static TreeNode buildTreeFromLevelOrder(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode curr = q.poll();
            if (arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    static int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    static int max(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(max(root.left), max(root.right)));
    }

    static int min(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(min(root.left), min(root.right)));
    }

    static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int maxW = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            maxW = Math.max(maxW, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return maxW;
    }

    static boolean isComplete(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean end = false;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                end = true;
            } else {
                if (end) return false;
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入二元樹的層序（用空格分隔，null 表示空節點，例如: 1 2 3 null 4）:");
        String[] parts = sc.nextLine().trim().split("\\s+");
        Integer[] arr = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = parts[i].equalsIgnoreCase("null") ? null : Integer.parseInt(parts[i]);
        }
        TreeNode root = buildTreeFromLevelOrder(arr);

        System.out.println("節點總和: " + sum(root));
        System.out.println("節點平均值: " + (sum(root) / (double) count(root)));
        System.out.println("最大值: " + max(root));
        System.out.println("最小值: " + min(root));
        System.out.println("樹的最大寬度: " + maxWidth(root));
        System.out.println("是否為完全二元樹: " + isComplete(root));
    }
}
