import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K, V> implements PriorityQueue<K, V> {

    public List<Entry<K, V>> entries;
    public Comparator<K> comparator;

    public Heap (Comparator<K> comparator) {
        this.comparator = comparator;
        entries = new ArrayList<Entry<K, V>>();
    }

    public int compare(K a, K b) {
        return this.comparator.compare(a, b);
    }

    /**
     * Adds key and value entry to the heap, then sorts it in
     */
    @Override
    public void add (K k, V v) {
        entries.add(new Entry(k, v));
        if (size() > 1) {
            bubbleUp(entries.size() - 1);
        }
    }

    /**
     * Returns the top entry, and then deletes that entry while also sorting the list
     */
    @Override
    public Entry<K, V> poll() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            return entries.remove(0);
        }
        Entry<K, V> output = entries.get(0);
        entries.set(0, entries.remove(size() - 1));
        bubbleDown(0);
        return output;
    }

    /**
     * Returns the top entry, but doesn't delete it
     */
    @Override
    public Entry<K, V> peek() {
        if (size() == 0) {throw new NoSuchElementException("Empty Heap");}
        return entries.get(0);
    }

    /**
     * Returns the heap as an array of entries
     */
    @Override
    public List<Entry<K, V>> toArray() {
        return entries;
    }

    /**
     * Returns true if heap is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Returns index of the parent element of given index
     * 
     * @param index Index of element to find parent of
     * @return Index of parent
     */
    public int parent(int index) {
        if (index > size()) {
            throw new NoSuchElementException("Invalid index");
        } else if (index == 0) {
            return 0;
        }
        return (index - 1) / 2;
    }

    /**
     * Returns left side child of given element at index
     * 
     * @param index Index of element to find child of
     * @return Index of left side child
     */
    public int left (int index) {
        if (index > size()) {
            throw new NoSuchElementException("Index doesn't exist");
        } 
        return (index * 2) + 1;
    }

    /**
     * Returns right side child of given element at index
     * 
     * @param index Index of element to find child of
     * @return Index of right side child
     */
    public int right(int index) {
        if (index > size()) {
            throw new NoSuchElementException("Index doesn't exist");
        } 
        return (index * 2) + 2;
    }

    /**
     * Returns size of the heap
     * 
     * @return Size of the heap
     */
    public int size() {
        return entries.size();
    }

    /**
     * Swaps the two elements at the given index 
     * 
     * @param i1 First element to swap
     * @param i2 Second element to swap
     */
    public void swap(int i1, int i2) {
        try {
            Entry<K, V> oldi1 = entries.get(i1);
            entries.set(i1, entries.get(i2));
            entries.set(i2, oldi1);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Pushes up element at given index to its appropriate spot 
     * 
     * @param index Index of element to move 
     */
    public void bubbleUp(int index) {
        if (index > size()) {
            return;
        }
        Entry<K,V> parent = this.entries.get(parent(index));
        Entry<K,V> child = this.entries.get(index);
        if (this.compare(child.getKey(), parent.getKey()) > 0) {
            swap(index, parent(index));
            bubbleUp(parent(index));
        } else {
            return;
        }
    }

    /**
     * Sends element at given index down to its appropriate spot 
     * 
     * @param index Index of element to move
     */
    public void bubbleDown(int index) {
        if (index > size()) {
            return;
        }
        if (left(index) > size()) {
            return;
        }
        if (existsAndGreater(right(index), left(index))) {
            if (existsAndGreater(right(index), index)) {
                swap(right(index), index);
                bubbleDown(right(index));
            }
        } else {
            if (existsAndGreater(left(index), index)) {
                swap(left(index), index);
                bubbleDown(left(index));
            }
        }
    }

    /**
     * Returns true if the first element is larger than the second (given that both exist)
     * If one or both don't exist, then return false
     * 
     * @param i1 Index of first element
     * @param i2 Index of second element to compare i1 to 
     * @return 
     */
    public boolean existsAndGreater(int i1, int i2) {
        try {
            int result = this.compare(entries.get(i1).getKey(), entries.get(i2).getKey());
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Return the heap as a string list of entries
     */
    public String toString() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Entry<K,V> entry : entries) {
            stringList.add(entry.toString());
        }
        return stringList.toString();
    }
}
