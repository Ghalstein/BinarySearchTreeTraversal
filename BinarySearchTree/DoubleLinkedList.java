/******************************************************************************                                        
 *  Compilation:  javac LinkedList.java                                                                                
 *  Execution:    java LinkedList                                                                                      
 *  Dependencies: Picture.java                                                                                         
 ******************************************************************************/
 
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;
import java.awt.Color;
 
public class DoubleLinkedList<Item> implements Iterable<Item> { // to enable for each syntax                            
    private int n;          // number of elements                                                                      
    private Node first;     // beginning of the list                                                                   
    private Node last;      // end of the list                                                                         
 
    // helper linked list class                                                                                        
    private class Node {
        protected Item item;
        private Node next;
        private Node prev; // reference to the predecessor node
    }
 
    /** Initializes an empty list. */
    public DoubleLinkedList() {
        first = null;
        last = null;
        n = 0;
    }
 
    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }

    /* add to beginning of list */
    public void addFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        if (last == null)
            last = first;
        first.item = item;
        first.next = oldfirst;
        oldfirst.prev = first;
        first.prev = null;
        n++;
    }
 
    /* add to end of list */
    public void addLast(Item item) {
        Node temp = new Node();
        temp.item = item;
        if (last == null) {
            first = temp;
            last = temp;
            last.prev = null;
        }
        else {
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        n++;
    }

    /** Removes the item at the beginning of the list. **/
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("List empty");
        Item item = first.item;        // save item to return                                                          
        first = first.next;            // delete first node                                                            
        n--;
        return item;                   // return the saved item                                                        
    }
 
    /** Returns (but does not remove) the first element in the list. */
    public Item getFirst() {
        if (isEmpty())
            throw new NoSuchElementException("List empty");
        return first.item;
    }

    //Returns last element in the list
    public Item getLast() {
        if (isEmpty())
            throw new NoSuchElementException("List empty");
        return last.item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("List empty");
        Item item = last.prev.item;      // save item to return                                                          
        last = last.prev; 
        last.next = null;           // delete first node                                                            
        n--;
        return item;                   // return the saved item                                                        
    }

    public void remove(Item v){
        if (isEmpty())
            throw new NoSuchElementException("List empty");
        Node curr = first;
        if(curr.item == v){
            removeFirst();
            return;
        }
        while(curr.next != null){
            if(curr.next.item == v){
                curr.next = curr.next.next;
                if(curr.next == null)
                    last = curr;
                else curr.next.prev = curr;
                n--;
                return;
            } curr = curr.next;
        }
    }
 
    /**                                                                                                                
     * Returns an iterator to this list that traverses the items from first to last.                                   
     */

    public Iterator<Item> iterator() {
        return new ListIterator();
    }
 
    // The Iterator<T> interface contains methods hasNext(), remove(), next()                                          
    // (remove() is optional)                                                                                          
    public class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    //returns a reverse iterator
    public Iterator<Item> revIterator() {
        return new RevListIterator();
    }

    // reverse iterator
    public class RevListIterator implements Iterator<Item> {
        private Node current = last;
        public boolean hasNext()  {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }
 
    /** Unit test with some pictures */
    public static void pause(int t) {     /** stops for t milliseconds */
	try {
            Thread.sleep(t);
        } catch (Exception e) {}
    } 

    public static void main(String[] args){
        DoubleLinkedList<Integer> testList = new DoubleLinkedList<Integer>();
        testList.addLast(1);
        testList.addLast(2);
        testList.addLast(3);
        testList.addLast(1);
        testList.remove(1); // tests for removing an item in the middle of the list and if it is a duplicate it just removes the first occurrence. 
        testList.remove(5); // tests for removing an item that is not in the list 
        Iterator<Integer> it = testList.iterator();
        for(int test : testList){
            System.out.println(test);
        }
    }
}
