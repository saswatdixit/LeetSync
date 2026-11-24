import java.math.BigInteger;

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            BigInteger product = BigInteger.ONE;
            long gcdVal = 0;
            long lcmVal = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product = product.multiply(BigInteger.valueOf(x));

                gcdVal = gcd(gcdVal, x);
                lcmVal = (lcmVal / gcd(lcmVal, x)) * x;

                BigInteger expected = BigInteger.valueOf(gcdVal)
                        .multiply(BigInteger.valueOf(lcmVal));

                if (product.equals(expected)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}