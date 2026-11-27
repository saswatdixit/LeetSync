import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] bonus = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    bonus[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int d = bonus[i];

            int[] prev = dp.clone();
            int[] active = new int[budget + 1];
            Arrays.fill(active, -1);

            for (int b = price; b <= budget; b++) {
                int first = prev[b - price] + 1 + d;
                int more = active[b - price] + 1;

                active[b] = Math.max(first, more);
                dp[b] = Math.max(dp[b], active[b]);
            }
        }

        return dp[budget];
    }
}