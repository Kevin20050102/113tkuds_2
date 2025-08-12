import java.util.*;

public class KthSmallestElement {
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    public static int kthSmallestUsingMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入陣列
        System.out.print("請輸入陣列大小：");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("請輸入陣列元素：");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("請輸入 K (第幾小的元素)：");
        int k = sc.nextInt();

        if (k < 1 || k > n) {
            System.out.println("K 必須介於 1 到 " + n + " 之間！");
            return;
        }

        long start1 = System.nanoTime();
        int ans1 = kthSmallestUsingMaxHeap(arr, k);
        long end1 = System.nanoTime();


        long start2 = System.nanoTime();
        int ans2 = kthSmallestUsingMinHeap(arr, k);
        long end2 = System.nanoTime();

        System.out.println("\n--- 結果 ---");
        System.out.println("方法 1（大小為 K 的 Max Heap）：第 " + k + " 小的元素是 " + ans1);
        System.out.println("執行時間：" + (end1 - start1) + " ns");

        System.out.println("方法 2（Min Heap 提取 K 次）：第 " + k + " 小的元素是 " + ans2);
        System.out.println("執行時間：" + (end2 - start2) + " ns");

        sc.close();
    }
}

