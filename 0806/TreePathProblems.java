import java.util.*;

public class TreePathProblems {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入節點（層序，-1 表示 null）：");
        String[] input = scanner.nextLine().split(" ");
        List<TreeNode> nodes = new ArrayList<>();

        for (String val : input) {
            if (val.equals("-1")) nodes.add(null);
            else nodes.add(new TreeNode(Integer.parseInt(val)));
        }

        for (int i = 0, j = 1; j < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            if (node != null) {
                if (j < nodes.size()) node.left = nodes.get(j++);
                if (j < nodes.size()) node.right = nodes.get(j++);
            }
        }

        TreeNode root = nodes.get(0);

        System.out.println("根到葉節點路徑：");
        List<List<Integer>> paths = new ArrayList<>();
        getAllPaths(root, new ArrayList<>(), paths);
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }

    static void getAllPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            getAllPaths(node.left, path, result);
            getAllPaths(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }
}
