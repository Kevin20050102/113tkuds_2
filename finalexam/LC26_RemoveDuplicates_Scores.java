import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        int write = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[write - 1]) {
                arr[write++] = arr[i];
            }
        }
        System.out.println(write);
        for (int i = 0; i < write; i++) {
            System.out.print(arr[i]);
            if (i != write - 1) System.out.print(" ");
        }
    }
}
