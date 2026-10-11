class Solution(object):
    def reverseKGroup(self, head, k):
        dummy = ListNode(0)
        dummy.next = head
        prev = dummy

        while True:
            kth = prev

            for i in range(k):
                kth = kth.next
                if kth is None:
                    return dummy.next

            next_group = kth.next

            # Reverse k nodes
            prev_node = next_group
            curr = prev.next

            while curr != next_group:
                temp = curr.next
                curr.next = prev_node
                prev_node = curr
                curr = temp

            temp = prev.next
            prev.next = kth
            prev = temp

        return dummy.next