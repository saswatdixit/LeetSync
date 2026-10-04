class Solution {
    public int divide(int dividend, int divisor) {
        int INT_MAX = Integer.MAX_VALUE;
        int INT_MIN = Integer.MIN_VALUE;

        // Special overflow case
        if (dividend == INT_MIN && divisor == -1) {
            return INT_MAX;
        }

        boolean negative = (dividend < 0) != (divisor < 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long quotient = 0;

        while (a >= b) {
            long value = b;
            long count = 1;

            while (a >= value + value) {
                value += value;
                count += count;
            }

            a -= value;
            quotient += count;
        }

        if (negative) {
            quotient = -quotient;
        }

        return (int) quotient;
    }
}