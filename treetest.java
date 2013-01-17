

	public class treetest
	
	{
		public static void main(String[] args){
			BST tree=new BST();
			tree.insert(1);
			tree.insert(12);
			tree.insert(5);
			tree.insert(15);
			tree.insert(9);
			tree.insert(4);
			tree.printInOrder(tree.getRoot());
			System.out.println(tree.countTNode(tree.getRoot()));
		}
		
public static class BST {

    private Node root;
    
    public BST(){
        root = null;
    }
    
    /**
     * Insert a node in given tree.
     * @param val 
     */
    public void insert(int val){
        
        Node t = new Node(val);
        if(root == null){
            root = t;
            return;
        }else{
            Node temp = root, prev = null;
            while(temp != null){
                prev = temp;
                if(val > temp.val){
                    temp = temp.right;
                }else{
                    temp = temp.left;
                }
            }
            
            t.parent = prev; // assign the parent to new node;
            
            if(val > prev.val)
                prev.right = t;
            else
                prev.left = t;
        }
        return;
    }
    
    /**
     * Find the node whose data is given.
     * @param t
     * @param val
     * @return 
     */
    public Node findNode(Node t, int val){
        
        Node temp = null;
        
        if(t != null && t.val == val)
            return t;
        
        if(t.left != null)
            temp = findNode(t.left, val);
        if(temp == null && t.right != null)
            temp = findNode(t.right, val);
        
        return temp;
    }
    
    /**
     * Returns the min value in a non-empty binary search tree. 
     * @param t
     * @return 
     */
    public Node findMin(Node t){
        
        if(t != null && t.left == null)
            return t;
        else
            return findMin(t.left);
    }
    
    /**
     * Returns the min value in a non-empty binary search tree.
     * @param t
     * @return 
     */
    public Node findMax(Node t){
        
        if(t != null && t.right == null)
            return t;
        else
            return findMax(t.right);
    }
    
    /**
     * Print path of each leaf node in recursive order.
     * @param sum
     * @param t
     * @return 
     */
    public int printPath(int sum, Node t){
        
        while(t != null){
            System.out.print("\t" + t.val);
            sum += t.val;
            t = t.parent;
        }
        return sum;
    }
    
    /**
     * Print leaf nodes
     * @param t 
     */
    public void printLeafNodes(Node t){
        
        if(t != null && t.left == null && t.right == null){
            System.out.print("\t" + t.val);
        }
        
        if(t != null){
            printLeafNodes(t.left);
            printLeafNodes(t.right);
        }
        
        return;
    }
    
    /**
     * find path for each leaf node
     * @param t 
     */
    public void findPath(Node t){
        if(t != null && t.left == null && t.right == null) {
            System.out.print("Path: ");
            int sum = printPath(0, t);
            System.out.println("\tSum: " + sum);
        }
        if(t != null){
            findPath(t.left);
            findPath(t.right);
        }
        return;
    }
    
    /**
     * Check if any path has given sum.
     * @param t
     * @param sum
     * @return 
     */
    public boolean hasPathSum(Node t, int sum){
        if(t == null)
            return (sum == 0);
        else{
            sum -= t.val;
            return hasPathSum(t.left, sum) || hasPathSum(t.right, sum);
        }
    }
    
    /**
     * Make mirror image of given tree.
     * @param t 
     */
    public void mirrorTree(Node t){
        
        if(t == null)
            return;
        
        Node temp = null;
        mirrorTree(t.left);
        mirrorTree(t.right);
        
        temp = t.left;
        t.left = t.right;
        t.right = temp;
        
        return;
    }
    
    /**
     * For each node in a binary search tree, create a new duplicate node, and insert
     * the duplicate as the left child of the original node.
     * The resulting tree should still be a binary search tree. So the tree...
     *          2
     *         / \
     *        1   3
     * Is changed to...
     *          2
     *         / \
     *        2   3
     *      /    /
     *     1    3
     *    /
     *   1
     * 
     **/
    public void doubleTree(Node t){
        
        if(t == null)
            return;
        
        Node oldLeft = null;
        doubleTree(t.left);
        doubleTree(t.right);
        
        oldLeft = t.left;
        t.left = new Node(t.val);
        t.left.parent = t; // assign parent to new node
        t.left.left = oldLeft;
        if(t.left.left != null)
            t.left.left.parent = t.left; // update the parent for old node
        
    }
    
    /**
     * Given two trees, return true if they are structurally identical.
     * @param t1
     * @param t2
     * @return 
     */
    public boolean checkMatchingTree(Node t1, Node t2){
      
        // both empty
        if(t1 == null && t2 == null)
            return true;
        
        else if(t1 != null && t2 != null)
            return (t1.val == t2.val && 
                    checkMatchingTree(t1.left, t2.left) && 
                    checkMatchingTree(t1.right, t2.right));
        else // one of them empty
            return false;
    }
    
    /**
     * Count number of nodes in a given tree.
     * @param t
     * @return 
     */
    public int countTNode(Node t){
        
        if(t == null)
            return 0;
        else
            return (countTNode(t.left) + 1 + countTNode(t.right));
            
    }
    
    /**
     * For the key values 1...numKeys, how many structurally unique binary 
     * search trees are possible that store those keys? 
     * Strategy: consider that each value could be the root.
     * Recursively find the size of the left and right subtrees.
     * @param key
     * @return 
     */
    public int countTrees(int key){
        
        if(key <= 1)
            return 1;
        else{
            // there will be one value at the root, with whatever remains
            // on the left and right each forming their own subtrees.
            // Iterate through all the values that could be the root...
            int sum = 0;
            int lht, rht, rt = 0;
            
            for(rt=1; rt<=key; rt++ ){
                lht = countTrees(rt-1);
                rht = countTrees(key-rt);
                
                // number of possible trees with this root == left*right
                sum += lht * rht;
            }
            
            return sum;
        }
    }
    
    /**
     * Find the maximum depth of a given tree.
     * @param t
     * @return 
     */
    public int maxDept(Node t){
        
        if(t == null)
            return 0;
        
        int lDepth = maxDept(t.left);
        int rDepth = maxDept(t.right);
        
        return (lDepth>rDepth)?(lDepth+1):(rDepth+1);
        
    }
    
    /**
     * Print the tree in In-Order.
     * @param t 
     */
    public void printInOrder(Node t){
        if(t == null)
            return;
        
        printInOrder(t.left);
        System.out.print("\t"+t.val);
        printInOrder(t.right);
    }
    
    /**
     * print the tree in Post-Order.
     * @param t 
     */
    public void printPostOrder(Node t){
        if(t == null)
            return;
        
        printPostOrder(t.left);
        printPostOrder(t.right);
        System.out.print("\t"+t.val);
        
    }
    
    /**
     * Print the tree in Pre-order.
     * @param t 
     */
    public void printPreOrder(Node t){
        if(t == null)
            return;
        
        System.out.print("\t"+t.val);
        printPreOrder(t.left);
        printPreOrder(t.right);
    }
    
    /**
     * Get root of the node.
     * @return 
     */
    public Node getRoot(){
        return root;
    }
}
        public static class Node {
            
            Node left;
            Node right;
            Node parent;
            int     val;
            int     height;
            int     balanceFactor;
            int     NaV = -99999999; // not a value.
           
           public Node(){
               left = right = parent = null;
               val = height = balanceFactor = NaV;
           }
           
           public Node(int val){
               left = right = parent = null;
               this.val = val;
               height = balanceFactor = NaV;
           }
           
           public Node(int val, int key){
               left = right = parent = null;
               this.val = val;
               this.balanceFactor = key;
               height = NaV;
           }
       }
	}
