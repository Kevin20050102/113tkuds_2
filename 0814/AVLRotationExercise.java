class Node {
    int key;
    Node left, right;
    int height;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVLRotationExercise {
    Node root;

    int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x; 
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y; 
    }

    Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLRotationExercise tree = new AVLRotationExercise();

        Node root1 = new Node(30);
        root1.left = new Node(20);
        root1.left.left = new Node(10);
        tree.updateHeight(root1.left);
        tree.updateHeight(root1);
        System.out.println("LL Case 旋轉前:");
        tree.inorder(root1);
        System.out.println();
        root1 = tree.rightRotate(root1);
        System.out.println("右旋後:");
        tree.inorder(root1);
        System.out.println("\n");

        Node root2 = new Node(10);
        root2.right = new Node(20);
        root2.right.right = new Node(30);
        tree.updateHeight(root2.right);
        tree.updateHeight(root2);
        System.out.println("RR Case 旋轉前:");
        tree.inorder(root2);
        System.out.println();
        root2 = tree.leftRotate(root2);
        System.out.println("左旋後:");
        tree.inorder(root2);
        System.out.println("\n");

        Node root3 = new Node(30);
        root3.left = new Node(10);
        root3.left.right = new Node(20);
        tree.updateHeight(root3.left);
        tree.updateHeight(root3);
        System.out.println("LR Case 旋轉前:");
        tree.inorder(root3);
        System.out.println();
        root3 = tree.leftRightRotate(root3);
        System.out.println("左右旋後:");
        tree.inorder(root3);
        System.out.println("\n");

        Node root4 = new Node(10);
        root4.right = new Node(30);
        root4.right.left = new Node(20);
        tree.updateHeight(root4.right);
        tree.updateHeight(root4);
        System.out.println("RL Case 旋轉前:");
        tree.inorder(root4);
        System.out.println();
        root4 = tree.rightLeftRotate(root4);
        System.out.println("右左旋後:");
        tree.inorder(root4);
    }
}
