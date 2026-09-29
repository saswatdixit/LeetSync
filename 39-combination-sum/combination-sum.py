class Solution(object):
    def combinationSum(self, candidates, target):
        result = []

        def backtrack(start, target, current):
            if target == 0:
                result.append(current[:])
                return

            if target < 0:
                return

            for i in range(start, len(candidates)):
                current.append(candidates[i])

                # i, not i + 1, because we can reuse the same number
                backtrack(i, target - candidates[i], current)

                current.pop()

        backtrack(0, target, [])
        return result