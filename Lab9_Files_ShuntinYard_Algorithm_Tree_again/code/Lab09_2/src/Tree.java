
import java.util.Iterator;

/**
 *
 * @author Goodrich, Tamassia, Goldwasser
 */
public interface Tree<E> extends Iterable<E> {
    /**
     * 
     * @return the position of the root of the tree or null if empty.
     */
    Position<E> root();
    /**
     * 
     * @param p position 
     * @return the position of the parent of position p or null if p is the root
     */
    Position <E> parent(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @param p
     * @return an iterable collection containing the children of position p (if any)
     * @throws IllegalArgumentException 
     */
    Iterable<Position<E>>children(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @param p
     * @return the number of children of position p
     * 
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @return true if positio p has at least one child
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @param p
     * @return treue if posion p does not have any children
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @param p
     * @return true if position p is the root of the tree
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @return the number of positions(and hence elements) 
     * that are contained in the tree.
     */
    int size();
    
    /**
     * 
     * @return true if the tree does not contain any positions.
     */
    boolean isEmpty();
    
    /**
     * 
     * @return an iterator for all elements in the tree
     * (so that the tree itself is Iterable).
     */
    Iterator<E> iterator();
    
    /**
     * 
     * @return an iterable collection of all position of the tree.
     */
    Iterable<Position<E>> positions();
}
