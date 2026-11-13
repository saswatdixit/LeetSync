import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> dq = new ArrayDeque<>();

        int right = n - 1;
        long sumNums = 0;
        long sumMax = 0;
        long ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            int count = 1;

            // Merge groups whose maximum is <= x
            while (!dq.isEmpty() && dq.peekFirst()[0] <= x) {
                int[] group = dq.removeFirst();

                sumMax -= (long) group[0] * group[1];
                count += group[1];
            }

            dq.addFirst(new int[]{x, count});

            sumMax += (long) x * count;
            sumNums += x;

            // Remove elements from the right until cost <= k
            while (sumMax - sumNums > k) {
                int[] group = dq.peekLast();

                sumMax -= group[0];
                sumNums -= nums[right];

                group[1]--;

                if (group[1] == 0) {
                    dq.removeLast();
                }

                right--;
            }

            // All subarrays [i...j], where i <= j <= right, are valid
            ans += right - i + 1;
        }

        return ans;
    }
}