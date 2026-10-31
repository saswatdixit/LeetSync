class Solution(object):
    def getPermutation(self, n, k):
        nums = [str(i) for i in range(1, n + 1)]

        fact = [1] * (n + 1)
        for i in range(1, n + 1):
            fact[i] = fact[i - 1] * i

        k -= 1
        ans = ""

        for i in range(n, 0, -1):
            index = k // fact[i - 1]
            ans += nums.pop(index)
            k %= fact[i - 1]

        return ans