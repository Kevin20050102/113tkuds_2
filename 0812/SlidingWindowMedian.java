import java.util.*;

public class SlidingWindowMedian {

    // 兩個 heap：maxHeap 存左邊小的部分，minHeap 存右邊大的部分
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // 加入新數字
    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    // 移除數字
    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    // 保持兩個 heap 大小平衡
    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // 取得中位數
    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return (double) maxHeap.peek();
        }
    }

    // 主邏輯：計算滑動視窗的中位數
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new double[0];

        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);

            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
                remove(nums[i - k + 1]);
            }
        }

        return result;
    }

    // 主程式：讀取輸入並執行
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SlidingWindowMedian swm = new SlidingWindowMedian();

        System.out.println("請輸入陣列長度：");
        int n = scanner.nextInt();
        int[] nums = new int[n];

        System.out.println("請輸入陣列元素：");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println("請輸入視窗大小 K：");
        int k = scanner.nextInt();

        double[] medians = swm.medianSlidingWindow(nums, k);
        System.out.println("滑動視窗中位數為：");
        System.out.println(Arrays.toString(medians));
    }
}
