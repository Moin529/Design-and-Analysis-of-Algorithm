import java.util.*;

public class ActivitySelection {
    static class Activity {
        int start, end;

        Activity(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of activities: ");
        int n = sc.nextInt();
        Activity[] activities = new Activity[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Start and end time of activity " + (i + 1) + ": ");
            activities[i] = new Activity(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        List<Activity> selected = new ArrayList<>();
        selected.add(activities[0]);
        int lastEnd = activities[0].end;

        for (int i = 1; i < n; i++) {
            if (activities[i].start >= lastEnd) {
                selected.add(activities[i]);
                lastEnd = activities[i].end;
            }
        }

        System.out.println("Selected activities:");
        for (Activity a : selected)
            System.out.println("(" + a.start + ", " + a.end + ")");
    }
}
