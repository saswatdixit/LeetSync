class Solution(object):
    def smallestUniqueSubarray(self, nums):
        n = len(nums)

        MOD1 = 1000000007
        MOD2 = 1000000009
        BASE = 911382323

        pow1 = [1] * (n + 1)
        pow2 = [1] * (n + 1)
        pre1 = [0] * (n + 1)
        pre2 = [0] * (n + 1)

        for i in range(n):
            pow1[i + 1] = pow1[i] * BASE % MOD1
            pow2[i + 1] = pow2[i] * BASE % MOD2

            pre1[i + 1] = (pre1[i] * BASE + nums[i]) % MOD1
            pre2[i + 1] = (pre2[i] * BASE + nums[i]) % MOD2

        def has_unique(k):
            freq = {}

            for i in range(n - k + 1):
                h1 = (pre1[i + k] - pre1[i] * pow1[k]) % MOD1
                h2 = (pre2[i + k] - pre2[i] * pow2[k]) % MOD2

                key = (h1, h2)
                freq[key] = freq.get(key, 0) + 1

            return any(count == 1 for count in freq.values())

        left, right = 1, n

        while left < right:
            mid = (left + right) // 2

            if has_unique(mid):
                right = mid
            else:
                left = mid + 1

        return left