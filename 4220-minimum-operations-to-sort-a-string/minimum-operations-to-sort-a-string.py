class Solution(object):
    def minOperations(self, s):
        n = len(s)

        # Already sorted
        sorted_flag = True
        for i in range(1, n):
            if s[i] < s[i - 1]:
                sorted_flag = False
                break
        if sorted_flag:
            return 0

        # Impossible for unsorted length 2
        if n == 2:
            return -1

        mn = min(s)
        mx = max(s)

        # One operation
        if s[0] == mn or s[-1] == mx:
            return 1

        # Two operations
        for i in range(1, n - 1):
            if s[i] == mn or s[i] == mx:
                return 2

        # Otherwise
        return 3