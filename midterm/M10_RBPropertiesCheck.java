import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color; // 'B' or 'R'
        Node left, right;
        Node(int v, char c) {
            val = v;
            color = c;
        }
    }

    static Node buildTree(int n, int[] vals, char[] colors) {
        if (n == 0 || vals[0] == -1) return null;

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            if (vals[i] == -1) {
                nodes[i] = null; // 空節點
            } else {
                nodes[i] = new Node(vals[i], colors[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nodes[i] != null) {
                int li = 2 * i + 1, ri = 2 * i + 2;
                if (li < n) nodes[i].left = nodes[li];
                if (ri < n) nodes[i].right = nodes[ri];
            }
        }
        return nodes[0];
    }

    // 檢查紅紅相鄰、並回傳黑高度
    static class Result {
        boolean ok;
        int blackHeight;
        String error;
        Result(boolean ok, int bh, String err) {
            this.ok = ok;
            this.blackHeight = bh;
            this.error = err;
        }
    }

    static Result dfs(Node root, int index) {
        if (root == null) {
            return new Result(true, 1, ""); // NIL 節點黑色
        }

        // 檢查紅紅相鄰
        if (root.color == 'R') {
            if (root.left != null && root.left.color == 'R') {
                return new Result(false, -1, "RedRedViolation at index " + index);
            }
            if (root.right != null && root.right.color == 'R') {
                return new Result(false, -1, "RedRedViolation at index " + index);
            }
        }

        Result L = dfs(root.left, index * 2 + 1);
        if (!L.ok) return L;

        Result R = dfs(root.right, index * 2 + 2);
        if (!R.ok) return R;

        // 黑高度不一致
        if (L.blackHeight != R.blackHeight) {
            return new Result(false, -1, "BlackHeightMismatch");
        }

        int bh = L.blackHeight + (root.color == 'B' ? 1 : 0);
        return new Result(true, bh, "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] vals = new int[n];
        char[] colors = new char[n];

        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            String c = sc.next();
            colors[i] = (vals[i] == -1 ? 'B' : c.charAt(0)); // 空節點視為黑
        }

        Node root = buildTree(n, vals, colors);

        if (root == null) {
            System.out.println("RB Valid");
            return;
        }

        // 檢查 1. 根節點必須黑色
        if (root.color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 檢查 2 & 3
        Result res = dfs(root, 0);
        if (!res.ok) {
            System.out.println(res.error);
        } else {
            System.out.println("RB Valid");
        }
    }
}
