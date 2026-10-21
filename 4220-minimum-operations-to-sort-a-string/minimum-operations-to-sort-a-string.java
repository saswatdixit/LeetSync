class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        boolean sorted = true;
        char mn = cs[0], mx = cs[0];

        for (int i = 1; i < n; i++) {
            if (cs[i] < cs[i - 1]) sorted = false;
            mn = (char)Math.min(mn, cs[i]);
            mx = (char)Math.max(mx, cs[i]);
        }

        if (sorted) return 0;
        if (n == 2) return -1;

        if (cs[0] == mn || cs[n - 1] == mx) return 1;

        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == mn || cs[i] == mx) return 2;
        }

        return 3;
    }
}