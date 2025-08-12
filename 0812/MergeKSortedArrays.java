import java.util.*;

public class MergeKSortedArrays {

    // 包裝節點資訊的類別：值、陣列的索引、該陣列內的元素索引
    static class Node {
        int value;
        int arrayIndex;
        int elementIndex;

        Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();

        // 使用 PriorityQueue 當作 Min Heap，根據 Node 的 value 排序
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));

        // 初始化，每個陣列取第一個元素放入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Node(arrays[i][0], i, 0));
            }
        }

        // 重複取出 heap 的最小值並放入結果，再補上對應陣列的下一個元素
        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            result.add(current.value);

            int nextElementIndex = current.elementIndex + 1;
            if (nextElementIndex < arrays[current.arrayIndex].length) {
                int nextValue = arrays[current.arrayIndex][nextElementIndex];
                minHeap.offer(new Node(nextValue, current.arrayIndex, nextElementIndex));
            }
        }

        return result;
    }

    // 主程式：讀取使用者輸入，並輸出結果
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入陣列數量 K：");
        int k = scanner.nextInt();
        int[][] arrays = new int[k][];

        for (int i = 0; i < k; i++) {
            System.out.println("請輸入第 " + (i + 1) + " 個陣列的長度：");
            int length = scanner.nextInt();
            arrays[i] = new int[length];

            System.out.println("請輸入第 " + (i + 1) + " 個陣列的元素（由小到大）：");
            for (int j = 0; j < length; j++) {
                arrays[i][j] = scanner.nextInt();
            }
        }

        List<Integer> merged = mergeKSortedArrays(arrays);
        System.out.println("合併後的有序陣列：");
        System.out.println(merged);
    }
}
