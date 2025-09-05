import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int left = lowerBound(arr, target);
        int right = lowerBound(arr, target + 1) - 1;
        if (left < n && arr[left] == target) {
            System.out.println(left + " " + right);
        } else {
            System.out.println("-1 -1");
        }
    }

    static int lowerBound(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] < x) l = m + 1;
            else r = m;
        }
        return l;
    }
}
