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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Dummy node 簡化邊界處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev 是每段開始前的節點
        ListNode prev = dummy;

        while (true) {
            // 檢查是否有 k 個節點可以反轉
            ListNode node = prev;
            for (int i = 0; i < k && node != null; i++) {
                node = node.next;
            }
            if (node == null) break; // 不足 k 個，不反轉

            // 開始反轉 k 個節點
            ListNode curr = prev.next;
            ListNode next = curr.next;

            for (int i = 1; i < k; i++) {
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }

            // prev 移動到下一段的前面
            prev = curr;
        }

        return dummy.next;
    }
}
