class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (true) {
            ListNode kth = prev;

            for (int i = 0; i < k; i++) {
                kth = kth.next;

                if (kth == null) {
                    return dummy.next;
                }
            }

            ListNode nextGroup = kth.next;

            // Reverse k nodes
            ListNode prevNode = nextGroup;
            ListNode curr = prev.next;

            while (curr != nextGroup) {
                ListNode temp = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = temp;
            }

            ListNode temp = prev.next;
            prev.next = kth;
            prev = temp;
        }
    }
}