import java.util.*;

class Node {
    int value;
    Node left, right;

    Node(int x) {
        value = x;
        left = null;
        right = null;
    }
}

public class BinarySearch {

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

    public static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.value + " ");
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        Node root = null;
        String[] nodeStr = sc.nextLine().split(",");
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(nodeStr[i]);
            root = insert(root, v);
        }
        int target = sc.nextInt();
        boolean found = find(root, target);
        if (found) {
            System.out.println(target + " found in the BST");

        } else {
            System.out.println(target + " not found in the BST");
        }

        System.out.println("In-Order Traversal: ");
        inorderTraversal(root);
        System.out.println();
    }
}