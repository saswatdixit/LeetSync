
class Solution(object):
    def maxAlternatingSum(self, nums):
        squares = sorted([x * x for x in nums])

        n = len(squares)
        half = n // 2

        positive = squares[half:]
        negative = squares[:half]

        return sum(positive) - sum(negative)
