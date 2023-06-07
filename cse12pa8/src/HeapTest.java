// import static org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

/**
 * HeapTest class implements tester that will test the methods from heap file
 */
public class HeapTest {

	@Test
	public void testAdd() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(500, "10");
		heap.add(53, "10");
		heap.add(5, "10");
		heap.add(30, "10");
		heap.add(27, "10");
		heap.add(26, "10");
		heap.add(3, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		System.out.println("Heap: " + heap.toString());
		heap.poll();
		heap.poll();
		heap.poll();
		System.out.println("Heap: " + heap.toString());

		assertEquals(11, heap.entries.size());
	}

	@Test
	public void testEmpty() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(19, "");
		System.out.println("Heap: " + heap.toString());
		System.out.println("Polled: " + heap.poll());
		System.out.println("Heap: " + heap.toString());
		System.out.println("Empty? " + heap.isEmpty());
		assertEquals(1, heap.entries.size());
	}
	@Test
	public void testFinder() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, Integer> heap = new Heap<Integer, Integer>(maxHeapComparator);
		heap.add(1, 1);
		heap.add(4, 4);
		heap.add(6, 6);
		heap.add(8, 8);
		heap.add(9, 9);
		heap.add(10, 10);
		heap.add(13, 13);
		heap.add(14, 14);
		heap.add(0, 0);
		heap.add(98, 98);
		heap.add(96, 96);
		heap.add(5, 5);
		heap.add(3, 3);
		heap.add(2, 2);
		System.out.println(heap.toString());

		ElementFinder findElement = new ElementFinder();
		System.out.println(findElement.Kth_finder("src/input.txt", 4, "largest"));

		assertEquals(14, heap.entries.size());
	}



}