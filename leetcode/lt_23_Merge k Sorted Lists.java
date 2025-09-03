import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // 處理空輸入情況
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 建立最小堆，依照 ListNode 的 val 做排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // 將每個 linked list 的頭節點加入最小堆
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // 建立 dummy 節點與尾指標
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 不斷從最小堆取出最小值節點並加入合併後的 linked list
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll(); // 取出最小節點
            tail.next = minNode; // 接到結果上
            tail = tail.next; // 更新尾指標

            // 如果該節點還有下一個，繼續加入最小堆
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        // 回傳合併後的 linked list（從 dummy.next 開始）
        return dummy.next;
    }
}
