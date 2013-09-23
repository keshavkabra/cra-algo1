import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
 
 private int size;
 private class Node {
  private Item item;
  private Node next;
  private Node prev;
 }
 private Node first;
 private Node last;
  
 public Deque()                     // construct an empty deque
 {
  size = 0;
  first = null;
  last = null;
 }
 
 public boolean isEmpty()           // is the deque empty?
 {
  return size == 0;
 }
 
 public int size()                  // return the number of items on the deque
 {
  return size;
 }
 
 public void addFirst(Item item)    // insert the item at the front
 {
  if (item == null)
   throw new NullPointerException();
  Node temp = first;
  first = new Node();
  first.item = item;
  first.next = temp;
  first.prev = null;
  if (temp != null)
   temp.prev = first;
  if (last == null)
   last = first;
  size++;
 }
 
 public void addLast(Item item)     // insert the item at the end
 {
  if (item == null)
   throw new NullPointerException();
  Node temp = last;
  last = new Node();
  last.item = item;
  last.next = null;
  last.prev = temp;
  if (temp != null)
   temp.next = last;
  if (first == null)
   first = last;
  size++;
 }
 
 public Item removeFirst()          // delete and return the item at the front
 {
  if (first == null)
   throw new NoSuchElementException();
  Node temp = first.next;
  Item ret = first.item;
  if (temp != null)
   temp.prev = null;
  if (first == last)
   last = temp;
  first = temp;
  size--;
  return ret;
 }
 
 public Item removeLast()           // delete and return the item at the end
 {
  if (last == null)
   throw new NoSuchElementException();
  Node temp = last.prev;
  Item ret = last.item;
  if (temp != null)
   temp.next = null;
  if (first == last)
   first = temp;
  last = temp;
  size--;
  return ret;
 }
 
 public Iterator<Item> iterator()   // return an iterator over items in order from front to end
 {
  return new ListIterator();
 }
 
 private class ListIterator implements Iterator<Item>
 {
  private Node current = first;
  public boolean hasNext()
  { 
   return current != null;
  }
  
  public void remove()
  {
   throw new UnsupportedOperationException();
  }
  
  public Item next()
  {
   if (!hasNext())
                throw new NoSuchElementException();
         Item item = current.item;
         current = current.next;
         return item;
  }
 }
}