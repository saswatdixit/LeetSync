class Solution(object):
    def maxSelectedElements(self, nums):
        nums.sort()

        dp = {}
        ans = 0

        for x in nums:
            # Increase x by 1
            dp[x + 1] = max(dp.get(x + 1, 0), dp.get(x, 0) + 1)

            # Keep x unchanged
            dp[x] = max(dp.get(x, 0), dp.get(x - 1, 0) + 1)

            ans = max(ans, dp[x], dp[x + 1])

        return ans