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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立 dummy 節點，方便處理刪除頭節點的情況
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n+1 步，確保 slow 停在要刪除節點的前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 和 slow 一起走，直到 fast 到達尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;

        return dummy.next;
    }
}
