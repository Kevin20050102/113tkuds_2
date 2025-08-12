import java.util.*;

public class MovingAverageStream {

    private final int size;
    private final Queue<Integer> window;
    private final PriorityQueue<Integer> maxHeap; // 小的一半
    private final PriorityQueue<Integer> minHeap; // 大的一半
    private final TreeMap<Integer, Integer> counts;
    private int sum = 0;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.counts = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        counts.put(val, counts.getOrDefault(val, 0) + 1);

        // 加入到 heap 中維持中位數
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }

        // 移除舊值
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            counts.put(removed, counts.get(removed) - 1);
            if (counts.get(removed) == 0) {
                counts.remove(removed);
            }

            // Lazy removal from heaps
            if (!maxHeap.remove(removed)) {
                minHeap.remove(removed);
            }
        }

        balanceHeaps();

        return (double) sum / window.size();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (window.isEmpty()) return 0;
        if (window.size() % 2 == 0) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return (double) maxHeap.peek();
        }
    }

    public int getMin() {
        if (counts.isEmpty()) throw new NoSuchElementException("Window is empty");
        return counts.firstKey();
    }

    public int getMax() {
        if (counts.isEmpty()) throw new NoSuchElementException("Window is empty");
        return counts.lastKey();
    }

    // 主程式：只輸入測資，無提示語句
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int windowSize = sc.nextInt();
        int opCount = sc.nextInt(); // 操作數量

        MovingAverageStream ma = new MovingAverageStream(windowSize);

        for (int i = 0; i < opCount; i++) {
            String op = sc.next();
            if (op.equals("next")) {
                int val = sc.nextInt();
                System.out.printf("%.2f\n", ma.next(val));
            } else if (op.equals("median")) {
                System.out.printf("%.2f\n", ma.getMedian());
            } else if (op.equals("min")) {
                System.out.println(ma.getMin());
            } else if (op.equals("max")) {
                System.out.println(ma.getMax());
            }
        }
    }
}
