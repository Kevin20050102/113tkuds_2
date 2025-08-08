import java.util.*;

public class MultiWayTreeAndDecisionTree {
    static class MultiTreeNode {
        String value;
        List<MultiTreeNode> children = new ArrayList<>();
        MultiTreeNode(String val) { value = val; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MultiTreeNode root = new MultiTreeNode("你在想一個數字嗎？");

        MultiTreeNode yes = new MultiTreeNode("是偶數嗎？");
        MultiTreeNode no = new MultiTreeNode("是奇數嗎？");

        root.children.add(yes);
        root.children.add(no);

        yes.children.add(new MultiTreeNode("答案是 2"));
        no.children.add(new MultiTreeNode("答案是 3"));

        System.out.println("決策樹 DFS：");
        dfs(root, 0);
    }

    static void dfs(MultiTreeNode node, int depth) {
        if (node == null) return;
        System.out.println("  ".repeat(depth) + node.value);
        for (MultiTreeNode child : node.children) {
            dfs(child, depth + 1);
        }
    }
}
