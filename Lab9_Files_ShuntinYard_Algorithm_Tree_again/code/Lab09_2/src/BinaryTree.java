/**
 * An interface for a binary tree, in which each node has
 * at most two children.
 * @author Goodrich, Tamassia, Goldwasser
 */
public interface BinaryTree<E> extends Tree<E> {
    /**
     * 
     * @param p
     * @return the position of p's left child (or null if no child exist)
     * @throws IllegalArgumentException 
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;
   
    /**
     * 
     * @param p
     * @return the position of p's right child
     * @throws IllegalArgumentException 
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @param p
     * @return the position of the sibling of p or null
     * @throws IllegalArgumentException 
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
    
}
