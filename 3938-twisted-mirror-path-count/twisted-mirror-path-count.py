class Solution(object):
    def uniquePaths(self, grid):
        MOD = 10**9 + 7
        m, n = len(grid), len(grid[0])

        dp = [[[0, 0] for _ in range(n)] for _ in range(m)]

        # Start at (0, 0)
        dp[0][0][0] = 1

        for i in range(m):
            for j in range(n):
                for d in range(2):
                    ways = dp[i][j][d]

                    if ways == 0:
                        continue

                    if grid[i][j] == 0:
                        # Empty cell: can move right or down
                        if j + 1 < n:
                            dp[i][j + 1][0] = (
                                dp[i][j + 1][0] + ways
                            ) % MOD

                        if i + 1 < m:
                            dp[i + 1][j][1] = (
                                dp[i + 1][j][1] + ways
                            ) % MOD

                    else:
                        # Mirror: reflect based on entry direction
                        if d == 0 and i + 1 < m:
                            dp[i + 1][j][1] = (
                                dp[i + 1][j][1] + ways
                            ) % MOD

                        elif d == 1 and j + 1 < n:
                            dp[i][j + 1][0] = (
                                dp[i][j + 1][0] + ways
                            ) % MOD

        return sum(dp[m - 1][n - 1]) % MOD