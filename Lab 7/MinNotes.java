import java.util.*;
import java.lang.reflect.*;;

public class MinNotes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] notes = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000 };
        int v = sc.nextInt();
        int count = 0;

        for (int i = 0; i < notes.length; i++) {
            for (int j = i + 1; j < notes.length; j++) {
                if (notes[i] < notes[j]) {
                    int temp = notes[i];
                    notes[i] = notes[j];
                    notes[j] = temp;
                }
            }
        }
        ArrayList<Integer> notesUsed = new ArrayList<>();

        for (int i = 0; i < notes.length; i++) {
            if (notes[i] <= v) {
                while (notes[i] <= v) {
                    v = v - notes[i];
                    count++;
                    notesUsed.add(notes[i]);
                }
            }
        }

        System.out.println(count);
    }
}