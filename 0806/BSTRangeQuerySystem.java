import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int v) { val = v; }
}

public class BSTRangeQuerySystem {
    static BSTNode insert(BSTNode root, int val) {
        if (root == null) return new BSTNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    static void rangeQuery(BSTNode root, int min, int max, List<Integer> list) {
        if (root == null) return;
        if (root.val > min) rangeQuery(root.left, min, max, list);
        if (root.val >= min && root.val <= max) list.add(root.val);
        if (root.val < max) rangeQuery(root.right, min, max, list);
    }

    static int rangeCount(List<Integer> list) {
        return list.size();
    }

    static int rangeSum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    static int closest(BSTNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if (target < root.val) root = root.left;
            else if (target > root.val) root = root.right;
            else break;
        }
        return closest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTNode root = null;
        System.out.println("請輸入 BST 節點值（用空格分隔）:");
        String[] nums = sc.nextLine().trim().split("\\s+");
        for (String s : nums) root = insert(root, Integer.parseInt(s));

        System.out.print("輸入範圍查詢最小值: ");
        int min = sc.nextInt();
        System.out.print("輸入範圍查詢最大值: ");
        int max = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        rangeQuery(root, min, max, list);
        System.out.println("範圍內的節點: " + list);
        System.out.println("節點數量: " + rangeCount(list));
        System.out.println("節點總和: " + rangeSum(list));

        System.out.print("輸入要找最接近的值: ");
        int target = sc.nextInt();
        System.out.println("最接近的節點值: " + closest(root, target));
    }
}
