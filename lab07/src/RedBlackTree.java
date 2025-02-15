public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        if (node.left != null && node.right != null && !node.left.isBlack && !node.right.isBlack) {
            node.isBlack = false;
            node.left.isBlack = true;
            node.right.isBlack = true;
            if (node.item == root.item) node.isBlack = true;
        }
    }
    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> newRight = new RBTreeNode<>(node.left.isBlack, node.item, node.left.right, node.right);
        return new RBTreeNode<>(node.isBlack, node.left.item, node.left.left, newRight);
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> newLeft = new RBTreeNode<>(node.right.isBlack, node.item, node.left, node.right.left);
        return new RBTreeNode<>(node.isBlack, node.right.item, newLeft, node.right.right);
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        if (node == null) return new RBTreeNode<>(false, item, null, null);
        if (item.compareTo(node.item) < 0) {
            if (node.left == null) {
                node = helper(new RBTreeNode<>(node.isBlack, node.item, new RBTreeNode<>(false, item, null, null), node.right));
            }
            else {
                node = helper(new RBTreeNode<>(node.isBlack, node.item, helper(insert(node.left, item)), node.right));

            }

        }

        else if (item.compareTo(node.item) > 0) {
            if (node.right== null) {
                node = helper(new RBTreeNode<>(node.isBlack, node.item, node.left, new RBTreeNode<>(false, item, null, null)));

            }
            else {
                node = helper(new RBTreeNode<>(node.isBlack, node.item, node.left, helper(insert(node.right, item))));
            }

        }
         return node;
    }

    private RBTreeNode<T> helper(RBTreeNode<T> node) {
        if (node.left != null && node.left.left != null && isRed(node.left.left) && isRed(node.left)) {
            node = rotateRight(node);
        }else if (node.left == null && isRed(node.right)) {
            node = rotateLeft(node);
        }else if (node.right == null && isRed(node.left) && isRed(node.left.right)) {
            node = new RBTreeNode<>(true, node.item, rotateLeft(node.left), null);
        }
        if (node.left != null && node.right != null && !node.left.isBlack && !node.right.isBlack) {
            flipColors(node);
        }
    return node;
    }
}


