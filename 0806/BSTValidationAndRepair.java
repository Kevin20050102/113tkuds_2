import java.util.*;

public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請依序輸入節點（使用 -1 表示 null，依層序輸入）：");

        List<TreeNode> nodes = new ArrayList<>();
        String[] parts = scanner.nextLine().split(" ");
        for (String part : parts) {
            if (part.equals("-1")) nodes.add(null);
            else nodes.add(new TreeNode(Integer.parseInt(part)));
        }

        for (int i = 0, j = 1; j < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            if (node != null) {
                if (j < nodes.size()) node.left = nodes.get(j++);
                if (j < nodes.size()) node.right = nodes.get(j++);
            }
        }

        TreeNode root = nodes.get(0);
        System.out.println("是否為合法 BST：" + isValidBST(root));

        // 修復邏輯請根據需求加入
    }

    static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
}

