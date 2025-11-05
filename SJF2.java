
package process;
import java.util.Scanner;

public class SJF2 { 
	public static void main(String[] args) { 
    int burst_time[], process[], waiting_time[], tat[], arr_time[], completion_time[], 
i, j, n, total = 0; 
    float wait_avg, TAT_avg; 

    Scanner sc = new Scanner(System.in); 
    System.out.println("Enter the number of process: "); 
    n = sc.nextInt(); 
    process = new int[n]; 
    burst_time = new int[n]; 
    waiting_time = new int[n]; 
    arr_time = new int[n]; 
    tat = new int[n]; 
    completion_time = new int[n]; 

    // Input burst time 
    System.out.println("\nEnter the burst time: "); 
    for (i = 0; i < n; i++) { 
        System.out.println("\nProcess[" + (i + 1) + "]:"); 
        burst_time[i] = sc.nextInt(); 
        process[i] = i + 1; 
    } 

    // Input arrival time 
    System.out.println("Enter the arrival time:"); 
    for (i = 0; i < n; i++) { 
        System.out.println("\nProcess[" + (i + 1) + "]:"); 
        arr_time[i] = sc.nextInt(); 
    } 

    // SJF Scheduling Algorithm 
    int currentTime = 0; 
    int completed = 0; 
    boolean[] visited = new boolean[n]; 
     
    while (completed < n) { 
        int shortest = -1; 
        int minBurst = Integer.MAX_VALUE; 
         
        // Find the process with shortest burst time that has arrived 
        for (i = 0; i < n; i++) { 
            if (!visited[i] && arr_time[i] <= currentTime && burst_time[i] < minBurst) 
{ 
                minBurst = burst_time[i]; 
                shortest = i; 
            } 
        } 
         
        if (shortest == -1) { 
            currentTime++; 
            continue; 
        } 
         
        // Execute the shortest job 
        completion_time[shortest] = currentTime + burst_time[shortest]; 
        currentTime = completion_time[shortest]; 
        visited[shortest] = true; 
        completed++; 
    } 

    // Calculate waiting time and turnaround time 
    for (i = 0; i < n; i++) { 
        tat[i] = completion_time[i] - arr_time[i]; 
        waiting_time[i] = tat[i] - burst_time[i]; 
        total += waiting_time[i]; 
    } 

    // Calculate averages 
    wait_avg = (float) total / n; 
    total = 0; 
     
    for (i = 0; i < n; i++) { 
        total += tat[i]; 
    } 
    TAT_avg = (float) total / n; 

    // Display results 
    System.out.println ("\nProcess\tBurst Time\tArrival Time\tCompletion Time\tWaiting Time\tTurnaround Time"); 
    for (i = 0; i < n; i++) { 
        System.out.println(process[i] + "\t\t" + burst_time[i] + "\t\t" + arr_time[i] + "\t\t" + completion_time[i] + "\t\t" + waiting_time[i] + "\t\t" + tat[i]); 
    } 

    System.out.println("\nAverage Waiting Time: " + wait_avg); 
    System.out.println("Average Turnaround Time: " + TAT_avg); 
} 


}
