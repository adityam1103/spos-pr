package page_replacement;
import java.util.Scanner;

public class PageReplacement {
	 public static void main(String[] args) { 
	        LRU lru = new LRU(); 
	        optimal optimal = new optimal(); 
	        Scanner sc = new Scanner(System.in); 
	        int choice; 
	 
	        do { 
	            System.out.println("\n1. Least Recently Used"); 
	            System.out.println("2. Optimal Page Replacement"); 
	            System.out.println("3. Exit"); 
	            System.out.print("Enter your choice: "); 
	            choice = sc.nextInt(); 
	 
	            switch (choice) { 
	                case 1 -> lru.execute(); 
	                case 2 -> optimal.execute(); 
	                case 3 -> System.out.println("Exiting..."); 
	                default -> System.out.println("Invalid choice!"); 
	            } 
	        } while (choice != 3); 
	        sc.close(); 
	    }
}
