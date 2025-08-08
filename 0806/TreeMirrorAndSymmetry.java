import java.util.*;

public class TreeMirrorAndSymmetry {
    static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();
            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                q.add(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    static boolean isSame(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("輸入主樹層序 (空格分隔, null 表空):");
        String[] parts = sc.nextLine().split("\\s+");
        Integer[] arr = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++)
            arr[i] = parts[i].equals("null") ? null : Integer.parseInt(parts[i]);
        TreeNode root = buildTree(arr);

        System.out.println("是否對稱: " + isSymmetric(root));

        System.out.println("鏡像後層序輸出:");
        TreeNode mirrored = mirror(root);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(mirrored);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                System.out.print("null ");
                continue;
            }
            System.out.print(n.val + " ");
            q.add(n.left);
            q.add(n.right);
        }

        System.out.println("\n輸入子樹層序:");
        String[] subParts = sc.nextLine().split("\\s+");
        Integer[] subArr = new Integer[subParts.length];
        for (int i = 0; i < subParts.length; i++)
            subArr[i] = subParts[i].equals("null") ? null : Integer.parseInt(subParts[i]);
        TreeNode subTree = buildTree(subArr);

        System.out.println("是否為子樹: " + isSubtree(root, subTree));
    }
}
