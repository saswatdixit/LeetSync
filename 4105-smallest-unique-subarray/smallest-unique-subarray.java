import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        Integer[] sa = new Integer[n];
        int[] rank = nums.clone();

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        // Build suffix array
        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] r = rank;

            Arrays.sort(sa, (a, b) -> {
                if (r[a] != r[b]) {
                    return Integer.compare(r[a], r[b]);
                }

                int x = a + len < n ? r[a + len] : -1;
                int y = b + len < n ? r[b + len] : -1;

                return Integer.compare(x, y);
            });

            int[] newRank = new int[n];

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a1 = rank[a];
                int b1 = rank[b];

                int a2 = a + k < n ? rank[a + k] : -1;
                int b2 = b + k < n ? rank[b + k] : -1;

                newRank[b] = newRank[a];

                if (a1 != b1 || a2 != b2) {
                    newRank[b]++;
                }
            }

            rank = newRank;

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }

            if (k > n / 2) {
                break;
            }
        }

        // Find suffix positions
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        // Build LCP array using Kasai's algorithm
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;
            h = Math.max(0, h - 1);
        }

        // Find shortest unique subarray
        int ans = n;

        for (int r = 0; r < n; r++) {
            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int length = maxLcp + 1;

            if (length <= n - sa[r]) {
                ans = Math.min(ans, length);
            }
        }

        return ans;
    }
}