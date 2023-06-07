import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class ElementFinder {

	Comparator<Integer> smallComparator;
	Comparator<Integer> largeComparator;
	static Heap<Integer, Integer> heap;

	public static int Kth_finder(String filename, int K, String operation) {
		// Create a comparator depending upon the type of operation
		Comparator<Integer> smallComparator;
		Comparator<Integer> largeComparator;
		if (operation.equals("largest") || operation.equals("smallest")) {
			smallComparator = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			};

			largeComparator = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
			};
			if (operation.equals("largest")) {
				heap = new Heap<>(smallComparator);
			} else {
				heap = new Heap<>(largeComparator);
			}
			
		} else {
			return -1;
		}


		try {
            File f = new File(filename);
            Scanner sc = new Scanner(f);
			int size = K + 1;
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(" ");
                for (int i = 0; i < data.length; i++) {
					heap.add(Integer.valueOf(data[i]), Integer.valueOf(data[i]));
					if (heap.entries.size() > size) {
						heap.poll();
					}
				}
            }
            sc.close();
        } catch (FileNotFoundException e) {
            return -1;
        }
		if (heap.size() == 0) {
			return -1;
		}

		heap.poll();
		return heap.peek().key;
		
	}
	
	/* Add any helper methods you find useful */

}

