import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                System.out.println(map.get(arr[i]) + " " + i);
                return;
            }
            map.put(target - arr[i], i);
        }
        System.out.println("-1 -1");
    }
}

