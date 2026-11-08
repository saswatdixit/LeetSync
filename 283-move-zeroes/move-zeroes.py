class Solution(object):
    def moveZeroes(self, nums):
        index = 0

        # Move non-zero elements to the front
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[index] = nums[i]
                index += 1

        # Fill remaining positions with zeros
        while index < len(nums):
            nums[index] = 0
            index += 1