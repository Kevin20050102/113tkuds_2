import java.util.*;

public class M01_BuildHeap {

    // 主程式：讀取輸入並執行
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入 heap 型態 (max / min)
        String type = sc.next();
        boolean isMaxHeap = type.equals("max");

        int n = sc.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }

        // 建堆
        buildHeap(heap, isMaxHeap);

        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i] + (i == n - 1 ? "" : " "));
        }
    }

    /**
     * 自底向上建堆 (Bottom-up Build Heap)
     * 時間複雜度: O(n)
     *   - 雖然單次 heapifyDown 最差 O(log n)，
     *     但只有少數節點接近 log n 深度，大部分較淺。
     *   - 加總下來複雜度為 O(n)，比逐一插入 O(n log n) 快。
     */
    private static void buildHeap(int[] arr, boolean isMaxHeap) {
        int n = arr.length;
        // 從最後一個非葉節點開始做 heapifyDown
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, isMaxHeap);
        }
    }

    /**
     * heapifyDown: 維護堆的性質
     * 複雜度: O(log n) (單次呼叫)
     */
    private static void heapifyDown(int[] arr, int n, int i, boolean isMaxHeap) {
        int parent = i;
        while (true) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int candidate = parent;

            if (isMaxHeap) {
                if (left < n && arr[left] > arr[candidate]) candidate = left;
                if (right < n && arr[right] > arr[candidate]) candidate = right;
            } else { // Min-heap
                if (left < n && arr[left] < arr[candidate]) candidate = left;
                if (right < n && arr[right] < arr[candidate]) candidate = right;
            }

            if (candidate == parent) break; // 已滿足堆性質
            swap(arr, parent, candidate);
            parent = candidate;
        }
    }

    // 工具：交換陣列元素
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
