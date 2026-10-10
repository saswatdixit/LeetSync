class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long total = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }

        long k = (long) k1 + k2;

        if (total <= k) {
            return 0;
        }

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                needed += Math.max(0, d - mid);
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int level = left;
        long used = 0;
        long result = 0;

        for (int d : diff) {
            int reduced = Math.min(d, level);
            result += (long) reduced * reduced;
            used += Math.max(0, d - level);
        }

        long remaining = k - used;

        for (int d : diff) {
            if (remaining == 0) {
                break;
            }

            if (d >= level && level > 0) {
                result -= 2L * level - 1;
                remaining--;
            }
        }

        return result;
    }
}