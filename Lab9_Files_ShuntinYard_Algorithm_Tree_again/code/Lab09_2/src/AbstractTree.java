/**
 *
 * @author Goodrich, Tamassia, Goldwasser
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {return numChildren(p) > 0 ; }
    public boolean isExternal(Position<E> p) {return numChildren(p) == 0;}
    public boolean isRoot(Position<E> p) {return p == root();}
    public boolean isEmpty()    { return size() == 0;}
    
    public Iterable<Position<E>> positions(){return preorder();}
    
    /**adds positions of the subtree rooted at Position p to the given snapshot */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot)
    {
        snapshot.add(p);        // for preoder, we add positon p before exploring subtress
        for(Position<E> c: children(p))
            preorderSubtree(c,snapshot);
    }
    public Iterable<Position<E>> preorder()
    {
         List<Position<E>> snapshot = new ArrayList<>();
         if(!isEmpty())
             preorderSubtree(root(),snapshot);      // fill the snapshot recursively
         return snapshot;
    }
    
    //--------------------Posorder traversal----------------
    
    /**Adds positions of the subtree rooted at Position p to the given sanpshot*/
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot)
    {
       for(Position<E> c:children(p))
           postorderSubtree(c,snapshot);
       snapshot.add(p);                     //for postorder, we add position p after exploring subtrees   
    }
    
    /** Returns an iterable collection of positions of the tree, reported in postorder. */
    public Iterable<Position<E>> postorder()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            postorderSubtree(root(), snapshot); // fill the snapshot recursively
        return snapshot;
    }
    //-------------------End of code for postOrder ------------------
    
    
    //---------------   Nested iterarator class  -----------
    /* 8.16 This class adapts the iteration produced by positions() tor return elements */
    private class ElementIterator implements Iterator<E>
    {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext(){return posIterator.hasNext();}
        public E next(){return posIterator.next().getElement();}
        public void remove(){posIterator.remove();}
        
    }
    /******** End of Nested ElementIterator Class *****/
    
    /**returns an iterator of the elements stored in the tree */
    public Iterator<E> iterator(){ return new ElementIterator();}
    
    /** 
     * Returns an iterable collection of positions of the tree in breadth-first order.
     * code fragment 8.21
     */
    public Iterable<Position<E>> breathFirst()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
        {
           Queue<Position<E>> fringe = new LinkedQueue<>();
           fringe.enqueue(root());
           while(!fringe.isEmpty())
           {
               Position<E> p = fringe.dequeue();
               snapshot.add(p);
               for(Position<E> c: children(p))
               fringe.enqueue(c);   
           }
                 
        }
        return snapshot;
    }
    
    
}
