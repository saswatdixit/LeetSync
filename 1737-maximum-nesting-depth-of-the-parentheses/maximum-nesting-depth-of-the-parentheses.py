class Solution(object):
    def maxDepth(self, s):
        depth = 0
        maxDepth = 0

        for ch in s:
            if ch == '(':
                depth += 1
                maxDepth = max(maxDepth, depth)
            elif ch == ')':
                depth -= 1

        return maxDepth