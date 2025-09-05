import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListNode dummy = new ListNode(0), cur = dummy;
        String line = sc.nextLine().trim();
        if (!line.isEmpty()) {
            String[] parts = line.split(" ");
            for (String s : parts) {
                cur.next = new ListNode(Integer.parseInt(s));
                cur = cur.next;
            }
        }

        ListNode head = dummy.next;
        dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;
            a.next = b.next;
            b.next = a;
            prev.next = b;
            prev = a;
        }

        cur = dummy.next;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" ");
            cur = cur.next;
        }
    }
}
