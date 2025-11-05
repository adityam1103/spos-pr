package page_replacement;
import java.util.Scanner; 

public class optimal {
	 public void execute() { 
	        Scanner sc = new Scanner(System.in); 
	        int noOfPages, capacity, hit = 0, fault = 0; 
	 
	        System.out.print("Enter the number of pages: "); 
	        noOfPages = sc.nextInt(); 
	        int[] pages = new int[noOfPages]; 
	        System.out.println("Enter the page numbers:"); 
	        for (int i = 0; i < noOfPages; i++) { 
	            pages[i] = sc.nextInt(); 
	        } 
	 
	        System.out.print("Enter frame capacity: "); 
	        capacity = sc.nextInt(); 
	 
	        int[] frame = new int[capacity]; 
	        int[] recent = new int[capacity]; 
	        int[][] table = new int[noOfPages][capacity]; 
	        boolean isFull = false; 
	        int ptr = 0; 
	 
	        for (int i = 0; i < capacity; i++) frame[i] = -1; 
	 
	        System.out.println("------------------------------------------------------------"); 
	        for (int i = 0; i < noOfPages; i++) { 
	            int search = -1; 
	            for (int j = 0; j < capacity; j++) { 
	                if (frame[j] == pages[i]) { 
	                    search = j; 
	                    hit++; 
	                    System.out.printf("%4s", "H"); 
	                    break; 
	                } 
	            } 
	            if (search == -1) { 
	                if (isFull) { 
	                    int min = recent[0], pos = 0; 
	                    for (int j = 1; j < capacity; j++) { 
	                        if (recent[j] < min) { 
	                            min = recent[j]; 
	                            pos = j; 
	                        } 
	                    } 
	                    frame[pos] = pages[i]; 
	                    recent[pos] = i + 1; 
	                } else { 
	                    frame[ptr] = pages[i]; 
	                    recent[ptr] = i + 1; 
	                    ptr++; 
	                    if (ptr == capacity) { 
	                        ptr = 0; 
	                        isFull = true; 
	                    } 
	                } 
	                fault++; 
	                System.out.printf("%4s", "F"); 
	            } else { 
	                recent[search] = i + 1; 
	            } 
	            System.arraycopy(frame, 0, table[i], 0, capacity); 
	        } 
	 
	        System.out.println("\n------------------------------------------------------------"); 
	        for (int i = 0; i < capacity; i++) { 
	            for (int j = 0; j < noOfPages; j++) { 
	                if (table[j][i] == -1) 
	                    System.out.printf("%3s ", "-"); 
	                else 
	                    System.out.printf("%3d ", table[j][i]); 
	            } 
	            System.out.println(); 
	        } 
	 
	        double hitRatio = ((double) hit / noOfPages) * 100; 
	        double faultRatio = ((double) fault / noOfPages) * 100; 
	        System.out.println("------------------------------------------------------------"); 
	        System.out.println("Page Faults: " + fault + "\nPage Hits: " + hit); 
	        System.out.printf("Hit Ratio: %.2f%%\nFault Ratio: %.2f%%\n", hitRatio, faultRatio); 
	    } 
}
