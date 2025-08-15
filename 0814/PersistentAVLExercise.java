class Node {
    final int key;
    final Node left, right;
    final int height;

    Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = Math.max(height(left), height(right)) + 1;
    }

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }
}
