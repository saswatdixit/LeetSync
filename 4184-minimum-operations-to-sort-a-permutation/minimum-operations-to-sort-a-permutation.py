class Solution(object):
    def minOperations(self, nums):
        n = len(nums)
        pos = nums.index(0)

        # Case 1: Only rotations
        k = pos
        if all(nums[(i + k) % n] == i for i in range(n)):
            ans = min(k, n - k + 2)
        else:
            ans = float('inf')

        # Case 2: Rotate, then reverse
        k = (pos + 1) % n
        if all(nums[(k - 1 - i) % n] == i for i in range(n)):
            ans = min(ans, k + 1, n - k + 1)

        return ans if ans != float('inf') else -1