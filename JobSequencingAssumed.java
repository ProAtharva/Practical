import java.util.*;

// Simple helper class — note: not public now
class Job {
    char id;      // Job ID (like A, B, C)
    int deadline; // Deadline for the job
    int profit;   // Profit if job is done before deadline

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencingAssumed {
    public static void main(String[] args) {
        // Step 1: Assume some input data (no user input)
        Job[] jobs = {
            new Job('A', 1, 200),
            new Job('B', 2, 20),
            new Job('C', 2, 60),
            new Job('D', 1, 150)
        };

        // Step 2: Sort jobs by profit (descending)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 3: Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs)
            maxDeadline = Math.max(maxDeadline, job.deadline);

        // Step 4: Create slot/result arrays
        char[] result = new char[maxDeadline + 1];
        boolean[] slot = new boolean[maxDeadline + 1];

        int totalProfit = 0;

        // Step 5: Schedule jobs
        for (Job job : jobs) {
            for (int j = job.deadline; j > 0; j--) {
                if (!slot[j]) {
                    result[j] = job.id;
                    slot[j] = true;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Step 6: Print scheduled jobs
        System.out.print("Scheduled Jobs: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slot[i])
                System.out.print(result[i] + " ");
        }

        // Step 7: Print total profit
        System.out.println("\nTotal Profit: " + totalProfit);
    }
}
