class Solution(object):
    def combinationSum2(self, candidates, target):
        candidates.sort()
        result = []

        def backtrack(start, target, current):
            if target == 0:
                result.append(current[:])
                return

            if target < 0:
                return

            for i in range(start, len(candidates)):
                # Skip duplicate values at the same level
                if i > start and candidates[i] == candidates[i - 1]:
                    continue

                if candidates[i] > target:
                    break

                current.append(candidates[i])

                # i + 1 because each number can be used only once
                backtrack(i + 1, target - candidates[i], current)

                current.pop()

        backtrack(0, target, [])

        return result