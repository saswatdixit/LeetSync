class Solution(object):
    def minSumSquareDiff(self, nums1, nums2, k1, k2):
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        k = k1 + k2

        if sum(diff) <= k:
            return 0

        left = 0
        right = max(diff)

        while left < right:
            mid = (left + right) // 2
            needed = sum(max(0, d - mid) for d in diff)

            if needed <= k:
                right = mid
            else:
                left = mid + 1

        level = left
        remaining = k - sum(max(0, d - level) for d in diff)

        result = 0

        for d in diff:
            reduced = min(d, level)
            result += reduced * reduced

        for d in diff:
            if remaining == 0:
                break

            if d >= level and level > 0:
                result -= level * level - (level - 1) * (level - 1)
                remaining -= 1

        return result