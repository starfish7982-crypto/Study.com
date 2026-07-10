import java.util.Scanner;


public class BST {
    //Node class to define the structure of the tree
    public static class Node {
        int key;
        Node leftChild, rightChild;
        public Node(int item) {
            key = item;
            leftChild = rightChild = null;
        }
    }
    Node root;
    
    //Create a binary search tree
    public void createABinarySearchTree() {
        int data[] = {1,2,3,4,5,6,7};
        root = sortedArrayToBST(data, 0, data.length - 1);
        System.out.println("Create A Binary Search Tree Successfully!");
        System.out.println("The tree is created with the following nodes:");
        printPreOrder(root);
        System.out.println("\n");
    }
    //Convert a sorted array to a balanced BST
    public Node sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.leftChild = sortedArrayToBST(arr, start, mid - 1);
        node.rightChild = sortedArrayToBST(arr, mid + 1, end);
        return node;
    }

     //Add a node to the tree
    public Node addNode(int data, boolean shouldPrint) {
        root = insertNode(root, data);
        if (shouldPrint) {
            System.out.println("After adding the node, the tree is:");
            printNodeByInOrder(root);
            System.out.println("\n");
        }
        return root;
    }
    //Recursive insert method to define the nodes
    public Node insertNode (Node node, int key){
        if (node == null){
            node = new Node(key);
            return node;
        }
        if (key <= node.key){
            node.leftChild = insertNode(node.leftChild, key);
        } else if (key > node.key){
            node.rightChild = insertNode(node.rightChild, key);
        }
        return node;
    }

    //Delete a node from the tree
    public Node deleteNode(int data) {
        root = deleteNode(root, data);
        System.out.println("After deleting the node, the tree is:");
        printNodeByInOrder(root);
        System.out.println("\n");
        return root;
    }
    //Recursive delete method to define the nodes
    public Node deleteNode(Node node, int key) {
        if (node == null) {
            System.out.println("The node you want to delete is not found!");
            System.out.println("\n");
            return node;
        }
        if (key < node.key) {
            node.leftChild = deleteNode(node.leftChild, key);
        } else if (key > node.key) {
            node.rightChild = deleteNode(node.rightChild, key);
        } else {
            // Node with only one child or no child
            if (node.leftChild == null) {
                return node.rightChild;
            } else if (node.rightChild == null) {
                return node.leftChild;
            }
            // Node with two children: get the inorder successor
            node.key = minValue(node.rightChild);
            // Delete the inorder successor
            node.rightChild = deleteNode(node.rightChild, node.key);
        }
        return node;
    }
    //Find the minimum value in the tree
    public int minValue(Node node) {
        int min = node.key;
        while (node.leftChild != null) {
            node = node.leftChild;
            min = node.key;
        }
        return min;
    }

    //Print the tree By InOrder
    public void printNodeByInOrder(Node node) {
        System.out.print("The InOrder tree is: ");
        printInOrder(node);
        System.out.println("\n");
    }
    public void printInOrder(Node node) {
        if(node==null){
            return;
        }
        printInOrder(node.leftChild);
        System.out.print(node.key + " ");
        printInOrder(node.rightChild);
    }

    //Print the tree By PreOrder
    public void printNodeByPreOrder(Node node) {
        System.out.print("The PreOrder tree is: ");
        printPreOrder(node);
        System.out.println("\n");
    }
    public void printPreOrder(Node node) {
        if(node==null){
            return;
        }
        System.out.print(node.key + " ");
        printPreOrder(node.leftChild);
        printPreOrder(node.rightChild);
    }

    //Print the tree By PostOrder
    public void printNodeByPostOrder(Node node) {
        System.out.print("The PostOrder tree is: ");
        printPostOrder(node);
        System.out.println("\n");
    }
    public void printPostOrder(Node node) {
        if(node==null){
            return;
        }
        printPostOrder(node.leftChild);
        printPostOrder(node.rightChild);
        System.out.print(node.key + " ");
    }

    //Exit the program
    public void exit(Scanner scanner) {
        scanner.close(); //close the scanner
        System.out.println("Exiting the program...");
    }
   
    public static void main (String [] args){
        BST bst = new BST();

        System.out.println("---------------Menu---------------");
        System.out.println("1. Create A Binary Search Tree");
        System.out.println("2. Add a node");
        System.out.println("3. Delete a node");
        System.out.println("4. Print the tree in order");
        System.out.println("5. Print the tree in pre order");
        System.out.println("6. Print the tree in post order");
        System.out.println("7. Exit");
        System.out.println("----------------------------------");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while(choice != 7){
            System.out.println("Please enter your choice:");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid choice. Please try again.");
                System.out.println("\n");
                continue;
            }
            switch (choice) {
                case 1:
                    bst.createABinarySearchTree();
                    break;
                case 2:
                    System.out.println("Please enter the node you want to add:");
                    bst.addNode(scanner.nextInt(),true);
                    break;
                case 3:
                    System.out.println("Please enter the node you want to delete:");
                    bst.deleteNode(scanner.nextInt());
                    break;
                case 4:
                    bst.printNodeByInOrder(bst.root);
                    break;
                case 5:
                    bst.printNodeByPreOrder(bst.root);
                    break;
                case 6:
                    bst.printNodeByPostOrder(bst.root);
                    break;
                case 7:
                    bst.exit(scanner);
                    break;
                default:
                    break;
            }
        }
        
    }
}