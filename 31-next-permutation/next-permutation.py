class Solution(object):
    def nextPermutation(self, nums):
        n = len(nums)
        i = n - 2

        # Find the first decreasing position from the right
        while i >= 0 and nums[i] >= nums[i + 1]:
            i -= 1

        if i >= 0:
            j = n - 1

            # Find the next greater element
            while nums[j] <= nums[i]:
                j -= 1

            nums[i], nums[j] = nums[j], nums[i]

        # Reverse the remaining part
        left = i + 1
        right = n - 1

        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1