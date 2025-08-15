// 檔名：AVLLeaderboardSystem.java
import java.util.*;

class PlayerNode {
    String playerId;
    int score;
    int height;
    int size; // 子樹大小
    PlayerNode left, right;

    PlayerNode(String playerId, int score) {
        this.playerId = playerId;
        this.score = score;
        this.height = 1;
        this.size = 1;
    }
}

public class AVLLeaderboardSystem {
    PlayerNode root;
    Map<String, Integer> playerScores = new HashMap<>(); // 方便快速查分數

    // 計算高度
    int height(PlayerNode node) {
        return (node == null) ? 0 : node.height;
    }

    // 計算子樹大小
    int size(PlayerNode node) {
        return (node == null) ? 0 : node.size;
    }

    // 更新節點資訊
    void updateNode(PlayerNode node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            node.size = size(node.left) + size(node.right) + 1;
        }
    }

    // 平衡因子
    int getBalance(PlayerNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // 右旋
    PlayerNode rightRotate(PlayerNode y) {
        PlayerNode x = y.left;
        PlayerNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateNode(y);
        updateNode(x);
        return x;
    }

    // 左旋
    PlayerNode leftRotate(PlayerNode x) {
        PlayerNode y = x.right;
        PlayerNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateNode(x);
        updateNode(y);
        return y;
    }

    // 平衡調整
    PlayerNode balance(PlayerNode node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 插入 (根據分數排序，分數高的在左邊；同分數比 playerId)
    PlayerNode insert(PlayerNode node, String playerId, int score) {
        if (node == null) return new PlayerNode(playerId, score);

        if (score > node.score || (score == node.score && playerId.compareTo(node.playerId) < 0))
            node.left = insert(node.left, playerId, score);
        else
            node.right = insert(node.right, playerId, score);

        updateNode(node);
        return balance(node);
    }

    // 刪除
    PlayerNode delete(PlayerNode node, String playerId, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && playerId.compareTo(node.playerId) < 0)) {
            node.left = delete(node.left, playerId, score);
        } else if (score < node.score || (score == node.score && playerId.compareTo(node.playerId) > 0)) {
            node.right = delete(node.right, playerId, score);
        } else {
            // 找到節點
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            PlayerNode successor = minValueNode(node.right);
            node.playerId = successor.playerId;
            node.score = successor.score;
            node.right = delete(node.right, successor.playerId, successor.score);
        }

        updateNode(node);
        return balance(node);
    }

    PlayerNode minValueNode(PlayerNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 添加玩家分數
    public void addScore(String playerId, int score) {
        if (playerScores.containsKey(playerId)) {
            updateScore(playerId, playerScores.get(playerId) + score);
        } else {
            playerScores.put(playerId, score);
            root = insert(root, playerId, score);
        }
    }

    // 更新玩家分數
    public void updateScore(String playerId, int newScore) {
        if (!playerScores.containsKey(playerId)) return;
        int oldScore = playerScores.get(playerId);
        root = delete(root, playerId, oldScore);
        playerScores.put(playerId, newScore);
        root = insert(root, playerId, newScore);
    }

    // 查詢玩家排名 (1-based)
    public int getRank(String playerId) {
        if (!playerScores.containsKey(playerId)) return -1;
        int score = playerScores.get(playerId);
        return getRankHelper(root, playerId, score) + 1;
    }

    private int getRankHelper(PlayerNode node, String playerId, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && playerId.compareTo(node.playerId) < 0)) {
            return getRankHelper(node.left, playerId, score);
        } else if (score < node.score || (score == node.score && playerId.compareTo(node.playerId) > 0)) {
            return size(node.left) + 1 + getRankHelper(node.right, playerId, score);
        } else {
            return size(node.left);
        }
    }

    // 查詢前 K 名玩家
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopKHelper(root, k, result);
        return result;
    }

    private void getTopKHelper(PlayerNode node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        getTopKHelper(node.left, k, result);
        if (result.size() < k) result.add(node.playerId + "(" + node.score + ")");
        getTopKHelper(node.right, k, result);
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();
        leaderboard.addScore("Alice", 50);
        leaderboard.addScore("Bob", 70);
        leaderboard.addScore("Charlie", 60);
        leaderboard.addScore("David", 70);

        System.out.println("Top 3: " + leaderboard.getTopK(3));
        System.out.println("Rank of Charlie: " + leaderboard.getRank("Charlie"));

        leaderboard.updateScore("Charlie", 80);
        System.out.println("Top 3 after update: " + leaderboard.getTopK(3));
        System.out.println("Rank of Charlie after update: " + leaderboard.getRank("Charlie"));
    }
}
