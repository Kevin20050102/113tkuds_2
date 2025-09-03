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
    public ListNode swapPairs(ListNode head) {
        // 建立 dummy 節點，讓 head 的前面有一個節點方便操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev 是每次交換的前一個節點
        ListNode prev = dummy;

        // 當前和下一個節點都不為 null 時，才能交換
        while (prev.next != null && prev.next.next != null) {
            // 兩個要交換的節點
            ListNode first = prev.next;
            ListNode second = first.next;

            // 執行交換
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 將 prev 移動到下一組的前一個節點
            prev = first;
        }

        // 返回新的 head（即 dummy.next）
        return dummy.next;
    }
}
