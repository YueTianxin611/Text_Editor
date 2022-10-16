package util;

/**
 * A mutable collection of strings.
 */
public class Trie {
    TreeNode root;

    /**
     * Create an empty trie.
     */
    public Trie() {
        root = new TreeNode();
    }

    /**
     * Add {@code elem} to the collection.
     * The run time is O(k), k is the length of an element
     */
    public void insert(String elem) {
        TreeNode current = root;
        for (char ch : elem.toCharArray()) {
            current = current.getChildren().computeIfAbsent(ch, c -> new TreeNode());
        }

        current.setLeaf(true);
    }

    /**
     * Remove {@code elem} from the collection, if it is there.
     * Use delete(int index, TreeNode current, String elem) method
     * The run time is O(k)
     */
    public void delete(String elem) {
        delete(0, root, elem);
    }

    /**
     * Delete elem from the Trie
     * Depth First Search is used in this method
     * The run time is O(k), k is the length of the elem
     * @param index current index of the char in the elem
     * @param current current tree node
     * @param elem The element to be deleted
     * @return true if deleted, false if elem is not in Trie
     */
    public boolean delete(int index, TreeNode current, String elem) {
        if (index == elem.length()) {
            if (!current.isLeaf())
                return false;
            current.setLeaf(false);
            return current.getChildren().isEmpty();
        }

        char ch = elem.charAt(index);
        TreeNode child = current.getChildren().get(ch);
        if (child == null)
            return false;

        boolean deleteit = delete(index + 1, child, elem) && !child.isLeaf();

        if (deleteit) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }

        return false;
    }

    /**
     * Return true if this trie contains {@code elem}, false otherwise.
     * run time is O(k)
     */
    public boolean contains(String elem) {
        TreeNode current = root;
        for (int i = 0; i < elem.length(); i++) {
            char ch = elem.charAt(i);
            TreeNode child = current.getChildren().get(ch);
            if (child == null) {
                return false;
            }
            current = child;
        }
        return current.isLeaf();
    }

    /**
     * Return a word contained in the trie of minimal length with {@code prefix}. If no such word
     * exists, return null.
     * Return the shortest entry in the trie having the given prefix.
     */
    public String closestWordToPrefix(String prefix) {
        if (contains(prefix))
            return prefix;

        String res = "";

        int min = Integer.MAX_VALUE;

        LinkedList<String> list = searchWords(prefix);

        if (list == null)
            return "";

        for (String s : list) {
            int len = s.length();

            if (len <= min) {
                res = s;
                min = len;
            }
        }

        return res;
    }

    /**
     * Method used to find the words contained in the trie with the given prefix
     * @param prefix the given prefix
     * @return A list of string that contain the given prefix
     */
    public LinkedList<String> searchWords(String prefix) {
        TreeNode current = root;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.children.containsKey(ch)) {
                return null;
            } else {
                sb.append(ch);
                current = current.children.get(ch);
            }
        }

        LinkedList<String> list = new LinkedList<>();
        dfs(current, sb.toString(), list);

        return list;
    }

    /**
     * Depth First Search to get the world contains the prefix
     * @param node current tree node
     * @param prefix given prefix
     * @param list add to this list
     */
    private void dfs(TreeNode node, String prefix, LinkedList<String> list) {
        if (node.isLeaf) {
            list.add(prefix);
        }

        for (char c : node.getChildren().keySet()) {
            dfs(node.getChildren().get(c), prefix + c, list);
        }
    }
}
