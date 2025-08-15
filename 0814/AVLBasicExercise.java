
class AVLNode {
    int key;
    AVLNode left, right;
    int height;

    AVLNode(int key) {
        this.key = key;
        height = 1; // 新節點高度為 1
    }
}

public class AVLBasicExercise {
    AVLNode root;

    // 計算節點高度
    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // 計算平衡因子
    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 右旋轉
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 左旋轉
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 插入節點
    AVLNode insert(AVLNode node, int key) {
        // 1. 標準 BST 插入
        if (node == null)
            return new AVLNode(key);
        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // 不允許重複值
            return node;

        // 2. 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. 計算平衡因子
        int balance = getBalance(node);

        // 4. 檢查平衡，做旋轉
        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 搜尋節點
    boolean search(AVLNode node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return (key < node.key) ? search(node.left, key) : search(node.right, key);
    }

    // 檢查是否為有效的 AVL 樹
    boolean isAVL(AVLNode node) {
        if (node == null) return true;

        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;

        return isAVL(node.left) && isAVL(node.right);
    }

    // 測試
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();

        int[] keys = {10, 20, 30, 40, 50, 25};
        for (int key : keys) {
            tree.root = tree.insert(tree.root, key);
        }

        System.out.println("搜尋 25: " + tree.search(tree.root, 25));
        System.out.println("高度: " + tree.height(tree.root));
        System.out.println("是否為 AVL: " + tree.isAVL(tree.root));
    }
}
