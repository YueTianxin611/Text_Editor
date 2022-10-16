package util;

public class TreeNode {
    char c;
    HashTable<Character, TreeNode> children = new HashTable<>(10);
    TreeNode parent;
    boolean isLeaf;

    /**
     * Create a new tree node
     */
    public TreeNode() {
    }

    /**
     * Get the children of a tree node
     * The children are stored in a hash table
     * char stored in child is Key
     * child itself is a tree node, is the Value
     *
     * @return Children stored in a hash table
     */
    public HashTable<Character, TreeNode> getChildren() {
        return children;
    }

    /**
     * Set the tree node to be the leaf of the tree
     * @param isLeaf true if set to leaf, false if not
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * whether the tree node is a leaf
     *
     * @return true if is leaf, false if not
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * The rest methods not used in this project, maybe used in the future
     */
    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(char c) {
        this.parent = parent;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }
}
