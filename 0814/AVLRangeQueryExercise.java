import java.util.*;

class Node {
    int key;
    Node left, right;
    int height;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVLRangeQueryExercise {
    Node root;

    // 計算高度
    int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 更新高度
    void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    // 計算平衡因子
    int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 右旋
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // 左旋
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // 平衡修正
    Node balance(Node node) {
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 插入
    Node insert(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // 不允許重複

        updateHeight(node);
        return balance(node);
    }

    // 範圍查詢 (中序 + 剪枝)
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 如果當前節點的值大於 min，才需要去左子樹找
        if (node.key > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 如果當前節點在範圍內，加入結果
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 如果當前節點的值小於 max，才需要去右子樹找
        if (node.key < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // 中序輸出（方便檢查）
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] keys = {10, 5, 1, 7, 40, 50, 30};
        for (int key : keys) {
            tree.root = tree.insert(tree.root, key);
        }

        System.out.print("中序遍歷: ");
        tree.inorder(tree.root);
        System.out.println();

        int min = 5, max = 30;
        List<Integer> result = tree.rangeQuery(min, max);
        System.out.println("範圍查詢 " + min + " 到 " + max + ": " + result);
    }
}

