import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K, V> implements PriorityQueue<K, V> {

    public List<Entry<K, V>> entries;
    public Comparator<K> comparator;

    public Heap (Comparator comparator) {
        this.comparator = comparator;
        entries = new ArrayList<Entry<K, V>>();
    }

    public int compare(K a, K b) {
        return this.comparator.compare(a, b);
    }

    @Override
    public void add (K k, V v) {
        entries.add(new Entry(k, v));
        if (size() > 1) {
            bubbleUp(entries.size() - 1);
        }
    }

    @Override
    public Entry<K, V> poll() {
        Entry<K, V> output = entries.get(0);
        entries.set(0, entries.remove(size() - 1));
        bubbleDown(0);
        return output;
    }

    @Override
    public Entry<K, V> peek() {
        return entries.get(0);
    }

    @Override
    public List<Entry<K, V>> toArray() {
        return entries;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public int parent(int index) {
        if (index == 0 || index > size()) {
            throw new NoSuchElementException("Invalid index");
        } 
        return (index - 1) / 2;
    }

    public int left (int index) {
        if (index > size()) {
            throw new NoSuchElementException("Index doesn't exist");
        } 
        return (index * 2) + 1;
    }

    public int right(int index) {
        if (index > size()) {
            throw new NoSuchElementException("Index doesn't exist");
        } 
        return (index * 2) + 2;
    }

    public int size() {
        return entries.size();
    }

    public void swap(int i1, int i2) {
        try {
            Entry<K, V> oldi1 = entries.get(i1);
            entries.set(i1, entries.get(i2));
            entries.set(i2, oldi1);
        } catch (Exception e) {
            return;
        }
    }

    public void bubbleUp(int index) {
        if (index > size()) {
            throw new NoSuchElementException("Index doesn't exist");
        }
        Entry<K,V> parent = this.entries.get(parent(index));
        Entry<K,V> child = this.entries.get(index);
        if (this.compare(child.key, parent.key) > 0) {
            swap(index, parent(index));
            bubbleUp(index);
        } else {
            return;
        }
    }

    public void bubbleDown(int index) {
        if (index > size()) {
            throw new NoSuchElementException("Index doesn't exist");
        }
        if (left(index) > size()) {
            return;
        }
        if (existsAndGreater(right(index), left(index))) {
            swap(right(index), index);
            bubbleDown(right(index));
        } else {
            swap(left(index), index);
            bubbleDown(left(index));
        }
    }

    public boolean existsAndGreater(int i1, int i2) {
        try {
            int result = this.compare(entries.get(i1).key, entries.get(i2).key);
            System.out.println(result);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String toString() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Entry<K,V> entry : entries) {
            stringList.add(entry.toString());
        }
        return stringList.toString();
    }
}
