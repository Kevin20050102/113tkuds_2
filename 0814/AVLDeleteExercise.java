// 檔名：AVLDeleteExercise.java
class Node {
    int key;
    Node left, right;
    int height;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVLDeleteExercise {
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

    // 插入（方便測試）
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

    // 刪除
    Node delete(Node node, int key) {
        if (node == null) return null;

        // 1. 標準 BST 刪除
        if (key < node.key)
            node.left = delete(node.left, key);
        else if (key > node.key)
            node.right = delete(node.right, key);
        else {
            // Case 1: 葉子節點
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: 一個子節點
            else if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }
            // Case 3: 兩個子節點
            else {
                Node successor = minValueNode(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }
        }

        // 2. 更新高度
        updateHeight(node);

        // 3. 重新平衡
        return balance(node);
    }

    // 取得最小值節點
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 平衡判斷與旋轉
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
        AVLDeleteExercise tree = new AVLDeleteExercise();
        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys) {
            tree.root = tree.insert(tree.root, key);
        }

        System.out.println("初始樹:");
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("刪除 40:");
        tree.root = tree.delete(tree.root, 40);
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("刪除 25:");
        tree.root = tree.delete(tree.root, 25);
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("刪除 10:");
        tree.root = tree.delete(tree.root, 10);
        tree.inorder(tree.root);
        System.out.println();
    }
}
