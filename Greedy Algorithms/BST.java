import java.util.*;

class Node {
    int data;
    Node left;
    Node right;
    
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BST {
    
 
     
    static void inorder(Node root) {
        // base case 
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    static Node insertIntoBST(Node root, int d) {
        // base case
        if (root == null) {
            root = new Node(d);
            return root;
        }
        
        if (d > root.data) {
            // right subtree
            root.right = insertIntoBST(root.right, d);
        } else {
            // left subtree
            root.left = insertIntoBST(root.left, d);
        }
        
        return root;
    }
    
    // Method to insert a number into BST
    static Node insert(Node root, int num) {
        return insertIntoBST(root, num);
    }
    
    // Method to search for a key in BST
    static boolean search(Node root, int key) {
        // base case
        if (root == null) {
            return false;
        }
        
        if (root.data == key) {
            return true;
        }
        
        if (key > root.data) {
            // search in right subtree
            return search(root.right, key);
        } else {
            // search in left subtree
            return search(root.left, key);
        }
    }
    
    // Method to find Lowest Common Ancestor (LCA) of two nodes in BST
    static Node lca(Node root, int n1, int n2) {
        // Base case
        if (root == null) {
            return null;
        }
        
        // If both n1 and n2 are smaller than root, then LCA lies in left subtree
        if (n1 < root.data && n2 < root.data) {
            return lca(root.left, n1, n2);
        }
        
        // If both n1 and n2 are greater than root, then LCA lies in right subtree
        if (n1 > root.data && n2 > root.data) {
            return lca(root.right, n1, n2);
        }
        
        // If one value is smaller and other is greater than root,
        // or one of them is equal to root, then root is the LCA
        return root;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter numbers to insert into BST (comma-separated):");
        
        int n = scanner.nextInt(); 
          
        Node root = null;

        for (int i = 0; i < n; i++) {
             
            int num = scanner.nextInt();
            root = insert(root, num);
        }

        System.out.println("Inorder Traversal of BST:");
        inorder(root);

        System.out.println(); // Print a new line after inorder traversal
        System.out.println("Enter a number to search in BST:");
        int key = scanner.nextInt();
        boolean found = search(root, key);
        if (found) {
            System.out.println("Number " + key + " found in BST.");
        } else {
            System.out.println("Number " + key + " not found in BST.");
        }

        // Example usage of LCA method
        System.out.println("Enter two numbers to find their LCA in BST:");
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        Node lcaNode = lca(root, n1, n2);
        if (lcaNode != null) {
            System.out.println("LCA of " + n1 + " and " + n2 + " is: " + lcaNode.data);
        } else {
            System.out.println("LCA not found for the given nodes.");
        }

        scanner.close();
    }
}
 
