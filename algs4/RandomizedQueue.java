import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
 
 private int size;
 private Item[] item;   
 
 public RandomizedQueue()           // construct an empty randomized queue
 {
    size = 0;
    item = (Item[]) new Object[1];
 }
 
 public boolean isEmpty()           // is the queue empty?
 {
  return size == 0;
 }
 
 public int size()                  // return the number of items on the queue
 {
  return size;
 }
 
 public void enqueue(Item it)     // add the item
 {
  if (it == null)
   throw new NullPointerException();
  if (size == item.length)
   resize(2*item.length);
  item[size] = it;
  size++;    
 }
 
 public Item dequeue()              // delete and return a random item
 {
  if (size == 0)
   throw new NoSuchElementException();
  int temp = StdRandom.uniform(size);
  Item ret = item[temp];
  if (temp == size-1)
      item[temp] = null;
  else
  {
      item[temp] = item[size-1];
      item[size-1] = null;
  }
  if (size > 0 && size == item.length/4)
    resize(item.length/2);
  size--;
  return ret;
 }
 
 public Item sample()               // return (but do not delete) a random item
 {
  if (size == 0)
   throw new NoSuchElementException();
  int temp = StdRandom.uniform(size);
  return item[temp];
 }
 
 public Iterator<Item> iterator()   // return an independent iterator over items in random order
 {
  return new RandomizedIterator();
 }
 
 private class RandomizedIterator implements Iterator<Item>
 {
     private int tempSize;
     private Item[] tempItem;
  public RandomizedIterator()
  {
      tempSize = size;
      tempItem = (Item[]) new Object[size];
      for (int i = 0; i < size; i++)
          tempItem[i] = item[i];
  }
  public boolean hasNext() 
  {
   return tempSize != 0;
  }
  public Item next()
  {
   if (!hasNext())
    throw new NoSuchElementException();
         int temp = StdRandom.uniform(tempSize);
         Item ret = tempItem[temp];
         if (temp == tempSize - 1)
         {
               tempSize--;
                return ret;
         }
         else
         {
                tempItem[temp] = tempItem[tempSize-1];
                tempItem[tempSize-1] = ret;
                tempSize--;
                return ret;
         }

        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
 }
 
 
 private void resize(int max)
 { // Move stack to a new array of size max.
  Item[] temp = (Item[]) new Object[max];
  for (int i = 0; i < size; i++)
   temp[i] = item[i];
  item = temp;
 }
}