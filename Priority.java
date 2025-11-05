package process;
import java.util.*;

class Process {
    int pid;            // Process ID
    int burstTime;      // Burst Time
    int priority;       // Priority (lower number = higher priority)
    int waitingTime;    // Waiting Time
    int turnAroundTime; // Turn Around Time

    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class Priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for process " + (i + 1));
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            System.out.print("Priority (lower = higher priority): ");
            int pr = sc.nextInt();
            p[i] = new Process(i + 1, bt, pr);
        }

        // Sort by priority (ascending)
        Arrays.sort(p, Comparator.comparingInt(proc -> proc.priority));

        // Calculate waiting time and turnaround time
        p[0].waitingTime = 0; // first process has no waiting time
        for (int i = 1; i < n; i++) {
            p[i].waitingTime = 0;
            for (int j = 0; j < i; j++)
                p[i].waitingTime += p[j].burstTime;
        }

        for (int i = 0; i < n; i++) {
            p[i].turnAroundTime = p[i].burstTime + p[i].waitingTime;
        }

        // Display results
        System.out.println("\nProcess\tBT\tPriority\tWT\tTAT");
        int totalWT = 0, totalTAT = 0;

        for (int i = 0; i < n; i++) {
            totalWT += p[i].waitingTime;
            totalTAT += p[i].turnAroundTime;
            System.out.println(p[i].pid + "\t" + p[i].burstTime + "\t" +
                    p[i].priority + "\t\t" + p[i].waitingTime + "\t" + p[i].turnAroundTime);
        }

        System.out.printf("\nAverage Waiting Time: %.2f", (float) totalWT / n);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", (float) totalTAT / n);
        sc.close();
    }
}
