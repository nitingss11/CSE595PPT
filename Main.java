import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given program from the question
 */
public class Main {
    // Main function: The driver function
    public static void main(String[] args) {
        System.out.println("BT = Binary Tree");
        System.out.println("----------------------------------------------------");
        BinaryTree tree = new BinaryTree("A");

        /* Construct the following binary search tree
               50
            /     \
           30      70
          /  \    /  \
        20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        System.out.println("----------------------------------------------------");

        BinaryTree tree2 = new BinaryTree("B");
        tree2.insert(50);
        tree2.insert(30);
        tree2.insert(20);
        tree2.insert(40);
        tree2.insert(70);
        tree2.insert(60);
        tree2.insert(90);

        tree.isIdentical(tree2);

        tree2.copyRecursive(tree);

        boolean res = tree.isIdenticalTreeMorris(tree, tree2);
        System.out.println("Morris " + res);

        tree.isIdentical(tree2);

        tree2.insert(35);

        tree.isIdentical(tree2);

        tree.copyIterative(tree2);

        tree2.isIdentical(tree);

        System.out.println("----------------------------------------------------");


    }
}

class BinaryTree {

    // BT Node class: This class is made a nested private class to adhere to OOP principles.
    private class Node {
        int key;
        Node left, right;
        // Constructor for the Node class
        public Node(int item)
        { key = item; left = right = null; }
    }

    private Node root;    // Root node of the BT
    private String name;  // Name given to the BT

    // Constructor for the BinaryTree class
    BinaryTree(String treename) {
        root = null; name = treename;
        System.out.println("BT " + name + " is constructed and initialized");
    }

    // Insert a new node in the BT
    public void insert(int key) {
        System.out.println("Insert " + key + " into BT " + name);
        root = insertRecursive(root, key);
    }

    // Recursive function for inserting a new node in the BT
    private Node insertRecursive(Node curr, int key) {
        if (curr == null)
        { curr = new Node(key); return curr; }
        else if (key < curr.key)
            curr.left = insertRecursive(curr.left, key);
        else if (key > curr.key)
            curr.right = insertRecursive(curr.right, key);
        return curr;
    }

    // Check if the given BT is identical to the current BT
    public void isIdentical(BinaryTree tree) {
        System.out.println("Through iterative solution : Are BTs " + name + " and " + tree.name + " identical? " + isIdenticalIterative(root, tree.root));
        System.out.println("Through recursive solution : Are BTs " + name + " and " + tree.name + " identical? " + isIdenticalRecursive(root, tree.root));
    }

    // Recursive function to check if two BTs are identical or not
    private boolean isIdenticalRecursive(Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null)
            return (root1.key == root2.key && isIdenticalRecursive(root1.left, root2.left) && isIdenticalRecursive(root1.right, root2.right));
        return false;
    }

    // Iterative algorithm to check if trees are identical
    private boolean isIdenticalIterative(Node root1, Node root2) {
        if (root1 == null && root2 == null)  return true;
        if (root1 == null || root2 == null)  return false;

        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(root1);
        queue2.add(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            Node front1 = queue1.poll();
            Node front2 = queue2.poll();
            if (front1.key != front2.key) return false;

            if (front1.left != null && front2.left != null) {
                queue1.add(front1.left);
                queue2.add(front2.left);
            }
            else if (front1.left != null || front2.left != null)
                return false;

            if (front1.right != null && front2.right != null) {
                queue1.add(front1.right);
                queue2.add(front2.right);
            }
            else if (front1.right != null || front2.right != null)
                return false;
        }
        return true;
    }

    // Copy a BT to the current BT - Recursive
    public void copyRecursive(BinaryTree tree) {
        System.out.println("Through recursive solution : Copy BT " + tree.name + " to BT " + name);
        root = copyRecursiveHelper(tree.root);
    }

    // Recursive function to copy BT to the current BT
    private Node copyRecursiveHelper(Node curr) {
        if (curr == null) return null;
        Node newnode = new Node(curr.key);
        newnode.left = copyRecursiveHelper(curr.left);
        newnode.right = copyRecursiveHelper(curr.right);
        return newnode;
    }

    // Copy a BT to the current BT - Iterative
    public void copyIterative(BinaryTree tree) {
        System.out.println("Through iterative solution : Copy BT " + tree.name + " to BT " + name);
        root = copyIterativeHelper2(tree.root);
    }

    // Iterative function to copy BT to the current BT
    private Node copyIterativeHelper(Node root1) {
        if (root1 == null) return null;

        Queue<Node> queue1 = new LinkedList<>();
        queue1.add(root1);

        Queue<Node> queue2 = new LinkedList<>();
        Node root2 = new Node(root1.key);
        queue2.add(root2);

        while (!queue1.isEmpty()) {
            Node front1 = queue1.poll();
            Node front2 = queue2.poll();
            if (front1.left != null) {
                queue1.add(front1.left);
                front2.left = new Node(front1.left.key);
                queue2.add(front2.left);
            }
            if (front1.right != null) {
                queue1.add(front1.right);
                front2.right= new Node(front1.right.key);
                queue2.add(front2.right);
            }
        }
        return root2;
    }

    // Iterative function to copy BT to the current BT
    private Node copyIterativeHelper2(Node root1) {
        if (root1 == null) return null;

        Queue<Node> qu = new LinkedList<>();
        qu.add(root1);
        Node root2 = new Node(root1.key);
        qu.add(root2);

        while (!qu.isEmpty()) {
            Node front1 = qu.poll();
            Node front2 = qu.poll();
            if (front1.left != null) {
                qu.add(front1.left);
                front2.left = new Node(front1.left.key);
                qu.add(front2.left);
            }
            if (front1.right != null) {
                qu.add(front1.right);
                front2.right= new Node(front1.right.key);
                qu.add(front2.right);
            }
        }
        return root2;
    }


    /***
     * Following are the methods used by me, Using single queue/stack instead of two
     */
    public boolean isIdenticalTreeBFS(Node p, Node q) {

        if(p==null && q==null) {
            return true;
        } else if(p==null || q==null) {
            return false;
        }

        Queue<Node> qu = new LinkedList<>();

        qu.offer(p);
        qu.offer(q);

        while(!qu.isEmpty()) {
            Node a = qu.poll();
            Node b = qu.poll();
            if(a.key != b.key) {
                return false;
            }

            if(a.left != null && b.left != null) {
                qu.offer(a.left);
                qu.offer(b.left);
            } else if(a.left != null || b.left != null) {
                return false;
            }

            if(a.right != null && b.right != null) {
                qu.offer(a.right);
                qu.offer(b.right);
            } else if(a.right != null || b.right != null) {
                return false;
            }
        }

        return true;
    }

    //DFS Iterative
    public boolean isIdenticalTreeDFS(Node p, Node q) {

        if(p==null && q==null) {
            return true;
        } else if(p==null || q==null) {
            return false;
        }

        Stack<Node> st = new Stack<>();

        st.push(q);
        st.push(p);

        while(!st.isEmpty()) {
            Node a = st.pop();
            Node b = st.pop();
            if(a.key != b.key) {
                return false;
            }

            if(a.right != null && b.right != null) {
                st.push(b.right);
                st.push(a.right);
            } else if(a.right != null || b.right != null) {
                return false;
            }

            if(a.left != null && b.left != null) {
                st.push(b.left);
                st.push(a.left);
            } else if(a.left != null || b.left != null) {
                return false;
            }

        }

        return true;
    }

    //Recursive
    public boolean isIdenticalTreeRecursive(Node p, Node q) {

        if(p==null && q==null) {
            return true;
        } else if(p==null || q==null) {
            return false;
        } else if(p.key == q.key) {
            return isIdenticalTreeRecursive(p.left, q.left) && isIdenticalTreeRecursive(p.right, q.right);
        } else {
            return false;
        }

    }

    public boolean isIdenticalTreeMorris(BinaryTree p, BinaryTree q) {
        // Base cases
        Node r1 = p.root;
        Node r2 = q.root;
        if (r1 == null && r2 == null) // both trees are empty
            return true;
        if (r1 == null || r2 == null) // one of the trees is empty, which
            // means they are not identical
            return false;

        // Morris traversal
        while (r1 != null && r2 != null) {
            if (r1.key != r2.key) // if the val of the current nodes
                // is not the same, then the trees
                // are not identical
                return false;

            // Morris traversal for r1
            if (r1.left == null) { // if the left child is NULL, move to
                // the right child
                r1 = r1.right;
            } else { // if the left child is not NULL, find the
                // predecessor
                Node pre = r1.left;
                while (pre.right != null && pre.right != r1) // find the rightmost node
                    // in the left subtree
                    pre = pre.right;
                if (pre.right == null) { // if the right pointer of the
                    // predecessor is NULL, make it
                    // point to the current node
                    pre.right = r1;
                    r1 = r1.left;
                } else { // if the right pointer of the
                    // predecessor is already pointing to the
                    // current node, set it back to NULL and
                    // move to the right child
                    pre.right = null;
                    r1 = r1.right;
                }
            }

            // Morris traversal for r2, same as for r1
            if (r2.left == null) {
                r2 = r2.right;
            } else {
                Node pre = r2.left;
                while (pre.right != null && pre.right != r2)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = r2;
                    r2 = r2.left;
                } else {
                    pre.right = null;
                    r2 = r2.right;
                }
            }
        }

        return (r1 == null && r2 == null); // if both r1 and r2 are NULL,
        // then the trees are identical
    }

}