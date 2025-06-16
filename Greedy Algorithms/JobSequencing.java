import java.util.*;

public class JobSequencing {
    static class Job {
        char id;
        int deadline, profit;

        Job(char id, int d, int p) {
            this.id = id;
            deadline = d;
            profit = p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Job ID, deadline, and profit: ");
            jobs[i] = new Job(sc.next().charAt(0), sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = Arrays.stream(jobs).mapToInt(j -> j.deadline).max().orElse(0);
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];

        for (Job job : jobs) {
            for (int j = Math.min(maxDeadline - 1, job.deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = job.id;
                    break;
                }
            }
        }

        System.out.print("Scheduled jobs: ");
        for (int i = 0; i < maxDeadline; i++)
            if (slot[i]) System.out.print(result[i] + " ");
        System.out.println();
    }
}
