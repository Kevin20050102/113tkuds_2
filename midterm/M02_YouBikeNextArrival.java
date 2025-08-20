import java.util.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); 

        int[] times = new int[n];
        String[] rawTimes = new String[n];

        for (int i = 0; i < n; i++) {
            rawTimes[i] = sc.nextLine().trim();
            times[i] = toMinutes(rawTimes[i]);
        }

        String queryStr = sc.nextLine().trim();
        int query = toMinutes(queryStr);

        int idx = upperBound(times, query);

        if (idx == n) {
            System.out.println("No bike");
        } else {
            System.out.println(rawTimes[idx]);
        }
    }
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }
    private static int upperBound(int[] arr, int key) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
