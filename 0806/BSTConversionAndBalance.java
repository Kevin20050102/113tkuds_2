import java.util.*;

public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入有序陣列以建立平衡 BST（例如：1 2 3 4 5 6）：");
        int[] sortedArray = Arrays.stream(scanner.nextLine().split(" "))
                                  .mapToInt(Integer::parseInt).toArray();

        TreeNode root = sortedArrayToBST(sortedArray, 0, sortedArray.length - 1);
        System.out.println("平衡 BST 建立完成。根節點：" + root.val);
    }

    static TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, left, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, right);
        return node;
    }
}

