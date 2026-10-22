class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        // Case 1: Only rotations
        int k = pos;
        boolean ok = true;

        for (int i = 0; i < n; i++) {
            if (nums[(i + k) % n] != i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans = Math.min(k, n - k + 2);
        }

        // Case 2: Rotate, then reverse
        k = (pos + 1) % n;
        ok = true;

        for (int i = 0; i < n; i++) {
            int idx = (k - 1 - i + n) % n;
            if (nums[idx] != i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}