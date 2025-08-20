import java.util.*;

public class M12_MergeKTimeTables {
    // Heap 中儲存的節點資訊
    static class Node {
        int time;       // 時間（分鐘）
        int listIndex;  // 來自第幾個列表
        int elemIndex;  // 該列表中的第幾個元素

        Node(int time, int listIndex, int elemIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elemIndex = elemIndex;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();  // 輸入 K 組

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(sc.nextInt());
            }
            lists.add(list);
        }

        // Min-Heap：時間越小優先
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));

        // 將每條列表的第一個時間推入 heap
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.add(new Node(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();

        // 開始合併
        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            result.add(curr.time);

            // 如果還有下一個時間，加入 heap
            List<Integer> fromList = lists.get(curr.listIndex);
            int nextIndex = curr.elemIndex + 1;
            if (nextIndex < fromList.size()) {
                minHeap.add(new Node(fromList.get(nextIndex), curr.listIndex, nextIndex));
            }
        }

        // 輸出結果
        for (int time : result) {
            System.out.print(time + " ");
        }

        sc.close();
    }
}
