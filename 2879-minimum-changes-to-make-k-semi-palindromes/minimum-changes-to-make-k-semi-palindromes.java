import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Precompute minimum changes for every substring
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int best = Integer.MAX_VALUE;

                // Check all proper divisors
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;
                    int groupLen = len / d;

                    // Check palindrome pairs in each group
                    for (int r = 0; r < d; r++) {
                        int left = 0;
                        int right = groupLen - 1;

                        while (left < right) {
                            char a = s.charAt(i + r + left * d);
                            char b = s.charAt(i + r + right * d);

                            if (a != b) {
                                changes++;
                            }

                            left++;
                            right--;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][i + len - 1] = best;
            }
        }

        // DP
        int INF = 1000000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}