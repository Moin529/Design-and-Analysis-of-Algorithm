import java.util.*;

public class BinarySearchTree {
    // Node class representing each node in the tree
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    // Insert key into BST
    void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive insert helper
    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }

    // Search a key in BST
    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null)
            return false;
        if (key == root.key)
            return true;
        return key < root.key ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    // Inorder traversal
    void inorderTraversal() {
        System.out.print("In-Order Traversal: ");
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Find LCA
    int lca(int n1, int n2) {
        Node lcaNode = lcaRec(root, n1, n2);
        return lcaNode != null ? lcaNode.key : -1;
    }

    Node lcaRec(Node node, int n1, int n2) {
        if (node == null)
            return null;

        if (n1 < node.key && n2 < node.key)
            return lcaRec(node.left, n1, n2);

        if (n1 > node.key && n2 > node.key)
            return lcaRec(node.right, n1, n2);

        return node;
    }

    // Main method for interaction
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();

        // Input number of nodes
        int n = Integer.parseInt(sc.nextLine().trim());

        // Input values
        String[] values = sc.nextLine().trim().split(",");
        for (String val : values) {
            tree.insert(Integer.parseInt(val.trim()));
        }

        // Input search key
        int searchKey = Integer.parseInt(sc.nextLine().trim());

        // Perform search
        if (tree.search(searchKey))
            System.out.println(searchKey + " found in the BST.");
        else
            System.out.println(searchKey + " not found in the BST.");

        // In-order traversal
        tree.inorderTraversal();

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        // test LCA
        System.out.println("LCA of " + n1 + " and " + n2 + " : " + tree.lca(n1, n2));
        System.out.println("LCA of " + n2 + " and " + n1 + " : " + tree.lca(n1, n2));
    }
}

/*
 * 50
 * / \
 * 30 70
 * / \ / \
 * 20 40 60 80
 * 
 */