class Node {
    int value;
    Node left, right;

    Node(int x) {
        value = x;
        left = null;
        right = null;
    }
}

public class BSTOperations {

    public static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key <= root.value) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    public static Node findMin(Node root) {
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static Node findMax(Node root) {
        while (root != null && root.right != null) {
            root = root.right;
        }
        return root;
    }

    public static Node deleteNodeFromRight(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.value) {
            root.left = deleteNodeFromRight(root.left, key);
        } else if (key > root.value) {
            root.right = deleteNodeFromRight(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node curr = findMin(root.right);
            root.value = curr.value;
            root.right = deleteNodeFromRight(root.right, curr.value);
        }

        return root;
    }

    public static Node deleteNodeFromLeft(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.value) {
            root.left = deleteNodeFromLeft(root.left, key);
        } else if (key > root.value) {
            root.right = deleteNodeFromLeft(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node curr = findMax(root.left);
            root.value = curr.value;
            root.left = deleteNodeFromLeft(root.left, curr.value);
        }

        return root;
    }

    public static boolean find(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (key > root.value) {
            return find(root.right, key);
        } else if (key < root.value) {
            return find(root.left, key);
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Node root = null;
        root = insert(root, 50);
        root = insert(root, 30);
        root = insert(root, 20);
        root = insert(root, 40);
        root = insert(root, 70);
        root = insert(root, 60);
        root = insert(root, 80);

        System.out.println(find(root, 40)); // true
        System.out.println(find(root, 41)); // false

        root = deleteNodeFromRight(root, 40);

        System.out.println(find(root, 40)); // false
    }
}
