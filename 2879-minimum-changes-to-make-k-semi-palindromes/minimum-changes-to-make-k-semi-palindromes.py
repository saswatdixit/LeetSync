class Solution(object):
    def minimumChanges(self, s, k):
        n = len(s)

        # Precompute minimum changes for every substring
        cost = [[0] * n for _ in range(n)]

        for length in range(2, n + 1):
            for i in range(n - length + 1):
                best = float('inf')

                # Find all proper divisors of length
                for d in range(1, length):
                    if length % d != 0:
                        continue

                    changes = 0
                    groups = d
                    group_len = length // d

                    # Check palindrome pairs in each group
                    for r in range(groups):
                        left = 0
                        right = group_len - 1

                        while left < right:
                            a = s[i + r + left * d]
                            b = s[i + r + right * d]

                            if a != b:
                                changes += 1

                            left += 1
                            right -= 1

                    best = min(best, changes)

                cost[i][i + length - 1] = best

        # DP: dp[p][i] = minimum changes to divide
        # first i characters into p semi-palindromes
        INF = float('inf')
        dp = [[INF] * (n + 1) for _ in range(k + 1)]

        dp[0][0] = 0

        for p in range(1, k + 1):
            for i in range(2 * p, n + 1):
                for j in range(2 * (p - 1), i - 1):
                    dp[p][i] = min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    )

        return dp[k][n]