import java.util.*;

public class BSTKthElement {
    static BSTNode insert(BSTNode root, int val) {
        if (root == null) return new BSTNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    static void inorder(BSTNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    static int kthSmallest(BSTNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(k - 1);
    }

    static int kthLargest(BSTNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(list.size() - k);
    }

    static List<Integer> rangeK(BSTNode root, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.subList(k - 1, j);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTNode root = null;
        System.out.println("輸入 BST 節點值:");
        String[] nums = sc.nextLine().trim().split("\\s+");
        for (String s : nums) root = insert(root, Integer.parseInt(s));

        System.out.print("輸入 k (第k小): ");
        int k = sc.nextInt();
        System.out.println("第 " + k + " 小: " + kthSmallest(root, k));

        System.out.print("輸入 k (第k大): ");
        k = sc.nextInt();
        System.out.println("第 " + k + " 大: " + kthLargest(root, k));

        System.out.print("輸入 k 與 j (找第k小到第j小): ");
        int kk = sc.nextInt(), jj = sc.nextInt();
        System.out.println("結果: " + rangeK(root, kk, jj));
    }
}
