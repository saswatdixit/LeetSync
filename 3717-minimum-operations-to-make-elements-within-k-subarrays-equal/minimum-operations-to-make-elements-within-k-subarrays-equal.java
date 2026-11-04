import java.util.*;

class Fenwick {
    int n;
    int[] count;
    long[] sum;

    Fenwick(int n) {
        this.n = n;
        count = new int[n + 1];
        sum = new long[n + 1];
    }

    void add(int i, int c, long s) {
        i++;

        while (i <= n) {
            count[i] += c;
            sum[i] += s;
            i += i & -i;
        }
    }

    int queryCount(int i) {
        int ans = 0;

        while (i > 0) {
            ans += count[i];
            i -= i & -i;
        }

        return ans;
    }

    long querySum(int i) {
        long ans = 0;

        while (i > 0) {
            ans += sum[i];
            i -= i & -i;
        }

        return ans;
    }

    // Returns the 0-based index of the kth smallest element
    int kth(int k) {
        int idx = 0;

        int bit = Integer.highestOneBit(n);

        while (bit > 0) {
            int next = idx + bit;

            if (next <= n && count[next] < k) {
                idx = next;
                k -= count[next];
            }

            bit >>= 1;
        }

        return idx;
    }
}

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Coordinate compression
        int[] vals = Arrays.stream(nums)
                           .distinct()
                           .sorted()
                           .toArray();

        int size = vals.length;

        HashMap<Integer, Integer> pos = new HashMap<>();

        for (int i = 0; i < size; i++) {
            pos.put(vals[i], i);
        }

        Fenwick bit = new Fenwick(size);

        // Insert first window
        for (int i = 0; i < x; i++) {
            int idx = pos.get(nums[i]);
            bit.add(idx, 1, nums[i]);
        }

        long[] costs = new long[n - x + 1];

        // Calculate cost of every window
        for (int start = 0; start <= n - x; start++) {
            int medianIdx = bit.kth((x + 1) / 2);
            long median = vals[medianIdx];

            int leftCount = bit.queryCount(medianIdx + 1);
            long leftSum = bit.querySum(medianIdx + 1);

            long totalSum = bit.querySum(size);

            int rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            long cost =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            costs[start] = cost;

            // Slide the window
            if (start + x < n) {
                int removeIdx = pos.get(nums[start]);
                int addIdx = pos.get(nums[start + x]);

                bit.add(removeIdx, -1, -((long) nums[start]));
                bit.add(addIdx, 1, nums[start + x]);
            }
        }

        // Dynamic programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select a window of size x
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + costs[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}