class Solution(object):
    def findMissingElements(self, nums):
        result = []

        start = min(nums)
        end = max(nums)

        for i in range(start, end + 1):
            if i not in nums:
                result.append(i)

        return result