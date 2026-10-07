class Solution(object):
    def myPow(self, x, n):
        if n == 0:
            return 1.0

        negative = n < 0

        # Use a positive Python integer, which handles -2^31 safely
        n = abs(n)

        result = 1.0

        while n > 0:
            if n & 1:
                result *= x

            x *= x
            n >>= 1

        if negative:
            return 1.0 / result

        return result