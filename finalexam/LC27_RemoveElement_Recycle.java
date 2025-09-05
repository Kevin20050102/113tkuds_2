import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int write = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != val) arr[write++] = arr[i];
        }
        System.out.println(write);
        for (int i = 0; i < write; i++) {
            System.out.print(arr[i]);
            if (i != write - 1) System.out.print(" ");
        }
    }
}
