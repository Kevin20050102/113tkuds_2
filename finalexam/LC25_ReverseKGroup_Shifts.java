import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = end, curr = start;
        while (curr != end) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode dummy = new ListNode(0), cur = dummy;
        while (sc.hasNextInt()) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }

        dummy.next = reverseKGroup(dummy.next, k);

        cur = dummy.next;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" ");
            cur = cur.next;
        }
    }

    static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (true) {
            ListNode end = prev;
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = prev.next;
            ListNode next = end.next;
            prev.next = reverse(start, end.next);
            prev = start;
        }
        return dummy.next;
    }
}
